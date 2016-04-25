package form;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import plugins.Plugin;
import plugins.PluginLoader;

import java.nio.file.NotDirectoryException;

/**
 * Created by Evgeny Shilov on 24.04.2016.
 */
public class MainApplication extends Application {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final String WINDOW_TITLE = "SimplePaint";

    private Form form;

    @Override
    public void start(Stage primaryStage) throws Exception {
        form = new Form();
        initPrimaryStage(primaryStage);
        primaryStage.setScene(new Scene(form));
        primaryStage.show();
        loadPlugins();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadPlugins() throws NotDirectoryException {
        for (Plugin plugin : new PluginLoader().loadPlugins("plugins/")) {
            plugin.setup(form);
        }
    }

    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.setWidth(WINDOW_WIDTH);
    }
}
