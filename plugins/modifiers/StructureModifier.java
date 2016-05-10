package plugins.modifiers;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Evgeny Shilov on 10.05.2016.
 */
public class StructureModifier {
    private ArrayList<Modifier> modifiers;

    public StructureModifier() {
        modifiers = new ArrayList<>();
    }

    public void preAction(byte[] data) {
        for (Modifier modifier : modifiers) {
            modifier.preAction(data);
        }
    }

    public void postAction(byte[] data) {
        for (Modifier modifier : modifiers) {
            modifier.postAction(data);
        }
    }

    public void addModifier(Modifier modifier) {
        modifiers.add(modifier);
    }

    public void removeModifier(Modifier modifier) {
        try {
            modifiers.remove(modifier);
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }
}
