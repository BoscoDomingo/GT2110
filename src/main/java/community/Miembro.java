package community;

import interfaces.IMiembro;
import pkg.CuentaUsuario;
import pkg.Perfil;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class Miembro extends CuentaUsuario implements IMiembro {
    private ArrayList<Comunidad> comunidades;

    public Miembro(String id, String alias, String correoUPM, ArrayList<Comunidad> comunidades) {
        super(id, alias, correoUPM);
        this.comunidades = comunidades;
    }

    public void dejarComuniad(){};
    public void postear(){};
    private void volverACuentaUsuario(){};

    public String getAlias() {
        return null;
    }//invocada si perteneceA.isEmpty() == true;
}
