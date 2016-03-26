package drawing.shapes;

/**
 * Created by Speane on 26.03.2016.
 */
public class Rectangle extends Square {
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width);
        this.height = height;
    }

    public static final class Factory implements drawing.shapes.Factory<Rectangle> {
        @Override
        public Rectangle create(double x, double y) {
            return new Rectangle(x, y, 100, 100);
        }
    }
}
