package appGCI;

import tdas.*;

public class Usuario {

    private String codigo;
    private String nombre;
    public Lista<Perfil> listaPerfiles = new Lista<>();
    
    public Usuario(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(Lista<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    
}
