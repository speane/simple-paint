package drawing;

import drawing.drawers.*;
import drawing.shapes.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.Stack;

/**
 * Created by Evgeny Shilov on 24.04.2016.
 */
public class Painter {
    public final int CANVAS_WIDTH = 600;
    public final int CANVAS_HEIGHT = 600;


    private EventHandler<MouseEvent> mousePressedEventHandler;
    private EventHandler<MouseEvent> mouseDraggedEventHandler;
    private EventHandler<MouseEvent> mouseReleasedEventHandler;

    private Canvas canvas;
    private Factory shapeFactory;
    private DrawerFactory drawerFactory;
    private Stack<Shape> shapeStack;
    private Shape currentShape;
    private double startX;
    private double startY;

    private final EventHandler<MouseEvent> defaultMousePressedEventHandler = event -> {
        startX = event.getX();
        startY = event.getY();
    };

    private final EventHandler<MouseEvent> defaultMouseDraggedEventHandler = event -> {
        drawCurrentShape(event.getX(), event.getY());
    };

    private final EventHandler<MouseEvent> defaultMouseReleasedEventHandler = event -> {
        shapeStack.push(currentShape);
    };

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        shapeFactory = new Rectangle.Factory();

        shapeStack = new Stack<>();

        mousePressedEventHandler = defaultMousePressedEventHandler;
        mouseDraggedEventHandler = defaultMouseDraggedEventHandler;
        mouseReleasedEventHandler = defaultMouseReleasedEventHandler;

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

    public Stack<Shape> getShapeStack() {
        return shapeStack;
    }

    private void initCanvas() {
        canvas.setWidth(CANVAS_WIDTH);
        canvas.setHeight(CANVAS_HEIGHT);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEventHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDraggedEventHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEventHandler);
    }

    public void changeMousePressedEventHandler(EventHandler<MouseEvent> mousePressedEventHandler) {
        canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.mousePressedEventHandler);
        this.mousePressedEventHandler = mousePressedEventHandler;
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this.mousePressedEventHandler);
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

    public DrawerFactory getDrawerFactory() {
        return drawerFactory;
    }
}
