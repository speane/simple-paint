package form;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        form.moveSidePanelRowIndex(2);
        form.loadPlugins("plugins/");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.setWidth(WINDOW_WIDTH);
    }
}
