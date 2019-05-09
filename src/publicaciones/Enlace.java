package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

public class Enlace extends Publicacion {
    private String enlaceExterno;
    
    public Enlace(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes, Valoracion valoracion
            , ArrayList<Comentario> tiene, ArrayList<Timeline> perteneceA, String enlaceExterno) {
        super(fecha, hora, texto, numeroLikes, numeroDislikes, valoracion, tiene, perteneceA);
        this.enlaceExterno = enlaceExterno;
    }
}
