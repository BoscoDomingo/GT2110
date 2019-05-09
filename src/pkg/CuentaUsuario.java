package pkg;

import interfaces.ICuentaUsuario;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class CuentaUsuario implements ICuentaUsuario {
    private String alias;
    private String correoUPM;
    private int tipoUsuario;
    private Perfil perfil;
    private ArrayList<Valoracion> valoraciones;
    private ArrayList<CuentaUsuario> seguidores;
    private ArrayList<CuentaUsuario> sigue;
}
