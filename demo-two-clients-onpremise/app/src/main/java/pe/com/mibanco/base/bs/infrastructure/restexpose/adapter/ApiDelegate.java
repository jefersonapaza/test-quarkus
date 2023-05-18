package pe.com.mibanco.base.bs.infrastructure.restexpose.adapter;

import pe.com.mibanco.base.bs.server.model.BaseError;
import pe.com.mibanco.base.bs.server.model.PersonasInner;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import pe.com.mibanco.base.bs.application.PersonaService;
import pe.com.mibanco.base.bs.application.dto.ObtenerPersonasDTO;
import pe.com.mibanco.base.bs.server.api.OpenapiApi;

public class ApiDelegate implements OpenapiApi {

    @Inject
    PersonaService personaService;


    @Override
    public Response openapiListadoPost(String statusCode) {
        ObtenerPersonasDTO obtenerPersonasDTO = personaService.obtenerPersonas(statusCode);
        if(obtenerPersonasDTO.getCodigo()==0){
            List<PersonasInner> personsResponse = obtenerPersonasDTO.getPersonas().stream().map(persona -> {
                PersonasInner transaccionResponseListaTransaccionesInner = new PersonasInner();
                transaccionResponseListaTransaccionesInner.setId(persona.getId());
                transaccionResponseListaTransaccionesInner.setName(persona.getName());
                return transaccionResponseListaTransaccionesInner;
            }).collect(Collectors.toList());
            return Response.ok().entity(personsResponse).build()  ;
        }else{
            //aqui se puede validar con el codigo sea 1 o 2 de donde viene el
            //     codigo funcional y hacer algo con el usando el obtenerPersonasDTO.getCodigo() para la prueba simplemente se mostraran lo errores
            BaseError baseError=new BaseError();
            baseError.setTitle(obtenerPersonasDTO.getTitle());
            baseError.setDetail(obtenerPersonasDTO.getMensaje());
            return Response.status(202).entity(baseError).build()  ;
        }
    }
}
