package publicaciones;

import pkg.CuentaUsuario;
import pkg.Timeline;

import java.util.ArrayList;

public class Enlace extends Publicacion {
    private String enlaceExterno;

    public Enlace(String id, CuentaUsuario poster, String texto, ArrayList<Valoracion> valoraciones,
                  ArrayList<Comentario> comentarios, ArrayList<Timeline> perteneceATimelines,
                  String enlaceExterno) {
        super(id, poster, texto, valoraciones, comentarios, perteneceATimelines);
        this.enlaceExterno = enlaceExterno;
    }
}
