package controllers;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    private DatabaseController database;
    private HashMap<String, TimeController> cuentasBloqueadas;
    private HashMap<String, Integer> intentosDeInicioSesion;
    private TimeController tiempo;

    public LoginController() {
        database = new DatabaseController("src/main/resources/USERS.txt");
        intentosDeInicioSesion = new HashMap<>();
        cuentasBloqueadas = new HashMap<>();
    }

    public boolean crearCuenta(String email, String password){
        boolean exito = false;
        if(comprobarValidezCorreo(email)){
            //creacion de la cuenta
        }
        return exito;

    }
    public boolean comprobarValidezCorreo(String email) {
        boolean vale = false;
        if (!comprobarExistenciaCorreoAlumnado(email) && !comprobarExistenciaCorreoPersonal(email))
            System.out.println("Error, su correo no pertenece al alumnado de la UPM.");
        else if (!comprobarYaRegistrado(email)){
            System.out.println("Error, el correo ya ha sido registrado.");
        }
        else {
            System.out.println("Correcto, el correo pertenece al alumnado o personal de la UPM.");
            vale = true;
        }
        return vale;
    }
    public boolean comprobarYaRegistrado(String email){
        boolean existe= false;
        String respuesta = database.consultasParaBD(email, 2);
        if (respuesta.equals("error")) {
            existe= true;               //se comprueba que el correo ya estaba antes en la base de datos
        }
        return existe;
    }
    public boolean comprobarExistenciaCorreoAlumnado(String email){
        String reg = "^[A-Za-z0-9+_.-]+@(alumnos.upm.es)$"; //se comprueba que la extension es alumnos.upm.es
        Pattern patron = Pattern.compile(reg);
        Matcher matcher = patron.matcher(email);
        return matcher.matches();
    }
    public boolean comprobarExistenciaCorreoPersonal(String email){
        String reg = "^[A-Za-z0-9+_.-]+@(upm.es)$"; //se comprueba que la extension es upm.es
        Pattern patron = Pattern.compile(reg);
        Matcher matcher = patron.matcher(email);
        return matcher.matches();
    }
    public void cerrarSesion() {
    }

    ;

    public boolean iniciarSesion(String email) {
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        String respuesta = database.consultasParaBD(email, 2);
        if (respuesta.equals("error")) {
            System.out.println("Email no encontrado en la BD. ¿Estás seguro de que ese es tu correo?");
        } else if (!isAccountBlocked(email)) {
            System.out.println("Introduce tu contraseña");
            String password = scan.nextLine();
            if (password.equals(respuesta)) {//las contraseñas son de mínimo 8 caracteres
                success = true;
            } else {
                System.out.println("Contraseña erronea, vuelve a intentarlo.");
                intentoFallido(email);
            }
        } else if (isAccountBlocked(email)) {
            System.out.println(
                    "La cuenta está bloqueada porque has pasado el numero de intentos de inicio de sesión. " +
                            "\nVuelve a intentarlo pasados 60 minutos desde el ultimo intento");
            System.out.println("Te quedan " + (3600 - cuentasBloqueadas.get(
                    email).getSegundos()) / 60 + " minutos" + " y " + (60 - cuentasBloqueadas.get(
                    email).getSegundos()) + " segundos");
        }
        return success;
    }

    ;

    private void intentoFallido(String email) {
        if (intentosDeInicioSesion.containsKey(email)) {
            int incremento = intentosDeInicioSesion.get(email) + 1;
            intentosDeInicioSesion.replace(email, intentosDeInicioSesion.get(email), incremento);
            if (incremento == 3) {
                System.out.println(
                        "Te has excedido en el numero de intentos para acceder a la cuenta. \nCuenta bloqueada durante 60 minutos.");
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

    public void restaurarPass(String email) {
        Scanner in = new Scanner(System.in);
        String primeraPass;
        String segundaPass;
        System.out.println("\n-- Has accedido al menu de restauracion de contraseña. --");
        System.out.println(
                "-- Recuerda que para que se cambie la contraseña con exito, tendrás que elegir una con un tamaño mayor que 8. -- ");
        System.out.println("-- Además ambos campos deben coincidir -- ");
        System.out.println("-- Si una de las dos condiciones de arriba no se cumple deberás volver a intentarlo --");
        do {
            System.out.println("Introduce tu nueva contraseña: ");
            primeraPass = in.nextLine();
            System.out.println("Introduce la contraseña de nuevo: ");
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

    ;

    public void introducirNuevaContraseña(String newPassword) {
    }

    ;

    public void olvidadoPass(String email) {
        Scanner in = new Scanner(System.in);
        if (!database.consultasParaBD(email, 11).equals("error")) {
            System.out.println(
                    "Ahora vamos a intentar restaurar tu contraseña, pero antes tendrás que introducir la palabra de seguridad asociada a tu email para continuar.");
            String palabraSeguridad = in.nextLine();
            if (database.consultasParaBD(email, 11).equals(palabraSeguridad)) {
                restaurarPass(email);
            } else {
                System.out.println("La palabra de seguridad no coincide con la introducida. Vuelve a intentarlo");
            }
        } else {
            System.out.println("No encontramos ese mail en nuestra BD");
        }

    }
}

