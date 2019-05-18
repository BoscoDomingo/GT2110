package community;

import interfaces.IAdminComunidad;
import pkg.CuentaUsuario;
import pkg.Perfil;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class AdministradorComunidad extends community.Miembro implements IAdminComunidad {
    public AdministradorComunidad(String id, String alias, String correoUPM,
                                  ArrayList<Comunidad> comunidades) {
        super(id, alias, correoUPM, comunidades);
    }

    public void modificarPermisos() {
    }

    public void expulsarMiembros() {
    }

    public void validarMiembro() {
    }

}
