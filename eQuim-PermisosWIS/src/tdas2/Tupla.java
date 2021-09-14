package tdas2;

import tdas.*;

public class Tupla<T,K> {

    private T primero;
    private K segundo;

    public Tupla(T t, K k) {
        this.primero = t;
        this.segundo = k;
    }

    public T getPrimero(){
        return this.primero;
    }

    public K getSegundo(){
        return this.segundo;
    }
    
}
