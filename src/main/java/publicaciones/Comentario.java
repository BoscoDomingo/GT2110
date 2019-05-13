package publicaciones;

import pkg.CuentaUsuario;

import java.util.Date;

public class Comentario {
    private final String id;
    private Date fecha;
    private String texto;
    private Publicacion perteneceA;
    private CuentaUsuario escritoPor;

    public Comentario(String id, Date fecha, String texto, Publicacion perteneceA, CuentaUsuario escritoPor) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.perteneceA = perteneceA;
        this.escritoPor = escritoPor;
    }
}