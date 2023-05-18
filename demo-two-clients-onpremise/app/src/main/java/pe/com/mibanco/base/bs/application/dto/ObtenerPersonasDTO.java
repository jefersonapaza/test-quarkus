package pe.com.mibanco.base.bs.application.dto;


import pe.com.mibanco.base.bs.domain.model.Persona;
import java.util.ArrayList;
import java.util.List;


public class ObtenerPersonasDTO {
    private int codigo;
    private String mensaje;
    private String title;
    private List<Persona> personas=new ArrayList<>();

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

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
