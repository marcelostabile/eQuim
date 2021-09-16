package appGCI;

import java.util.LinkedList;
import archivos.ManejadorArchivosExcel;
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
    public static void armarReportePermisos() {

        // crear lista de permisos.
        Lista<Permiso> basePermisos = new Lista<>();
        crearListaPermisos(rutaEntrada, gciTareas, gciTransacciones, basePermisos);

        // crear lista de usuarios.
        LinkedList<Usuario> baseUsuarios = new LinkedList<>();
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
        Integer campos = 5;                     // CodigoTarea, __, Descripcion, CodSeguridad, __
        for (String linea : lstTareas) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                // Permiso tarea.
                Permiso permiso = new Permiso("TA", reg[0], reg[2], reg[3]);
                // Nodo permiso.
                String etiqueta = permiso.getTipo() + "-" + permiso.getCodSeguridad();
                Nodo<Permiso> nodoPermiso = new Nodo<Permiso>(etiqueta, permiso);
                // Ingreso en la lista permisos.
                basePermisos.insertar(nodoPermiso);
            }
        }
        int cantidad = basePermisos.cantElementos();
        System.out.println("Permisos de tareas cargados: " + cantidad + "/" + lstTareas.length);

        // Permisos a partir de transacciones.
        campos = 45;
        for (String linea: lstTransa) {

            String[] reg = linea.split(";");
            if (reg.length == campos) {
                // Permiso transa.
                Permiso permiso = new Permiso("TR", reg[1], reg[2], reg[3]);
                // Nodo permiso.
                String etiqueta = permiso.getTipo() + "-" + permiso.getCodSeguridad();
                Nodo<Permiso> nodoPermiso = new Nodo<Permiso>(etiqueta, permiso);
                // Ingreso en la lista permisos.
                basePermisos.insertar(nodoPermiso);
            }
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
    public static void crearListaUsuarios(String rutaEntrada, String archivoUsuarios, LinkedList<Usuario> baseUsuarios, Lista<Permiso> basePermisos) {

        /**
         * Para cada linea
         * creamos un usuario
         * creamos un perfil
         * creamos varios permisos
         * ingresamos los permisos en los perfiles del usuario
         * 
         */

        // Leer excel.
        String[] lstUsuarios = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, archivoUsuarios);

        // Procesar las lineas del excel.
        // En cada linea tenemos: usuario (id, nom) perfil (id, nom) permisos (string ABC123*)
        Integer campos = 5;                     
        for (String linea : lstUsuarios) { 
            String[] reg = linea.split(";");    // ID_USUARIO; NOMBRE_USUARIO; ID_PERFIL; NOMBRE_PERFIL; PERMISOS
            if (reg.length == campos) { 

                // Usuario.
                if (!baseUsuarios.contains(new Usuario(reg[0], reg[1]))) {
                    Usuario usuario = new Usuario(reg[0], reg[1]);
                    baseUsuarios.add(usuario);
                } else {
                    Usuario usuario = baseUsuarios.get(reg[0]);
                }

                // Perfil.
                Perfil perfil = new Perfil(Integer.parseInt(reg[2]), reg[3]);

                // Permisos.
                char[] regPermisos = reg[4].toCharArray();   // permisos (cadena ABC123*)
                for (char c : regPermisos) {

                    // Creamos el permiso a partir de una letra.
                    Permiso permiso = new Permiso(reg[2], String.valueOf(c), "", String.valueOf(c));
                    String descri = basePermisos.buscar(permiso.getTipo() + "-" + permiso.getCodSeguridad()).getDato().getDescripcion();
                    permiso.setDescripcion(descri);

                    // Verificamos si existe en la lista de permisos del usuario.
                    if (usuario.listaPerfiles.contains(perfil)) {
                        usuario.listaPerfiles.get(perfil.getCodigo()).ingresarPermiso(permiso);
                    } else {
                        perfil.ingresarPermiso(permiso);
                        usuario.listaPerfiles.add(perfil);
                    }
                }



    }







            }
        }
    }





                // Agregar lista de perfiles con permisos.

                // Nodo usuario.
                Nodo<Usuario> nodoUsuario = new Nodo<Usuario>(reg[0], usuario);
                // Ingresamos el usuario o si existe agregamos el nuevo perfil.
                Nodo<Usuario> nodoAux = listaUsuarios.buscar(nodoUsuario.getEtiqueta());
                if (nodoAux == null) {
                    listaUsuarios.insertar(nodoUsuario);
                } else {
                    nodoAux.getDato().listaPerfiles.add(nodoUsuario.getDato().listaPerfiles.getFirst());
                }
            }
        }



        System.out.println("Permisos de transacciones cargados: " + (listaPermisos.cantElementos()-cantidad) + "/" + lstTransa.length);
    }





        // Cargar usuarios.
        String[] lstUsuarios = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, archivoEntrada);
        Integer campos = 5;   // ID_USUARIO; NOMBRE_USUARIO; ID_PERFIL; NOMBRE_PERFIL; PERMISOS

        for (String linea : lstUsuarios) {
            String[] reg = linea.split(";");
            if (reg.length == campos) { 
                // Usuario.
                Usuario usuario = new Usuario(reg[0], reg[1]);
                // Nodo usuario.
                Nodo<Usuario> nodoUsuario = new Nodo<Usuario>(reg[0], usuario);
                // Ingresamos el usuario o si existe agregamos sus permisos.
                Nodo<Usuario> nodoAux = listaUsuarios.buscar(nodoUsuario.getEtiqueta());

                if (listaUsuarios.buscar(nodoUsuario.getEtiqueta()) == null) {
                    listaUsuarios.insertar(nodoUsuario);
                }
            }
        }
        System.out.println("Registros: " + listaUsuarios.cantElementos());
    }

}

//         // Leer los archivos excel.
        
//         String[] lstTareas = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, gciTareas);
//         String[] lstTransacciones = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, gciTransacciones);
//         String[] lstFranjas = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, gciFranjas);

//         System.out.println("Usuarios: " + lstUsuarios.length);
//         System.out.println("Tareas: " + lstTareas.length);
//         System.out.println("Transacciones: " + lstTransacciones.length);
//         System.out.println("Franjas: " + lstFranjas.length);



//         // GCI

//         // Cargar usuarios.
//         Lista<Usuario> listaUsuarios = new Lista<>();
//         cargarUsuarios(rutaEntrada, gciUsuarios, listaUsuarios);

//         // // Cargar perfiles.
//         // Lista<Perfil> listaPerfiles = new Lista<>();
//         // cargarPerfiles(rutaEntrada, gciUsuarios, listaPerfiles);



//         // Cargar recursos en los perfiles.
//         cargarRecursosPorPerfil(rutaEntrada, listaPerfiles, listaRecursos);

//         // Cargar perfiles en los usuarios.
//         cargarPerfilesPorUsuario(rutaEntrada, listaPerfiles, listaUsuarios);

//         // Cargar perfiles personales en los usuarios.

//         // Salida de resultados.
//         String[] salida = listarRecursos(listaUsuarios, listaPerfiles, listaRecursos);

//         ManejadorArchivosGenerico.escribirArchivo(rutaEntrada + "resultados.txt", salida);


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

        
//     }

//     /**
//      * Crear una lista de usuarios.
//      * @param rutaEntrada
//      * @param archivoEntrada
//      * @param listaUsuarios
//      */
//     private static void cargarUsuarios(String rutaEntrada, String archivoEntrada, Lista<Usuario> listaUsuarios) {

//         // Cargar usuarios.
//         String[] lstUsuarios = ManejadorArchivosExcel.leerArchivoExcel(rutaEntrada, archivoEntrada);
//         Integer campos = 5;   // ID_USUARIO; NOMBRE_USUARIO; ID_PERFIL; NOMBRE_PERFIL; PERMISOS

//         for (String linea : lstUsuarios) {
//             String[] reg = linea.split(";");
//             if (reg.length == campos) { 
//                 // Usuario.
//                 Usuario usuario = new Usuario(reg[0], reg[1]);
//                 // Nodo usuario.
//                 Nodo<Usuario> nodoUsuario = new Nodo<Usuario>(reg[0], usuario);
//                 // Ingresamos el usuario o si existe agregamos sus permisos.
//                 Nodo<Usuario> nodoAux = listaUsuarios.buscar(nodoUsuario.getEtiqueta());

//                 if (listaUsuarios.buscar(nodoUsuario.getEtiqueta()) == null) {
//                     listaUsuarios.insertar(nodoUsuario);
//                 }
//             }
//         }
//         System.out.println("Registros: " + listaUsuarios.cantElementos());
//     }


// }
