
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
import javax.swing.event.InternalFrameAdapter;

import tdas.*;
import busqueda.*;

public class testBusqueda {

    @BeforeEach
    public void setUp() throws Exception {

        // arreglos de prueba.
        final int[] arregloDesordenado = {89, 3, 79, 43, 5, 67, 13, 61, 17, 31, 37, 47, 29, 53, 23, 2, 59, 71, 11, 73, 19, 83, 7, 41, 97};
        final int[] arregloOrdenado = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    }

    /**
     * Búsqueda Lineal.
     */
    @Test
    public void testBusquedaLineal() {
        final int[] arregloDesordenado = {89, 3, 79, 43, 5, 67, 13, 61, 17, 31, 37, 47, 29, 53, 23, 2, 59, 71, 11, 73, 19, 83, 7, 41, 97};
 
        assertEquals(89, Busqueda.busquedaLineal(89, arregloDesordenado));
        assertEquals(29, Busqueda.busquedaLineal(29, arregloDesordenado));
        assertEquals(97, Busqueda.busquedaLineal(97, arregloDesordenado));
        assertEquals(-1, Busqueda.busquedaLineal(25, arregloDesordenado));
    }

    /**
     * Búsqueda Binaria.
     */
    @Test
    public void testBusquedaBinaria() {
        final int[] arregloDesordenado = {89, 3, 79, 43, 5, 67, 13, 61, 17, 31, 37, 47, 29, 53, 23, 2, 59, 71, 11, 73, 19, 83, 7, 41, 97};

        assertEquals(2, Busqueda.busquedaBinaria(2, arregloDesordenado));
        assertEquals(19, Busqueda.busquedaBinaria(19, arregloDesordenado));
        assertEquals(97, Busqueda.busquedaBinaria(97, arregloDesordenado));
        assertEquals(-1, Busqueda.busquedaBinaria(25, arregloDesordenado));
    }

}
