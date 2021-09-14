package tdas;

import java.util.ArrayList;

public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;

    public Lista() {
        primero = null;
    }

    /**
     * Retorna el primer nodo de la lista.
     * @return
     */
    public Nodo<T> getPrimero() {
        return primero;
    }

    /**
     * Inserta el nodo en el primer lugar.
     */
    @Override
    public void setPrimero(Nodo<T> unNodo) {
        if (esVacia()) {
            this.primero = unNodo;
        } else {
            Nodo<T> aux = this.primero;
            unNodo.setSiguiente(aux);
            this.primero = unNodo;
        }
    }

    /**
     * Metodo encargado de agregar un nodo al final de la lista.
     * @param nodo - Nodo a agregar
     */
    @Override
    public void insertar(Nodo<T> unNodo) {
        if (esVacia()) {
            primero = unNodo;
        } else {
            Nodo<T> aux = primero;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(unNodo);
        }
    }

    /**
     * Metodo encargado de buscar un nodo cuya clave es la indicada.
     * @param clave - Clave del nodo a buscar.
     * @return El nodo encontrado. En caso de no encontrarlo, retornar null.
     */
    @Override
    public Nodo<T> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> aux = primero;
            while (aux != null) {
                if (aux.getEtiqueta().equals(clave)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    /**
     * Metodo encargado de eliminar un nodo cuya clave es la indicada.
     * @param clave Clave del nodo a eliminar.
     * @return True en caso de que la eliminaci�n haya sido efectuada con �xito.
     */
    @Override
    public Nodo<T> eliminar(Comparable clave) {

        // lista vacía.
        if (esVacia()) return null;

        // Lista con un solo nodo y que coincide con la clave para eliminar.
        if (primero.getSiguiente() == null) {
            if (primero.getEtiqueta().equals(clave)) {
                Nodo<T> aux = this.primero;
                primero = null;
                return aux;
            }
        }

        // Lista con más de un elemento.
        Nodo<T> aux = this.primero;

        //Eliminamos el primer elemento y dejamos el segundo en su lugar.
        if (aux.getEtiqueta().compareTo(clave) == 0) {
            Nodo<T> segundo = aux.getSiguiente();
            primero = segundo;
            return aux;
        }

        // Busco en la lista el nodo.
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getEtiqueta().equals(clave)) {
                Nodo<T> temp = aux.getSiguiente();
                aux.setSiguiente(temp.getSiguiente());
                return aux;
            }
            aux = aux.getSiguiente();
        }

        // No se encontró, retorno null.
        return null;
    }

    /**
     * Metodo encargado de imprimir en consola las claves de los nodos
     * contenidos en la lista.
     * @return 
     */
    @Override
    public String imprimir() {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            Nodo<T> temp = primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + "-" + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }
        }
        return aux;
    }

    /**
     * Retorna un String con las claves separadas por el separador pasado por
     * par�metro.
     * @param separador Separa las claves
     * @return
     */
    public String imprimir(String separador) {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            Nodo<T> temp = primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + separador + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }
        }
        return aux;
    }

    /**
     * Retorna la cantidad de elementos de la lista. En caso de que la lista
     * este vac�a, retornar 0.
     * @return Cantidad de elementos de la lista.
     */
    @Override
    public int cantElementos() {
        int contador = 0;
        if (esVacia()) {
            System.out.println("Cantidad de elementos 0.");
            return 0;
        } else {
            Nodo<T> aux = primero;
            while (aux != null) {
                contador++;
                aux = aux.getSiguiente();
            }
        }
        return contador;
    }

    /**
     * Indica si la lista contiene o no elementos.
     * @return Si tiene elementos o no.
     */
    @Override
    public boolean esVacia() {
        return primero == null;
    }

}
