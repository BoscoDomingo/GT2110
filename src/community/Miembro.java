package community;

import interfaces.IMiembro;
import pkg.CuentaUsuario;

import java.util.ArrayList;

public class Miembro extends CuentaUsuario implements IMiembro {
    private ArrayList<Comunidad> comunidades;
    
    public void dejarComuniad(){};
    public void postear(){};
    private void volverACuentaUsuario(){};//invocada si perteneceA.isEmpty() == true;
}
