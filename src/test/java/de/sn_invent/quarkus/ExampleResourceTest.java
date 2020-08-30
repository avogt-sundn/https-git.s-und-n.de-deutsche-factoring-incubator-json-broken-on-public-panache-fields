package de.sn_invent.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class ExampleResourceTest {

    public static final String NAME_GIVEN = "Mr. Me";

    @Test
    public void quarkusEnhancedEntityWithPublicFields() {
        runGet("/tellmeyourname/my",new MyEntity(NAME_GIVEN));
    }


    @Test
    public void lombok() {
        runGet("/tellmeyourname/mylombok",
                new MyLombokEntity(NAME_GIVEN));
    }

    @Test
    public void lombokmypublicfieldslombok() {
        runGet("/tellmeyourname/mypublicfieldslombok",new MyPublicFieldsLombokEntity(NAME_GIVEN));
    }

    private void runGet(String s, Object payload) {
        given()
                .body(payload).contentType(ContentType.JSON)
                .when()
                .get(s)
                .then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(equalTo(NAME_GIVEN));
    }

}