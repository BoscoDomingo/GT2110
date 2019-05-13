package publicaciones;

import pkg.CuentaUsuario;

import java.util.ArrayList;

public class PublicacionTest {


    @org.junit.Test
    public void show() {
        CuentaUsuario cuenta = new CuentaUsuario("a0001", "Pepote", "email3@upm.es", null, null, null, null, null,
                                                 null);
        Publicacion publicacion = new Publicacion("f0001", cuenta, "TEXTO DE EJEMPLO", new ArrayList<>(),
                                                  new ArrayList<>(), new ArrayList<>());
        publicacion.show();
    }
}