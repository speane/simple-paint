package plugins;

import form.Form;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.NotDirectoryException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Evgeny Shilov on 10.05.2016.
 */
public class PluginManager {
    private PluginVerifier verifier;
    private ArrayList<Plugin> plugins;

    public PluginManager() {
        verifier = new PluginVerifier();
        plugins = new ArrayList<>();
    }

    public void setupAll(Form form) {
        for (Plugin plugin : plugins) {
            plugin.setup(form);
        }
    }

    public void loadPlugins(String path) throws NotDirectoryException {
        File pluginsDir = new File(path);

        if (!pluginsDir.exists() || !pluginsDir.isDirectory()) {
            throw new NotDirectoryException(path);
        }

        for (File jar : pluginsDir.listFiles()) {
            try {
                if (!verifier.verify(jar)) {
                    continue;
                }
                JarFile jarFile = new JarFile(jar);
                Enumeration<JarEntry> jarEntries = jarFile.entries();

                URL[] urls = { new URL("jar:file:" + jar+"!/") };
                URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls, Thread.currentThread().getContextClassLoader());
                Thread.currentThread().setContextClassLoader(urlClassLoader);

                while (jarEntries.hasMoreElements()) {
                    JarEntry entry = jarEntries.nextElement();
                    if(entry.isDirectory() || !entry.getName().endsWith(".class")){
                        continue;
                    }

                    String className = entry.getName().substring(0,entry.getName().length() - 6);
                    className = className.replace('/', '.');
                    Class<?> clazz = urlClassLoader.loadClass(className);
                    Thread.currentThread().setContextClassLoader(urlClassLoader);
                    Class[] interfaces = clazz.getInterfaces();
                    for (Class tempInterface : interfaces) {
                        if (tempInterface.getName().equals("plugins.Plugin")) {
                            Class<? extends Plugin> plugin = clazz.asSubclass(Plugin.class);
                            Constructor<? extends Plugin> constructor = plugin.getConstructor();
                            plugins.add(constructor.newInstance());
                        }
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException | IOException | ParseException ex) {
                System.out.println(ex);
            }
        }
    }
}
