package drawing.drawers;

import drawing.shapes.Polygon;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class PolygonDrawer implements Drawer<Polygon> {
    @Override
    public void draw(GraphicsContext graphicsContext, Polygon polygon) {
        graphicsContext.strokePolygon(polygon.getxCoords(), polygon.getyCoords(), polygon.getnPoints());
    }
}
