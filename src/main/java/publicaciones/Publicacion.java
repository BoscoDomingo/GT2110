package publicaciones;

import interfaces.IMenu;
import pkg.CuentaUsuario;
import pkg.Timeline;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Publicacion implements IMenu, Comparable<Publicacion> {
    private final String id;
    private final CuentaUsuario poster;
    protected final Date fecha;
    protected final String texto;
    protected int numeroLikes;
    protected int numeroDislikes;
    private ArrayList<Valoracion> valoraciones;
    protected ArrayList<Comentario> comentarios;
    private ArrayList<Timeline> perteneceATimelines;

    public Publicacion(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes,
                       int numeroDislikes) {
        this.id = id;
        this.poster = usuario;
        this.fecha = fecha;
        this.texto = texto;
        this.numeroLikes = numeroLikes;
        this.numeroDislikes = numeroDislikes;
        this.valoraciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.perteneceATimelines = new ArrayList<>();
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

    public void showAsNew() {
        System.out.println("\n***NUEVO***");
        this.show();
    }

    public void deletePublicacion() {
        getPoster().getPublicaciones().remove(this);
        this.perteneceATimelines.remove(this);

    }

    @Override
    public int compareTo(Publicacion p) { //para ordenar cronológicamente
        return this.fecha.compareTo(p.getFecha());
    }

    public void addValoracion(Valoracion valoracion) {
        this.valoraciones.add(valoracion);
        if (valoracion.getLikeDislike() == 0) {
            this.numeroDislikes++;
        } else {
            this.numeroLikes++;
        }
    }

    public void delete() {
        try {
            for (Timeline timeline : this.perteneceATimelines) {
                timeline.removePublicacion(this);
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error al borrar la publicacion. Por favor, inténtelo de nuevo: " + e);
        }
    }

    @Override
    public boolean menu() {
        boolean accionValida = false, goBack = false;
       /* CuentaUsuario currentUser = DatabaseController.getCurrentUser();
        System.out.println("Opciones:\n0-Valorar\n1-Comentar\n2-Referenciar\n9-Volver atrás");
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    accionValida = true;
                    currentUser.valorar(this);
                    break;
                case 1:
                    accionValida = true;
                    currentUser.comment(this);
                    break;
                case 2:
                    accionValida = true;
                    currentUser.publicar("referencia", this); //TODO: cambiar esto en su momento
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido");
            }
        }*/
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    public boolean ownerMenu() {
        boolean accionValida = false, goBack = false;
        CuentaUsuario currentUser = new CuentaUsuario("a9999", "PAAAAAA",
                                                      "PAA@PAA.com");//TODO: DatabaseController.getCurrentUser();
        System.out.println("Opciones:\n0-Borrar\n1-Comentar\n2-Valorar\n9-Volver atrás");
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    accionValida = true;
                    this.delete();
                    break;
                case 1:
                    accionValida = true;
                    //currentUser.comment(this);
                    break;
                case 2:
                    accionValida = true;
                    //currentUser.valorar(this);
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido");
            }
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    public void addComentario(Comentario comentario) {
        try {
            this.comentarios.add(comentario);
        } catch (Exception e) {
            System.out.println("Ha habido un error. Por favor, pruebe de nuevo " + e);
        }
    }

    public void addComentarios(ArrayList<Comentario> comentarios) {
        try {
            this.comentarios.addAll(comentarios);
        } catch (Exception e) {
            System.out.println("Ha habido un error. Por favor, pruebe de nuevo " + e);
        }
    }

     /*public void deleteComentario(String idComentario) {
        for (Comentario comentario : comentarios) {
            if (comentario.getID() == idComentario) {
                comentarios.remove(comentario);
            }
        }
    }*/

    //GETTERS & SETTERS

    public void setValoraciones(ArrayList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setPerteneceATimelines(ArrayList<Timeline> perteneceATimelines) {
        this.perteneceATimelines = perteneceATimelines;
    }

    public CuentaUsuario getPoster() {
        return poster;
    }

    public Date getFecha() {
        return fecha;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public String getId() {
        return id;
    }
}
