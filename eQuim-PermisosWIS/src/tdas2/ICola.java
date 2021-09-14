package tdas2;

import tdas.*;

public interface ICola<T>  {
    
    public void encolar(Nodo<T> nodo);

    public Nodo<T> desencolar();

    public boolean esVacia();

    public Nodo<T> getPrimero();

}   
