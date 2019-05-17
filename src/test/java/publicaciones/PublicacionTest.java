package publicaciones;

import pkg.CuentaUsuario;

import java.util.ArrayList;
import java.util.Date;

public class PublicacionTest {


    @org.junit.Test
    public void showingPublicacionAndAddingComentario() {
        CuentaUsuario cuenta = new CuentaUsuario("a0001", "Pepote", "email3@upm.es", null, null, null, null, null);
        CuentaUsuario cuenta2 = new CuentaUsuario("a0002", "Juan", "email80@upm.es", null, null, null, null, null);
        Publicacion publicacion = new Publicacion("f0001", cuenta, "Buah, pos el otro día me vi la nueva de avengers " +
                "y flipas colega", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Comentario comentario = new Comentario("c0001", new Date(), "ME MOLO MAZO TRONCO", publicacion, cuenta2);

        //publicacion.addComment(comentario);
        publicacion.show();
        //publicacion.showAsNew();
    }
}