package pe.com.mibanco.base.bs.infrastructure.restclient.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import pe.com.mibanco.base.bs.client.api.OnPremiseApi;
import pe.com.mibanco.base.bs.client.model.BaseError;
import pe.com.mibanco.base.bs.client.model.Persona;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseUno;
import pe.com.mibanco.base.bs.domain.spi.OnPremiseUnoPort;
import pe.com.mibanco.core.adapter.error.exception.ApiException;

@ApplicationScoped
public class OnPremiseUnoAdapter implements OnPremiseUnoPort {

    @RestClient
    OnPremiseApi onPremiseApi;


    @Override
    public PersonaDtoOnePremiseUno obtenerPersona(String prefer) {
        Response response;
        try {
            response = onPremiseApi.getPersonas(prefer);
        } catch (ClientWebApplicationException e) {
            //Aqui pueden especificar un poco mas usando
            e.getResponse().getStatus();//para obtenre el codigo htpp status
            e.getResponse().readEntity(String.class);//para obtenre el response y hacer validaciones
            throw ApiException.builder().code("MS-01").build();
        }
        if (response.getStatus() == 202) {
            BaseError baseError = response.readEntity(BaseError.class);
            if (baseError.getTitle().equalsIgnoreCase("error1")) {
                PersonaDtoOnePremiseUno personaDtoOnePremiseUno = new PersonaDtoOnePremiseUno();
                personaDtoOnePremiseUno.setCodigo(1);
                personaDtoOnePremiseUno.setTitle(baseError.getTitle());
                personaDtoOnePremiseUno.setMensaje(baseError.getDetail());
                return personaDtoOnePremiseUno;

            }
            if (baseError.getTitle().equalsIgnoreCase("error2")) {
                PersonaDtoOnePremiseUno personaDtoOnePremiseUno = new PersonaDtoOnePremiseUno();
                personaDtoOnePremiseUno.setCodigo(2);
                personaDtoOnePremiseUno.setTitle(baseError.getTitle());
                personaDtoOnePremiseUno.setMensaje(baseError.getDetail());
                return personaDtoOnePremiseUno;

            }
        }
        PersonaDtoOnePremiseUno personaDtoOnePremiseUno = new PersonaDtoOnePremiseUno();
        personaDtoOnePremiseUno.setCodigo(0);
        personaDtoOnePremiseUno.setMensaje("ok");
        Persona persona = response.readEntity(Persona.class);
        personaDtoOnePremiseUno.setPersona(persona);
        return personaDtoOnePremiseUno;
    }
}
