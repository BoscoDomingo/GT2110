package interfaces;

public interface ILoginController {
    boolean crearCuenta(String email, String password);

    boolean comprobarValidezCorreo(String email);

    boolean comprobarYaRegistrado(String email);

    boolean comprobarExistenciaCorreoAlumnado(String email);

    boolean comprobarExistenciaCorreoPersonal(String email);

    void cerrarSesion();

    boolean iniciarSesion(String email);

    void restaurarPass(String email);

    void olvidadoPass(String email);
}
