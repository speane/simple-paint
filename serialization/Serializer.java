package serialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Evgeny Shilov on 09.05.2016.
 */
public class Serializer {
    public <T> void serialize(T object, File file) throws IOException {
        FileOutputSerializingStream fileOutputSerializingStream = new FileOutputSerializingStream(file);
        fileOutputSerializingStream.write(object);
        fileOutputSerializingStream.close();
    }

    public <T> T deserialize(Class<? extends T> type, File file) throws IOException {
        FileInputSerializingStream fileInputSerializingStream = new FileInputSerializingStream(file);
        fileInputSerializingStream.close();
        return fileInputSerializingStream.read(type);
    }

    public <T> List<T> deserializeList(Class<? extends T> type, File file) throws IOException {
        ArrayList<T> result = new ArrayList<>();
        FileInputSerializingStream fileInputSerializingStream = new FileInputSerializingStream(file);
        while (fileInputSerializingStream.hasMoreElements()) {
            result.add(fileInputSerializingStream.read(type));
        }
        return result;
    }

    public <T> void serializeList(List<T> list, File file) throws IOException {
        FileOutputSerializingStream fileOutputSerializingStream = new FileOutputSerializingStream(file);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            fileOutputSerializingStream.write(iterator.next());
        }
        fileOutputSerializingStream.close();
    }
}
