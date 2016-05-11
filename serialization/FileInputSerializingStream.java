package serialization;

import plugins.modifiers.StructureModifier;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * Created by Evgeny Shilov on 21.04.2016.
 */
public class FileInputSerializingStream {
    private DataInputStream inputStream;

    public FileInputSerializingStream(File file) throws FileNotFoundException {
        inputStream = new DataInputStream(new FileInputStream(file));
    }

    public <T> T read(Class<T> type) throws IOException {
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

        return BSONSerializer.deserialize(data, type);
    }

    public <T> ArrayList<T> readListWithModification(Class<T> type, StructureModifier structureModifier) throws IOException {
        ArrayList<T> list = new ArrayList<>();

        byte[] allData = new byte[inputStream.available()];
        inputStream.readFully(allData);

        byte[] data = structureModifier.postAction(allData);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < data.length) {
            int documentLength = ByteBuffer.wrap(data, i, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
            byteArrayOutputStream.write(data, i, documentLength);
            list.add(BSONSerializer.deserialize(byteArrayOutputStream.toByteArray(), type));
            byteArrayOutputStream.reset();
            i += documentLength;
        }

        return list;
    }

    public boolean hasMoreElements() throws IOException {
        return inputStream.available() > 0;
    }

    public void close() throws IOException {
        inputStream.close();
    }
}
