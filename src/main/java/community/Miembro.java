package community;

import interfaces.IMiembro;
import pkg.CuentaUsuario;
import pkg.Perfil;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class Miembro extends CuentaUsuario implements IMiembro {
    private ArrayList<Comunidad> comunidades;

    public Miembro(String id, String alias, String correoUPM, ArrayList<CuentaUsuario> sigueA,
                   ArrayList<CuentaUsuario> seguidores, ArrayList<Comunidad> comunidades,
                   ArrayList<Publicacion> publicaciones, ArrayList<Valoracion> valoraciones,
                   ArrayList<Comunidad> comunidades1) {
        super(id, alias, correoUPM, sigueA, seguidores, comunidades, publicaciones, valoraciones);
        this.comunidades = comunidades1;
    }

    public void dejarComuniad(){};
    public void postear(){};
    private void volverACuentaUsuario(){};

    public String getAlias() {
        return null;
    }//invocada si perteneceA.isEmpty() == true;
}
