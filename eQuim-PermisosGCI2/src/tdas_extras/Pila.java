package tdas_extras;

import tdas.*;

public class Pila<T> extends Lista<T> implements IPila<T> {

    /**
     *  Este metodo push es de Orden lineal : O(N) , siendo N el largo de la pila
     */
    public void push(Nodo<T> nodo){
        super.insertar(nodo);      // O(N)
    }

    /**
     *  Este metodo pop es de Orden constante : O(N) , siendo N el largo de la pila
     */
    public Nodo<T> pop() {

        if(this.esVacia()) {     // O(1)
            return null;        // O(1)
        }

        Nodo<T> anterior = super.getPrimero();      // O(1)
        Nodo<T> nodo = super.getPrimero().getSiguiente();       // O(1)

        if(anterior.getSiguiente() == null) {        // O(1)
            super.eliminar(anterior.getEtiqueta());      // O(N)
            return anterior;        // O(1)
        }
        
        while (nodo.getSiguiente() != null) {       // O(N)
            anterior = nodo;        // O(1)
            nodo = nodo.getSiguiente();     // O(1)
        }
        
        Nodo<T> temp = new Nodo<>(nodo.getEtiqueta(), nodo.getDato());      // O(1)
        anterior.setSiguiente(nodo.getSiguiente());     // O(1)
        return temp;        // O(1)
    }
    
    /**
     *  Este metodo top es de Orden lineal : O(N) , siendo N el largo de la pila
     */
    public Nodo<T> top() {
        Nodo<T> nodo = super.getPrimero();      // O(1)
        while (nodo.getSiguiente() != null) {       // O(N)
            nodo = nodo.getSiguiente();     // O(1)
        }
        return nodo;     // O(1)
    }
    
}
