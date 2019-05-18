package publicaciones;

import pkg.CuentaUsuario;
import pkg.Timeline;

import java.util.ArrayList;
import java.util.Date;

public class Enlace extends Publicacion {
    private String enlaceExterno;

    public Enlace(String id, CuentaUsuario poster, String texto, ArrayList<Valoracion> valoraciones,
                  ArrayList<Comentario> comentarios, ArrayList<Timeline> perteneceATimelines,
                  String enlaceExterno) {
        super(id, poster, texto, valoraciones, comentarios, perteneceATimelines);
        this.enlaceExterno = enlaceExterno;
    }

    public Enlace(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes, int numeroDislikes,
                  String enlaceExterno) {
        super(id, usuario, fecha, texto, numeroLikes, numeroDislikes);
        this.enlaceExterno = enlaceExterno;
    }
}
