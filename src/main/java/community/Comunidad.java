package community;

import pkg.CuentaUsuario;
import pkg.Timeline;

import java.util.ArrayList;

public class Comunidad {
    private final String id;
    private String nombre;
    private Timeline timeline;
    private ArrayList<CuentaUsuario> miembros;
    private ArrayList<CuentaUsuario> admins;

    public Comunidad(String id, String nombre, Timeline timeline, ArrayList<CuentaUsuario> miembros,
                     ArrayList<CuentaUsuario> admins) {
        this.id = id;
        this.nombre = nombre;
        this.timeline = timeline;
        this.miembros = miembros;
        this.admins = admins;
    }


    //GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public ArrayList<CuentaUsuario> getMiembros() {
        return miembros;
    }

    public ArrayList<CuentaUsuario> getAdmins() {
        return admins;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}
