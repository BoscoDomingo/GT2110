package pkg;

import org.junit.Test;
import publicaciones.Comentario;
import publicaciones.Publicacion;

import java.util.Date;

public class CuentaUsuarioTest {

    @Test
    public void pruebas() {
        CuentaUsuario cuenta = new CuentaUsuario("b0001", "Pepote1", "email3@upm.es");
        CuentaUsuario cuenta2 = new CuentaUsuario("b0002", "Juan1", "email80@upm.es");
        Publicacion publicacion = new Publicacion("h0001", cuenta, new Date(), "Buah", 0, 0);
        cuenta2.valorarPublicacion(publicacion, 1);
        cuenta2.comentarPublicacion(publicacion,"Esto es un comentario de una publicacion");
        cuenta.comentarComentario(publicacion.getComentarios().get(0), "Esto es una respuesta a un comentario");
        cuenta2.comentarComentario(publicacion.getComentarios().get(0).getRespuestas().get(0),"Respuesta a respuesta");
        cuenta.comentarComentario(publicacion.getComentarios().get(0).getRespuestas().get(1),"Respuesta a respuesta de respuesta");
        for(Comentario comentario: publicacion.getComentarios()){
            System.out.println(comentario.getTexto());
            for(Comentario respuesta: comentario.getRespuestas()){
                System.out.println("\t@" + respuesta.getEscritoPor().getAlias() + ": " + respuesta.getTexto());
            }
        }// Esto es una prueba, no quedará visualmente totalmente así


    }

}