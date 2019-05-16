package main.java.publicaciones;

import pkg.CuentaUsuario;
import pkg.Timeline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Publicacion {
    private final String id;
    private final CuentaUsuario poster;
    private final Date fecha;
    private final String texto;
    private int numeroLikes;
    private int numeroDislikes;
    private ArrayList<Valoracion> valoraciones;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Timeline> perteneceATimelines;

    public Publicacion(){
        id = null;
        poster = null;
        fecha = null;
        texto = null;
    }

    public Publicacion(String id, CuentaUsuario poster, String texto, ArrayList<Valoracion> valoraciones,
                       ArrayList<Comentario> comentarios, ArrayList<Timeline> perteneceATimelines) {
        this.id = id;
        this.poster = poster;
        this.fecha = new Date();
        this.texto = texto;
        this.numeroLikes = 0;
        this.numeroDislikes = 0;
        this.valoraciones = valoraciones;
        this.comentarios = comentarios;
        this.perteneceATimelines = perteneceATimelines;
    }

    public void show() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("_____________________________________");
        //System.out.println("Publicacion de " + this.poster.getAlias() + ":");
        System.out.println("\n" + this.texto);
        System.out.println("\n" + formatoFecha.format(fecha));
        System.out.println("Likes: " + this.numeroLikes + " Dislikes: " + this.numeroDislikes);
        System.out.println("Comentarios: " + this.comentarios.size());
        System.out.println("_____________________________________");
    }

    public Publicacion addPublicacion(ArrayList<Publicacion> allPublicaciones) {
        System.out.println("Eliga el tipo de publicacion que desea realizar: ");
        System.out.println("1 - Texto\n2 - Enlace\n3 - Referencia");
        Scanner scan = new Scanner(System.in);
        int selector = scan.nextInt();
        switch (selector) {
            case 1:
                 return addPublicacionTexto(allPublicaciones);
            case 2:
                return addPublicacionEnlace(allPublicaciones);
            case 3:
                return addPublicacionReferencia(allPublicaciones);
        }
        return null;
    }

    private Publicacion addPublicacionTexto(ArrayList<Publicacion> allPublicaciones) {
            Scanner scan = new Scanner(System.in);
            String texto = null;
        do {
            System.out.println("Escriba el contenido a continuacion: "); //EVITAR TEXTO VACIO
            texto = scan.nextLine();
        }while(texto.length()==0);
        
        String id = "b0000";
        if(allPublicaciones.size()!=0) {
            id = allPublicaciones.get(allPublicaciones.size() - 1).getId();
            id = id.substring(1,5);
            id = "b" + String.format("%04d", Integer.parseInt(id)+1);           //El pinche numero del id junto a la b
        }
        System.out.println(id);
        return new Publicacion(id,null,texto,new ArrayList<Valoracion>(),new ArrayList<Comentario>(),new ArrayList<Timeline>());
    }

    private Publicacion addPublicacionEnlace(ArrayList<Publicacion> allPublicaciones) {
        System.out.println("Hola");
        return null;
    }

    private Publicacion addPublicacionReferencia(ArrayList<Publicacion> allPublicaciones) {
        return null;
    }

    /*public void deleteComentario(String idComentario) {
        for (Comentario comentario : comentarios) {
            if (comentario.getID() == idComentario) {
                comentarios.remove(comentario);
            }
        }
    }*/

    /*public void addValoracion(Valoracion valoracion) {
        this.valoraciones.add(valoracion);
        if (valoracion.getLikeDislike() == 0) {
            this.numeroDislikes++;
        }else{
            this.numeroLikes++;
        }
    }*/

    public CuentaUsuario getPoster() {
        return poster;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTexto() {
        return texto;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }
    public String getId() {
        return id;
    }

}
