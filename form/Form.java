package form;

import drawing.Painter;
import drawing.shapes.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import plugins.PluginManager;
import plugins.tunable.Tunable;
import serialization.Serializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny Shilov on 25.04.2016.
 */
public class Form extends HBox {
    private final int SHAPE_BUTTON_WIDTH = 200;
    private final int SHAPE_BUTTON_HEIGHT = 30;
    private int rowIndex;
    private Painter painter;
    private Serializer serializer;
    private PluginManager pluginManager;
    private ArrayList<Tunable> tunablePlugins;

    private Canvas canvas;
    private GridPane sidePanel;

    public Form() {
        canvas = new Canvas();
        painter = new Painter(canvas);
        serializer = new Serializer();
        pluginManager = new PluginManager();
        tunablePlugins = new ArrayList<>();
        sidePanel = new GridPane();
        initSidePanel();
        this.getChildren().addAll(canvas, sidePanel);
    }

    public void loadPlugins(String path) throws NotDirectoryException {
        pluginManager.loadPlugins(path);
        pluginManager.setupAll(this);
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
                try {
                    List shapes = serializer.deserializeList(Shape.class, selectedFile);
                    painter.getShapeStack().clear();
                    painter.getShapeStack().addAll(shapes);
                    painter.repaint();
                } catch (Exception e) {
                    System.out.println(e);
                    showAlert(Alert.AlertType.ERROR, "Cannot open file");
                }
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
        moveSidePanelRowIndex(1);
        addButton("Plugin Settings", event -> {
            Stage stage = new Stage();
            stage.setWidth(600);
            stage.setHeight(300);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Plugin settings");
            stage.setScene(new Scene(new PluginSettingsForm(tunablePlugins)));
            stage.show();
        });
        moveSidePanelRowIndex(1);
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

    public void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).showAndWait()
                .filter(response -> response == ButtonType.OK);
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public ArrayList<Tunable> getTunablePlugins() {
        return tunablePlugins;
    }
}
