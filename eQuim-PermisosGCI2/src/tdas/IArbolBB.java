package tdas;

import java.util.LinkedList;

public interface IArbolBB<T> {

    /**
     * Inserta un elemento en el arbol. En caso de ya existir un elemento con la
     * clave indicada en "unElemento", retorna falso.
     * @param unElemento Elemento a insertar
     * @return Exito de la operaci�n
     */
    public boolean insertar(TElementoAB<T> unElemento);

    /**
     * Busca un elemento dentro del �rbol.
     * @param unaEtiqueta Etiqueta identificadora del elemento a buscar. .
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public TElementoAB<T> buscar(Comparable unaEtiqueta);

    /**
     * Elimina un elemento dada una etiqueta.
     * @param unaEtiqueta
     * @return
     */
    public void eliminar(Comparable unaEtiqueta);

    /*
     * Imprime en PreOrden los elementos del �rbol, separados por guiones.
     *
     * @return String conteniendo el preorden separado por guiones.
    */
    public String preOrden();

    /**
     * Imprime en InOrden los elementos del �rbol, separados por guiones.
     * @return String conteniendo el preorden separado por guiones.
     */
    public String inOrden();

    /* 
     * Imprime en PostOrden los elementos del �rbol, separados por guiones.
     * @return String conteniendo el preorden separado por guiones.
    */
    public String postOrden();

    /**
     * Retorna una lista de elementos.
     */
    public Lista<T> inOrdenString();

    /**
     * Obtener tamaño del árbol.
     * @return
     */
    public int obtenerTamanio();

    /**
     * Obtener la altura del árbol.
     */
    public int altura();

    public long calcularCosto(int[] FrecExito, int[] FrecNoExito);

    void cuentaFrec(Comparable unaClave); // incrementa el campo de frecuencia que corresponda a ese argumento de búsqueda.	

    void completaVectores(TElementoAB[] elementos, int[] FrecExito, int[] FrecNoExito); // completa los vectores correspondientes.
    
    /**
     * Retorna si el árbol está vacío.
     * @return
     */
    public boolean esVacio();

}
