package plugins;

import signer.JarSigner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.NotDirectoryException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Evgeny Shilov on 25.04.2016.
 */
public class PluginLoader {
    public ArrayList<Plugin> loadPlugins(String path) throws NotDirectoryException {
        ArrayList<Plugin> plugins = new ArrayList<>();

        File pluginsDir = new File(path);

        if (!pluginsDir.exists() || !pluginsDir.isDirectory()) {
            throw new NotDirectoryException(path);
        }

        for (File jar : pluginsDir.listFiles()) {
            try {
                if (!checkPlugin(jar)) {
                    continue;
                }
                JarFile jarFile = new JarFile(jar);
                Enumeration<JarEntry> jarEntries = jarFile.entries();

                URL[] urls = { new URL("jar:file:" + jar+"!/") };
                URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);

                while (jarEntries.hasMoreElements()) {
                    JarEntry entry = jarEntries.nextElement();
                    if(entry.isDirectory() || !entry.getName().endsWith(".class")){
                        continue;
                    }

                    String className = entry.getName().substring(0,entry.getName().length() - 6);
                    className = className.replace('/', '.');
                    Class<?> clazz = urlClassLoader.loadClass(className);
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

        return plugins;
    }
    private boolean checkPlugin(File file) throws ParseException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        JarFile jarFile = new JarFile(file);
        Object value = jarFile.getManifest().getMainAttributes().get(new Attributes.Name("Sign"));
        if (value == null) {
            return false;
        }
        String signing = (String) value;
        System.out.println(signing);
        value = jarFile.getManifest().getMainAttributes().get(new Attributes.Name("Date"));
        if (value == null) {
            return false;
        }

        String date = (String) value;
        String newSigning = new JarSigner().getSigning(file, dateFormat.parse(date));
        System.out.println(newSigning);
        if (newSigning.equals(signing)) {
            if (dateFormat.parse("2016/04/23 00:00:00").before(dateFormat.parse(date))) {
                return true;
            }
        }
        return false;
    }
}
