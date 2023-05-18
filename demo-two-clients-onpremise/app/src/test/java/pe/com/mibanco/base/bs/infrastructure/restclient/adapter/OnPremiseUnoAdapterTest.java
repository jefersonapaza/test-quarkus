package pe.com.mibanco.base.bs.infrastructure.restclient.adapter;

import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pe.com.mibanco.base.bs.client.api.OnPremiseApi;
import pe.com.mibanco.base.bs.client.model.BaseError;
import pe.com.mibanco.base.bs.client.model.Persona;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseUno;
import pe.com.mibanco.core.adapter.error.exception.ApiException;

import static org.mockito.ArgumentMatchers.any;


class OnPremiseUnoAdapterTest {

    @InjectMocks
    OnPremiseUnoAdapter onPremiseUnoAdapter;

    @Mock
    OnPremiseApi onPremiseApi;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
    public void repuestaCodigo0(){
        Persona persona=new Persona();
        persona.setName("name mock 1");
        persona.setId(1);
        Mockito.when(onPremiseApi.getPersonas(any())).thenReturn(Response.ok().entity(persona).build());
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = onPremiseUnoAdapter.obtenerPersona("statusCode=200");
        Assertions.assertEquals(0,personaDtoOnePremiseUno.getCodigo());
        Assertions.assertEquals(persona.getName(),personaDtoOnePremiseUno.getPersona().getName());
        Assertions.assertEquals(persona.getId(),personaDtoOnePremiseUno.getPersona().getId());
    }

    @Test
    public void repuestaFuncional202_1(){
        BaseError baseError=new BaseError();
        baseError.setTitle("error1");
        baseError.setDetail("detail error1");
        Mockito.when(onPremiseApi.getPersonas(any())).thenReturn(Response.status(202).entity(baseError).build());
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = onPremiseUnoAdapter.obtenerPersona("statusCode=202");
        Assertions.assertEquals(1,personaDtoOnePremiseUno.getCodigo());
        Assertions.assertEquals(baseError.getTitle(),personaDtoOnePremiseUno.getTitle());
        Assertions.assertEquals(baseError.getDetail(),personaDtoOnePremiseUno.getMensaje());
    }

    @Test
    public void repuestaFuncional202_2(){
        BaseError baseError=new BaseError();
        baseError.setTitle("error2");
        baseError.setDetail("detail error2");
        Mockito.when(onPremiseApi.getPersonas(any())).thenReturn(Response.status(202).entity(baseError).build());
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = onPremiseUnoAdapter.obtenerPersona("statusCode=202");
        Assertions.assertEquals(2,personaDtoOnePremiseUno.getCodigo());
        Assertions.assertEquals(baseError.getTitle(),personaDtoOnePremiseUno.getTitle());
        Assertions.assertEquals(baseError.getDetail(),personaDtoOnePremiseUno.getMensaje());
    }

    @Test
    public void repuestaFuncionalError404(){
        ClientWebApplicationException error=new ClientWebApplicationException(Response.status(400).entity("").build());
        Mockito.when(onPremiseApi.getPersonas(any())).thenThrow(error);
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            onPremiseUnoAdapter.obtenerPersona("statusCode=400");
        });
        Assertions.assertEquals("MS-01",apiException.getCode());

    }

}