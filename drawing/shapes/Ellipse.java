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
        public Ellipse create(double startX, double startY, double finishX, double finishY) {
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
            return new Ellipse(x, y, width, height);
        }
    }
}