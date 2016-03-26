package drawing.drawers;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Speane on 26.03.2016.
 */
public interface Drawer<Shape extends drawing.shapes.Shape> {
    public void draw(GraphicsContext graphicsContext, Shape shape);
}