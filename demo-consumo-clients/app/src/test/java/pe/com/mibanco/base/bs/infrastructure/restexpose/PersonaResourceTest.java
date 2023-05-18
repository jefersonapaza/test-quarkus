package pe.com.mibanco.base.bs.infrastructure.restexpose;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonaResourceTest {

    @Test
    public void testHelloEndpoint() {
        given().contentType(MediaType.APPLICATION_JSON)
                .get("/v1/Persona")
                .then().statusCode(200)
                .body(is("Hello world!!"));
    }

}