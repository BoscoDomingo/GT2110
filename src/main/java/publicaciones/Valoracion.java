package publicaciones;

import pkg.CuentaUsuario;

public class Valoracion {
    private final String id;
    private int likeDislike;
    private CuentaUsuario valorador;
    private Publicacion publicacionValorada;

    public Valoracion(String id, int likeDislike, CuentaUsuario valorador, Publicacion publicacionValorada) {
        this.id = id;
        this.likeDislike = likeDislike;
        this.valorador = valorador;
        this.publicacionValorada = publicacionValorada;
    }

    public int getLikeDislike() {
        return likeDislike;
    }
}
