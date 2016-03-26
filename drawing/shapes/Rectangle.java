package drawing.shapes;

/**
 * Created by Speane on 26.03.2016.
 */
public class Rectangle implements Shape {
    private double x;
    private double y;
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public static class Factory implements drawing.shapes.Factory<Rectangle> {

        @Override
        public Rectangle create(double x, double y) {
            return new Rectangle(x, y, 100, 100);
        }
    }
}
