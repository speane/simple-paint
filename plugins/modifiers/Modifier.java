package plugins.modifiers;

/**
 * Created by Evgeny Shilov on 10.05.2016.
 */
public interface Modifier {
    void preAction(byte[] data);
    void postAction(byte[] data);
}
