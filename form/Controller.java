package form;

import drawing.drawers.Drawer;
import drawing.drawers.DrawerFactory;
import drawing.shapes.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class Controller {
    public Canvas drawCanvas;
    private Factory shapeFactory = new Rectangle.Factory();

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
        double finishX = ((MouseEvent)event).getX();
        double finishY = ((MouseEvent)event).getY();

        Shape shape = shapeFactory.create(startX, startY, finishX, finishY);
        Drawer drawer = DrawerFactory.getDrawer(shape.getClass());
        drawer.draw(drawCanvas.getGraphicsContext2D(), shape);
    }
}
