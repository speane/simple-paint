package drawing.drawers;

import drawing.shapes.Rectangle;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Speane on 26.03.2016.
 */
public class RectangleDrawer implements Drawer<Rectangle> {
    @Override
    public void draw(GraphicsContext graphicsContext, Rectangle rectangle) {
        graphicsContext.strokeRect(rectangle.getX(), rectangle.getY(),
                                    rectangle.getWidth(), rectangle.getHeight());
    }
}
