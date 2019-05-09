package publicaciones;

import pkg.Timeline;

import java.util.ArrayList;

public abstract class Publicacion {
    private String fecha;
    private String hora;
    private String texto;
    private int numeroLikes;
    private int numeroDislikes;
    private Valoracion valoracion;
    private ArrayList<Comentario> tiene;
    private ArrayList<Timeline> perteneceA;
    
    public Publicacion(String fecha, String hora, String texto, int numeroLikes, int numeroDislikes,
                       Valoracion valoracion, ArrayList<Comentario> tiene, ArrayList<Timeline> perteneceA) {
        this.fecha = fecha;
        this.hora = hora;
        this.texto = texto;
        this.numeroLikes = numeroLikes;
        this.numeroDislikes = numeroDislikes;
        this.valoracion = valoracion;
        this.tiene = tiene;
        this.perteneceA = perteneceA;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public int getNumeroLikes() {
        return numeroLikes;
    }
    
    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }
    
    public int getNumeroDislikes() {
        return numeroDislikes;
    }
    
    public void setNumeroDislikes(int numeroDislikes) {
        this.numeroDislikes = numeroDislikes;
    }
    
    public Valoracion getValoracion() {
        return valoracion;
    }
    
    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }
    
    public ArrayList<Comentario> getTiene() {
        return tiene;
    }
    
    public void setTiene(ArrayList<Comentario> tiene) {
        this.tiene = tiene;
    }
    
    public ArrayList<Timeline> getPerteneceA() {
        return perteneceA;
    }
    
    public void setPerteneceA(ArrayList<Timeline> perteneceA) {
        this.perteneceA = perteneceA;
    }
}
