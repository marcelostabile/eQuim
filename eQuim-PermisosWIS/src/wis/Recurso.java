package wis;

public class Recurso {
    
    public Integer id;
    public String descripcion;
    public String aplicacion;
    public String seccion;
    public String tipo;
    public String nombre;
    public String tipousuario;
    public String AAA;

    public Recurso(Integer id, String descripcion, String aplicacion, String seccion, 
                    String tipo, String nombre, String tipousuario, String aAA) {
        this.id = id;
        this.descripcion = descripcion;
        this.aplicacion = aplicacion;
        this.seccion = seccion;
        this.tipo = tipo;
        this.nombre = nombre;
        this.tipousuario = tipousuario;
        AAA = aAA;
    }

    public String misDatos(String separador) {

        String misDatos =   this.id + separador +
                            this.descripcion + separador +
                            this.aplicacion + separador +
                            this.seccion + separador +
                            this.tipo + separador +
                            this.nombre + separador + 
                            this.tipousuario + separador + 
                            this.AAA;
        return misDatos;
    }

}
