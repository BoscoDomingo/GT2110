package community;

import interfaces.IAdminComunidad;
import pkg.CuentaUsuario;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class AdministradorComunidad extends community.Miembro implements IAdminComunidad {
    public AdministradorComunidad(String id, String alias, String correoUPM, ArrayList<CuentaUsuario> sigueA,
                                  ArrayList<CuentaUsuario> seguidores,
                                  ArrayList<Comunidad> comunidades,
                                  ArrayList<Publicacion> publicaciones,
                                  ArrayList<Valoracion> valoraciones,
                                  ArrayList<Comunidad> comunidades1) {
        super(id, alias, correoUPM, sigueA, seguidores, comunidades, publicaciones, valoraciones, comunidades1);
    }

    public void modificarPermisos() {
    }

    public void expulsarMiembros() {
    }

    public void validarMiembro() {
    }

}
