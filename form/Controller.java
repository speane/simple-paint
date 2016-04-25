package form;

import drawing.drawers.*;
import drawing.shapes.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Stack;

public class Controller implements PaintController {
    public Canvas drawCanvas;
    public GridPane sidePane;
    private Factory shapeFactory;
    private DrawerFactory drawerFactory;
    private Stack<Shape> shapeStack;
    private Shape currentShape;
    private double startX;
    private double startY;

    public Controller() {
        shapeFactory = new Rectangle.Factory();

        shapeStack = new Stack<>();

        drawerFactory = new DrawerFactory();
        drawerFactory.addDrawer(Circle.class, new CircleDrawer());
        drawerFactory.addDrawer(Line.class, new LineDrawer());
        drawerFactory.addDrawer(Rectangle.class, new RectangleDrawer());
        drawerFactory.addDrawer(Rhombus.class, new PolygonDrawer());
        drawerFactory.addDrawer(Triangle.class, new PolygonDrawer());
        drawerFactory.addDrawer(Square.class, new SquareDrawer());
    }

    public void setShapeFactory(Factory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Stack<Shape> getShapeStack() {
        return shapeStack;
    }

    public Canvas getDrawCanvas() {
        return drawCanvas;
    }

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
        Drawer drawer = drawerFactory.getDrawer(shape.getClass());
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
        if (!shapeStack.isEmpty())
            shapeStack.pop();
        repaint();
    }
}