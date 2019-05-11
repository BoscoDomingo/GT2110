package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

public class Referencia extends Publicacion {
    private Publicacion referenciada;
    
    public Referencia(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes,
                      ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios,
                      ArrayList<Timeline> perteneceATimelines, Publicacion referenciada) {
        super(fecha, hora, texto, numeroLikes, numeroDislikes, valoraciones, comentarios, perteneceATimelines);
        this.referenciada = referenciada;
    }
}
