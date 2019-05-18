package pkg;

import org.junit.Test;
import publicaciones.Publicacion;

import java.util.Date;

public class CuentaUsuarioTest {

    @Test
    public void valorarPublicacion() {
        CuentaUsuario cuenta = new CuentaUsuario("b0001", "Pepote1", "email3@upm.es");
        CuentaUsuario cuenta2 = new CuentaUsuario("b0002", "Juan1", "email80@upm.es");
        Publicacion publicacion = new Publicacion("h0001", cuenta, new Date(), "Buah", 0, 0);
        cuenta2.valorarPublicacion(publicacion, 1);
    }
}