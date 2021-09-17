package busqueda;

public class Busqueda {
    
    /**
     * Vectores de ejemplo.
     */
    //public int[] arregloDesordenado = {89, 3, 79, 43, 5, 67, 13, 61, 17, 31, 37, 47, 29, 53, 23, 2, 59, 71, 11, 73, 19, 83, 7, 41, 97};
    //public int[] arregloOrdenado = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};


    /**
     * Búsqueda Lineal (secuencial).
     * 
     * Orden de Tiempo de Ejecución O(N) siendo N la cantidad de elementos de la lista.
     * En el peor caso realiza N comparaciones. Si no encuentra el valor retorna -1.
     * @param valorBuscado
     * @param lista
     * @return
     */
    public static int busquedaLineal(int valorBuscado, int[] lista) {

        for (int i = 0; i < lista.length; i++) {    // O(N)
            if (lista[i] == valorBuscado) {
                return lista[i];                    // O(1)
            }
        }
        return -1;                                  // O(1)
    }


    /**
     * Búsqueda Binaria.
     * 
     * Orden de Tiempo de Ejecución O(log N) siendo N la cantidad de elementos de la lista.
     * El arreglo DEBE estar ordenado. Si no encuentra el valor retorna -1.
     * En el peor caso realiza N comparaciones. 
     * @param valorBuscado
     * @param lista
     * @return
     */
    public static int busquedaBinaria(int valorBuscado, int[] lista) {

        int limiteINF = 0;                                          // O(1)
        int limiteSUP = lista.length;                               // O(1)
        int medio = (int) Math.floor(limiteSUP + limiteINF) / 2;    // O(1)
        
        // Recorrer la lista.
        boolean continuar = true;
        while (continuar) {         // tiempo total O(log N)

            // prueba.
            // System.out.println("Actual: " + lista[medio] +" * INF:"+ limiteINF +" - MED:"+ medio +" - SUP:"+ limiteSUP +" * Buscado: "+ valorBuscado);

            // si coincide con el valor buscado lo retornamos.
            if (lista[medio] == valorBuscado) { 
                continuar = false;                  // O(1)
                return lista[medio];                // O(1)
            }

            // si llega a los topes no continuará.
            if ((limiteINF == medio) || (limiteSUP == medio)) {
                continuar = false;
            }

            // si el valor buscado es menor, movemos limiteSUP.
            if (valorBuscado < lista[medio]) {
                limiteSUP = medio;                  // O(1)
                // calculamos el medio de la lista.
                medio = (int) Math.floor(limiteSUP + limiteINF) / 2;    // O(1) 

            // si el valor buscado es mayor, movemos limiteINF.
            } else {
                limiteINF = medio;                  // O(1)
                // calculamos el medio de la lista.
                medio = (int) Math.ceil(limiteSUP + limiteINF) / 2;     // O(1) 
            }
        }
        return -1;              // O(1)
    }

}
