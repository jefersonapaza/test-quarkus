package pe.com.mibanco.base.bs.infrastructure.restexpose;

import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.com.mibanco.base.bs.client.model.BaseError;
import pe.com.mibanco.base.bs.client.model.Persona;
import pe.com.mibanco.base.bs.domain.spi.PersonaPort;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class ApiDelegateTest {

    @InjectMock
    PersonaPort personaPort;


    @Test
    void respuestaOk() {
        Persona personaResponseMock=new Persona();
        personaResponseMock.setName("este es un mock");
        personaResponseMock.setId(1);
        Mockito.when(personaPort.obtenerPersonas(any())).thenReturn(Response.ok().entity(personaResponseMock).build());
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(200)
                .body(is("{\"title\":\"\",\"detail\":\"\",\"type\":\"\",\"transacciones\":[{\"id\":1,\"name\":\"este es un mock\"}]}"));

    }

    @Test
    void respuestaFuncional1() {
        BaseError baseError=new BaseError();
        baseError.setTitle("error1");
        baseError.setDetail("detail error1");
        baseError.setType("about:blank");
        Mockito.when(personaPort.obtenerPersonas(any())).thenReturn(Response.status(202).entity(baseError).build());
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(202)
                .body(is("{\"title\":\"error1\",\"detail\":\"detail error1\",\"type\":\"about:blank\"}"));

    }

    @Test
    void respuestaError404() {
        ClientWebApplicationException error = new ClientWebApplicationException("", Response.status(400).entity("").build());
        Mockito.when(personaPort.obtenerPersonas(any())).thenThrow(error);
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(400)
                .body(is("{\"title\":\"Error servicio onpremise\",\"detail\":\"Error al consultar el servicio onpremise\",\"type\":\"about:blank\"}"));

    }
}