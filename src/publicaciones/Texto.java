package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

public class Texto extends Publicacion {
    public Texto(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes,
                 ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios,
                 ArrayList<Timeline> perteneceATimelines) {
        super(fecha, hora, texto, numeroLikes, numeroDislikes, valoraciones, comentarios, perteneceATimelines);
    }
}
