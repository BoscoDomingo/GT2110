package controllers;

import interfaces.ILoginController;
import pkg.Sistema;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements ILoginController {

    private DatabaseController database;
    private HashMap<String, TimeController> cuentasBloqueadas;
    private HashMap<String, Integer> intentosDeInicioSesion;
    private TimeController tiempo;
    private Sistema sistema;

    public LoginController() {
        sistema = Sistema.getSistema();
        database = Sistema.getUsersDBController();
        intentosDeInicioSesion = new HashMap<>();
        cuentasBloqueadas = new HashMap<>();
    }

    @Override
    public boolean crearCuenta(String email, String password) {
        boolean exito = false;
        if (comprobarValidezCorreo(email)) {
            //creacion de la cuenta
        }
        return exito;

    }

    @Override
    public boolean comprobarValidezCorreo(String email) {
        boolean vale = false;
        if (!comprobarExistenciaCorreoAlumnado(email) && !comprobarExistenciaCorreoPersonal(email)) {
            System.out.println("Error, su correo no pertenece al alumnado de la UPM.");
        } else if (!comprobarYaRegistrado(email)) {
            System.out.println("Error, el correo ya ha sido registrado.");
        } else {
            System.out.println("Correcto, el correo pertenece al alumnado o personal de la UPM.");
            vale = true;
        }
        return vale;
    }

    @Override
    public boolean comprobarYaRegistrado(String email) {
        boolean existe = false;
        String respuesta = database.consultasParaBD(email, 2);
        if (respuesta.equals("error")) {
            existe = true;               //se comprueba que el correo ya estaba antes en la base de datos
        }
        return existe;
    }

    @Override
    public boolean comprobarExistenciaCorreoAlumnado(String email) {
        String reg = "^[A-Za-z0-9+_.-]+@(alumnos.upm.es)$"; //se comprueba que la extension es alumnos.upm.es
        Pattern patron = Pattern.compile(reg);
        Matcher matcher = patron.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean comprobarExistenciaCorreoPersonal(String email) {
        String reg = "^[A-Za-z0-9+_.-]+@(upm.es)$"; //se comprueba que la extension es upm.es
        Pattern patron = Pattern.compile(reg);
        Matcher matcher = patron.matcher(email);
        return matcher.matches();
    }

    @Override
    public void cerrarSesion() {
    }

    @Override
    public boolean iniciarSesion(String email) {
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        String respuesta = database.consultasParaBD(email, 2);
        if (respuesta.equals("error")) {
            System.out.println("Email no encontrado en la BD. ¿Está seguro de que ese es su correo?");
        } else if (!isAccountBlocked(email)) {
            System.out.println("Introduzca su contraseña");
            String password = scan.nextLine();
            if (password.equals(respuesta)) {//las contraseÃ±as son de mÃ­nimo 8 caracteres
                success = true;
                Sistema.setCurrentUser(email);
            } else {
                System.out.println("Contraseña errónea, vuelva a intentarlo.");
                intentoFallido(email);
            }
        } else if (isAccountBlocked(email)) {
            System.out.println(
                    "La cuenta está bloqueada porque ha sobrepasado el número de intentos de inicio de sesión. " +
                            "\nVuelva a intentarlo pasados 60 minutos desde el último intento");
            System.out.println("Te quedan " + (3600 - cuentasBloqueadas.get(
                    email).getSegundos()) / 60 + " minutos" + " y " + (60 - cuentasBloqueadas.get(
                    email).getSegundos()) + " segundos");
        }
        return success;
    }

    private void intentoFallido(String email) {
        if (intentosDeInicioSesion.containsKey(email)) {
            int incremento = intentosDeInicioSesion.get(email) + 1;
            intentosDeInicioSesion.replace(email, intentosDeInicioSesion.get(email), incremento);
            if (incremento == 3) {
                System.out.println(
                        "Se ha excedido en el número de intentos para acceder a la cuenta. \nCuenta bloqueada durante 60 minutos.");
                TimeController timerBloqueo = new TimeController(email);
                timerBloqueo.contar();
                cuentasBloqueadas.put(email, timerBloqueo);
            }
        } else {
            intentosDeInicioSesion.put(email, 1);
        }
    }

    private boolean isAccountBlocked(String email) {
        if (intentosDeInicioSesion.containsKey(email)) {
            if (intentosDeInicioSesion.get(email) >= 3) {
                if (cuentasBloqueadas.get(email).getSegundos() >= 3600) {
                    cuentasBloqueadas.get(email).detener();
                    cuentasBloqueadas.remove(email);
                    intentosDeInicioSesion.remove(email);
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void restaurarPass(String email) {
        Scanner in = new Scanner(System.in);
        String primeraPass;
        String segundaPass;
        System.out.println("\n-- Ha accedido al menu de restauración de contraseña. --");
        System.out.println(
                "-- Recuerde que para que se cambie la contraseña con éxito, tendrá que elegir una con un tamaño mayor que 8. -- ");
        System.out.println("-- Además ambos campos deben coincidir -- ");
        System.out.println("-- Si una de las dos condiciones de arriba no se cumple deberá volver a intentarlo --");
        do {
            System.out.println("Introduzca su nueva contraseña: ");
            primeraPass = in.nextLine();
            System.out.println("Introduzca la contraseña de nuevo: ");
            segundaPass = in.nextLine();
            if (!primeraPass.equals(segundaPass)) {
                System.out.println("Las contraseñas no coinciden.");
            }
            if (primeraPass.length() < 8) {
                System.out.println("La longitud de contraseña es errónea.");
            }
        } while (!primeraPass.equals(segundaPass) || primeraPass.length() < 8);

        database.cambiosEnBD(email, 2, primeraPass);
        System.out.println("Cambio realizado con éxito.");

    }
    
    @Override
    public void olvidadoPass(String email) {
        Scanner in = new Scanner(System.in);
        if (!database.consultasParaBD(email, 11).equals("error")) {
            System.out.println(
                    "Ahora vamos a intentar restaurar su contraseña, pero antes tendrá que introducir la palabra de seguridad asociada a su email para continuar.");
            String palabraSeguridad = in.nextLine();
            if (database.consultasParaBD(email, 11).equals(palabraSeguridad)) {
                restaurarPass(email);
            } else {
                System.out.println("La palabra de seguridad no coincide con la introducida. Vuelva a intentarlo");
            }
        } else {
            System.out.println("No encontramos ese mail en nuestra BD");
        }

    }
}

