package drawing.drawers;

import javafx.scene.shape.Rectangle;

/**
 * Created by Speane on 26.03.2016.
 */
public class RectangleDrawer implements Drawer<Rectangle> {
    @Override
    public void draw(Rectangle rectangle) {
        System.out.println("Draw rectangle");
    }
}
