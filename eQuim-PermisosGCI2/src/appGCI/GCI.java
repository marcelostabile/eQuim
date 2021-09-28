package appGCI;

import java.util.*;

import archivos.*;
import tdas.*;

public class GCI {
    
    static String rutaEntrada = "src\\entrada\\";

    static String gciUsuarios = "CltaGCI_1_Usuarios.xlsx";
    static String gciTareas = "CltaGCI_2_Tareas.xlsx";
    static String gciTransacciones = "CltaGCI_3_Transacciones.xlsx";
    static String gciFranjas = "CltaGCI_4_Franjas.xlsx";

    /**
     * Seguridad GCI.
     */
    public static void armarReporteDePermisos() {

        // cargar lista de permisos.
        Lista<Permiso> basePermisos = new Lista<>();
        crearListaPermisos(rutaEntrada, gciTareas, gciTransacciones, basePermisos);

        // // crear lista de usuarios.
        Lista<Usuario> baseUsuarios = new Lista<>();
        crearListaUsuarios(rutaEntrada, gciUsuarios, baseUsuarios, basePermisos);

        // Imprimir lista.
        imprimirBaseUsuarios(baseUsuarios);
    }

    /**
     * Crear lista de permisos.
     * @param rutaEntrada
     * @param archivoTareas
     * @param archivoTransa
     * @param basePermisos
     */
    private static void crearListaPermisos(String rutaEntrada, String archivoTareas, String archivoTransa, Lista<Permiso> basePermisos) {

        // Leer excel.
        String[] lstTareas = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, archivoTareas);
        String[] lstTransa = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, archivoTransa);

        // Permisos a partir de tareas.
        Integer campos = 5;                                 // CodigoTarea, __, Descripcion, CodSeguridad, __
        for (String linea : lstTareas) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                // Creamos el nuevo permiso tipo tarea.
                Permiso permiso = new Permiso(reg[0], reg[2], reg[3], "");
                // Ingreso el permiso en la lista.
                basePermisos.insertar(new Nodo<Permiso>(reg[3], permiso));
            }
        }
        int cantidad = basePermisos.cantElementos();
        System.out.println("Permisos de tareas cargados: " + cantidad + "/" + lstTareas.length);

        // Permisos a partir de transacciones.
        for (String linea: lstTransa) {
            String[] reg = linea.split(";");
            // Permiso transa.
            Permiso permiso = new Permiso(reg[1], reg[2], reg[3], reg[0]);
            // Ingreso en la lista permisos.
            basePermisos.insertar(new Nodo<Permiso>(reg[3], permiso));
        }
        System.out.println("Permisos de transacciones cargados: " + (basePermisos.cantElementos()-cantidad) + "/" + lstTransa.length);
    }


    /**
     * Crear lista de usuarios.
     * @param rutaEntrada
     * @param archivoUsuarios
     * @param baseUsuarios
     * @param basePermisos
    */
    public static void crearListaUsuarios(String rutaEntrada, String archivoUsuarios, Lista<Usuario> baseUsuarios, Lista<Permiso> basePermisos) {

        // Leer excel.
        String[] lstUsuarios = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, archivoUsuarios);

        // En cada linea tenemos: usuario (id, nom) perfil (id, nom) permisos (string ABC123*)
        Integer campos = 5;
        for (String linea : lstUsuarios) { 
            String[] reg = linea.split(";");                // ID_USUARIO; NOMBRE_USUARIO; ID_PERFIL; NOMBRE_PERFIL; PERMISOS
            if (reg.length == campos && reg[0] != null) { 

                // Usuario.
                Usuario usuario = new Usuario(reg[0], reg[1]);
                if ( baseUsuarios.buscar(usuario.getCodigo()) == null) {
                    baseUsuarios.insertar(new Nodo<Usuario>(usuario.getCodigo(), usuario));
                } else {
                    usuario = baseUsuarios.buscar(usuario.getCodigo()).getDato();
                }

                // Perfil.
                Perfil perfil = new Perfil(Integer.parseInt(reg[2]), reg[3]);
                if ( usuario.listaPerfiles.buscar(perfil.getCodigo()) == null ) {
                    usuario.listaPerfiles.insertar(new Nodo<Perfil>(perfil.getCodigo(), perfil));
                } else {
                    perfil = usuario.listaPerfiles.buscar(perfil.getCodigo()).getDato();
                }

                // Tipo de permiso según el perfil.
                String tipo = "";
                switch (perfil.getCodigo()) 
                {
                    case 10:
                        tipo = "Tarea";
                        break;

                    case 60: case 90: case 95: case 97: case 98:
                        tipo = "Transacción";
                        break;

                    default:
                        tipo = "";
                        break;
                }

                // Si la cadena es vacía se cargan todos los permisos "blancos".
                if ( reg[4].isEmpty() ) {
                    // cargar todos los permisos blancos.
                    Nodo<Permiso> nodoActual = basePermisos.getPrimero();
                    while (nodoActual != null) {
                        if ( nodoActual.getDato().getClaveSeguridad() == "" ) { 
                            Permiso nodoPermiso = nodoActual.getDato();
                            Permiso permiso = new Permiso(  nodoPermiso.getCodigo(), 
                                                            nodoPermiso.getDescripcion(), 
                                                            nodoPermiso.getClaveSeguridad(), 
                                                            nodoPermiso.getEmpresa() );
                            perfil.agregarPermiso(permiso);
                        }
                        // Siguiente.
                        nodoActual = nodoActual.getSiguiente();
                    }
                }

                // Si la cadena tiene * se cargan todos los permisos de tipo Tarea o Transa.
                if ( reg[4].contains("*") ) {
                    // cargar todos los permisos.
                    Permiso permiso = new Permiso(perfil.getCodigo().toString(), perfil.getDescripcion(), "*", "");
                    perfil.agregarPermiso(permiso);
                } 
                else {      
                    // Buscar los permisos con igual letra de seguridad.
                    char[] charPermisos = reg[4].toCharArray();      // permisos (cadena ABC123*)
                    for (char c : charPermisos) { 
                        // Cargar todos los registros con esa letra y tipo.
                        Nodo<Permiso> nodoActual = basePermisos.getPrimero();
                        while (nodoActual != null) {
                            if ( nodoActual.getDato().getClaveSeguridad() == Character.toString(c) ) { 
                                Permiso nodoPermiso = nodoActual.getDato();
                                Permiso permiso = new Permiso(  nodoPermiso.getCodigo(), 
                                                                nodoPermiso.getDescripcion(), 
                                                                nodoPermiso.getClaveSeguridad(), 
                                                                nodoPermiso.getEmpresa() );
                                perfil.agregarPermiso(permiso);
                            }
                            // Siguiente.
                            nodoActual = nodoActual.getSiguiente();
                        }
                    }
                }
            }
        }
    }

    private static void imprimirBaseUsuarios(Lista<Usuario> baseUsuarios) { 

        ArrayList<String> salidaBase = new ArrayList<>();

        // Inicio de la lista de usuarios.
        Nodo<Usuario> nodoUsuario = baseUsuarios.getPrimero();
        // Recorrer los usuarios.
        while (nodoUsuario != null) {
            // Usuario.
            Usuario usuario = nodoUsuario.getDato();
            String txtUsuario = usuario.getCodigo() + ";" + usuario.getNombre();
            // Inicio de la lista de perfiles.
            Nodo<Perfil> nodoPerfil = usuario.listaPerfiles.getPrimero();
            // Recorrer los perfiles.
            while (nodoPerfil != null) { 
                // Perfil.
                Perfil perfil = nodoPerfil.getDato();
                String txtPerfil = perfil.getCodigo() + ";" + perfil.getDescripcion();
                // Inicio de la lista de permisos.
                Nodo<Permiso> nodoPermiso = perfil.getListaPermisos().getPrimero();
                // Recorrer los permisos.
                while (nodoPermiso != null) {
                    // Permiso.
                    Permiso permiso = nodoPermiso.getDato();
                    String txtPermiso = permiso.getCodigo() + ";" + permiso.getDescripcion() + ";" + 
                                        permiso.getClaveSeguridad() + ";" + permiso.getTipo() + ";" + 
                                        permiso.getEmpresa();
                    // siguiente.
                    nodoPermiso = nodoPermiso.getSiguiente();

                    // guardar los datos.
                    String txt = txtUsuario + ";" + txtPerfil + ";" + txtPermiso;
                    salidaBase.add(txt);
                }
                // siguiente.
                nodoPerfil = nodoPerfil.getSiguiente();
            }
        // siguiente;
        nodoUsuario = nodoUsuario.getSiguiente();
        }
        // salida
        ManejadorArchivosGenerico.escribirArchivo(rutaEntrada + "salida.txt", salidaBase.toArray(new String[0]));
    }

}


//         /**
//         // Listar usuarios.
//         for (String[] reg : arrUsuarios) {
//             Usuario usuario = new Usuario(reg[0], reg[1]);
//             listaUsuarios.addToTail(usuario);
//         }
//         System.out.println("Lista Usuarios: " + listaUsuarios.size());

//         // listar permisos de tareas y transacciones.
//         for (String[] reg : arrTareas) {
//             Permiso tarea = new Permiso(reg[0], reg[1], reg[2], reg[4]);
//             listaPermisos.addToTail(tarea);
//         }
//         System.out.println("Lista Permisos: " + listaPermisos.size());

//         for (String[] reg : arrTransacciones) {
//             Permiso transaccion = new Permiso(reg[0], reg[1], reg[2], reg[4]);
//             listaPermisos.addToTail(transaccion);
//         }
//         System.out.println("Lista Permisos: " + listaPermisos.size());
//         */
