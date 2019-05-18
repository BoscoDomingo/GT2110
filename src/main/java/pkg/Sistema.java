package pkg;

import community.AdministradorComunidad;
import community.Comunidad;
import community.Miembro;
import controllers.DatabaseController;
import publicaciones.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Sistema {

    private DatabaseController usersDBController;
    private DatabaseController publicacionesDBController;
    private DatabaseController comentariosDBController;
    private DatabaseController valoracionesDBController;
    private DatabaseController comunidadesDBController;

    private CuentaUsuario currentUser;

    private HashMap<String, CuentaUsuario> allUsers;
    private HashMap<String, Publicacion> allPublicaciones;
    private HashMap<String, Comentario> allComentarios;
    private HashMap<String, Valoracion> allValoraciones;
    private HashMap<String, Comunidad> allComunidades;

    private String lastUserID;
    private String lastPublicacionID;
    private String lastComentarioID;
    private String lastValoracionID;
    private String lastComunidadID;


    public Sistema(String email) {

        this.usersDBController = new DatabaseController("src/main/resources/USERS.txt");
        this.allUsers = inicializarUsers();

        this.publicacionesDBController = new DatabaseController("src/main/resources/TWEETS.txt");
        this.allPublicaciones = inicializarPublicaciones();

        this.comentariosDBController = new DatabaseController("src/main/resources/COMMENTS.txt");
        this.allComentarios = inicializarComentarios();

        this.valoracionesDBController = new DatabaseController("src/main/resources/VALORACIONES.txt");
        this.allValoraciones = inicializarValoraciones();

        this.comunidadesDBController = new DatabaseController("src/main/resources/COMMUNITIES.txt");
        this.allComunidades = inicializarComunidades();

        actualizarUsuarios(email);
        actualizarPublicaciones();
    }

    private HashMap<String, CuentaUsuario> inicializarUsers() { //requiere actualizar todas las colecciones
        HashMap<String, CuentaUsuario> usersTratados = new HashMap<>();
        try {
            for (ArrayList<String> userFormatoArrayList : usersDBController.getResponseBD()) {
                usersTratados.put(userFormatoArrayList.get(0),
                                  new CuentaUsuario(userFormatoArrayList.get(0), userFormatoArrayList.get(5),
                                                    userFormatoArrayList.get(1)));
            }
        } catch (Exception e) {
            System.out.println("Error en inicialización de usuarios: " + e);
        }
        return usersTratados;
    }

    private HashMap<String, Publicacion> inicializarPublicaciones() { //requiere actualizar todas las colecciones
        HashMap<String, Publicacion> publicacionesTratadas = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        try {
            for (ArrayList<String> publicacionFormatoArrayList : publicacionesDBController.getResponseBD()) {
                String id = publicacionFormatoArrayList.get(0);
                String tipo = publicacionFormatoArrayList.get(1);
                CuentaUsuario poster = (CuentaUsuario) this.allUsers.get(publicacionFormatoArrayList.get(2));
                Date fecha = dateFormat.parse(publicacionFormatoArrayList.get(3), new ParsePosition(0));
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

            }
        } catch (Exception e) {
            System.out.println("Error en inicializacion de publicaciones: " + e);
        }
        return publicacionesTratadas;
    }

    private HashMap<String, Comentario> inicializarComentarios() { //no requiere actualizar, se crean enteros
        HashMap<String, Comentario> comentariosTratados = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        try {
            for (ArrayList<String> comentarioFormatoArrayList : comentariosDBController.getResponseBD()) {
                comentariosTratados.put(comentarioFormatoArrayList.get(0),
                                        new Comentario(comentarioFormatoArrayList.get(0),
                                                       dateFormat.parse(comentarioFormatoArrayList.get(1),
                                                                        new ParsePosition(0)),
                                                       comentarioFormatoArrayList.get(2),
                                                       this.allPublicaciones.get(comentarioFormatoArrayList.get(3)),
                                                       this.allUsers.get(comentarioFormatoArrayList.get(5))));
            }
        } catch (Exception e) {
            System.out.println("Error en inicializacion de comentarios: " + e);
        }
        return comentariosTratados;
    }

    private HashMap<String, Valoracion> inicializarValoraciones() { //no requiere actualizar, se crean enteras
        HashMap<String, Valoracion> valoracionesTratadas = new HashMap<>();
        try {
            for (ArrayList<String> valoracionFormatoArrayList : comentariosDBController.getResponseBD()) {
                valoracionesTratadas.put(valoracionFormatoArrayList.get(0),
                                         new Valoracion(valoracionFormatoArrayList.get(0),
                                                        Integer.parseInt(valoracionFormatoArrayList.get(1)),
                                                        this.allUsers.get(valoracionFormatoArrayList.get(5)),
                                                        this.allPublicaciones.get(valoracionFormatoArrayList.get(3))));
            }
        }catch (Exception e){
            System.out.println("Error en inicializacion de valoraciones: "+ e);
        }
        return valoracionesTratadas;
    }

    private HashMap<String, Comunidad> inicializarComunidades() { //no requiere actualizar, se crean enteras
        HashMap<String, Comunidad> comunidadesTratadas = new HashMap<>();
        try {
            for (ArrayList<String> comunidadFormatoArrayList : comunidadesDBController.getResponseBD()) {
                //Añadir miembros y admins
                ArrayList<Miembro> miembros = new ArrayList<>();
                ArrayList<AdministradorComunidad> admins = new ArrayList<>();

                ArrayList<String> miembrosString = comunidadesDBController.tratarLista(
                        comunidadFormatoArrayList.get(2));
                ArrayList<String> adminsString = comunidadesDBController.tratarLista(comunidadFormatoArrayList.get(3));

                for (String idMiembro : miembrosString) {
                    miembros.add((Miembro) this.allUsers.get(idMiembro));
                }

                for (String idAdmin : adminsString) {
                    admins.add((AdministradorComunidad) this.allUsers.get(idAdmin));
                }

                //Añadir publicaciones
                ArrayList<Publicacion> publicacionesTimeline = new ArrayList<>();
                for (CuentaUsuario usuario : miembros) {
                    publicacionesTimeline.addAll(usuario.getPublicaciones());
                }
                for (CuentaUsuario usuario : admins) {
                    publicacionesTimeline.addAll(usuario.getPublicaciones());
                }
                Timeline timeline = new Timeline(publicacionesTimeline);
                timeline.sort();

                //Crear la comunidad
                comunidadesTratadas.put(comunidadFormatoArrayList.get(0),
                                        new Comunidad(comunidadFormatoArrayList.get(0),
                                                      comunidadFormatoArrayList.get(1),
                                                      timeline, miembros, admins));
            }
        }catch (Exception e){
            System.out.println("Error en inicializacion de Comunidades: "+e);
        }
        return comunidadesTratadas;
    }

    private void actualizarUsuarios(String email) {
        for (ArrayList<String> userFormatoArrayList : usersDBController.getResponseBD()) { //Vamos usuario a usuario
            try {
                CuentaUsuario userToUpdate = this.allUsers.get(userFormatoArrayList.get(0));


                //metemos los seguidos
                ArrayList<String> listaSeguidos = usersDBController.tratarLista(userFormatoArrayList.get(6));
                ArrayList<CuentaUsuario> seguidosAInsertar = new ArrayList<>();
                for (String idUsuario : listaSeguidos) {
                    seguidosAInsertar.add(this.allUsers.get(idUsuario));
                }
                userToUpdate.setSigueA(seguidosAInsertar);

                //metemos los seguidores
                ArrayList<String> listaSeguidores = usersDBController.tratarLista(userFormatoArrayList.get(7));
                ArrayList<CuentaUsuario> seguidoresAInsertar = new ArrayList<>();
                for (String idUsuario : listaSeguidores) {
                    seguidoresAInsertar.add(this.allUsers.get(idUsuario));
                }
                userToUpdate.setSeguidores(seguidoresAInsertar);

                //metemos las comunidades
                ArrayList<String> listaComunidades = usersDBController.tratarLista(userFormatoArrayList.get(8));
                ArrayList<Comunidad> comunidadesAInsertar = new ArrayList<>();
                for (String idComunidad : listaComunidades) {
                    comunidadesAInsertar.add(this.allComunidades.get(idComunidad));
                }
                userToUpdate.setComunidades(comunidadesAInsertar);

                //metemos las publicaciones
                ArrayList<String> listaPublicaciones = usersDBController.tratarLista(userFormatoArrayList.get(9));
                ArrayList<Publicacion> publicacionesAInsertar = new ArrayList<>();
                for (String idPublicacion : listaPublicaciones) {
                    publicacionesAInsertar.add(this.allPublicaciones.get(idPublicacion));
                }
                userToUpdate.setPublicaciones(publicacionesAInsertar);

                //metemos valoraciones
                ArrayList<String> listaValoraciones = usersDBController.tratarLista(userFormatoArrayList.get(10));
                ArrayList<Valoracion> valoracionesAInsertar = new ArrayList<>();
                for (String idValoracion : listaValoraciones) {
                    valoracionesAInsertar.add(this.allValoraciones.get(idValoracion));
                }
                userToUpdate.setValoraciones(valoracionesAInsertar);

                //metemos comentarios
                ArrayList<String> listaComentarios = usersDBController.tratarLista(userFormatoArrayList.get(12));
                ArrayList<Comentario> comentariosAInsertar = new ArrayList<>();
                for (String idComentario : listaComentarios) {
                    comentariosAInsertar.add(this.allComentarios.get(idComentario));
                }
                userToUpdate.setComentarios(comentariosAInsertar);

                if (userToUpdate.getCorreoUPM().equals(email)) { //aprovechamos y miramos quién es el currentUser
                    this.currentUser = userToUpdate;
                }
            } catch (Exception e) {
                System.out.println("Error al actualizar usuarios: " + e);
            }
        }
    }

    private void actualizarPublicaciones() {
        for (ArrayList<String> publicacionFormatoArrayList : publicacionesDBController.getResponseBD()) { //Vamos usuario a usuario
            try {
                Publicacion publicacionToUpdate = this.allPublicaciones.get(publicacionFormatoArrayList.get(0));

                //metemos valoraciones
                ArrayList<String> listaValoraciones = publicacionesDBController.tratarLista(
                        publicacionFormatoArrayList.get(7));
                ArrayList<Valoracion> valoracionesAInsertar = new ArrayList<>();
                for (String idValoracion : listaValoraciones) {
                    valoracionesAInsertar.add(this.allValoraciones.get(idValoracion));
                }
                publicacionToUpdate.setValoraciones(valoracionesAInsertar);

                //metemos comentarios
                ArrayList<String> listaComentarios = publicacionesDBController.tratarLista(
                        publicacionFormatoArrayList.get(8));
                ArrayList<Comentario> comentariosAInsertar = new ArrayList<>();
                for (String idComentario : listaComentarios) {
                    comentariosAInsertar.add(this.allComentarios.get(idComentario));
                }
                publicacionToUpdate.setComentarios(comentariosAInsertar);

            } catch (Exception e) {
                System.out.println("Error al actualizar publicaciones: " + e);
            }
        }
    }

    public DatabaseController getUsersDBController() {
        return usersDBController;
    }

    public DatabaseController getPublicacionesDBController() {
        return publicacionesDBController;
    }

    public CuentaUsuario getCurrentUser() {
        return currentUser;
    }

    public CuentaUsuario getUserByID(String id) {
        return allUsers.get(id);
    }

    public String getLastUserID() {
        return lastUserID;
    }

    public void setLastUserID(String lastUserID) {
        this.lastUserID = lastUserID;
    }

    public String getLastPublicacionID() {
        return lastPublicacionID;
    }

    public void setLastPublicacionID(String lastPublicacionID) {
        this.lastPublicacionID = lastPublicacionID;
    }

    public String getLastComentarioID() {
        return lastComentarioID;
    }

    public void setLastComentarioID(String lastComentarioID) {
        this.lastComentarioID = lastComentarioID;
    }

    public String getLastValoracionID() {
        return lastValoracionID;
    }

    public void setLastValoracionID(String lastValoracionID) {
        this.lastValoracionID = lastValoracionID;
    }

    public String getLastComunidadID() {
        return lastComunidadID;
    }

    public void setLastComunidadID(String lastComunidadID) {
        this.lastComunidadID = lastComunidadID;
    }
}
