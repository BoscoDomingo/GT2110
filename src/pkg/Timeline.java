package pkg;

import community.Comunidad;
import interfaces.ITimeline;
import publicaciones.Publicacion;

import java.util.ArrayList;

public class Timeline implements ITimeline {
    private ArrayList<Publicacion> publicaciones;
    private Perfil perfil;
    private Comunidad comunidad;
    
    public void eliminarPublicacion(){};
    public void mostrarTimeline(){};
}
