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
        public Square create(double startX, double startY, double finishX, double finishY) {
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

            if (width > height) {
                width = height;
            }
            return new Square(x, y, width);
        }
    }
}
