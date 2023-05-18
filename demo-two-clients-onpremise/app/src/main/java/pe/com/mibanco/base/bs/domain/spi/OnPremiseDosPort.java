package pe.com.mibanco.base.bs.domain.spi;

import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseDos;

/*
 * class interface port example
 */
public interface OnPremiseDosPort {

    public PersonaDtoOnePremiseDos obtenerPersona(String prefer);

}
