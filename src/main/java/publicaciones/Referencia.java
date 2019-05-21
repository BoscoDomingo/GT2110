package main.java.publicaciones;

<<<<<<< HEAD
import pkg.CuentaUsuario;

import java.util.Date;
=======
import main.java.pkg.CuentaUsuario;
import main.java.pkg.Timeline;

import java.util.ArrayList;
>>>>>>> Luis

public class Referencia extends Publicacion {
    private Publicacion referenciada;

    public Referencia(String id, CuentaUsuario usuario, Date fecha, String texto, int numeroLikes, int numeroDislikes,
                      Publicacion referenciada) {
        super(id, usuario, fecha, texto, numeroLikes, numeroDislikes);
        this.referenciada = referenciada;
    }

    public Publicacion getReferenciada() {
        return referenciada;
    }

    public void setReferenciada(Publicacion referenciada) {
        this.referenciada = referenciada;
    }
}
