package drawing.factories;

import drawing.drawers.CircleDrawer;
import drawing.drawers.Drawer;
import drawing.drawers.RectangleDrawer;
import drawing.shapes.ShapeType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class DrawerFactory {
    private static Map<ShapeType, Drawer> drawerMap;
    static {
        drawerMap = new HashMap<>();
        drawerMap.put(ShapeType.RECTANGLE, new RectangleDrawer());
        drawerMap.put(ShapeType.CIRCLE, new CircleDrawer());
    }
    public static Drawer getDrawer(ShapeType shapeType) {
        return drawerMap.get(shapeType);
    }
}