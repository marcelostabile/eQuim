package wis;

import java.util.ArrayList;

import tdas.*;

public class Usuario {
    
    public Integer id;
    public String nombreUsuario;
    public String nombre;
    public Lista<Perfil> listaPerfiles = new Lista<>();

    public Usuario(Integer id, String nombreUsuario, String nombre) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
    }

}
