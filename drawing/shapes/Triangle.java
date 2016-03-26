package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Triangle extends Polygon {
    public Triangle(double x, double y, double width, double height) {
        nPoints = 3;

        xCoords = new double[nPoints];
        yCoords = new double[nPoints];

        xCoords[0] = x + width / 2;
        yCoords[0] = y;

        xCoords[1] = x + width;
        yCoords[1] = y + height;

        xCoords[2] = x;
        yCoords[2] = y + height;
    }

    public static final class Factory implements drawing.shapes.Factory<Triangle> {
        @Override
        public Triangle create(double x, double y) {
            return new Triangle(x, y, 100, 100);
        }
    }
}
