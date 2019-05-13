package pkg;

import interfaces.ICuentaUsuario;
import publicaciones.Valoracion;

import java.util.ArrayList;

public class CuentaUsuario implements ICuentaUsuario {
    private final String id;
    private String alias;
    private String correoUPM;
    private final Perfil perfil;
    private ArrayList<Valoracion> valoraciones;
    private ArrayList<CuentaUsuario> seguidores;
    private ArrayList<CuentaUsuario> sigueA;

    public CuentaUsuario(String id, String alias, String correoUPM, ArrayList<Valoracion> valoraciones,
                         ArrayList<CuentaUsuario> seguidores, ArrayList<CuentaUsuario> sigueA) {
        this.id = id;
        this.alias = alias;
        this.correoUPM = correoUPM;
        this.perfil = new Perfil();
        this.valoraciones = valoraciones;
        this.seguidores = seguidores;
        this.sigueA = sigueA;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCorreoUPM() {
        return correoUPM;
    }

    public void setCorreoUPM(String correoUPM) {
        this.correoUPM = correoUPM;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void addValoracion(Valoracion valoracion) {
        this.valoraciones.add(valoracion);
    }

    public void removeValoracion(Valoracion valoracion) {
        this.valoraciones.remove(valoracion);
    }

    public ArrayList<CuentaUsuario> getSeguidores() {
        return seguidores;
    }

    public void addSeguidores(ArrayList<CuentaUsuario> seguidores) {
        this.seguidores.addAll(seguidores);
    }

    public void removeSeguidores(ArrayList<CuentaUsuario> seguidores) {
        this.seguidores.removeAll(seguidores);
    }

    public ArrayList<CuentaUsuario> getSigueA() {
        return sigueA;
    }

    public void follow(ArrayList<CuentaUsuario> newFollowed) {
        this.sigueA.addAll(newFollowed);
    }
}
