package wis;

import tdas.*;

public class Perfil {

    public Integer id;
    public String descripcion;
    public Lista<Recurso> listaRecursos = new Lista<>();

    public Perfil(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
}
