package pe.com.mibanco.base.bs.domain.spi;


import jakarta.ws.rs.core.Response;

/* TODO: Borrar este comentario: esta intarface deberia de tener su implements en infraetructura
         y deberia de tener el subfijo Adapter e internamente en el adaptador llamar por ejemplo
         a tu base de datos o cualquier otro componente externo. Ejemplo de esta clase:
https://github.com/hirannor/spring-boot-hexagonal-architecture/blob/master/domain/src/main/java/eteosf/hexagonal/domain/spi/ProductPersistencePort.java

         Y este seria su implements su adaptador:
https://github.com/hirannor/spring-boot-hexagonal-architecture/blob/master/infrastructure/adapter-persistence-spring-data-jpa/src/main/java/eteosf/hexagonal/persistence/jpa/adapter/ProductSpringJpaAdapter.java

 */
/*
 * class interface port example
 */
public interface PersonaPort{
 public Response obtenerPersonas(String httpStatusCode);

}
