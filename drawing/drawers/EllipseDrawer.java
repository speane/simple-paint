package drawing.drawers;

import drawing.shapes.Ellipse;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class EllipseDrawer implements Drawer<Ellipse> {
    @Override
    public void draw(GraphicsContext graphicsContext, Ellipse ellipse) {
        graphicsContext.strokeOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
    }
}
