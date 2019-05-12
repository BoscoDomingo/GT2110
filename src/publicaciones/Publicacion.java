package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

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
    
    public Publicacion(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes,
                       ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios,
                       ArrayList<Timeline> perteneceATimelines) {
        this.fecha = fecha;
        this.hora = hora;
        this.texto = texto;
        this.numeroLikes = 0;
        this.numeroDislikes = 0;
    }

    public void show() {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("_____________________________________");
        System.out.println("Publicacion de " + this.poster.getAlias() + ":");
        System.out.println(this.texto);
        System.out.println(formatoFecha.format(fecha));
        System.out.println("Likes: " + this.numeroLikes + "Dislikes: " + this.numeroDislikes);
        System.out.println("Comentarios: " + this.comentarios.size());
        System.out.println("_____________________________________");
    }

    public void deleteComentario(String idComentario) {
        for (Comentario comentario : comentarios) {
            if (comentario.getID() == idComentario) {
                comentarios.remove(comentario);
            }
        }
    }

    public void addValoracion(Valoracion valoracion) {
        this.valoraciones.add(valoracion);
        if (valoracion.getLikeDislike() == 0) {
            this.numeroDislikes++;
        }else{
            this.numeroLikes++;
        }
    }

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

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
        this.numeroDislikes = numeroDislikes;
        this.valoraciones = valoraciones;
        this.comentarios = comentarios;
        this.perteneceATimelines = perteneceATimelines;
    }
}
