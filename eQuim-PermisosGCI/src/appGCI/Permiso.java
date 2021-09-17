package appGCI;

public class Permiso {

    private String codigo;
    private String descripcion;     
    private String claveSeguridad;    // Código de seguridad.
    private String tipo;            // 'TA'rea, 'TR'ansacción, 'FR'anja.
    private String empresa;

    public Permiso(String Codigo, String Descripcion, String ClaveSeguridad, String Empresa) {

        this.codigo = Codigo;
        this.descripcion = Descripcion;
        this.claveSeguridad = ClaveSeguridad;
        this.empresa = Empresa;
        switch (Codigo) {
            case "10":
                this.tipo = "TA";
            case "60":
                this.tipo = "TR";
            case "*":
                this.tipo = "*";
            default:
                tipo = "";
        }
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

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
