package de.sn_invent.quarkus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.sn_invent.quarkus.ExampleResourceTest.NAME_GIVEN;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonSerializerTest {


    @Test
    @DisplayName("using Jackson ObjectMapper")
    public void testWitObjectMapper() throws JsonProcessingException {
        final MyPublicFieldsLombokEntity myPublicFieldsLombokEntity = new MyPublicFieldsLombokEntity(NAME_GIVEN);
        String s = "/tellmeyourname/MyPublicFieldsLombokEntity";
        final ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        final String payload = om.writeValueAsString(myPublicFieldsLombokEntity);
        assertTrue(payload.contains(NAME_GIVEN));
    }
}
