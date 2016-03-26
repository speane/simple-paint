package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public class Rhombus extends Polygon {
    public Rhombus(double x, double y, double width, double height) {
        nPoints = 4;

        xCoords = new double[nPoints];
        yCoords = new double[nPoints];

        xCoords[0] = x + width / 2;
        yCoords[0] = y;

        xCoords[1] = x + width;
        yCoords[1] = y + height / 2;

        xCoords[2] = x + width / 2;
        yCoords[2] = y + height;

        xCoords[3] = x;
        yCoords[3] = y + height / 2;
    }


    public static final class Factory implements drawing.shapes.Factory<Rhombus> {
        @Override
        public Rhombus create(double startX, double startY, double finishX, double finishY) {
            double x;
            double y;
            double width;
            double height;

            if (finishX < startX) {
                x = finishX;
                width = startX - finishX;
            }
            else {
                x = startX;
                width = finishX - startX;
            }

            if (finishY < startY) {
                y = finishY;
                height = startY - finishY;
            }
            else {
                y = startY;
                height = finishY - startY;
            }

            return new Rhombus(x, y, width, height);
        }
    }
}
