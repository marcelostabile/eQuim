package tdas_extras;

public class Tupla<T,K> implements ITupla<T,K> {

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
