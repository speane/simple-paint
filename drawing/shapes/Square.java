package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Square implements Shape {
    protected double x;
    protected double y;
    protected double width;

    public Square(double x, double y, double width) {
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

    public static class Factory implements drawing.shapes.Factory<Square> {

        @Override
        public Square create(double x, double y) {
            return new Square(x, y, 100);
        }
    }
}
