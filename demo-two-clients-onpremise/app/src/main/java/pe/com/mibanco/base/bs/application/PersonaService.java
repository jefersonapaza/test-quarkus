package pe.com.mibanco.base.bs.application;


import pe.com.mibanco.base.bs.application.dto.ObtenerPersonasDTO;

/*
 * class service example
 */
public interface PersonaService {

    public ObtenerPersonasDTO obtenerPersonas(String ref);

}