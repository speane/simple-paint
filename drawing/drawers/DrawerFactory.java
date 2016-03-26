package drawing.drawers;

import drawing.shapes.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class DrawerFactory {
    private static Map<Class, Drawer> drawerMap;
    static {
        drawerMap = new HashMap<>();
        drawerMap.put(Rectangle.class, new RectangleDrawer());
    }
    public static Drawer getDrawer(Class shapeType) {
        return drawerMap.get(shapeType);
    }
}