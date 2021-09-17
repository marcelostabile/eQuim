package tdas;

public interface INodo<T> {

    /**
     * devuelve el dato del nodo
     * @return 
     */
    public T getDato();

    /**
     * setea el dato del nodo 
     */
    public void setDato(T dato);
    
    /**
     * devuelve el siguiente del nodo
     * @return 
     */
    public Nodo<T> getSiguiente();
    
    /**
     * "engancha" otro nodo a continuación.
     */
    public void setSiguiente(Nodo<T> nodo);

    /**
     * Retorna la etiqueta del nodo
     * @return etiqueta del nodo
     */
    public Comparable getEtiqueta();

    /**
     * Imprime la etiqueta del nodo
     */
    public void imprimirEtiqueta();

    /**
     * Imprime los datos del nodo
     */
    public void imprimir();

    /**
     * Retorna un clon de este nodo.
     */
    public Nodo<T> clonar();

}
