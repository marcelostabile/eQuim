package clasificacion;

public class Insercion {

    /**
     * Ordenamiento por inserción directa.
     * @param lista
     * @return una lista.
     */
    public static int[] InsercionDirecta(int[] lista) {

        for (int i = 1; i < lista.length; i++) {
            int aux = lista[i];
            int anterior = i - 1;
            while (anterior >= 0 && aux < lista[anterior]) {
                lista[anterior + 1] = lista[anterior];
                anterior = anterior - 1;
            }  
            lista[anterior + 1] = aux;
        }
        return lista;
    }


    /**
     * Ordenamiento por inserción directa.
     * @param lista
     */
    public static void ordenaInsercionDirecta(int[] lista) {

        for (int i = 1; i < lista.length; i++) {
            int aux = lista[i];
            int anterior = i - 1;
            while (anterior >= 0 && aux < lista[anterior]) {
                lista[anterior + 1] = lista[anterior];
                anterior = anterior - 1;
            }  
            lista[anterior + 1] = aux;
        }
    }

    
    /*
    PSEUDO:

    COMIENZO
        DESDE i = 2 hasta N HACER
            aux <- V[i]
            anterior <- i - 1
            MIENTRAS anterior >= 0 AND aux.clave < V[anterior].clave HACER
                V[anterior + 1] <- V[anterior]
                anterior <- anterior - 1
            FIN MIENTRAS
            V[anterior + 1] <- aux
        FIN DESDE
    FIN
    */

}
