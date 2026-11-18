package io.github.gleidsonmt.glad;

import io.github.gleidsonmt.glad.tail.ClassWatcher;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 08/10/2026
 */
public class HotTools {

    private static ClassWatcher watcher;

    public static ClassWatcher getWatcher() {
        return watcher;
    }

    public static void init(Scene scene, Launch.MODE mode) {
        switch (mode) {
            case DEV:
                watcher = installWatcher(scene, true);
                analyzeNodes(scene);
                listenCss(scene);
                break;
            case RUN:
                watcher = installWatcher(scene, false);
                break;
            case DEBUG:
                break;
        }
    }

    /**
     * Install a watcher to analyze nodes in the given scene.
     * This watcher writes a resource file with styleClasses.
     * If you add a class to a node, the watcher will write the class name to the resource file.
     * The file will be called tailwind.css.
     * You can add this file to the scene to apply the styles.
     *
     * @param scene The scene to analyze.
     */
    public static ClassWatcher installWatcher(Scene scene, boolean write) {
        return new ClassWatcher(scene,write);
    }

    public static void installWatcher(Parent scene, boolean write) {
        new ClassWatcher(scene,write);
    }

    private static Class<?> loadClassJar(String jarName, String fullClass) {
        Class<?> cls;
        try {
            File jarFile = new File(jarName);
            if (!jarFile.exists()) return null;
            URL[] urls = {jarFile.toURI().toURL()};

            URLClassLoader classLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            Thread.currentThread().setContextClassLoader(classLoader);
            cls = Class.forName(fullClass, true, classLoader);
        } catch (MalformedURLException | ClassNotFoundException _) {
            throw new RuntimeException("Error loading class from jar: " + jarName);
        }
        return cls;
    }

    /**
     * Invoke a scenic view to analyze nodes.
     * ScenicView is a amazing tool to see properties and nodes together in real time.
     *
     * @param scene The scene to analyze.
     */
    public static void analyzeNodes(Scene scene) {
        if (scene == null) return;
        ClassLoader originalCtx = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loadClassJar("./vendor/scenicview.jar", "org.scenicview.ScenicView");
        if (clazz == null) return;

        try {
            clazz.getMethod("show", Scene.class).invoke(null, scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("ScenicView not found." + e);
        } finally {
            Thread.currentThread().setContextClassLoader(originalCtx);
        }
    }

    /**
     * The CCSFX is a CSS engine that allows you to change CSS in JavaFX when is running and see imeditiatly.
     *
     * @param scene The scene to listen.
     */
    public static void listenCss(Scene scene) {
        if (scene == null) return;
        ClassLoader originalCtx = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loadClassJar("./vendor/cssfx-11.5.1.jar", "fr.brouillard.oss.cssfx.CSSFX");
        if (clazz == null) return;
        try {
            clazz.getMethod("start", Scene.class).invoke(null, scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException _) {
            throw new RuntimeException("CSSFX not found.");
        } finally {
            Thread.currentThread().setContextClassLoader(originalCtx);
        }
    }
}
