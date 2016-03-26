package drawing.drawers;

import drawing.shapes.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class DrawerFactory {
    private static Map<Class, Drawer> drawerMap;
    static {
        drawerMap = new HashMap<>();
        drawerMap.put(Square.class, new SquareDrawer());
        drawerMap.put(Rectangle.class, new RectangleDrawer());
        drawerMap.put(Circle.class, new CircleDrawer());
        drawerMap.put(Ellipse.class, new EllipseDrawer());
        drawerMap.put(Line.class, new LineDrawer());
        PolygonDrawer polygonDrawer = new PolygonDrawer();
        drawerMap.put(Triangle.class, polygonDrawer);
        drawerMap.put(Rhombus.class, polygonDrawer);
    }
    public static Drawer getDrawer(Class shapeType) {
        return drawerMap.get(shapeType);
    }
}