package appGCI;

import java.util.LinkedList;

public class Usuario {

    private String codigo;
    private String nombre;
    private LinkedList listaPerfiles;
    
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

    public LinkedList getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(LinkedList listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    
}
