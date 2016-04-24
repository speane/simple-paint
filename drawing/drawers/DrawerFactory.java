package drawing.drawers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class DrawerFactory {
    private Map<Class, Drawer> drawerMap;

    public DrawerFactory() {
        drawerMap = new HashMap<>();
    }

    public Drawer getDrawer(Class shapeType) {
        return drawerMap.get(shapeType);
    }

    public void addDrawer(Class shapeType, Drawer drawer) {
        drawerMap.put(shapeType, drawer);
    }
}