package interfaces;

import controllers.DatabaseController;
import pkg.CuentaUsuario;

public interface ISistema {
    public DatabaseController getUsersDBController();

    public DatabaseController getPublicacionesDBController();

    public CuentaUsuario getCurrentUser();

    public CuentaUsuario getUserByID(String id);

    public String getLastUserID();

    public void setLastUserID(String lastUserID);

    public String getLastPublicacionID();

    public void setLastPublicacionID(String lastPublicacionID);

    public String getLastComentarioID();

    public void setLastComentarioID(String lastComentarioID);

    public String getLastValoracionID();

    public void setLastValoracionID(String lastValoracionID);

    public String getLastComunidadID();

    public void setLastComunidadID(String lastComunidadID);
}
