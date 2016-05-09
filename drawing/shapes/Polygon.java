package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public abstract class Polygon implements Shape {
    protected double[] xCoords;
    protected double[] yCoords;
    protected int nPoints;

    public Polygon() {
        xCoords = yCoords = new double[0];
        nPoints = -1;
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
}
