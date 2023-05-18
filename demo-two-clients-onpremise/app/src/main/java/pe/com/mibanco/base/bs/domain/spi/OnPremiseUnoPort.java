package pe.com.mibanco.base.bs.domain.spi;

import pe.com.mibanco.base.bs.domain.dto.PersonaDtoOnePremiseUno;

/*
 * class interface port example
 */
public interface OnPremiseUnoPort {

    public PersonaDtoOnePremiseUno obtenerPersona(String prefer);

}
