import javax.swing.event.InternalFrameAdapter;

import java.util.ArrayList;
import archivos.ManejadorArchivosGenerico;
import tdas.*;
import tdas2.*;
import wis.*;

public class main {

    public static void main(String[] args) throws Exception {

        String rutaEntrada = "src/entrada/";

        // Cargar usuarios.
        Lista<Usuario> listaUsuarios = new Lista<>();
        cargarUsuarios(rutaEntrada, listaUsuarios);

        // Cargar perfiles.
        Lista<Perfil> listaPerfiles = new Lista<>();
        cargarPerfiles(rutaEntrada, listaPerfiles);

        // Cargar recursos.
        Lista<Recurso> listaRecursos = new Lista<>();
        cargarRecursos(rutaEntrada, listaRecursos);

        // Cargar recursos en los perfiles.
        cargarRecursosPorPerfil(rutaEntrada, listaPerfiles, listaRecursos);

        // Cargar perfiles en los usuarios.
        cargarPerfilesPorUsuario(rutaEntrada, listaPerfiles, listaUsuarios);

        // Cargar perfiles personales en los usuarios.

        // Salida de resultados.
        String[] salida = listarRecursos(listaUsuarios, listaPerfiles, listaRecursos);

        ManejadorArchivosGenerico.escribirArchivo(rutaEntrada + "resultados.txt", salida);
        
    }

    /**
     * Cargar los usuarios.
     * @param rutaEntrada
     * @param listaUsuarios
     */
    private static void cargarUsuarios(String rutaEntrada, Lista<Usuario> listaUsuarios) {

        // Cargar usuarios.
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(rutaEntrada + "base_usuarios.csv");
        Integer campos = 3;   // ID_USUARIO;NOMBRE_USUARIO;NOMBRE_COMPLETO

        for (String linea : archivo) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                Usuario usuario = new Usuario(Integer.parseInt(reg[0]), reg[1].toString(), reg[1].toString());
                Nodo<Usuario> nodoUsuario = new Nodo<Usuario>(Integer.parseInt(reg[0]), usuario);
                if (listaUsuarios.buscar(nodoUsuario.getEtiqueta()) == null) {
                    listaUsuarios.insertar(nodoUsuario);
                }
            }
        }
        System.out.println("Registros: " + listaUsuarios.cantElementos());
    }

    /**
     * Cargar los perfiles.
     * @param rutaEntrada
     * @param listaPerfiles
     */
    private static void cargarPerfiles(String rutaEntrada, Lista<Perfil> listaPerfiles) {

        // Cargar Perfiles.
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(rutaEntrada + "base_perfiles.csv");
        Integer campos = 2;   // PERFIL;DESCRIPCION

        for (String linea : archivo) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                Perfil perfil = new Perfil(Integer.parseInt(reg[0]), reg[1].toString());
                Nodo<Perfil> nodoPerfil = new Nodo<Perfil>(Integer.parseInt(reg[0]), perfil);
                if (listaPerfiles.buscar(nodoPerfil.getEtiqueta()) == null) {
                    listaPerfiles.insertar(nodoPerfil);
                }
            }
        }
        System.out.println("Registros: " + listaPerfiles.cantElementos());
    }

    /**
     * Cargar los recursos.
     * @param rutaEntrada
     * @param listaRecursos
     */
    private static void cargarRecursos(String rutaEntrada, Lista<Recurso> listaRecursos) {

        // Cargar Perfiles.
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(rutaEntrada + "base_recursos_por_perfil.csv");
        Integer campos = 8;   // RECURSO;APLICACION;SECCION;TIPO;NOMBRE;TIPO_USUARIO;A

        for (String linea : archivo) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                Recurso recurso = new Recurso(Integer.parseInt(reg[0]), reg[1], reg[2], reg[3], reg[4], reg[5], reg[6], reg[7]);
                Nodo<Recurso> nodoRecurso = new Nodo<Recurso>(Integer.parseInt(reg[0]), recurso);
                if (listaRecursos.buscar(nodoRecurso.getEtiqueta()) == null) {
                    listaRecursos.insertar(nodoRecurso);
                }
            }
        }
        System.out.println("Registros: " + listaRecursos.cantElementos());
    }


    /**
     * Cargar recursos en los perfiles.
     * @param rutaEntrada
     */
    private static void cargarRecursosPorPerfil(String rutaEntrada, Lista<Perfil> listaPerfiles, Lista<Recurso> listaRecursos) {

        // Cargar recursos por perfil.
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(rutaEntrada + "base_recursos2.csv");
        Integer campos = 10;   // PERFIL;DESC_PERFIL;RECURSO;;APLICACION;SECCION;TIPO;NOMBRE;TIPO_USUARIO;A
        Integer contador = 0;

        for (String linea : archivo) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                // código del perfil.
                Integer codigoPerfil = Integer.parseInt(reg[0]);
                // código del recurso.
                Integer codigoRecurso = Integer.parseInt(reg[2]);
                // Obtengo los elementos.
                Perfil perfil = listaPerfiles.buscar(codigoPerfil).getDato();
                Nodo<Recurso> nodoRecurso = listaRecursos.buscar(codigoRecurso).clonar();
                // Inserto el recuros en el perfil.
                perfil.listaRecursos.insertar(nodoRecurso);

                contador ++;
                System.out.println(contador);
            }
        }
        System.out.println("Registros: " + contador);
    }


    /**
     * 
     * @param rutaEntrada
     * @param listaPerfiles
     * @param listaUsuarios
     */
    private static void cargarPerfilesPorUsuario(String rutaEntrada, Lista<Perfil> listaPerfiles, Lista<Usuario> listaUsuarios) {

        // Cargar perfiles en los usuarios.
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(rutaEntrada + "base_perfiles_por_usuario.csv");
        Integer campos = 5;   // ID_USUARIO;NOMBRE_USUARIO;NOMBRE_COMPLETO;PERFIL;DESCRIPCION
        Integer contador = 0;

        for (String linea : archivo) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                // código del usuario.
                Integer codigoUsuario = Integer.parseInt(reg[0]);
                // código del perfil.
                Integer codigoPerfil = Integer.parseInt(reg[3]);
                // Obtengo los elementos.
                Usuario usuario = listaUsuarios.buscar(codigoUsuario).getDato();
                Nodo<Perfil> nodoPerfil = listaPerfiles.buscar(codigoPerfil).clonar();
                // Inserto el perfil en el usuario.
                usuario.listaPerfiles.insertar(nodoPerfil);
                
                contador ++;
                System.out.println(contador);
            }
        }
        System.out.println("Registros: " + contador);
    }  

    /**
     * Listar los resultados.
     * @param listaUsuarios
     * @param listaPerfiles
     * @param listaRecursos
     * @return
     */
    public static String[] listarRecursos(Lista<Usuario> listaUsuarios, Lista<Perfil> listaPerfiles, Lista<Recurso> listaRecursos) {

        ArrayList <String> resultado = new ArrayList<>();

        // Recorro la lista de los usuarios.
        Nodo<Usuario> nodoUsuario = listaUsuarios.getPrimero();
        while (nodoUsuario != null) {
            // Datos del usuario.
            String auxUsuario = nodoUsuario.getDato().id + ";" + nodoUsuario.getDato().nombre;
            // recorro la lista de perfiles.
            Nodo<Perfil> nodoPerfil = nodoUsuario.getDato().listaPerfiles.getPrimero();
            while (nodoPerfil != null) {
                // datos del perfil.
                String auxPerfil = auxUsuario + ";" + nodoPerfil.getDato().id + ";" + nodoPerfil.getDato().descripcion;
                // recorro la lista de recursos.
                Nodo<Recurso> nodoRecurso = nodoPerfil.getDato().listaRecursos.getPrimero();
                while (nodoRecurso != null) {
                    // datos del recurso.
                    String auxRecurso = auxPerfil + ";" + nodoRecurso.getDato().misDatos(";");
                    // guardar resultado.
                    resultado.add(auxRecurso);
                    // siguiente.
                    nodoRecurso = nodoRecurso.getSiguiente();
                }
                // siguiente.
                nodoPerfil = nodoPerfil.getSiguiente();
            }
            // siguiente.
            nodoUsuario = nodoUsuario.getSiguiente();
        }

        // retornar resultados.
        return resultado.toArray(new String[0]);
    }

}
