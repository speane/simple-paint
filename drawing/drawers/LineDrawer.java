package drawing.drawers;

import drawing.shapes.Line;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class LineDrawer implements Drawer<Line> {
    @Override
    public void draw(GraphicsContext graphicsContext, Line line) {
        graphicsContext.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
}
