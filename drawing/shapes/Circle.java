package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Circle implements Shape {
    private double x;
    private double y;
    private double width;

    public Circle(double x, double y, double width) {
        this.x = x;
        this.y = y;
        this.width = width;
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

    public static final class Factory implements drawing.shapes.Factory<Circle> {
        @Override
        public Circle create(double x, double y) {
            return new Circle(x, y, 100);
        }
    }
}
