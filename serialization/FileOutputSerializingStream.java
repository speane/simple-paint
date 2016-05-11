package serialization;

import plugins.modifiers.StructureModifier;

import java.io.*;
import java.util.List;

/**
 * Created by Evgeny Shilov on 21.04.2016.
 */
public class FileOutputSerializingStream {

    private DataOutputStream outputStream;

    public FileOutputSerializingStream(File file) throws FileNotFoundException {
        outputStream = new DataOutputStream(new FileOutputStream(file));
    }

    public <T> void write(T object, StructureModifier structureModifier) throws IOException {
        byte[] objectBytes = BSONSerializer.serialize(object);
        structureModifier.preAction(objectBytes);
        outputStream.write(objectBytes);
    }

    public void close() throws IOException {
        outputStream.close();
    }

    public <T> void writeListWithModification(List<T> list, StructureModifier structureModifier) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (Object tempObject : list) {
            byte[] objectBytes = BSONSerializer.serialize(tempObject);
            byteArrayOutputStream.write(objectBytes);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        data = structureModifier.preAction(data);
        System.out.println(new String(data));
        outputStream.write(data);
    }
}
