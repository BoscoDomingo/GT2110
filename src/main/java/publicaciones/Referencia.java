package publicaciones;


import pkg.CuentaUsuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Referencia extends Publicacion {
    private Publicacion referenciada;

    public Referencia(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes, int numeroDislikes,
                      Publicacion referenciada) {
        super(id, usuario, fecha, texto, numeroLikes, numeroDislikes);
        this.referenciada = referenciada;
    }

    @Override
    public void show() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("_____________________________________");
        System.out.println(super.getPoster().getAlias());
        System.out.println("\nReferencia: \n" + referenciada.getPoster().getAlias()+ ": " +referenciada.getTexto());
        System.out.println("\n" + this.texto);
        System.out.println("\n" + formatoFecha.format(fecha));
        System.out.println("Likes: " + this.numeroLikes + " Dislikes: " + this.numeroDislikes);
        System.out.println("Comentarios: " + this.comentarios.size());
        System.out.println("_____________________________________");
    }

    public Publicacion getReferenciada() {
        return referenciada;
    }

    public void setReferenciada(Publicacion referenciada) {
        this.referenciada = referenciada;
    }
}
