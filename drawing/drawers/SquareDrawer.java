package drawing.drawers;

import drawing.shapes.Square;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class SquareDrawer implements Drawer<Square> {
    @Override
    public void draw(GraphicsContext graphicsContext, Square square) {
        graphicsContext.strokeRect(square.getX(), square.getY(), square.getWidth(), square.getWidth());
    }
}
