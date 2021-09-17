package tdas;

public class TElementoAB<T> implements IElementoAB<T> {

    private final Comparable etiqueta;
    private TElementoAB hijoIzq;
    private TElementoAB hijoDer;
    private final T datos;

    private int frecExito;
    private int frecNoExitoIzq;
    private int frecNoExitoDer;

    /**
     * Constructor.
     * @param unaEtiqueta
     * @param unosDatos
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;

        frecExito = 0;
        frecNoExitoIzq = 0;
        frecNoExitoDer = 0;
    }

    /**
     * Obtener etiqueta.
     * @return
     */
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * Retorna el hijo izquierdo.
     * @return
     */
    public TElementoAB getHijoIzq() {
        return hijoIzq;
    }

    /**
     * Establecer el hijo izquierdo.
     * @param elemento
     */
    public void setHijoIzq(TElementoAB elemento) {
        this.hijoIzq = elemento;
    }

    /**
     * Retorna el hijo derecho.
     * @return
     */
    public TElementoAB getHijoDer() {
        return hijoDer;
    }

    /**
     * Establecer hijo derecho.
     * @param elemento
     */
    public void setHijoDer(TElementoAB elemento) {
        this.hijoDer = elemento;
    }

    /**
     * Obtener datos.
     * @return
     */
    public T getDatos() {
        return datos;
    }

    /**
     * Insertar un elemento.
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB unElemento) {

        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } 

        if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        }

        // ya existe un elemento con la misma etiqueta.-
        return false;
    }

    /**
     * Buscar un elemento.
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
        return null;
        }
    }

    /**
     * Eliminar un elemento.
     * @param unaEtiqueta
     */
    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (this.hijoIzq != null) {
                this.hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        if (unaEtiqueta.compareTo(this.getEtiqueta()) > 0) {
            if (this.hijoDer != null) {
                this.hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }
        return quitaElNodo();
    }

    private TElementoAB quitaElNodo() {
        if (hijoIzq == null) {
            return hijoDer;
        }

        if (hijoDer == null) {
            return hijoIzq;
        }

        TElementoAB elHijo = hijoIzq;
        TElementoAB elPadre = this;

        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }

        if (elPadre != this) {
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(hijoIzq);
        }

        elHijo.setHijoDer(hijoDer);
        return elHijo;
    }

    /**
     * Retorna un clon del elemento.
     * @return
     */
    public TElementoAB clonar() {
        return new TElementoAB<>(this.etiqueta, this.datos);
    }

    /**
     * Retorna un cadena de elementos en preOrden.
     * @return
     */
    public String preOrden() {
        String tempStr = "";        // O(1)
        String separa = "-";        // O(1)

        // Elemento
        tempStr += this.etiqueta.toString();        // O(1)

        // Izquierdo
        if (hijoIzq != null) {      // O(1)
            tempStr += separa;      // O(1)
            tempStr += hijoIzq.preOrden();
        }

        // Derecho
        if (hijoDer != null) {      // O(1)
            tempStr += separa;      // O(1)
            tempStr += hijoDer.preOrden();
        }
        return tempStr;     // O(1)
    }

    /**
     *  inOrden: O(N) , N siendo la cantidad de elementos del arbol
     * @return
     */
    public String inOrden() {
        String tempStr = "";        // O(1)
        String separa = "-";        // O(1)

        if (hijoIzq != null) {      // O(1)
            tempStr += hijoIzq.inOrden();
            tempStr += separa;      // O(1)
        }

        tempStr += this.etiqueta.toString();        // O(1)

        if (hijoDer != null) {      // O(1)
            tempStr += separa;      // O(1)
            tempStr += hijoDer.inOrden();
        }
        return tempStr;     // O(1)
    }

    /**
     *  postOrden: O(N) , N siendo la cantidad de elementos del arbol
    * @return
    */
    public String postOrden() {
        String tempStr = "";        // O(1)
        String separa = "-";        // O(1)

        if (hijoIzq != null) {      // O(1)
            tempStr += hijoIzq.postOrden();
            tempStr += separa;      // O(1)
        }

        if (hijoDer != null) {      // O(1)
            tempStr += hijoDer.postOrden();
            tempStr += separa;      // O(1)
        }

        tempStr += this.etiqueta.toString();        // O(1)

        return tempStr;     // O(1)
    }

    /**
     * Recibe una lista y le ingresa datos inOrden.
     * @param unaLista
     */
    public void inOrden(Lista<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.inOrden(unaLista);

        }
        unaLista.insertar(new Nodo<T>(this.etiqueta,this.getDatos()));
        if (hijoDer != null) {
            hijoDer.inOrden(unaLista);
        }
    }

    /**
     * Retorna el tamaño del árbol.
     */
    @Override
    public int obtenerTamanio() {
        int elTam = 1;
        if (hijoIzq != null) {
            elTam = elTam + hijoIzq.obtenerTamanio();

        }
        if (hijoDer != null) {
            elTam = elTam + hijoDer.obtenerTamanio();
        }
        return elTam;
    }

    /**
     * Retorna la altura del árbol.
     * @return
     */
    public int altura() {
        
        int subArbolIzquierdo = -1;     // O(1)
        int subArbolDerecho = -1;       // O(1)

        if(hijoIzq != null){        // O(1)
            subArbolIzquierdo = hijoIzq.altura();
        }

        if(hijoDer != null){        // O(1)
            subArbolDerecho = hijoDer.altura();
        }

        if (subArbolIzquierdo > subArbolDerecho ){      // O(1)
            return 1 + subArbolIzquierdo;       // O(1)
        }
        
        return 1 + subArbolDerecho;     // O(1)
    }

    @Override
    public int calcularCosto(int[] FrecExito, int[] FrecNoExito, int[] indiceFE, int[] indiceFNE, int nivel) {
        int Costo = 0;
        int CostoIzq = 0;
        int CostoDer = 0;
        if (this.hijoIzq != null) {
            CostoIzq = hijoIzq.calcularCosto(FrecExito, FrecNoExito, indiceFE, indiceFNE, nivel + 1);
        } else {
            CostoIzq = FrecNoExito[indiceFNE[0]] * (nivel + 1);
            indiceFNE[0]++;
        }

        Costo = FrecExito[indiceFE[0]] * nivel;
        indiceFE[0]++;

        if (this.hijoDer != null) {
            CostoDer = hijoDer.calcularCosto(FrecExito, FrecNoExito, indiceFE, indiceFNE, nivel + 1);
        } else {
            CostoDer = FrecNoExito[indiceFNE[0]] * (nivel + 1);
            indiceFNE[0]++;
        }

        return Costo + CostoIzq + CostoDer;
    }

    @Override
    public void completaVectores(TElementoAB[] elementos, int[] FrecExito, int[] FrecNoExito, int[] indiceFE, int[] indiceFNE) {
        if (hijoIzq != null) {
            hijoIzq.completaVectores(elementos, FrecExito, FrecNoExito, indiceFE, indiceFNE);
        } else {
            FrecNoExito[indiceFNE[0]] = this.frecNoExitoIzq;
            indiceFNE[0]++;
        }
        
        elementos[indiceFE[0]] = this.clonar();
        FrecExito[indiceFE[0]] = this.frecExito;
        indiceFE[0]++; 

        if (hijoDer != null) {
            hijoDer.completaVectores(elementos, FrecExito, FrecNoExito, indiceFE, indiceFNE);
        } else {
            FrecNoExito[indiceFNE[0]] = this.frecNoExitoDer;
            indiceFNE[0]++;
        }
    }

    @Override
    public void cuentaFrec(Comparable unaClave) {
        if (unaClave.equals(this.etiqueta)) {
            this.frecExito++;
            return;
        }
        if (unaClave.compareTo(this.etiqueta) < 0) {
            if (hijoIzq != null) {
                hijoIzq.cuentaFrec(unaClave);
            } else {
                this.frecNoExitoIzq++;
            }
            return;
        }
        if (hijoDer != null) {
            hijoDer.cuentaFrec(unaClave);
        } else {
            this.frecNoExitoDer++;
        }
    }
    
}
