package drawing.drawers;

import drawing.shapes.Circle;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class CircleDrawer implements Drawer<Circle> {
    @Override
    public void draw(GraphicsContext graphicsContext, Circle circle) {
        graphicsContext.strokeOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getWidth());
    }
}
