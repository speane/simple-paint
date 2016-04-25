package form;

import drawing.Painter;
import drawing.shapes.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by Evgeny Shilov on 25.04.2016.
 */
public class Form extends HBox {
    private final int SHAPE_BUTTON_WIDTH = 200;
    private final int SHAPE_BUTTON_HEIGHT = 30;
    private int rowIndex;
    private Painter painter;

    private Canvas canvas;
    private GridPane sidePanel;

    public Form() {
        canvas = new Canvas();
        painter = new Painter(canvas);
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
        addButton("RECTANGLE", event -> painter.setShapeFactory(new Rectangle.Factory()));
        addButton("CIRCLE", event -> painter.setShapeFactory(new Circle.Factory()));
        addButton("SQUARE", event -> painter.setShapeFactory(new Square.Factory()));
        addButton("ELLIPSE", event -> painter.setShapeFactory(new Ellipse.Factory()));
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
}
