package appGCI;

import tdas.*;

public class Perfil {
    
    private Integer codigo;
    private String descripcion;
    private Lista<Permiso> listaPermisos;
    
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

    public Lista<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(Lista<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public void ingresarPermiso(Permiso permiso) {
        if ( listaPermisos.buscar(permiso.getCodigo()) == null ) {
            Nodo<Permiso> nodoPermiso = new Nodo<Permiso>(permiso.getCodigo(), permiso);
            listaPermisos.insertar(nodoPermiso);
        }
    }
    
}
