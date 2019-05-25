package interfaces;

import pkg.CuentaUsuario;
import pkg.Perfil;
import publicaciones.Comentario;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;
import java.util.Date;

public interface ICuentaUsuario {

    void seguir(String id);

    void valorarPublicacion(Publicacion publicacion);

    void comentarPublicacion(Publicacion publicacion);

    void comentarComentario(Comentario comentario);

    void borrarComentario(Comentario comentario);

    boolean mostrarPropiasPublicaciones();

    boolean publicar();

    void referenciar(Publicacion referencia);

    void addPublicacion(int selector, Publicacion referencia);

    void addPublicacion(int selector);

    void borrarPublicacion(int id);

    void follow(ArrayList<CuentaUsuario> newFollowed);

    void follow(CuentaUsuario newFollowed);

    void addSeguidores(ArrayList<CuentaUsuario> seguidores); //si a esta cuenta le siguen

    void addSeguidor(CuentaUsuario seguidor); //si a esta cuenta le siguen

    void removeSeguidores(ArrayList<CuentaUsuario> seguidores);//si a esta cuenta le dejan de seguir

    void addValoracion(Valoracion valoracion);

    void removeValoracion(Valoracion valoracion);

    void cerrarSesion();

    //GETTERS & SETTERS
    String getId();

    public String getAlias();

    void setAlias(String alias);

    String getCorreoUPM();

    void setCorreoUPM(String correoUPM);

    Perfil getPerfil();

    ArrayList<Valoracion> getValoraciones();

    ArrayList<CuentaUsuario> getSeguidores();

    ArrayList<CuentaUsuario> getSigueA();

    ArrayList<Publicacion> getPublicaciones();

    Date getUltimaSesion();

    ArrayList<Comentario> getComentarios();

    void setPublicaciones(ArrayList<Publicacion> publicaciones);

    void setSigueA(ArrayList<CuentaUsuario> sigueA);

    void setSeguidores(ArrayList<CuentaUsuario> seguidores);

    void setValoraciones(ArrayList<Valoracion> valoraciones);

    void setUltimaSesion(Date ultimaSesion);

    void setComentarios(ArrayList<Comentario> comentarios);
}
