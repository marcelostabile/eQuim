package tdas;

public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    /**
     * Constructor.
     */
    public TArbolBB() {
        raiz = null;
    }

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    /**
     * @param unElemento
     * @return
     */
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (esVacio()) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }

    /**
     * eliminar una etiqueta.
     * @param unaEtiqueta
     */
    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (!esVacio()) {
            this.raiz = this.raiz.eliminar(unaEtiqueta);
        }
    }

    public String preOrden() {
        if (esVacio()) {        // O(1)
            return null;        // O(1)
        } else {
            return raiz.preOrden();
        }
    }

    public String inOrden() {
        if (esVacio()) {        // O(1)
            return "arbol vacio";       // O(1)
        }
        return raiz.inOrden();
    }

    public String postOrden() {
        if (esVacio()) {        // O(1)
            return "arbol vacio";       // O(1)
        }
        return raiz.postOrden();
    }

    public Lista<T> inOrdenString() {
        Lista<T> listaInorden = new Lista<>();
        if (!esVacio()) {

            raiz.inOrden(listaInorden);
        }
        return listaInorden;
    }

    @Override
    public int obtenerTamanio() {
        if (!esVacio()) { // O(1)
            return raiz.obtenerTamanio();
        }
        return 0; // O(1)
    }

    public int altura() {
        if(!esVacio()){     // O(1)
            return raiz.altura();
        }
        return -1;      // O(1)
    }

    @Override
    public long calcularCosto(int[] FrecExito, int[] FrecNoExito) {
        if (esVacio()) {
            return 1;
        }
        int[] indiceFE = new int[1];
        int[] indiceFNE = new int[1];
        indiceFE[0] = 1;
        indiceFNE[0] = 0;
        return raiz.calcularCosto(FrecExito, FrecNoExito, indiceFE, indiceFNE, 1);
    }

    @Override
    public void cuentaFrec(Comparable unaClave) {
        if (!esVacio()) {
            raiz.cuentaFrec(unaClave);
        }
    }

    @Override
    public void completaVectores(TElementoAB[] elementos, int[] FrecExito, int[] FrecNoExito) {
        if (!esVacio()) {
            int[] indiceFE = { 1 };
            int[] indiceFNE = { 0 };
            raiz.completaVectores(elementos, FrecExito, FrecNoExito, indiceFE, indiceFNE);
        }
    }

    /**
     * Retorna si el árbol está vacío.
     * @return
     */
    public boolean esVacio() {
        return (raiz == null);
    }

}
