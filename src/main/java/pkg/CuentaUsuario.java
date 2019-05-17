package pkg;

import community.Comunidad;
import controllers.DatabaseController;
import interfaces.ICuentaUsuario;
import publicaciones.Publicacion;
import publicaciones.Valoracion;

import java.util.ArrayList;
import java.util.Date;

public class CuentaUsuario implements ICuentaUsuario {
    private final String id;
    private final Perfil perfil;
    private String alias;
    private String correoUPM;
    private Date ultimaSesion;
    private ArrayList<CuentaUsuario> sigueA;
    private ArrayList<CuentaUsuario> seguidores;
    private ArrayList<Comunidad> comunidades;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Valoracion> valoraciones;

    public CuentaUsuario(String id, String alias, String correoUPM, ArrayList<CuentaUsuario> sigueA,
                         ArrayList<CuentaUsuario> seguidores, ArrayList<Comunidad> comunidades,
                         ArrayList<Publicacion> publicaciones, ArrayList<Valoracion> valoraciones) {
        this.id = id;
        this.perfil = new Perfil();
        this.alias = alias;
        this.correoUPM = correoUPM;
        this.ultimaSesion = new Date();
        this.sigueA = sigueA;
        this.seguidores = seguidores;
        this.comunidades = comunidades;
        this.publicaciones = publicaciones;
        this.valoraciones = valoraciones;
    }

    public void publicar() {
    }

    public void borrarPublicacion() {
        //pregunta el ID de la publicacion a delete y llama a publicacion.delete()
    }

    public void follow(ArrayList<CuentaUsuario> newFollowed) {
        try {
            this.sigueA.addAll(newFollowed);
            for (CuentaUsuario followed : newFollowed) {
                followed.addSeguidor(this);
            }
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void follow(CuentaUsuario newFollowed) {
        try {
            this.sigueA.add(newFollowed);
            newFollowed.addSeguidor(this);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void addSeguidores(ArrayList<CuentaUsuario> seguidores) {
        try {
            this.seguidores.addAll(seguidores);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    } //si a esta cuenta le siguen

    public void addSeguidor(CuentaUsuario seguidor) {
        try {
            this.seguidores.add(seguidor);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    } //si a esta cuenta le siguen

    public void removeSeguidores(ArrayList<CuentaUsuario> seguidores) {
        try {
            this.seguidores.removeAll(seguidores);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }//si a esta cuenta le dejan de seguir

    public void addValoracion(Valoracion valoracion) {
        try {
            this.valoraciones.add(valoracion);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void removeValoracion(Valoracion valoracion) {
        try {
            this.valoraciones.remove(valoracion);
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    public void cerrarSesion() {
        //cosas variadas
        try {
            this.ultimaSesion = new Date();
        } catch (Exception e) {
            System.out.println("Algo ha fallado. Por favor pruebe de nuevo" + e.toString());
        }
    }

    /*public void selectPublicacion(String idPublicacion) {
        Publicacion selected = DatabaseController.getPublicacionByID(idPublicacion);
        if (selected.getPoster().getId().equals(this.id)) {//prefiero comparar IDs a comparar objetos por temas de
            // referencias a memoria y tal
            selected.ownerMenu();
        } else {
            selected.menu();
        }
    }*/

    //GETTERS & SETTERS
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

    public ArrayList<CuentaUsuario> getSeguidores() {
        return seguidores;
    }

    public ArrayList<CuentaUsuario> getSigueA() {
        return sigueA;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
