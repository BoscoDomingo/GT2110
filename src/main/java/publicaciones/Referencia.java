package publicaciones;

import pkg.CuentaUsuario;
import pkg.Timeline;

import java.util.ArrayList;
import java.util.Date;

public class Referencia extends Publicacion {
    private Publicacion referenciada;

    public Referencia(String id, CuentaUsuario poster, String texto,
                      ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios,
                      ArrayList<Timeline> perteneceATimelines, Publicacion referenciada) {
        super(id, poster, texto, valoraciones, comentarios, perteneceATimelines);
        this.referenciada = referenciada;
    }

    public Referencia(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes,
                      int numeroDislikes, Publicacion referenciada) {
        super(id, usuario, fecha, texto, numeroLikes, numeroDislikes);
        this.referenciada = referenciada;
    }
}
