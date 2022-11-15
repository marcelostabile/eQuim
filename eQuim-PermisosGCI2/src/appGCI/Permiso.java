package appGCI;

public class Permiso {

    private String codigo;
    private String descripcion;     
    private String claveSeguridad;
    private String tipo;
    private String empresa;

    public Permiso(String Codigo, String Descripcion, String ClaveSeguridad, String Empresa) {

        this.codigo = Codigo;
        this.descripcion = Descripcion;
        this.claveSeguridad = ClaveSeguridad;
        switch (Codigo) {
            case "10":
                this.tipo = "Aplicación";
            case "60":
                this.tipo = "Tarea";
            case "*":
                this.tipo = "Todo";
            default:
                tipo = "";
        }
        this.empresa = Empresa;
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
