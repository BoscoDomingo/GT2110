package pkg;

import controllers.DatabaseController;
import publicaciones.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Sistema {

    private static DatabaseController usersDBController;
    private static DatabaseController publicacionesDBController;
    private static DatabaseController comentariosDBController;
    private static DatabaseController valoracionesDBController;
    private static DatabaseController comunidadesDBController;

    private static CuentaUsuario currentUser;
    private static Timeline timeline = new Timeline(new ArrayList<>());

    private static HashMap<String, CuentaUsuario> allUsers;
    private static HashMap<String, Publicacion> allPublicaciones;
    private static HashMap<String, Comentario> allComentarios;
    private static HashMap<String, Valoracion> allValoraciones;


    private static String lastUserID;
    private static String lastPublicacionID;
    private static String lastComentarioID;
    private static String lastValoracionID;
    private static String lastComunidadID;

    private static Sistema instanciacion;


    public static Sistema getSistema() {
        if (instanciacion == null) {
            instanciacion = new Sistema();
        }
        return instanciacion;
    }

    private Sistema() {

        this.usersDBController = new DatabaseController("src/main/resources/USERS.txt");
        this.allUsers = inicializarUsers();

        this.publicacionesDBController = new DatabaseController("src/main/resources/TWEETS.txt");
        this.allPublicaciones = inicializarPublicaciones();

        this.comentariosDBController = new DatabaseController("src/main/resources/COMMENTS.txt");
        this.allComentarios = inicializarComentarios();

        this.valoracionesDBController = new DatabaseController("src/main/resources/VALORACIONES.txt");
        this.allValoraciones = inicializarValoraciones();

        actualizarUsuarios();
        actualizarPublicaciones();
        actualizarReferencias();
    }

    private HashMap<String, CuentaUsuario> inicializarUsers() { //requiere actualizar todas las colecciones
        HashMap<String, CuentaUsuario> usersTratados = new HashMap<>();
        try {
            for (ArrayList<String> userFormatoArrayList : usersDBController.getResponseBD()) {
                usersTratados.put(userFormatoArrayList.get(0),
                                  new CuentaUsuario(userFormatoArrayList.get(0), userFormatoArrayList.get(5),
                                                    userFormatoArrayList.get(1)));
                this.lastUserID = userFormatoArrayList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error en inicialización de usuarios: " + e);
        }
        return usersTratados;
    }

    private HashMap<String, Publicacion> inicializarPublicaciones() { //requiere actualizar todas las colecciones
        HashMap<String, Publicacion> publicacionesTratadas = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        try {
            for (ArrayList<String> publicacionFormatoArrayList : publicacionesDBController.getResponseBD()) {
                String id = publicacionFormatoArrayList.get(0);
                String tipo = publicacionFormatoArrayList.get(1);
                CuentaUsuario poster = (CuentaUsuario) this.allUsers.get(publicacionFormatoArrayList.get(2));
                Date fecha = dateFormat.parse(publicacionFormatoArrayList.get(3));
                String texto = publicacionFormatoArrayList.get(4);
                int numeroLikes = Integer.parseInt(publicacionFormatoArrayList.get(5));
                int numeroDislikes = Integer.parseInt(publicacionFormatoArrayList.get(6));
                switch (tipo) {
                    case "T":
                        publicacionesTratadas.put(publicacionFormatoArrayList.get(0),
                                                  new Publicacion(id, poster, fecha, texto, numeroLikes,
                                                                  numeroDislikes));
                        break;
                    case "E":
                        String enlace = publicacionFormatoArrayList.get(9);
                        publicacionesTratadas.put(publicacionFormatoArrayList.get(0),
                                                  new Enlace(id, poster, fecha, texto, numeroLikes, numeroDislikes,
                                                             enlace));
                        break;
                    case "R":
                        publicacionesTratadas.put(publicacionFormatoArrayList.get(0),
                                                  new Referencia(id, poster, fecha, texto, numeroLikes, numeroDislikes,
                                                                 null));
                        break;
                }
                this.lastPublicacionID = publicacionFormatoArrayList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error en inicializacion de publicaciones: " + e);
        }
        return publicacionesTratadas;
    }

    private HashMap<String, Comentario> inicializarComentarios() { //no requiere actualizar, se crean enteros
        HashMap<String, Comentario> comentariosTratados = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        try {
            for (ArrayList<String> comentarioFormatoArrayList : comentariosDBController.getResponseBD()) {
                comentariosTratados.put(comentarioFormatoArrayList.get(0),
                                        new Comentario(comentarioFormatoArrayList.get(0),
                                                       dateFormat.parse(comentarioFormatoArrayList.get(1)),
                                                       comentarioFormatoArrayList.get(2),
                                                       this.allPublicaciones.get(comentarioFormatoArrayList.get(3)),
                                                       this.allUsers.get(comentarioFormatoArrayList.get(4)),
                                                       new ArrayList<>()));//TODO: inicializar atributos correctamente
                this.lastComentarioID = comentarioFormatoArrayList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error en inicializacion de comentarios: ");
            e.printStackTrace();
        }
        return comentariosTratados;
    }

    private HashMap<String, Valoracion> inicializarValoraciones() { //no requiere actualizar, se crean enteras
        HashMap<String, Valoracion> valoracionesTratadas = new HashMap<>();
        try {
            for (ArrayList<String> valoracionFormatoArrayList : valoracionesDBController.getResponseBD()) {
                valoracionesTratadas.put(valoracionFormatoArrayList.get(0),
                                         new Valoracion(valoracionFormatoArrayList.get(0),
                                                        Integer.parseInt(valoracionFormatoArrayList.get(1)),
                                                        this.allUsers.get(valoracionFormatoArrayList.get(2)),
                                                        this.allPublicaciones.get(valoracionFormatoArrayList.get(3))));
                this.lastValoracionID = valoracionFormatoArrayList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error en inicializacion de valoraciones: ");
            e.printStackTrace();
        }
        return valoracionesTratadas;
    }

    private void actualizarUsuarios() {
        for (ArrayList<String> userFormatoArrayList : usersDBController.getResponseBD()) { //Vamos usuario a usuario
            try {
                CuentaUsuario userToUpdate = this.allUsers.get(userFormatoArrayList.get(0));

                //metemos los seguidos
                ArrayList<String> listaSeguidos = usersDBController.tratarLista(userFormatoArrayList.get(6));
                ArrayList<CuentaUsuario> seguidosAInsertar = new ArrayList<>();
                for (String idUsuario : listaSeguidos) {
                    if (!idUsuario.equals("VACIO")) {
                        seguidosAInsertar.add(this.allUsers.get(idUsuario));
                    }
                }
                userToUpdate.setSigueA(seguidosAInsertar);

                //metemos los seguidores
                ArrayList<String> listaSeguidores = usersDBController.tratarLista(userFormatoArrayList.get(7));
                ArrayList<CuentaUsuario> seguidoresAInsertar = new ArrayList<>();
                for (String idUsuario : listaSeguidores) {
                    if (!idUsuario.equals("VACIO")) {
                        seguidoresAInsertar.add(this.allUsers.get(idUsuario));
                    }
                }
                userToUpdate.setSeguidores(seguidoresAInsertar);

                //metemos las publicaciones
                ArrayList<String> listaPublicaciones = usersDBController.tratarLista(userFormatoArrayList.get(9));
                ArrayList<Publicacion> publicacionesAInsertar = new ArrayList<>();
                for (String idPublicacion : listaPublicaciones) {
                    if (!idPublicacion.equals("VACIO")) {
                        publicacionesAInsertar.add(this.allPublicaciones.get(idPublicacion));
                    }
                }
                userToUpdate.setPublicaciones(publicacionesAInsertar);

                //metemos valoraciones
                ArrayList<String> listaValoraciones = usersDBController.tratarLista(userFormatoArrayList.get(10));
                ArrayList<Valoracion> valoracionesAInsertar = new ArrayList<>();
                for (String idValoracion : listaValoraciones) {
                    if (!idValoracion.equals("VACIO")) {
                        valoracionesAInsertar.add(this.allValoraciones.get(idValoracion));
                    }
                }
                userToUpdate.setValoraciones(valoracionesAInsertar);

                //metemos comentarios
                ArrayList<String> listaComentarios = usersDBController.tratarLista(userFormatoArrayList.get(12));
                ArrayList<Comentario> comentariosAInsertar = new ArrayList<>();
                for (String idComentario : listaComentarios) {
                    if (!idComentario.equals("VACIO")) {
                        comentariosAInsertar.add(this.allComentarios.get(idComentario));
                    }
                }
                userToUpdate.setComentarios(comentariosAInsertar);

            } catch (Exception e) {
                System.out.println("Error al actualizar usuarios: ");
                e.printStackTrace();
            }
        }
    }

    private void actualizarPublicaciones() {
        for (ArrayList<String> publicacionFormatoArrayList : publicacionesDBController.getResponseBD()) { //Vamos
            // publicacion a publicacion
            try {
                Publicacion publicacionToUpdate = this.allPublicaciones.get(publicacionFormatoArrayList.get(0));

                //metemos valoraciones
                ArrayList<String> listaValoraciones = publicacionesDBController.tratarLista(
                        publicacionFormatoArrayList.get(7));
                ArrayList<Valoracion> valoracionesAInsertar = new ArrayList<>();
                for (String idValoracion : listaValoraciones) {
                    if (!idValoracion.equals("VACIO")) {
                        valoracionesAInsertar.add(this.allValoraciones.get(idValoracion));
                    }
                }
                publicacionToUpdate.setValoraciones(valoracionesAInsertar);

                //metemos comentarios
                ArrayList<String> listaComentarios = publicacionesDBController.tratarLista(
                        publicacionFormatoArrayList.get(8));
                ArrayList<Comentario> comentariosAInsertar = new ArrayList<>();
                for (String idComentario : listaComentarios) {
                    if (!idComentario.equals("VACIO")) {
                        comentariosAInsertar.add(this.allComentarios.get(idComentario));
                    }
                }
                publicacionToUpdate.setComentarios(comentariosAInsertar);
            } catch (Exception e) {
                System.out.println("Error al actualizar publicaciones: " + e);
            }
        }
    }

    public static void putPublicacionAllPublicaciones(Publicacion nuevaPublicacion){
        allPublicaciones.put(nuevaPublicacion.getId(), nuevaPublicacion);
    }

    private void actualizarReferencias() {
        try {
            for (ArrayList<String> referenciaFormatoArrayList : this.publicacionesDBController.getResponseBD()) {
                if (referenciaFormatoArrayList.get(1).equals("R")) {
                    Referencia referencia = (Referencia) this.allPublicaciones.get(referenciaFormatoArrayList.get(0));
                    referencia.setReferenciada(this.allPublicaciones.get(referenciaFormatoArrayList.get(9)));
                }
            }
        } catch (Exception e) {
            System.out.println("Error actualizando referencias");
            e.printStackTrace();
        }
    }

    private static void actualizarTimeline(){
        //metemos en el timeline del currentUser las publicaciones de los que sigue
        for (CuentaUsuario seguido : currentUser.getSigueA()) {
            timeline.addPublicaciones(seguido.getPublicaciones());
        }
       timeline.sort(); //ordenadas cronológicamente
    }

    public static void setCurrentUser(String email) {
        try {
            for (ArrayList<String> userFormatoArrayList : usersDBController.getResponseBD()) { //Vamos usuario a usuario
                if (userFormatoArrayList.get(1).equals(email)) {
                    currentUser = allUsers.get(userFormatoArrayList.get(0));
                    break;
                }
            }
            actualizarTimeline();
        } catch (Exception e) {
            System.out.println("Error al actualizar currentUser: " + e);
        }
    }

    public static DatabaseController getUsersDBController() {
        return usersDBController;
    }

    public static DatabaseController getPublicacionesDBController() {
        return publicacionesDBController;
    }

    public static DatabaseController getComentariosDBController() {
        return comentariosDBController;
    }

    public static DatabaseController getValoracionesDBController() {
        return valoracionesDBController;
    }

    public static DatabaseController getComunidadesDBController() {
        return comunidadesDBController;
    }

    public static CuentaUsuario getCurrentUser() {
        return currentUser;
    }

    public static CuentaUsuario getUserByID(String id) {
        return allUsers.get(id);
    }

    public static String getLastUserID() {
        return lastUserID;
    }

    public static void setLastUserID(String lastUserID) {
        Sistema.lastUserID = lastUserID;
    }

    public static String getLastPublicacionID() {
        return lastPublicacionID;
    }

    public static void setLastPublicacionID(String lastPublicacionID) {
        Sistema.lastPublicacionID = lastPublicacionID;
    }

    public static String getLastComentarioID() {
        return lastComentarioID;
    }

    public static void setLastComentarioID(String lastComentarioID) {
        Sistema.lastComentarioID = lastComentarioID;
    }

    public static String getLastValoracionID() {
        return lastValoracionID;
    }

    public static void setLastValoracionID(String lastValoracionID) {
        Sistema.lastValoracionID = lastValoracionID;
    }

    public static String getLastComunidadID() {
        return lastComunidadID;
    }

    public static void setLastComunidadID(String lastComunidadID) {
        Sistema.lastComunidadID = lastComunidadID;
    }

    public static HashMap<String, Publicacion> getAllPublicaciones() {
        return allPublicaciones;
    }

    public static Timeline getTimeline() {
        return timeline;
    }

    public static void setTimeline(Timeline timeline) {
        Sistema.timeline = timeline;
    }

}