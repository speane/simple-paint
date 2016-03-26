package drawing.shapes;

/**
 * Created by Evgeny Shilov on 26.03.2016.
 */
public interface Factory<Shape extends drawing.shapes.Shape> {
    public Shape create(double startX, double startY, double finishX, double finishY);
}
