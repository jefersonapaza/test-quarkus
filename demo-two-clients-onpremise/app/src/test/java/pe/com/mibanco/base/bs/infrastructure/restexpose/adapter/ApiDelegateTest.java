package pe.com.mibanco.base.bs.infrastructure.restexpose.adapter;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import pe.com.mibanco.base.bs.domain.model.Persona;
import java.util.ArrayList;


import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.com.mibanco.base.bs.application.PersonaService;
import pe.com.mibanco.base.bs.application.dto.ObtenerPersonasDTO;
import pe.com.mibanco.core.adapter.error.exception.ApiException;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class ApiDelegateTest {


    @InjectMock
    PersonaService personaService;


    @Test
    public void respuestaCorrecta(){
        ObtenerPersonasDTO obtenerPersonasDTO=new ObtenerPersonasDTO();
        obtenerPersonasDTO.setCodigo(0);
        obtenerPersonasDTO.setMensaje("ok");
        ArrayList<Persona> personas = new ArrayList<Persona>();
        Persona persona = new Persona();
        persona.setName("nombre mock");
        persona.setId(1);
        personas.add(persona);
        obtenerPersonasDTO.setPersonas(personas);
        Mockito.when(personaService.obtenerPersonas(any())).thenReturn(obtenerPersonasDTO);
        given().contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(200).body(is("[{\"id\":1,\"name\":\"nombre mock\"}]"));
    }

    @Test
    public void respuestaFuncionalOnPremise1(){
        ObtenerPersonasDTO obtenerPersonasDTO=new ObtenerPersonasDTO();
        obtenerPersonasDTO.setCodigo(1);
        obtenerPersonasDTO.setTitle("onpremise 1");
        obtenerPersonasDTO.setMensaje("error onpremise 1");
        Mockito.when(personaService.obtenerPersonas(any())).thenReturn(obtenerPersonasDTO);
        given().contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(202).body(is("{\"title\":\"onpremise 1\",\"detail\":\"error onpremise 1\",\"type\":\"about:blank\"}"));
    }

    @Test
    public void respuestaFuncionalOnPremise2(){
        ObtenerPersonasDTO obtenerPersonasDTO=new ObtenerPersonasDTO();
        obtenerPersonasDTO.setCodigo(2);
        obtenerPersonasDTO.setTitle("onpremise 2");
        obtenerPersonasDTO.setMensaje("error onpremise 2");
        Mockito.when(personaService.obtenerPersonas(any())).thenReturn(obtenerPersonasDTO);
        given().contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(202).body(is("{\"title\":\"onpremise 2\",\"detail\":\"error onpremise 2\",\"type\":\"about:blank\"}"));
    }

    @Test
    public void respuestaFuncionalErrorOnPremise1(){
        Mockito.when(personaService.obtenerPersonas(any())).thenThrow(ApiException.builder().code("MS-01").build());
        given().contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(400)
                .body(is("{\"title\":\"onpremise 1\",\"detail\":\"ocurrio un problema en el servicio on premise 1\",\"type\":\"about:blank\"}"));
    }

    @Test
    public void respuestaFuncionalErrorOnPremise2(){
        Mockito.when(personaService.obtenerPersonas(any())).thenThrow(ApiException.builder().code("MS-02").build());
        given().contentType(MediaType.APPLICATION_JSON)
                .post("/openapi/listado").then().statusCode(400)
                .body(is("{\"title\":\"onpremise 2\",\"detail\":\"ocurrio un problema en el servicio on premise 2\",\"type\":\"about:blank\"}"));
    }

}