package pe.com.mibanco.base.bs.domain.dto;

import pe.com.mibanco.base.bs.client.model.Persona;

public class PersonaDtoOnePremiseUno {

    private int codigo;
    private String mensaje;
    private String title;
    private Persona persona;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
