package form;

import drawing.drawers.Drawer;
import drawing.factories.DrawerFactory;
import drawing.shapes.Rectangle;
import drawing.shapes.ShapeType;
import javafx.event.ActionEvent;

public class Controller {

    public void drawButtonClicked(ActionEvent actionEvent) {
        Drawer drawer = DrawerFactory.getDrawer(ShapeType.RECTANGLE);
        drawer.draw(new Rectangle());
    }
}
