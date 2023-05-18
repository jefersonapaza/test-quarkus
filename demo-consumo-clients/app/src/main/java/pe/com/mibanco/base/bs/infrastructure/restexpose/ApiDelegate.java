package pe.com.mibanco.base.bs.infrastructure.restexpose;

import io.quarkus.logging.Log;
import jakarta.ws.rs.WebApplicationException;
import lombok.AllArgsConstructor;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import pe.com.mibanco.base.bs.client.model.BaseError;
import pe.com.mibanco.base.bs.client.model.Persona;
import pe.com.mibanco.base.bs.server.model.TransaccionResponseListaTransaccionesInner;

import java.util.ArrayList;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

import pe.com.mibanco.base.bs.domain.spi.PersonaPort;
import pe.com.mibanco.base.bs.server.api.OpenapiApi;
import pe.com.mibanco.base.bs.server.model.TransaccionResponseLista;
import pe.com.mibanco.core.adapter.error.exception.ApiException;
import pe.com.mibanco.core.adapter.error.resolver.model.HttpProblem;

@AllArgsConstructor
public class ApiDelegate implements OpenapiApi {


    private final PersonaPort personaPort;


    @Override
    public Response openapiListadoPost(String status, @Valid TransaccionResponseLista transaccionResponseLista) {
        Response response;
        try {
            status=status!=null&&!status.equalsIgnoreCase("")?status:"200";
            response = personaPort.obtenerPersonas("statusCode="+status);
        } catch (ClientWebApplicationException e) {
            //Entrara aqui cuando sean respuestas >=400
            Log.error("---", e);
            if (e.getResponse().getStatus() == 400) {
                //Asi puedes obtener el response body para hacer validaciones y lanzar errores personalizado o escalar el error
                Log.info(e.getResponse().readEntity(String.class));
                throw ApiException.builder().code("MS_01").build();
            }
            throw e;
        }
        if (response.getStatus() == 202) {
            BaseError baseErrorClient = response.readEntity(BaseError.class);
            pe.com.mibanco.base.bs.server.model.BaseError baseError = new pe.com.mibanco.base.bs.server.model.BaseError();
            baseError.setTitle(baseErrorClient.getTitle());
            baseError.setDetail(baseErrorClient.getDetail());
            return Response.status(202).entity(baseError).build();
        }
        Persona personaRestClient = response.readEntity(Persona.class);
        TransaccionResponseLista transaccionResponseLista1 = new TransaccionResponseLista();
        transaccionResponseLista1.setTitle("");
        transaccionResponseLista1.setDetail("");
        transaccionResponseLista1.setType("");
        transaccionResponseLista1.setTransacciones(new ArrayList<TransaccionResponseListaTransaccionesInner>());
        TransaccionResponseListaTransaccionesInner transaccionResponseListaTransaccionesInner = new TransaccionResponseListaTransaccionesInner();
        transaccionResponseListaTransaccionesInner.setId(personaRestClient.getId());
        transaccionResponseListaTransaccionesInner.setName(personaRestClient.getName());
        transaccionResponseLista1.getTransacciones().add(transaccionResponseListaTransaccionesInner);
        return Response.status(200).entity(transaccionResponseLista1).build();
    }
}
