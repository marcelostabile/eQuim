package appGCI;

import java.util.ArrayList;
import com.graphbuilder.struc.LinkedList;

import archivos.ManejadorArchivosExcel;

public class SeguridadGCI {
    
    static LinkedList listaUsuarios = new LinkedList();
    static LinkedList listaPermisos = new LinkedList();

    /**
     * main de seguridad GCI.
     */
    public static void armarReportePermisos() {

        String[] lstUsuarios = ManejadorArchivosExcel.leerArchivoExcel("CltaGCI_1_Usuarios.xlsx");
        String[] lstTareas = ManejadorArchivosExcel.leerArchivoExcel("CltaGCI_2_Tareas.xlsx");
        String[] lstTransacciones = ManejadorArchivosExcel.leerArchivoExcel("CltaGCI_3_Transacciones.xlsx");
        String[] lstFranjas = ManejadorArchivosExcel.leerArchivoExcel("CltaGCI_4_Franjas.xlsx");

        System.out.println("Usuarios: " + lstUsuarios.length);
        System.out.println("Tareas: " + lstTareas.length);
        System.out.println("Transacciones: " + lstTransacciones.length);
        System.out.println("Franjas: " + lstFranjas.length);

       


        /**
        // Listar usuarios.
        for (String[] reg : arrUsuarios) {
            Usuario usuario = new Usuario(reg[0], reg[1]);
            listaUsuarios.addToTail(usuario);
        }
        System.out.println("Lista Usuarios: " + listaUsuarios.size());

        // listar permisos de tareas y transacciones.
        for (String[] reg : arrTareas) {
            Permiso tarea = new Permiso(reg[0], reg[1], reg[2], reg[4]);
            listaPermisos.addToTail(tarea);
        }
        System.out.println("Lista Permisos: " + listaPermisos.size());

        for (String[] reg : arrTransacciones) {
            Permiso transaccion = new Permiso(reg[0], reg[1], reg[2], reg[4]);
            listaPermisos.addToTail(transaccion);
        }
        System.out.println("Lista Permisos: " + listaPermisos.size());
        */

        
    }

}
