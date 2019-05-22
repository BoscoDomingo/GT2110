package pkg;

import interfaces.ICuentaUsuario;
import publicaciones.Comentario;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CuentaUsuario implements ICuentaUsuario {
    private final String id;
    private final Perfil perfil;
    private String alias;
    private String correoUPM;
    private Date ultimaSesion;
    private ArrayList<CuentaUsuario> sigueA;
    private ArrayList<CuentaUsuario> seguidores;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Valoracion> valoraciones;
    private ArrayList<Comentario> comentarios;

    public CuentaUsuario(String id, String alias, String correoUPM) {
        this.id = id;
        this.alias = alias;
        this.correoUPM = correoUPM;
        this.perfil = new Perfil();
        this.sigueA = new ArrayList<>();
        this.seguidores = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public CuentaUsuario(String id, String alias, String correoUPM, ArrayList<CuentaUsuario> sigueA,
                         ArrayList<CuentaUsuario> seguidores, ArrayList<Publicacion> publicaciones,
                         ArrayList<Valoracion> valoraciones, ArrayList<Comentario> comentarios) {
        this.id = id;
        this.perfil = new Perfil();
        this.alias = alias;
        this.correoUPM = correoUPM;
        this.ultimaSesion = new Date();
        this.sigueA = sigueA;
        this.seguidores = seguidores;
        this.publicaciones = publicaciones;
        this.valoraciones = valoraciones;
        this.comentarios = comentarios;
    }

    public void seguir(String id) {
        CuentaUsuario nuevoSeguido = Sistema.getUserByID(id);
        this.sigueA.add(nuevoSeguido);
        nuevoSeguido.addSeguidor(this);
    }

    public void valorarPublicacion(Publicacion publicacion) {
        System.out.println("\n Desea dar like o dislike? \n\t1 - Like \n\t0 - Dislike");
        Scanner scan = new Scanner(System.in);
        int likeDislike = scan.nextInt();
        //TODO TIENES QUE COMPROBAR SI YA SE HA METIDO UNA VALORACION
        Valoracion nuevaValoracion = new Valoracion(Sistema.getLastValoracionID() + 1,
                                                    likeDislike, this, publicacion);
        publicacion.addValoracion(nuevaValoracion);
        this.valoraciones.add(nuevaValoracion);
    }

    public void comentarPublicacion(Publicacion publicacion) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca su comentario: \n");
        String texto = scan.nextLine();
        Comentario nuevoComentario = new Comentario(Sistema.getLastComentarioID() + 1, new Date(), texto,
                                                    null, publicacion, this, new ArrayList<>());
        this.comentarios.add(nuevoComentario);
        publicacion.addComentario(nuevoComentario);
    }

    public void comentarComentario(Comentario comentario) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca su comentario: \n");
        String texto = scan.nextLine();
        if (comentario.getRespondeA() == null) {
            responderComentario(comentario, texto);
        } else {
            responderRespuesta(comentario, texto);
        }
    }

    private void responderComentario(Comentario comentario, String textoRespuesta) {
        Comentario respuesta = new Comentario(Sistema.getLastComentarioID() + 1, new Date(), textoRespuesta, comentario,
                                              comentario.getPerteneceA(), this, null);
        comentario.addRespuesta(respuesta);
        this.comentarios.add(respuesta);
    }
    private void borrarComentario(Comentario comentario){
        String idComentario= comentario.getID();
        for (Comentario coment : comentarios) {
            if (coment.getID().equals(idComentario))
                comentarios.remove(comentario);
        }
    }

    private void responderRespuesta(Comentario respuestaComentario, String textoRespuesta) {
        Comentario nuevaRespuesta = new Comentario(Sistema.getLastComentarioID() + 1, new Date(), textoRespuesta,
                                                   respuestaComentario, respuestaComentario.getPerteneceA(), this,
                                                   null);

        if (respuestaComentario.getRespondeA().getRespuestas() != null) {
            respuestaComentario.getRespondeA().addRespuesta(nuevaRespuesta);
        } else {
            int i = 0, j = 0;
            boolean encontrado = false;
            Comentario coment = null;
            while (i < respuestaComentario.getPerteneceA().getComentarios().size() && !encontrado) {
                coment = respuestaComentario.getPerteneceA().getComentarios().get(i);
                while (j < coment.getRespuestas().size() && !encontrado) {
                    if (coment.getRespuestas().get(j) == respuestaComentario) {
                        encontrado = true;
                    }
                    j++;
                }
                i++;
            }
            coment.addRespuesta(nuevaRespuesta);
        }
        this.comentarios.add(nuevaRespuesta);
    }

    public boolean mostrarPropiasPublicaciones(){
        System.out.println("\n*****************ESTAS SON TUS PUBLICACIONES********************\n");
        for(Publicacion publicacion: publicaciones){
            publicacion.show();
        }
        System.out.println("\n*************************************\n");
        return perfil.menu();
    }

    public void publicar() {
    }

    public void borrarPublicacion(int id) {
        Sistema.getAllPublicaciones().remove(this.publicaciones.get(id).getId());
        this.publicaciones.remove(id);
    }

    public void follow(ArrayList<CuentaUsuario> newFollowed) {
        try {
            this.sigueA.addAll(newFollowed);
            for (CuentaUsuario followed : newFollowed) {
                followed.addSeguidor(this);
            }
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void follow(CuentaUsuario newFollowed) {
        try {
            this.sigueA.add(newFollowed);
            newFollowed.addSeguidor(this);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void addSeguidores(ArrayList<CuentaUsuario> seguidores) {
        try {
            this.seguidores.addAll(seguidores);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    } //si a esta cuenta le siguen

    public void addSeguidor(CuentaUsuario seguidor) {
        try {
            this.seguidores.add(seguidor);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    } //si a esta cuenta le siguen

    public void removeSeguidores(ArrayList<CuentaUsuario> seguidores) {
        try {
            this.seguidores.removeAll(seguidores);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }//si a esta cuenta le dejan de seguir

    public void addValoracion(Valoracion valoracion) {
        try {
            this.valoraciones.add(valoracion);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void removeValoracion(Valoracion valoracion) {
        try {
            this.valoraciones.remove(valoracion);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void cerrarSesion() {
        //cosas variadas
        try {
            this.ultimaSesion = new Date();
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    /*public void selectPublicacion(String idPublicacion) {
        Publicacion selected = DatabaseController.getPublicacionByID(idPublicacion);
        if (selected.getPoster().getId().equals(this.id)) {//prefiero comparar IDs a comparar objetos por temas de
            // referencias a memoria y tal
            selected.ownerMenu();
        } else {
            selected.menu();
        }
    }*/

    //GETTERS & SETTERS
    public String getId() {
        return id;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCorreoUPM() {
        return correoUPM;
    }

    public void setCorreoUPM(String correoUPM) {
        this.correoUPM = correoUPM;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public ArrayList<CuentaUsuario> getSeguidores() {
        return seguidores;
    }

    public ArrayList<CuentaUsuario> getSigueA() {
        return sigueA;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void setSigueA(ArrayList<CuentaUsuario> sigueA) {
        this.sigueA = sigueA;
    }

    public void setSeguidores(ArrayList<CuentaUsuario> seguidores) {
        this.seguidores = seguidores;
    }

    public void setValoraciones(ArrayList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
