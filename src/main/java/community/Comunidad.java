package community;

import pkg.Timeline;

import java.util.ArrayList;

public class Comunidad {
    private final String id;
    private String nombre;
    private Timeline timeline;
    private ArrayList<Miembro> miembros;
    private ArrayList<AdministradorComunidad> admins;

    public Comunidad(String id, String nombre, Timeline timeline, ArrayList<Miembro> miembros,
                     ArrayList<AdministradorComunidad> admins) {
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

    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }

    public ArrayList<AdministradorComunidad> getAdmins() {
        return admins;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}
