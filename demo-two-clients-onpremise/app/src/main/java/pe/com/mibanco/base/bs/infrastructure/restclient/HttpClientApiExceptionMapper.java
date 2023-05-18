package pe.com.mibanco.base.bs.infrastructure.restclient;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import pe.com.mibanco.httpclient.exception.HttpClientApiHandler;

@Provider
public class HttpClientApiExceptionMapper implements ResponseExceptionMapper<Throwable> {

    @Override
    public Throwable toThrowable(Response response) {
        return HttpClientApiHandler.toThrowable(response);
    }

}
