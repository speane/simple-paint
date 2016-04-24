package drawing;

import drawing.drawers.*;
import drawing.shapes.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.Stack;

/**
 * Created by Evgeny Shilov on 24.04.2016.
 */
public class Painter {
    private final int CANVAS_WIDTH = 600;
    private final int CANVAS_HEIGHT = 600;

    private Canvas canvas;
    private Factory shapeFactory;
    private DrawerFactory drawerFactory;
    private Stack<Shape> shapeStack;
    private Shape currentShape;
    private double startX;
    private double startY;

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        shapeFactory = new Circle.Factory();

        shapeStack = new Stack<>();

        drawerFactory = new DrawerFactory();
        drawerFactory.addDrawer(Circle.class, new CircleDrawer());
        drawerFactory.addDrawer(Ellipse.class, new EllipseDrawer());
        drawerFactory.addDrawer(Line.class, new LineDrawer());
        drawerFactory.addDrawer(Rectangle.class, new RectangleDrawer());
        drawerFactory.addDrawer(Rhombus.class, new PolygonDrawer());
        drawerFactory.addDrawer(Triangle.class, new PolygonDrawer());
        drawerFactory.addDrawer(Square.class, new SquareDrawer());

        initCanvas();
    }


    private void initCanvas() {
        canvas.setWidth(CANVAS_WIDTH);
        canvas.setHeight(CANVAS_HEIGHT);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            setStartX(event.getX());
            setStartY(event.getY());
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> drawCurrentShape(event.getX(), event.getY()));
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> shapeStack.push(currentShape));
    }

    public void drawCurrentShape(double finishX, double finishY) {
        repaint();
        currentShape = shapeFactory.create(startX, startY, finishX, finishY);
        drawShape(currentShape);
    }

    public void drawShape(Shape shape) {
        drawerFactory.getDrawer(shape.getClass()).draw(canvas.getGraphicsContext2D(), shape);
    }

    public void repaint() {
        clearCanvas();
        shapeStack.forEach(this::drawShape);
    }

    public void clearCanvas() {
        canvas.getGraphicsContext2D().clearRect(0, 0, 600, 800);
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setShapeFactory(Factory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
