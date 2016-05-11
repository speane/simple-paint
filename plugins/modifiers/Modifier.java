package plugins.modifiers;

/**
 * Created by Evgeny Shilov on 10.05.2016.
 */
public interface Modifier {
    byte[] preAction(byte[] data);
    byte[] postAction(byte[] data);
}
