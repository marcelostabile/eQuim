package tdas2;

import tdas.*;

public interface IPila<T> {

    public void push(Nodo<T> nodo);

    public Nodo<T> pop();
    
    public Nodo<T> top();

    public boolean esVacia();

}
