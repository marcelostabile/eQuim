package tdas;

public class Nodo<T> implements INodo<T> {

    private final Comparable etiqueta;
    private T dato;
    private Nodo<T> siguiente = null;

    public Nodo(Comparable etiqueta, T dato ) {
        this.etiqueta = etiqueta;
        this.dato = dato;
    }

    /**
     * devuelve el dato del nodo
     * @return 
     */
    @Override
    public T getDato() {
        return this.dato;
    }

    /**
     * setea el dato del nodo 
     */
    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * devuelve el siguiente del nodo
     * @return 
     */
    @Override
    public Nodo<T> getSiguiente() {
        return this.siguiente;
    }

    /**
     * "engancha" otro nodo a continuación.
     */
    @Override
    public void setSiguiente(Nodo<T> nodo) {
        this.siguiente = nodo;
    }

    /**
     * Retorna la etiqueta del nodo
     * @return etiqueta del nodo
     */
    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    /**
     * Imprime la etiqueta del nodo
     */
    @Override
    public void imprimirEtiqueta() {
        System.out.println(this.etiqueta);
    }

    /**
     * Imprime los datos del nodo
     */
    @Override
    public void imprimir() {
        System.out.println(dato.toString());
    }

    /**
     * Retorna un clon de este nodo.
     */
    public Nodo<T> clonar() {
        return new Nodo<>(this.etiqueta, this.dato);
    }

}
