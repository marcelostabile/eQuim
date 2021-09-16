package appGCI;

public class Permiso {
    
    private String tipo;            // 'TA'rea, 'TR'ansacción, 'FR'anja.
    private String codigo; 
    private String descripcion; 
    private String codSeguridad;    // Código de seguridad.

    private Integer trnEmpresa;
    private String trnTipoDoc;
    private String trnCodFranja; 
    
    public Permiso(String perfilTipo, String codigo, String descripcion, String codSeguridad) {
        switch (perfilTipo) {
            case "10":
                this.tipo = "TA";
            case "60":
                this.tipo = "TR";
            case "*":
                this.tipo = "*";
            default:
                tipo = "";
        }
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codSeguridad = codSeguridad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodSeguridad() {
        return codSeguridad;
    }

    public void setCodSeguridad(String codSeguridad) {
        this.codSeguridad = codSeguridad;
    }

    public Integer getTrnEmpresa() {
        return trnEmpresa;
    }

    public void setTrnEmpresa(Integer trnEmpresa) {
        this.trnEmpresa = trnEmpresa;
    }

    public String getTrnTipoDoc() {
        return trnTipoDoc;
    }

    public void setTrnTipoDoc(String trnTipoDoc) {
        this.trnTipoDoc = trnTipoDoc;
    }

    public String getTrnCodFranja() {
        return trnCodFranja;
    }

    public void setTrnCodFranja(String trnCodFranja) {
        this.trnCodFranja = trnCodFranja;
    }
    
}
