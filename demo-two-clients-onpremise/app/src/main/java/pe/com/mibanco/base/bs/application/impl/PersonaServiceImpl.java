package pe.com.mibanco.base.bs.application.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mapstruct.factory.Mappers;
import pe.com.mibanco.base.bs.application.PersonaService;
import pe.com.mibanco.base.bs.application.mapper.PersonMapper;
import pe.com.mibanco.base.bs.application.dto.ObtenerPersonasDTO;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseDos;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseUno;
import pe.com.mibanco.base.bs.domain.spi.OnPremiseDosPort;
import pe.com.mibanco.base.bs.domain.spi.OnPremiseUnoPort;

/*
 * class service example
 */
@ApplicationScoped
public class PersonaServiceImpl implements PersonaService {

    @Inject
    OnPremiseUnoPort onPremiseUnoPort;
    @Inject
    OnPremiseDosPort onPremisedosPort;

    @Override
    public ObtenerPersonasDTO obtenerPersonas(String ref) {
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = onPremiseUnoPort.obtenerPersona(ref);
        if (personaDtoOnePremiseUno.getCodigo() == 0) {
            PersonaDtoOnePremiseDos onPremiseDosPort = onPremisedosPort.obtenerPersona(ref);
            if (onPremiseDosPort.getCodigo() == 0) {
                ObtenerPersonasDTO obtenerPersonasDTO = new ObtenerPersonasDTO();
                obtenerPersonasDTO.setCodigo(0);
                obtenerPersonasDTO.setMensaje("ok");
                //Data cliente1: ejemplo mapper basico
                PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
                obtenerPersonasDTO.getPersonas().add(mapper.personToPersonDTO(personaDtoOnePremiseUno.getPersona()));
               //Data cliente2
                pe.com.mibanco.base.bs.domain.model.Persona personaClient2 = new pe.com.mibanco.base.bs.domain.model.Persona();
                personaClient2.setName(onPremiseDosPort.getPersona2().getName());
                personaClient2.setId(onPremiseDosPort.getPersona2().getId());
                obtenerPersonasDTO.getPersonas().add(personaClient2);
                return obtenerPersonasDTO;
            }else{
                ObtenerPersonasDTO obtenerPersonasDTO = new ObtenerPersonasDTO();
                obtenerPersonasDTO.setCodigo(2);
                obtenerPersonasDTO.setTitle(onPremiseDosPort.getTitle());
                obtenerPersonasDTO.setMensaje(onPremiseDosPort.getMensaje());
                return obtenerPersonasDTO;
            }
        }else{
            ObtenerPersonasDTO obtenerPersonasDTO = new ObtenerPersonasDTO();
            obtenerPersonasDTO.setCodigo(1);
            obtenerPersonasDTO.setTitle(personaDtoOnePremiseUno.getTitle());
            obtenerPersonasDTO.setMensaje(personaDtoOnePremiseUno.getMensaje());
            return obtenerPersonasDTO;

        }

    }
}