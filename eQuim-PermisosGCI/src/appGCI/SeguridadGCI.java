package appGCI;

import archivos.*;
import tdas.*;

public class SeguridadGCI {
    
    static String rutaEntrada = "src\\entrada\\";

    static String gciUsuarios = "CltaGCI_1_Usuarios.xlsx";
    static String gciTareas = "CltaGCI_2_Tareas.xlsx";
    static String gciTransacciones = "CltaGCI_3_Transacciones.xlsx";
    static String gciFranjas = "CltaGCI_4_Franjas.xlsx";

    /**
     * Seguridad GCI.
     */
    public static void armarReporteDePermisos() {

        // crear lista de permisos.
        Lista<Permiso> basePermisos = new Lista<>();
        crearListaPermisos(rutaEntrada, gciTareas, gciTransacciones, basePermisos);

        // crear lista de usuarios.
        Lista<Usuario> baseUsuarios = new Lista<>();
        crearListaUsuarios(rutaEntrada, gciUsuarios, baseUsuarios, basePermisos);
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
                String A = permiso.getTipo() + "-" + permiso.getClaveSeguridad();
                System.out.println(A);
                basePermisos.insertar(new Nodo<Permiso>(A, permiso));
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
            basePermisos.insertar(new Nodo<Permiso>(permiso.getTipo() + "-" + permiso.getClaveSeguridad(), permiso));
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

        // Procesar las lineas del excel.

        // En cada linea tenemos: usuario (id, nom) perfil (id, nom) permisos (string ABC123*)
        Integer campos = 5; 
        for (String linea : lstUsuarios) { 
            System.out.println(linea); 
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

                // Permisos.
                char[] regPermisos = reg[4].toCharArray();      // permisos (cadena ABC123*)
                for (char c : regPermisos) {

                    // Creamos el permiso a partir de una letra.
                    Permiso permiso = new Permiso(perfil.getCodigo().toString(), "", String.valueOf(c), "");
                    permiso.setDescripcion(basePermisos.buscar(permiso.getTipo() + "-" + permiso.getClaveSeguridad()).getDato().getDescripcion());
                    
                    perfil.agregarPermiso(permiso);
                }
            }
        }
        //System.out.println("Permisos de transacciones cargados: " + (basePermisos.cantElementos()-cantidad) + "/" + lstTransa.length);

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
