package xyz.enhorse.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 * The class to perform the scanning of the context class loader for the classes
 * that have the certain superclass {@link T}.
 * <p>
 * Note the fact that the class performs classpath searching only from the package of the type {@link T} that is
 * belonged to and deeper. That gives a capability to narrow a scope for searching and makes the process be more
 * optimized.
 * @param <T> the type to search
 * @author Pavel Kalinin on 06.04.2018
 */
public class PackageExplorer<T> {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageExplorer.class);
    /** The package to explore */
    private final String basePackage;
    /** The type to scan for */
    private final Class<T> type;


    /**
     * Create an instance of the class
     * @param type the type to scan for
     */
    public PackageExplorer(final Class<T> type) {
        this.type = Validate.notNull("type to explore", type);
        basePackage = type.getPackage().getName();
        LOGGER.debug("Base package was defined to \'{}\'", basePackage);
    }


    /**
     * Scans all instantiable classes accessible from the context class loader which have the superclass {@link T}.
     * @return The found classes
     */
    public Set<Class<T>> findAllInstantiableClasses() {
        return findAllAssignableClasses().stream()
                .filter(clazz -> !clazz.isInterface())
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .peek(clazz -> LOGGER.debug("Found the applicable class:\'{}\'", clazz.getCanonicalName()))
                .collect(Collectors.toSet());
    }


    /**
     * Scans all classes and interfaces accessible from the context class loader which have the superclass {@link T}.
     * @return The found classes
     */
    private Set<Class<T>> findAllAssignableClasses() {
        return getAvailableClasses().stream()
                .filter(type::isAssignableFrom)
                .collect(Collectors.toSet());
    }


    /**
     * Scans all classes accessible from the context class loader which belong to the base package and subpackages.
     * @return the found classes
     */
    private Set<Class<T>> getAvailableClasses() {
        return getResources().stream()
                .map(root -> "jar".equals(root.getProtocol())
                        ? findAllInJar(root)
                        : findAllInDirectory(new File(root.getFile()), basePackage))
                .flatMap(Collection::stream)
                .map(this::tryToGetClassFor)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }


    /**
     * Returns all resources of the base package that is accessible from the context class loader.
     * @return enumeration of all resources
     */
    private List<URL> getResources() {
        final String packageAsPath = basePackage.replace('.', '/');
        try {
            final ClassLoader context = Thread.currentThread().getContextClassLoader();
            return Collections.list(context.getResources(packageAsPath));
        } catch (IOException ex) {
            LOGGER.warn("Cannot get resources by the path:\'{}\'", packageAsPath, ex);
            return Collections.emptyList();
        }
    }


    /**
     * Method used to find all classes in a given jar.
     * @param root a base path
     * @return the found classes
     */
    private Set<FileEntity> findAllInJar(final URL root) {
        LOGGER.debug("Start to explore the .jar file over \'{}\'", root);

        return getJarContent(root)
                .stream()
                .map(ZipEntry::getName)
                .map(File::new)
                .map(FileEntity::new)
                .filter(FileEntity::isClass)
                .peek(fileEntity -> LOGGER.debug("Added the candidate:\'{}\'", fileEntity.pack()))
                .collect(Collectors.toSet());
    }


    /**
     * Recursive method used to find all classes in a given directory and its subdirectories.
     * @param directory   a base directory
     * @param basePackage a base package
     * @return the found classes
     */
    private Set<FileEntity> findAllInDirectory(final File directory, final String basePackage) {
        final Set<FileEntity> result = new HashSet<>();
        LOGGER.debug("Start to explore the directory \'{}\' as the package \'{}\'", directory, basePackage);

        Optional.ofNullable(directory)
                .filter(dir -> dir.exists() && dir.canRead() && dir.isDirectory())
                .map(File::listFiles)
                .ifPresent(files -> Stream.of(files)
                        .filter(File::exists)
                        .forEach(file -> {
                            final FileEntity fileEntity = new FileEntity(file, basePackage);
                            if (file.isDirectory()) {
                                result.addAll(findAllInDirectory(file, fileEntity.pack()));
                            } else {
                                if (fileEntity.isClass()) {
                                    LOGGER.debug("Added the candidate:\'{}\'", fileEntity.pack());
                                    result.add(fileEntity);
                                }
                            }
                        }));
        return result;
    }


    /**
     * Returns contents of a .jar file over its given URL.
     * @param url url of a .jar file
     * @return .jar file contents
     */
    private List<JarEntry> getJarContent(final URL url) {
        return Optional.ofNullable(url)
                .map(this::from)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(JarFile::entries)
                .map(Collections::list)
                .orElse(new ArrayList<>());
    }


    private Optional<JarFile> from(final URL jarUrl) {
        return Optional.ofNullable(jarUrl)
                .map(url -> {
                    try {
                        return url.openConnection();
                    } catch (IOException ex) {
                        LOGGER.warn("Opening connection to {} failed.", url, ex);
                        return null;
                    }
                })
                .filter(connection -> connection instanceof JarURLConnection)
                .map(connection -> {
                    try {
                        connection.setUseCaches(false);
                        return ((JarURLConnection) connection).getJarFile();
                    } catch (IOException ex) {
                        LOGGER.warn("Getting .jar file over a connection {} failed.", connection.getURL(), ex);
                        return null;
                    }
                });
    }


    /**
     * Returns {@link Class} object for its given {@link FileEntity} object
     * @param fileEntity the file
     * @return the {@link Class} object
     */
    @SuppressWarnings("unchecked")
    private Optional<Class<T>> tryToGetClassFor(final FileEntity fileEntity) {
        try {
            return Optional.of((Class<T>) Class.forName(fileEntity.pack()));
        } catch (ClassNotFoundException ex) {
            LOGGER.warn("Cannot get Class object associated with the name \'{}\'", fileEntity.pack(), ex);
            return Optional.empty();
        }
    }


    /** The class to instrument working with the {@link File} */
    private class FileEntity {

        /** The filename parts delimiter */
        private static final char DELIMITER = '.';
        /** The compiled java class file extension */
        private static final String CLASS_EXTENSION = ".class";

        /** The file */
        private final String file;
        /** The package of the file */
        private final String basePackage;


        FileEntity(final File file, final String basePackage) {
            this.file = file.getName();
            this.basePackage = basePackage;
        }


        FileEntity(final File file) {
            this.file = file.getName();
            this.basePackage = evaluatePackageName(file.getPath());
        }


        /** Returns the file name without extension */
        String name() {
            return getLastDotPosition()
                    .map(index -> file.substring(0, index))
                    .orElse(file);
        }


        /** Returns the file extension if it exists or an empty string otherwise */
        String extension() {
            return getLastDotPosition()
                    .map(index -> file.substring(index, file.length()))
                    .orElse("");
        }


        /** Adds the package part to the filename */
        String pack() {
            return basePackage + DELIMITER + name();
        }


        /**
         * Returns {@code true} if the file has a proper extension for ".class/java class"-file
         * or {@code false} otherwise
         */
        boolean isClass() {
            return CLASS_EXTENSION.equals(extension());
        }


        private Optional<Integer> getLastDotPosition() {
            return Optional.of(file.lastIndexOf(DELIMITER))
                    .filter(r -> r >= 0);
        }


        private String evaluatePackageName(final String file) {
            final int lastSlash = file.lastIndexOf(File.separator);

            return file.substring(0, lastSlash > 0 ? lastSlash : 0)
                    .replace(File.separator, ".");
        }
    }
}
