package drawing.shapes;

/**
 * Created by Speane on 26.03.2016.
 */
public class Rectangle extends Square {
    private double height;

    public Rectangle() {
        height = -1;
    }

    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public static final class Factory implements drawing.shapes.Factory<Rectangle> {
        @Override
        public Rectangle create(double startX, double startY, double finishX, double finishY) {
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

            return new Rectangle(x, y, width, height);
        }
    }
}
