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
    private Factory shapeFactory = new Rectangle.Factory();;

    public void rectangleButtonClicked(ActionEvent actionEvent) {
        shapeFactory = new Rectangle.Factory();
    }

    public void circleButtonClicked(ActionEvent actionEvent) {
        shapeFactory = new Circle.Factory();
    }

    public void mouseClicked(Event event) {
        Shape shape = shapeFactory.create(((MouseEvent)event).getX(), ((MouseEvent)event).getY());
        Drawer drawer = DrawerFactory.getDrawer(shape.getClass());
        drawer.draw(drawCanvas.getGraphicsContext2D(), shape);
    }

    public void lineButtonClicked(ActionEvent actionEvent) {
        shapeFactory = new Line.Factory();
    }
}
