package serialization;

import plugins.modifiers.StructureModifier;

import java.io.*;

/**
 * Created by Evgeny Shilov on 21.04.2016.
 */
public class FileOutputSerializingStream {

    private DataOutputStream outputStream;

    public FileOutputSerializingStream(File file, StructureModifier structureModifier) throws FileNotFoundException {
        outputStream = new DataOutputStream(new FileOutputStream(file));
    }

    public <T> void write(T object) throws IOException {
        byte[] objectBytes = BSONSerializer.serialize(object);
        outputStream.write(objectBytes);
    }

    public void close() throws IOException {
        outputStream.close();
    }
}
