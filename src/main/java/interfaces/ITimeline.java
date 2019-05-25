package interfaces;

import publicaciones.Publicacion;

import java.util.ArrayList;

public interface ITimeline {
    void showLastPage();

    void showPage(int pageNumber);

    void sort();

    void addPublicacion(Publicacion publicacion);

    void addPublicaciones(ArrayList<Publicacion> publicacionesAdd);

    void removePublicacion(Publicacion publicacion);

    void removePublicaciones(ArrayList<Publicacion> publicaciones);

    ArrayList<Publicacion> getPublicaciones();

    int getNumeroDePaginas();
}
