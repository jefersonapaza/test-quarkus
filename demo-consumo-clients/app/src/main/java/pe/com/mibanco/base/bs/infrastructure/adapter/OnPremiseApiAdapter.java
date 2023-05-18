package pe.com.mibanco.base.bs.infrastructure.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.com.mibanco.base.bs.client.api.OnPremiseApi;
import pe.com.mibanco.base.bs.domain.spi.PersonaPort;

@ApplicationScoped
public class OnPremiseApiAdapter implements PersonaPort {

    @RestClient
    OnPremiseApi onPremiseApi;


    @Override
    public Response obtenerPersonas(String httpStatusCode) {
        return onPremiseApi.getPersonas(httpStatusCode);
    }

}
