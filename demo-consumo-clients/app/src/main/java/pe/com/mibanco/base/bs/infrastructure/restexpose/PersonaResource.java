package pe.com.mibanco.base.bs.infrastructure.restexpose;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.com.mibanco.base.bs.application.PersonaService;


@Path("/v1/Persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaResource {

    @Inject
    PersonaService service;    

    @GET
    @Produces(MediaType.TEXT_PLAIN)    
    public Response replace() {
        return Response.ok("Hello world!!").build();
    }

}