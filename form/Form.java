package form;

import drawing.Painter;
import drawing.shapes.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import serialization.Serializer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny Shilov on 25.04.2016.
 */
public class Form extends HBox {
    private final int SHAPE_BUTTON_WIDTH = 200;
    private final int SHAPE_BUTTON_HEIGHT = 30;
    private int rowIndex;
    private Painter painter;
    private Serializer serializer;

    private Canvas canvas;
    private GridPane sidePanel;

    public Form() {
        canvas = new Canvas();
        painter = new Painter(canvas);
        serializer = new Serializer();
        sidePanel = new GridPane();
        initSidePanel();
        this.getChildren().addAll(canvas, sidePanel);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GridPane getSidePanel() {
        return sidePanel;
    }

    private void initSidePanel() {
        addButton("Open", event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Файлы рисунка", "*.draw"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                painter.getShapeStack().clear();
                try {
                    painter.getShapeStack().addAll(serializer.deserializeList(Shape.class, selectedFile));
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Cannot open file");
                }
                painter.repaint();
            }
        });
        addButton("Save", event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose save file");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Файлы рисунка", "*.draw"));
            File selectedFile = fileChooser.showSaveDialog(null);
            if (selectedFile != null) {
                try {
                    serializer.serializeList(
                            painter.getShapeStack().subList(0, painter.getShapeStack().size()),
                            selectedFile
                    );
                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, "Cannot save to file");
                }
            }
        });
        moveSidePanelRowIndex(2);
        addButton("RECTANGLE", event -> painter.setShapeFactory(new Rectangle.Factory()));
        addButton("CIRCLE", event -> painter.setShapeFactory(new Circle.Factory()));
        addButton("SQUARE", event -> painter.setShapeFactory(new Square.Factory()));
        addButton("TRIANGLE", event -> painter.setShapeFactory(new Triangle.Factory()));
        addButton("RHOMBUS", event -> painter.setShapeFactory(new Rhombus.Factory()));
        addButton("LINE", event -> painter.setShapeFactory(new Line.Factory()));
    }

    public void addButton(String title, EventHandler<MouseEvent> handler) {
        Button button = new Button(title);
        button.setPrefSize(SHAPE_BUTTON_WIDTH, SHAPE_BUTTON_HEIGHT);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

        sidePanel.addRow(rowIndex++, button);
    }

    public void moveSidePanelRowIndex(int count) {
        for (int i = 0; i <= count; i++) {
            sidePanel.addRow(rowIndex++, new Label(""));
        }
    }

    public Painter getPainter() {
        return painter;
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).showAndWait()
                .filter(response -> response == ButtonType.OK);
    }
}
