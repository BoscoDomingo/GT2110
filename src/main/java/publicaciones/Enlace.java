package publicaciones;

import pkg.CuentaUsuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Enlace extends Publicacion {
    private String enlaceExterno;

    public Enlace(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes, int numeroDislikes,
                  String enlaceExterno) {
        super(id, usuario, fecha, texto, numeroLikes, numeroDislikes);
        this.enlaceExterno = enlaceExterno;
    }

    @Override
    public void show() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("_____________________________________");
        System.out.println(super.getPoster().getAlias());
        System.out.println("\n" + this.texto);
        System.out.println(enlaceExterno);
        System.out.println("\n" + formatoFecha.format(fecha));
        System.out.println("Likes: " + this.numeroLikes + " Dislikes: " + this.numeroDislikes);
        System.out.println("Comentarios: " + this.comentarios.size());
        System.out.println("_____________________________________");
    }
}
