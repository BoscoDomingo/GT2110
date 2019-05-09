package pkg;

import interfaces.IPublicacion;
import publicaciones.Comentario;
import publicaciones.Valoracion;

import java.sql.Time;
import java.util.ArrayList;

public class Publicacion implements IPublicacion {
    private String fecha;
    private String texto;
    private String hora;
    private int numeroDislikes;
    private int numeroLikes;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Timeline> timelines;
    private Valoracion valoracion;

}
