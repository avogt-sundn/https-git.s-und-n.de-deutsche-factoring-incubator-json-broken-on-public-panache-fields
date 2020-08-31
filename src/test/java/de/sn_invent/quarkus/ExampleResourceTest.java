package de.sn_invent.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@QuarkusTest
public class ExampleResourceTest {

    public static final String NAME_GIVEN = "Mr. Me";


    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("/tellmeyourname/MyEntity", new MyEntity(NAME_GIVEN)),
                arguments("/tellmeyourname/MyPublicFieldsLombokEntity", new MyPublicFieldsLombokEntity(NAME_GIVEN)),
                arguments("/tellmeyourname/MyLombokEntity", new MyLombokEntity(NAME_GIVEN))
        );
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    public void runGet(String s, Object payload) {
        given().contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .body(payload)
                .when()
                .post(s)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.TEXT)
                .body(containsString(NAME_GIVEN));
    }

    @Test
    @DisplayName("Quarkus augmentation: field access redirected to getter method: panache bean with public fields")
    public void testQuarkusPanacheGet() {
        final MyEntity myEntity = new MyEntity(NAME_GIVEN);
        assertEquals(NAME_GIVEN, myEntity.name, "read field should call getter");
        assertTrue(myEntity.getWasCalled);
    }


    @Test
    @DisplayName("Quarkus augmentation: field access redirected to getter method: lombok @data panache bean with public fields")
    public void testBrokenQuarkusPanacheGet() {
        final MyPublicFieldsLombokEntity myPublicFieldsLombokEntity = new MyPublicFieldsLombokEntity(NAME_GIVEN);
        assertEquals(NAME_GIVEN, myPublicFieldsLombokEntity.name, "read field should call getter");
        assertTrue(myPublicFieldsLombokEntity.getWasCalled);
    }

    @Test
    @DisplayName("using ObjectMapper")
    public void testWitObjectMapper() throws JsonProcessingException {
        final MyPublicFieldsLombokEntity myPublicFieldsLombokEntity = new MyPublicFieldsLombokEntity(NAME_GIVEN);
        String s = "/tellmeyourname/MyPublicFieldsLombokEntity";
        final ObjectMapper om = new ObjectMapper();
        final String payload = om.writeValueAsString(myPublicFieldsLombokEntity);
        assertTrue(payload.contains(NAME_GIVEN));
    }

    @Test
    @DisplayName("posting using ObjectMapper")
    public void testPostWitObjectMapper() throws JsonProcessingException {
        final MyPublicFieldsLombokEntity myPublicFieldsLombokEntity = new MyPublicFieldsLombokEntity(NAME_GIVEN);
        String s = "/tellmeyourname/MyPublicFieldsLombokEntity";
        final ObjectMapper om = new ObjectMapper();
        final String payload = om.writeValueAsString(myPublicFieldsLombokEntity);
        given()
                .body(payload).contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .when()
                .post(s)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.TEXT)
                .body(containsString(NAME_GIVEN));
    }


}