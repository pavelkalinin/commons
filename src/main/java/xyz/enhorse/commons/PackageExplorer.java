package xyz.enhorse.commons;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
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
 * The class to perform the scanning of the context class loader for the classes that have the certain superclass {@link
 * T}.
 * <p>
 * Note the fact that the class performs classpath searching only from the package of the type {@link T} that is
 * belonged to and deeper. That gives a capability to narrow a scope for searching and makes the process be more
 * optimized.
 * @param <T> the type to search
 * @author Pavel Kalinin on 06.04.2018
 */
public class PackageExplorer<T> {

    /**
     * The package to explore
     */
    private final String basePackage;
    /**
     * The type to scan for
     */
    private final Class<T> type;


    /**
     * Create an instance
     * @param type the type to scan for
     */
    public PackageExplorer(final Class<T> type) {
        this.type = Validate.notNull("type to explore", type);
        basePackage = type.getPackage().getName();
    }


    /**
     * Scans all instantiable classes accessible from the context class loader which have the superclass {@link T}.
     * @return The found classes
     */
    public Set<Class<T>> findAllInstantiableClasses() {

        return findAllAssignableClasses().stream()
                .filter(clazz -> !clazz.isInterface())
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
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
                        ? findAllInJar(root.getFile())
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
        try {
            final ClassLoader context = Thread.currentThread().getContextClassLoader();
            return Collections.list(context.getResources(basePackage.replace('.', '/')));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


    /**
     * Method used to find all classes in a given jar.
     * @param root a base path
     * @return the found classes
     */
    private Set<FileEntity> findAllInJar(final String root) {
        final String filename = root.substring("jar:".length() + 1, root.indexOf("!"));

        return getJarContent(filename).stream()
                .map(ZipEntry::getName)
                .map(File::new)
                .map(FileEntity::new)
                .filter(FileEntity::isClass)
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
                                    result.add(fileEntity);
                                }
                            }
                        }));
        return result;
    }


    /**
     * Returns contents of the given jar.
     * @param filename name of jar file
     * @return jar contents
     */
    private List<JarEntry> getJarContent(final String filename) {
        try {
            return Collections.list(new JarFile(filename).entries());
        } catch (IOException ex) {
            return Collections.emptyList();
        }
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
            return Optional.empty();
        }
    }


    /**
     * The class to instrument working with the {@link File} that represents ".class/java class"-file
     */
    private class FileEntity {

        private static final char DELIMITER = '.';
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
                    .map(index -> file.substring(0, index)).orElse(file);
        }


        /** Returns the file extension if it exists or an empty string otherwise */
        String extension() {
            return getLastDotPosition()
                    .map(index -> file.substring(index, file.length())).orElse("");
        }


        /** Adds the package part to the file name */
        String pack() {
            return basePackage + DELIMITER + name();
        }


        /**
         * Returns {@code true} if the file has a proper extension for ".class/java class"-file or {@code false}
         * otherwise
         */
        boolean isClass() {
            return CLASS_EXTENSION.equals(extension());
        }


        private Optional<Integer> getLastDotPosition() {
            return Optional.of(file.lastIndexOf(DELIMITER)).filter(r -> r >= 0);
        }


        private String evaluatePackageName(final String file) {
            final int lastSlash = file.lastIndexOf(File.separator);
            return file.substring(0, lastSlash > 0 ? lastSlash : 0).replace(File.separator, ".");
        }
    }
}
