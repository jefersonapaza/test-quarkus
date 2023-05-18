package pe.com.mibanco.base.bs.infrastructure.restclient.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import pe.com.mibanco.base.bs.client.api.OnPremise2Api;
import pe.com.mibanco.base.bs.client.model.BaseError;
import pe.com.mibanco.base.bs.client.model.Persona2;
import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseDos;
import pe.com.mibanco.base.bs.domain.spi.OnPremiseDosPort;
import pe.com.mibanco.core.adapter.error.exception.ApiException;

@ApplicationScoped
public class OnPremiseDosAdapter implements OnPremiseDosPort {

    @RestClient
    OnPremise2Api onPremise2Api;

    @Override
    public PersonaDtoOnePremiseDos obtenerPersona(String prefer) {
        Response response;
        try {
            response = onPremise2Api.getPersonas(prefer);
        } catch (ClientWebApplicationException e) {
            //Aqui pueden especificar un poco mas usando
            e.getResponse().getStatus();//para obtenre el codigo htpp status
            e.getResponse().readEntity(String.class);//para obtenre el response y hacer validaciones
            throw ApiException.builder().code("MS-02").build();
        }
        if (response.getStatus() == 202) {
            BaseError baseError = response.readEntity(BaseError.class);
            if (baseError.getTitle().equalsIgnoreCase("error1")) {
                PersonaDtoOnePremiseDos personaDtoOnePremiseUno = new PersonaDtoOnePremiseDos();
                personaDtoOnePremiseUno.setCodigo(1);
                personaDtoOnePremiseUno.setTitle(baseError.getTitle());
                personaDtoOnePremiseUno.setMensaje(baseError.getDetail());
                return personaDtoOnePremiseUno;

            }
            if (baseError.getTitle().equalsIgnoreCase("error2")) {
                PersonaDtoOnePremiseDos personaDtoOnePremiseUno = new PersonaDtoOnePremiseDos();
                personaDtoOnePremiseUno.setCodigo(2);
                personaDtoOnePremiseUno.setTitle(baseError.getTitle());
                personaDtoOnePremiseUno.setMensaje(baseError.getDetail());
                return personaDtoOnePremiseUno;

            }
        }
        PersonaDtoOnePremiseDos personaDtoOnePremiseUno = new PersonaDtoOnePremiseDos();
        personaDtoOnePremiseUno.setCodigo(0);
        personaDtoOnePremiseUno.setMensaje("ok");
        Persona2 persona = response.readEntity(Persona2.class);
        personaDtoOnePremiseUno.setPersona2(persona);
        return personaDtoOnePremiseUno;
    }
}
