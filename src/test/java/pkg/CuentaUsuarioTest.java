package pkg;

import org.junit.Test;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CuentaUsuarioTest {

    @Test
   public void valorarPublicacion() {
        CuentaUsuario cuenta = new CuentaUsuario("b0001", "Pepote1", "email3@upm.es", null, null, null, null, new ArrayList<>());
        CuentaUsuario cuenta2 = new CuentaUsuario("b0002", "Juan1", "email80@upm.es", null, null, null, null, new ArrayList<>());
        Publicacion publicacion = new Publicacion("h0001", cuenta, "Buah", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cuenta2.valorarPublicacion(publicacion,1);
    }
}