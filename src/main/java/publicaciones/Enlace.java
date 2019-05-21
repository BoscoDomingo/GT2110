package main.java.publicaciones;

import main.java.pkg.CuentaUsuario;
import main.java.pkg.Timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Enlace extends Publicacion {
    private String enlaceExterno;

    public Enlace(String id, CuentaUsuario poster, String texto, ArrayList<Valoracion> valoraciones,
                  ArrayList<Comentario> comentarios, ArrayList<Timeline> perteneceATimelines,
                  String enlaceExterno) {
        super(id, poster, texto, valoraciones, comentarios, perteneceATimelines);
        this.enlaceExterno = enlaceExterno;
    }

    public void show() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("_____________________________________");
        //System.out.println("Publicacion de " + this.poster.getAlias() + ":");
        if(this.enlaceExterno!=null){
            System.out.println(enlaceExterno);
        }
        System.out.println("\n" + this.texto);
        System.out.println("\n" + formatoFecha.format(fecha));
        System.out.println("Likes: " + this.numeroLikes + " Dislikes: " + this.numeroDislikes);
        System.out.println("Comentarios: " + this.comentarios.size());
        System.out.println("_____________________________________");
    }
}
