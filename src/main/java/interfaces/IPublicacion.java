package interfaces;

import pkg.CuentaUsuario;
import pkg.Timeline;
import publicaciones.Comentario;
import publicaciones.Valoracion;

import java.util.ArrayList;
import java.util.Date;

public interface IPublicacion {


    void show();

    void showAsNew();

    void addValoracion(Valoracion valoracion);

    void delete();

    void addComentario(Comentario comentario);

    void addComentarios(ArrayList<Comentario> comentarios);

    void removeComentario(Comentario comentario);

    void setValoraciones(ArrayList<Valoracion> valoraciones);

    void setComentarios(ArrayList<Comentario> comentarios);

    void setPerteneceATimelines(ArrayList<Timeline> perteneceATimelines);

    CuentaUsuario getPoster();

    Date getFecha();

    ArrayList<Comentario> getComentarios();

    String getId();

    ArrayList<Valoracion> getValoraciones();

    void cambiarValoracion(int nuevaValoracion);

    String getTexto();
}
