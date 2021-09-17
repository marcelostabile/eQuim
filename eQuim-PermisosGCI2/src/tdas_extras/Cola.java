package tdas_extras;

import tdas.*;

public class Cola<T> extends Lista<T> implements ICola<T> {

    /**
     * El metodo encolar es de orden lineal O(N), siendo N el largo de la cola.
     */
    @Override
    public void encolar(Nodo<T> nodo) {
        super.insertar(nodo); // O(N)
    }

    /**
     * El metodo desencolar es de orden lineal O(N), siendo N el largo de la cola.
     */
    @Override
    public Nodo<T> desencolar() {
        Nodo<T> temp = new Nodo<>(super.getPrimero().getEtiqueta(), super.getPrimero().getDato());    // O(1)
        super.eliminar(super.getPrimero().getEtiqueta());    // O(N) 
        return temp;     // O(1)
    }

}
