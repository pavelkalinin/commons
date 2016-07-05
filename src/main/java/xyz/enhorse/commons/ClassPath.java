package xyz.enhorse.commons;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ClassPath {

    private ClassPath() {
    }


    /**
     * Scans all instantiable classes accessible from the context class loader which have the certain superclass.
     *
     * @param toFind The superclass of
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<Class<? extends T>> findAllInstantiableClasses(Class<T> toFind)
            throws ClassNotFoundException, IOException {

        if (toFind == null) {
            return Collections.emptyList();
        }

        return findAllAssignable(toFind).stream()
                .filter(clazz -> (!clazz.isInterface()) && (!Modifier.isAbstract(clazz.getModifiers())))
                .collect(Collectors.toList());
    }


    /**
     * Scans all classes and interfaces accessible from the context class loader which have the certain superclass.
     *
     * @param toFind The superclass of
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<Class<? extends T>> findAllAssignable(Class<T> toFind)
            throws ClassNotFoundException, IOException {

        if (toFind == null) {
            return Collections.emptyList();
        }

        return getAllAvailableClasses().stream().filter(toFind::isAssignableFrom).map(clazz ->
                (Class<? extends T>) clazz).collect(Collectors.toList());
    }


    /**
     * Scans all classes accessible from the context class loader.
     *
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Set<Class<?>> getAllAvailableClasses()
            throws ClassNotFoundException, IOException {

        Package[] ps = Package.getPackages();
        Set<Class<?>> classes = new HashSet<>();
        for (Package p : ps) {
            classes.addAll(getAllFromPackage(p.getName()).stream().collect(Collectors.toList()));
        }

        return classes;
    }


    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static List<Class<?>> getAllFromPackage(String packageName)
            throws ClassNotFoundException, IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findAllInDirectory(directory, packageName));
        }

        return classes;
    }


    /**
     * Recursive method used to find all classes in a given directory and subdirectories.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findAllInDirectory(File directory, String packageName)
            throws ClassNotFoundException {

        List<Class<?>> classes = new ArrayList<>();
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        assert !file.getName().contains(".");
                        classes.addAll(findAllInDirectory(file, packageName + "." + file.getName()));
                    } else if (file.getName().endsWith(".class")) {
                        classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                    }
                }
            }
        }

        return classes;
    }
}
