package pe.com.mibanco.base.bs.infrastructure.restexpose.mock;

import io.quarkus.test.Mock;
import io.vertx.ext.web.RoutingContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import pe.com.mibanco.extensions.auditoria.runtime.filters.AuditoriaFilterLogging;
import java.io.IOException;

@Mock
public class AuditoriaFilterMock implements AuditoriaFilterLogging {


    @Override
    public void request(RoutingContext rc) {
        rc.next();
    }

    @Override
    public void response(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

    }
}
