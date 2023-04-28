package cl.diego.balance.users.app.testing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class UtilForTesting {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper( )
            .disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );

    public static <T> T getMappedObjectFromFile( String path,
                                                 Class<T> tClass ) throws IOException {
        return OBJECT_MAPPER.readValue( new ClassPathResource( path ).getFile( ), tClass );
    }

}
