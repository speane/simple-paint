package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Rhombus implements Shape {
    private double[] xCoords;
    private double[] yCoords;
    private final int nPoints = 4;

    public Rhombus(double x, double y, double width, double height) {
        xCoords = new double[nPoints];

        xCoords[0] = x + width / 2;
        yCoords[0] = y;

        xCoords[1] = x + width;
        yCoords[1] = y + height / 2;

        xCoords[2] = x + width / 2;
        yCoords[2] = y + height;

        xCoords[3] = x;
        yCoords[3] = y + height / 2;
    }

    public double[] getxCoords() {
        return xCoords;
    }

    public double[] getyCoords() {
        return yCoords;
    }

    public int getnPoints() {
        return nPoints;
    }

    public static final class Factory implements drawing.shapes.Factory<Rhombus> {
        @Override
        public Rhombus create(double x, double y) {
            return new Rhombus(x, y, 100, 200);
        }
    }
}
