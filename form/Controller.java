package form;

import drawing.drawers.Drawer;
import drawing.drawers.DrawerFactory;
import drawing.shapes.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.Stack;

public class Controller {
    public Canvas drawCanvas;
    private Factory shapeFactory = new Rectangle.Factory();
    private Stack<Shape> shapeStack = new Stack<>();
    private Shape currentShape;

    private double startX;
    private double startY;

    public void rectangleButtonClicked(ActionEvent actionEvent) {
        shapeFactory = new Rectangle.Factory();
    }

    public void circleButtonClicked(ActionEvent actionEvent) {
        shapeFactory = new Circle.Factory();
    }

    public void lineButtonClicked(ActionEvent actionEvent) {
        shapeFactory = new Line.Factory();
    }

    public void triangleButtonClicked(Event event) {
        shapeFactory = new Triangle.Factory();
    }

    public void ellipseButtonClicked(Event event) {
        shapeFactory = new Ellipse.Factory();
    }

    public void rhombusButtonClicked(Event event) {
        shapeFactory = new Rhombus.Factory();
    }

    public void squareButtonClicked(Event event) {
        shapeFactory = new Square.Factory();
    }

    public void mousePressed(Event event) {
        startX = ((MouseEvent)event).getX();
        startY = ((MouseEvent)event).getY();
    }

    public void mouseReleased(Event event) {
        shapeStack.push(currentShape);
    }

    public void drawShape(Shape shape) {
        Drawer drawer = DrawerFactory.getDrawer(shape.getClass());
        drawer.draw(drawCanvas.getGraphicsContext2D(), shape);
    }

    public void mouseDragged(Event event) {
        repaint();

        double finishX = ((MouseEvent)event).getX();
        double finishY = ((MouseEvent)event).getY();
        currentShape = shapeFactory.create(startX, startY, finishX, finishY);

        drawShape(currentShape);
    }

    public void repaint() {
        clearCanvas();

        shapeStack.forEach(this::drawShape);
    }

    public void clearCanvas() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
    }

    public void clearButtonClicked(ActionEvent actionEvent) {
        clearCanvas();
        shapeStack.removeAllElements();
    }


    public void undoButtonClicked(ActionEvent actionEvent) {
        shapeStack.pop();
        repaint();
    }
}
