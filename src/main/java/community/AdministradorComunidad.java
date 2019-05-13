package community;

import interfaces.IAdminComunidad;
import pkg.CuentaUsuario;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class AdministradorComunidad extends community.Miembro implements IAdminComunidad {
    public AdministradorComunidad(String id, String alias, String correoUPM,
                                  ArrayList<Valoracion> valoraciones,
                                  ArrayList<CuentaUsuario> seguidores,
                                  ArrayList<CuentaUsuario> sigueA,
                                  ArrayList<Comunidad> comunidades) {
        super(id, alias, correoUPM, valoraciones, seguidores, sigueA, comunidades);
    }

    public void modificarPermisos() {
    }

    public void expulsarMiembros() {
    }

    public void validarMiembro() {
    }

}
