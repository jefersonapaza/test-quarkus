package pe.com.mibanco.base.bs.infrastructure.restexpose;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import pe.com.mibanco.extensions.web.error.runtime.WebServerHttpProblemExceptionHandler;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<Exception> {

    @Inject
    WebServerHttpProblemExceptionHandler webServerExceptionHandler;

    @Override
    public Response toResponse(Exception e) {
        return webServerExceptionHandler.toResponse(e);
    }

}
