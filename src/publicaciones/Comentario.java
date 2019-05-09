package publicaciones;

public class Comentario {
    private String fecha;
    private String texto;
    private String hora;
    private int numeroDislikes;
    private int numeroLikes;
    private Publicacion perteneceA;
    
    public Comentario(String fecha, String texto, String hora, int numeroDislikes, int numeroLikes,
                      Publicacion perteneceA) {
        this.fecha = fecha;
        this.texto = texto;
        this.hora = hora;
        this.numeroDislikes = numeroDislikes;
        this.numeroLikes = numeroLikes;
        this.perteneceA = perteneceA;
    }
}
