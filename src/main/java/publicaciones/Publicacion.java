package publicaciones;

import interfaces.IMenu;
import pkg.CuentaUsuario;
import pkg.Sistema;
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

    public Publicacion addPublicacion(ArrayList<Publicacion> allPublicaciones) {
        System.out.println("Eliga el tipo de publicacion que desea realizar: ");
        System.out.println("1 - Texto\n2 - Enlace\n3 - Referencia");
        Scanner scan = new Scanner(System.in);
        int selector = scan.nextInt();

        Scanner scanTexto = new Scanner(System.in);
        String texto = null;
        do {
            System.out.println("Escriba el contenido a continuacion: "); //EVITAR TEXTO VACIO
            texto = scanTexto.nextLine();
        } while (texto.length() == 0);

        String id = "b0000";
        if (allPublicaciones.size() != 0) {
            id = allPublicaciones.get(allPublicaciones.size() - 1).getId();
            id = id.substring(1, 5);
            id = "b" + String.format("%04d", Integer.parseInt(id) + 1);           //El pinche numero del id junto a la b
        }

        switch (selector) {
            case 1:
                return addPublicacionTexto(allPublicaciones, id, texto);
            case 2:
                return addPublicacionEnlace(allPublicaciones, id, texto);
            case 3:
                return addPublicacionReferencia(allPublicaciones, id, texto);
        }
        return null;
    }

    private Publicacion addPublicacionTexto(ArrayList<Publicacion> allPublicaciones, String id, String texto) {
        return new Publicacion(id, null, texto, new ArrayList<Valoracion>(), new ArrayList<Comentario>(),
                               new ArrayList<Timeline>());
    }

    private Publicacion addPublicacionEnlace(ArrayList<Publicacion> allPublicaciones, String id, String texto) {
        Scanner scanTexto = new Scanner(System.in);
        System.out.println("Introduce el link del enlace: ");
        String link = scanTexto.nextLine();
        InputStream response = null;
        try {
            String url = link;
            response = new URL(url).openStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            link = responseBody.substring(responseBody.indexOf("<title>") + 7,
                                          responseBody.indexOf("</title>")) + " - ";

        } catch (IOException ex) {
            System.out.println("Link no valido. La publicacion no ha sido añadida");
        }
        return new Enlace(id, null, new Date(), texto, 0, 0, link);
    }

    private Publicacion addPublicacionReferencia(ArrayList<Publicacion> allPublicaciones, String id, String texto) {
        return null; //new Publicacion(id, null, texto, new ArrayList<Valoracion>(), new ArrayList<Comentario>(), new ArrayList<Timeline>());
    }

    private void mostrarComentarios() {
        System.out.println("\nComentarios:\n");
        for (Comentario comentario : comentarios) {
            comentario.mostrarComentario();
            System.out.println("++++++");
        }
        System.out.println("\n-------------------------------------------------------------");
    }

    private boolean seleccionarComentario() {
        boolean accionValida = false, goBack = false;

        while (!accionValida) {
            System.out.println(
                    "\nIntroduzca el número de comentario (1 para el primero) que desea ver.\nSi desea salir, introduzca cualquier caracter no numérico");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int index = scan.nextInt();
                if (index >= 1) {
                    if (index <= this.comentarios.size()) {
                        System.out.println("\nElegido el comentario #" + index);
                        accionValida = !comentarios.get(index - 1).menu();
                    } else {
                        System.out.println("\nNo hay suficientes comentarios, por favor escoja un número más bajo, " +
                                                   "o un caracter para salir");
                    }
                } else {
                    System.out.println("\nPor favor, introduzca un número válido, o un caracter para salir");
                }
            } else {
                accionValida = true;
                goBack = true;
            }
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    @Override
    public int compareTo(Publicacion p) { //para ordenar cronológicamente
        return this.fecha.compareTo(p.getFecha()) * -1;
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
        boolean goBack = false;
        if (this.poster == Sistema.getCurrentUser()) {
            goBack = ownerMenu();
        } else {
            goBack = normalMenu();
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    public boolean normalMenu() {
        boolean accionValida = false, goBack = false;
        CuentaUsuario currentUser = Sistema.getCurrentUser();
        while (!accionValida) {
            System.out.println("\nOpciones de Publicacion:\n0 - Ver comentarios\n1 - Comentar\n2 - Referenciar\n9 - " +
                                       "Volver atrás");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    if (comentarios.size() == 0) {
                        System.out.println("\nEsta publicacion no posee comentarios");
                    } else {
                        mostrarComentarios(); //TODO: cambiar esto como se deba
                        accionValida = !seleccionarComentario();
                    }
                    break;
                case 1:
                    accionValida = true;

                    Sistema.getCurrentUser().comentarPublicacion(this);
                    break;
                case 2:
                    accionValida = true;
                    //currentUser.publicar("referencia", this); //TODO: cambiar esto como se deba
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("\nPor favor, introduzca un número válido");
            }
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }


    public boolean ownerMenu() {
        boolean accionValida = false, goBack = false;
        CuentaUsuario currentUser = Sistema.getCurrentUser();
        System.out.println("Opciones de Publicación:\n0 - Borrar\n1 - Comentar\n2 - Valorar\n9 - Volver atrás");
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
                    Sistema.getCurrentUser().comentarPublicacion(this);
                    break;
                case 2:
                    accionValida = true;
                    Sistema.getCurrentUser().valorarPublicacion(this);
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("\nPor favor, introduzca un número válido");
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
