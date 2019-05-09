package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

public class Texto extends Publicacion {
    public Texto(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes, Valoracion valoracion,
                 ArrayList<Comentario> tiene, ArrayList<Timeline> perteneceA) {
        super(fecha, hora, texto, numeroLikes, numeroDislikes, valoracion, tiene, perteneceA);
    }
}
