package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Line implements Shape {
    private double x1, y1;
    private double x2, y2;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public static class Factory implements drawing.shapes.Factory<Line> {
        @Override
        public Line create(double x, double y) {
            return new Line(x, y, 100, 100);
        }
    }
}
