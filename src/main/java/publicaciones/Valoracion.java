package publicaciones;

import pkg.CuentaUsuario;

public class Valoracion {
    private final String id;
    private int likeDislike;
    private final CuentaUsuario valorador;
    private final Publicacion publicacionValorada;

    public Valoracion(String id, int likeDislike, CuentaUsuario valorador, Publicacion publicacionValorada) {
        this.id = id;
        this.likeDislike = likeDislike;
        this.valorador = valorador;
        this.publicacionValorada = publicacionValorada;
    }

    public String getId() {
        return id;
    }

    public int getLikeDislike() {
        return likeDislike;
    }

    /*public void setLikeDislike(int likeDislike) {
        this.likeDislike = likeDislike;
        publicacionValorada.
    }*/

    public CuentaUsuario getValorador() {
        return valorador;
    }

    public Publicacion getPublicacionValorada() {
        return publicacionValorada;
    }

    public void setLikeDislike(int likeDislike) {
        this.likeDislike = likeDislike;
    }
}
