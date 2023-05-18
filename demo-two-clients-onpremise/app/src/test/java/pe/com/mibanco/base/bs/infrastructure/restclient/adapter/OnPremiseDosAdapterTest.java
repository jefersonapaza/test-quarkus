package pe.com.mibanco.base.bs.infrastructure.restclient.adapter;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pe.com.mibanco.base.bs.client.api.OnPremise2Api;
import pe.com.mibanco.base.bs.client.model.BaseError;
import pe.com.mibanco.base.bs.client.model.Persona2;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseDos;
import pe.com.mibanco.core.adapter.error.exception.ApiException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class OnPremiseDosAdapterTest {

    @InjectMocks
    OnPremiseDosAdapter onPremiseDosAdapter;

    @Mock
    OnPremise2Api onPremise2Api;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void repuestaCodigo0(){
        Persona2 persona=new Persona2();
        persona.setName("name mock 1");
        persona.setId(1);
        Mockito.when(onPremise2Api.getPersonas(any())).thenReturn(Response.ok().entity(persona).build());
        PersonaDtoOnePremiseDos personaDtoOnePremiseUno = onPremiseDosAdapter.obtenerPersona("statusCode=200");
        Assertions.assertEquals(0,personaDtoOnePremiseUno.getCodigo());
        Assertions.assertEquals(persona.getName(),personaDtoOnePremiseUno.getPersona2().getName());
        Assertions.assertEquals(persona.getId(),personaDtoOnePremiseUno.getPersona2().getId());
    }

    @Test
    public void repuestaFuncional202_1(){
        BaseError baseError=new BaseError();
        baseError.setTitle("error1");
        baseError.setDetail("detail error1");
        Mockito.when(onPremise2Api.getPersonas(any())).thenReturn(Response.status(202).entity(baseError).build());
        PersonaDtoOnePremiseDos personaDtoOnePremiseUno = onPremiseDosAdapter.obtenerPersona("statusCode=202");
        Assertions.assertEquals(1,personaDtoOnePremiseUno.getCodigo());
        Assertions.assertEquals(baseError.getTitle(),personaDtoOnePremiseUno.getTitle());
        Assertions.assertEquals(baseError.getDetail(),personaDtoOnePremiseUno.getMensaje());
    }

    @Test
    public void repuestaFuncional202_2(){
        BaseError baseError=new BaseError();
        baseError.setTitle("error2");
        baseError.setDetail("detail error2");
        Mockito.when(onPremise2Api.getPersonas(any())).thenReturn(Response.status(202).entity(baseError).build());
        PersonaDtoOnePremiseDos personaDtoOnePremiseUno = onPremiseDosAdapter.obtenerPersona("statusCode=202");
        Assertions.assertEquals(2,personaDtoOnePremiseUno.getCodigo());
        Assertions.assertEquals(baseError.getTitle(),personaDtoOnePremiseUno.getTitle());
        Assertions.assertEquals(baseError.getDetail(),personaDtoOnePremiseUno.getMensaje());
    }

    @Test
    public void repuestaFuncionalError404(){
        ClientWebApplicationException error=new ClientWebApplicationException(Response.status(400).entity("").build());
        Mockito.when(onPremise2Api.getPersonas(any())).thenThrow(error);
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            onPremiseDosAdapter.obtenerPersona("statusCode=400");
        });
        Assertions.assertEquals("MS-02",apiException.getCode());
    }
}