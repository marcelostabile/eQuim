package appGCI;

import java.util.LinkedList;

public class Perfil {
    
    private Integer codigo;
    private String descripcion;
    private LinkedList<Permiso> listaPermisos;
    
    public Perfil(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(LinkedList<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public void ingresarPermiso(Permiso permiso) {
        if ( listaPermisos.contains(permiso) == false ) {
            listaPermisos.add(permiso);
        }
    }
    
}
