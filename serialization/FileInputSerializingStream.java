package serialization;

import plugins.modifiers.StructureModifier;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Evgeny Shilov on 21.04.2016.
 */
public class FileInputSerializingStream {
    private DataInputStream inputStream;

    public FileInputSerializingStream(File file) throws FileNotFoundException {
        inputStream = new DataInputStream(new FileInputStream(file));
    }

    public <T> T read(Class<T> type, StructureModifier structureModifier) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for (int i = 0; i < 4; i++) {
            byteArrayOutputStream.write(inputStream.read());
        }
        int documentLength = ByteBuffer
                .wrap(byteArrayOutputStream.toByteArray())
                .order(ByteOrder.LITTLE_ENDIAN).getInt();

        for (int i = 0; i < documentLength - 4; i++) {
            byteArrayOutputStream.write(inputStream.read());
        }

        byte[] data = byteArrayOutputStream.toByteArray();

        structureModifier.preAction(data);

        return BSONSerializer.deserialize(data, type);
    }

    public boolean hasMoreElements() throws IOException {
        return inputStream.available() > 0;
    }

    public void close() throws IOException {
        inputStream.close();
    }
}
