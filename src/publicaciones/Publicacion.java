package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

public abstract class Publicacion {
    private String fecha;
    private String hora;
    private String texto;
    private int numeroLikes;
    private int numeroDislikes;
    private ArrayList<Valoracion> valoraciones;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Timeline> perteneceATimelines;
    
    public Publicacion(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes,
                       ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios,
                       ArrayList<Timeline> perteneceATimelines) {
        this.fecha = fecha;
        this.hora = hora;
        this.texto = texto;
        this.numeroLikes = numeroLikes;
        this.numeroDislikes = numeroDislikes;
        this.valoraciones = valoraciones;
        this.comentarios = comentarios;
        this.perteneceATimelines = perteneceATimelines;
    }
}
