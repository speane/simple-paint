package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Evgeny Shilov on 21.04.2016.
 */
public class BSONSerializer {
    private static ObjectMapper mapper = new ObjectMapper(new BsonFactory());

    public static <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, object);
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return mapper.readValue(byteArrayInputStream, type);
    }
}
