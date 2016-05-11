package serialization;

import plugins.modifiers.StructureModifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny Shilov on 09.05.2016.
 */
public class Serializer {
    private StructureModifier structureModifier;

    public Serializer() {
        structureModifier = new StructureModifier();
    }

    public <T> void serialize(T object, File file) throws IOException {
        FileOutputSerializingStream fileOutputSerializingStream = new FileOutputSerializingStream(file);
        fileOutputSerializingStream.write(object, structureModifier);
        fileOutputSerializingStream.close();
    }

    public <T> T deserialize(Class<? extends T> type, File file) throws IOException {
        FileInputSerializingStream fileInputSerializingStream = new FileInputSerializingStream(file);
        fileInputSerializingStream.close();
        return fileInputSerializingStream.read(type, structureModifier);
    }

    public <T> List<T> deserializeList(Class<T> type, File file) throws IOException {
        /*ArrayList<T> result = new ArrayList<>();
        FileInputSerializingStream fileInputSerializingStream = new FileInputSerializingStream(file);
        while (fileInputSerializingStream.hasMoreElements()) {
            result.add(fileInputSerializingStream.read(type, structureModifier));
        }
        return result;*/
        FileInputSerializingStream fileInputSerializingStream = new FileInputSerializingStream(file);
        ArrayList<T> result = fileInputSerializingStream.readListWithModification(type, structureModifier);
        fileInputSerializingStream.close();

        return result;
    }

    public <T> void serializeList(List<T> list, File file) throws IOException {
        FileOutputSerializingStream fileOutputSerializingStream = new FileOutputSerializingStream(file);
        fileOutputSerializingStream.writeListWithModification(list, structureModifier);
        fileOutputSerializingStream.close();
    }

    public StructureModifier getStructureModifier() {
        return structureModifier;
    }
}
