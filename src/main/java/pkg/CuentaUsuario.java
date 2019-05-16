package pkg;

import community.Comunidad;
import interfaces.ICuentaUsuario;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class CuentaUsuario implements ICuentaUsuario {
    private final String id;
    private String alias;
    private String correoUPM;
    private final Perfil perfil;
    private ArrayList<CuentaUsuario> sigueA;
    private ArrayList<CuentaUsuario> seguidores;
    private ArrayList<Comunidad> comunidades;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Valoracion> valoraciones;

    public CuentaUsuario(String id, String alias, String correoUPM, Perfil perfil,
                         ArrayList<CuentaUsuario> sigueA, ArrayList<CuentaUsuario> seguidores,
                         ArrayList<Comunidad> comunidades, ArrayList<Publicacion> publicaciones,
                         ArrayList<Valoracion> valoraciones) {
        this.id = id;
        this.alias = alias;
        this.correoUPM = correoUPM;
        this.perfil = new Perfil();
        this.sigueA = sigueA;
        this.seguidores = seguidores;
        this.comunidades = comunidades;
        this.publicaciones = publicaciones;
        this.valoraciones = valoraciones;
    }

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

    public void addValoracion(Valoracion valoracion) {
        this.valoraciones.add(valoracion);
    }

    public void removeValoracion(Valoracion valoracion) {
        this.valoraciones.remove(valoracion);
    }

    public ArrayList<CuentaUsuario> getSeguidores() {
        return seguidores;
    }

    public void addSeguidores(ArrayList<CuentaUsuario> seguidores) {
        this.seguidores.addAll(seguidores);
    }

    public void removeSeguidores(ArrayList<CuentaUsuario> seguidores) {
        this.seguidores.removeAll(seguidores);
    }

    public ArrayList<CuentaUsuario> getSigueA() {
        return sigueA;
    }

    public void follow(ArrayList<CuentaUsuario> newFollowed) {
        this.sigueA.addAll(newFollowed);
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
    public void publicar(){};

    public void borrarPublicacion(){
        //pregunta el ID de la publicacion a delete y llama a publicacion.delete()
    }
}
