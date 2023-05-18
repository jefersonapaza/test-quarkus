package pe.com.mibanco.base.bs.infrastructure.restexpose;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import pe.com.mibanco.extensions.auditoria.runtime.filters.AuditoriaFilterLogging;

@Provider
@ApplicationScoped
public class AuditoriaFilter implements ContainerResponseFilter {

    @Inject
    AuditoriaFilterLogging auditoriaFilterLogging;

    @RouteFilter(100)
    void request(RoutingContext rc) {
        auditoriaFilterLogging.request(rc);
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
        auditoriaFilterLogging.response(requestContext, responseContext);
    }

}
