package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Square implements Shape {
    private double x;
    private double y;
    private double width;
    private double height;

    public Square(double x, double y, double width, double height) {
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

    public static class Factory implements drawing.shapes.Factory<Square> {

        @Override
        public Square create(double x, double y) {
            return new Square(x, y, 50, 150);
        }
    }
}
