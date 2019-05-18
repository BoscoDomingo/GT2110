package publicaciones;

import pkg.CuentaUsuario;

import java.util.Date;

public class Enlace extends Publicacion {
    private String enlaceExterno;

    public Enlace(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes, int numeroDislikes,
                  String enlaceExterno) {
        super(id, usuario, fecha, texto, numeroLikes, numeroDislikes);
        this.enlaceExterno = enlaceExterno;
    }
}
