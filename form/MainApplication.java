package form;

import drawing.Painter;
import drawing.shapes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Evgeny Shilov on 24.04.2016.
 */
public class MainApplication extends Application {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final String WINDOW_TITLE = "SimplePaint";

    private final int SHAPE_BUTTON_WIDTH = 200;
    private final int SHAPE_BUTTON_HEIGHT = 30;

    private Painter painter;


    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();
        initUI(root);
        initPrimaryStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.setWidth(WINDOW_WIDTH);
    }

    private void initUI(HBox pane) {
        Canvas canvas = new Canvas();
        painter = new Painter(canvas);
        GridPane sidePanel = new GridPane();
        initSidePanel(sidePanel);
        pane.getChildren().addAll(canvas, sidePanel);
    }

    private void initSidePanel(GridPane pane) {
        int i = 0;
        pane.addRow(i++, createShapeButton("CIRCLE", new Circle.Factory()));
        pane.addRow(i++, createShapeButton("SQUARE", new Square.Factory()));
        pane.addRow(i++, createShapeButton("RECTANGLE", new Rectangle.Factory()));
        pane.addRow(i++, createShapeButton("TRIANGLE", new Triangle.Factory()));
        pane.addRow(i++, createShapeButton("RHOMBUS", new Rhombus.Factory()));
        pane.addRow(i++, createShapeButton("ELLIPSE", new Ellipse.Factory()));
        pane.addRow(i, createShapeButton("LINE", new Line.Factory()));
    }

    private Button createShapeButton(String title, Factory shapeFactory) {
        Button button = new Button(title);
        button.setPrefSize(SHAPE_BUTTON_WIDTH, SHAPE_BUTTON_HEIGHT);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> painter.setShapeFactory(shapeFactory));
        return button;
    }
}
