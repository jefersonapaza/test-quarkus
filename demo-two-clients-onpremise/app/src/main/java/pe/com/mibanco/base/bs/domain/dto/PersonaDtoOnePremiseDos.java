package pe.com.mibanco.base.bs.domain.dto;

import pe.com.mibanco.base.bs.client.model.Persona2;

public class PersonaDtoOnePremiseDos {
    private int codigo;
    private String mensaje;
    private String title;
    private Persona2 persona2;

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

    public Persona2 getPersona2() {
        return persona2;
    }

    public void setPersona2(Persona2 persona2) {
        this.persona2 = persona2;
    }
}
