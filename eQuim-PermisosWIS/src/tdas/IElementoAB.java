package tdas;

public interface IElementoAB<T> {

    /**
     * Obtener etiqueta.
     * @return
     */
    public Comparable getEtiqueta();

    /**
     * Retorna el hijo izquierdo.
     * @return
     */
    public TElementoAB getHijoIzq();

    /**
     * Establecer el hijo izquierdo.
     * @param elemento
     */
    public void setHijoIzq(TElementoAB elemento);

    /**
     * Retorna el hijo derecho.
     * @return
     */
    public TElementoAB getHijoDer();

    /**
     * Establecer hijo derecho.
     * @param elemento
     */
    public void setHijoDer(TElementoAB elemento);

    /**
     * Obtener datos.
     * @return
     */
    public T getDatos();

    /**
     * Inserta un elemento dentro del arbol.
     * @param unElemento Elemento a insertar.
     * @return Exito de la operacion.
     */
    public boolean insertar(TElementoAB unElemento);

    /**
     * Busca un elemento dentro del arbol con la etiqueta indicada.
     * @param unaEtiqueta del nodo a buscar
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public TElementoAB buscar(Comparable unaEtiqueta);

    /**
     * Elimina un elemento dada una etiqueta.
     * @param unaEtiqueta
     * @return
     */
    public TElementoAB eliminar(Comparable unaEtiqueta);

    /**
     * Retorna un clon del elemento.
     * @return
     */
    public TElementoAB clonar();

    /**
     * Retorna un cadena de elementos en preOrden.
     * @return
     */
    public String preOrden();

    /**
     * inOrden: O(N) , N siendo la cantidad de elementos del arbol
     * @return
     */
    public String inOrden();

    /**
     * postOrden: O(N) , N siendo la cantidad de elementos del arbol
     * @return
     */
    public String postOrden();

    /**
     * Recibe una lista y le ingresa datos inOrden.
     * @param unaLista
     */
    public void inOrden(Lista<T> unaLista);

    /**
     * Retorna el tamaño del árbol.
     */
    public int obtenerTamanio();

    /**
     * Retorna la altura del árbol.
     * @return
     */
    public int altura();

    public int calcularCosto(int[] FrecExito, int[] FrecNoExito, int[] indiceFE, int[] indiceFNE, int nivel);

    void completaVectores(TElementoAB[] elementos, int[] FrecExito, int[] FrecNoExito, int[] indiceFE, int[] indiceFNE);   // completa los vectores correspondientes.

    void cuentaFrec(Comparable unaClave); // incrementa el campo de frecuencia que corresponda a ese argumento de búsqueda.
    
}
