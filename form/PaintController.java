package form;

import drawing.shapes.Factory;
import drawing.shapes.Shape;
import javafx.scene.canvas.Canvas;

import java.util.Stack;

/**
 * Created by Evgeny Shilov on 24.04.2016.
 */
public interface PaintController {
    Stack<Shape> getShapeStack();
    Canvas getDrawCanvas();
    void setShapeFactory(Factory shapeFactory);
}
