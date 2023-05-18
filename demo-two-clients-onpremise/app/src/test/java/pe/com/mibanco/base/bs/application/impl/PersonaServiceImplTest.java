package pe.com.mibanco.base.bs.application.impl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import pe.com.mibanco.base.bs.application.dto.ObtenerPersonasDTO;
import pe.com.mibanco.base.bs.client.model.Persona2;
import pe.com.mibanco.base.bs.client.model.Persona;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseDos;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseUno;
import pe.com.mibanco.base.bs.domain.spi.OnPremiseDosPort;
import pe.com.mibanco.base.bs.domain.spi.OnPremiseUnoPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PersonaServiceImplTest {

    @InjectMocks
    PersonaServiceImpl personaService;

    @Mock
    OnPremiseUnoPort onPremiseUnoPort;

    @Mock
    OnPremiseDosPort onPremiseDosPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void codigo0(){
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = new PersonaDtoOnePremiseUno();
        personaDtoOnePremiseUno.setCodigo(0);
        personaDtoOnePremiseUno.setMensaje("ok");
        Persona persona = new Persona();
        persona.setName("onpremise1 mock");
        persona.setId(1);
        personaDtoOnePremiseUno.setPersona(persona);
        Mockito.when(onPremiseUnoPort.obtenerPersona(any())).thenReturn(personaDtoOnePremiseUno);

        PersonaDtoOnePremiseDos personaDtoOnePremiseDos = new PersonaDtoOnePremiseDos();
        personaDtoOnePremiseDos.setCodigo(0);
        personaDtoOnePremiseDos.setMensaje("ok");
        Persona2 persona2 = new Persona2();
        persona2.setName("onpremise2 mock");
        persona2.setId(2);
        personaDtoOnePremiseDos.setPersona2(persona2);
        Mockito.when(onPremiseDosPort.obtenerPersona(any())).thenReturn(personaDtoOnePremiseDos);
        ObtenerPersonasDTO obtenerPersonasDTO = personaService.obtenerPersonas("statusCode=200");

        Assertions.assertEquals(0,obtenerPersonasDTO.getCodigo());
    }

    @Test
    public void funcionalOnPremise1(){
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = new PersonaDtoOnePremiseUno();
        personaDtoOnePremiseUno.setCodigo(1);
        personaDtoOnePremiseUno.setMensaje("onpremise1 detail");
        personaDtoOnePremiseUno.setTitle("error1");
        Mockito.when(onPremiseUnoPort.obtenerPersona(any())).thenReturn(personaDtoOnePremiseUno);
        ObtenerPersonasDTO obtenerPersonasDTO = personaService.obtenerPersonas("statusCode=202");
        Assertions.assertEquals(1,obtenerPersonasDTO.getCodigo());
        Assertions.assertEquals("error1",obtenerPersonasDTO.getTitle());
        Assertions.assertEquals("onpremise1 detail",obtenerPersonasDTO.getMensaje());
    }

    @Test
    public void funcionalOnPremise2(){
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = new PersonaDtoOnePremiseUno();
        personaDtoOnePremiseUno.setCodigo(0);
        personaDtoOnePremiseUno.setMensaje("ok");
        Persona persona = new Persona();
        persona.setName("onpremise1 mock");
        persona.setId(1);
        personaDtoOnePremiseUno.setPersona(persona);
        Mockito.when(onPremiseUnoPort.obtenerPersona(any())).thenReturn(personaDtoOnePremiseUno);

        PersonaDtoOnePremiseDos personaDtoOnePremiseDos = new PersonaDtoOnePremiseDos();
        personaDtoOnePremiseDos.setCodigo(1);
        personaDtoOnePremiseDos.setMensaje("onpremise2");
        personaDtoOnePremiseDos.setTitle("title2");
        Mockito.when(onPremiseDosPort.obtenerPersona(any())).thenReturn(personaDtoOnePremiseDos);
        ObtenerPersonasDTO obtenerPersonasDTO = personaService.obtenerPersonas("statusCode=200");

        Assertions.assertEquals(2,obtenerPersonasDTO.getCodigo());
        Assertions.assertEquals("title2",obtenerPersonasDTO.getTitle());
        Assertions.assertEquals("onpremise2",obtenerPersonasDTO.getMensaje());
    }
}