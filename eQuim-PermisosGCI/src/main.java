import appGCI.*;
import archivos.*;
import busqueda.*;
import clasificacion.*;
import tdas.*;
import tdas_extras.*;

public class main {

    public static void main(String[] args) throws Exception {

        /**
         * Listas de entrada.
         */
        // int[] miLista = {89, 3, 79, 43, 5, 67, 13, 61, 17, 31, 37, 47, 29, 53, 23, 2, 59, 71, 11, 73, 19, 83, 7, 41, 97};
        // int[] miListaOrdenada = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        
        SeguridadGCI.armarReportePermisos();
    //     /**
    //      * MÉTODOS DE BÚSQUEDA.
    //      */
    //     System.out.println("MÉTODOS DE BÚSQUEDA\n");


    //     /**
    //      * Búsqueda Lineal.
    //      */
    //     System.out.println("Búsqueda Lineal\n");
    //     System.out.println("Búsqueda 17: " + Busqueda.busquedaLineal(17, miLista));
    //     System.out.println("Búsqueda 25: " + Busqueda.busquedaLineal(25, miLista));


    //     /**
    //      * Búsqueda Binaria.
    //      */
    //     System.out.println("Búsqueda Binaria\n");
    //     System.out.println("Búsqueda 17: " + Busqueda.busquedaBinaria(17, miLista));
    //     System.out.println("Búsqueda 25: " + Busqueda.busquedaBinaria(25, miLista));


    //     /**
    //      * MÉTODOS DE ORDENAMIENTO.
    //      */
    //     System.out.println("MÉTODOS DE ORDENAMIENTO\n");

        
    //     /**
    //      * Inserción Directa (1).
    //      */
    //     System.out.println("Inserción Directa - 1\n");
    //     Insercion.ordenaInsercionDirecta(miLista);
    //     imprimirLista(miLista);


    //     /**
    //      * Inserción Directa (2).
    //      */
    //     System.out.println("Inserción Directa - 2\n");
    //     int[] miListaResultado = Insercion.InsercionDirecta(miLista);
    //     imprimirLista(miListaResultado);

    // }

    }

    /**
     * Imprime por pantalla el contenido de una lista.
     * @param lista
     */
    private static void imprimirLista(int[] lista) {

        System.out.println("Imprimiendo lista:\n");
        for (int i = 0; i <= lista.length-1; i++) {
            System.out.println(lista[i]);
        }
    }

}
