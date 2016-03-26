package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Ellipse extends Circle {
    private double height;

    public Ellipse(double x, double y, double width, double height) {
        super(x, y, width);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public static final class Factory implements drawing.shapes.Factory<Ellipse> {
        @Override
        public Ellipse create(double x, double y) {
            return new Ellipse(x, y, 70, 130);
        }
    }
}