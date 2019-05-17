package publicaciones;

import pkg.CuentaUsuario;
import pkg.Timeline;

import java.util.ArrayList;

public class Referencia extends Publicacion {
    private Publicacion referenciada;

    public Referencia(String id, CuentaUsuario poster, String texto,
                      ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios,
                      ArrayList<Timeline> perteneceATimelines, Publicacion referenciada) {
        super(id, poster, texto, valoraciones, comentarios, perteneceATimelines);
        this.referenciada = referenciada;
    }
}
