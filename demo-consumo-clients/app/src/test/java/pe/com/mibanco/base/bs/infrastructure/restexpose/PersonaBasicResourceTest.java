package pe.com.mibanco.base.bs.infrastructure.restexpose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

public class PersonaBasicResourceTest {

    @InjectMocks
    PersonaResource resource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHelloEndpoint() {
        Assertions.assertEquals("Hello world!!",resource.replace().readEntity(String.class));
    }

}