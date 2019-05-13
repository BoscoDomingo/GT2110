package controllers;

import pkg.CuentaUsuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserController{

    private DatabaseController database;
    private HashMap<String, TimeController> cuentasBloqueadas;
    private HashMap<String, Integer> intentosDeInicioSesion;
    private TimeController tiempo;

    public UserController(){
        database = new DatabaseController("src/main/resources/USERS.txt");
        intentosDeInicioSesion = new HashMap<>();
        cuentasBloqueadas = new HashMap<>();
    }

    public boolean crearCuenta(String email, String password){
        boolean exito = false;
        return exito;
    }

    public void cerrarSesion(){};

    public boolean iniciarSesion(String email){
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        String respuesta = database.consultaPassword(email);
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
            } else if (isAccountBlocked(email)){
                System.out.println("La cuenta está bloqueada porque has pasado el numero de intentos de inicio de sesión. " +
                        "\nVuelve a intentarlo pasados 60 minutos desde el ultimo intento");
                System.out.println("Te quedan " + (3600 - cuentasBloqueadas.get(email).getSegundos())/60 + " minutos");
            }
        return success;
    };

    private void intentoFallido(String email){
        if (intentosDeInicioSesion.containsKey(email)){
            int incremento = intentosDeInicioSesion.get(email) + 1;
            intentosDeInicioSesion.replace(email, intentosDeInicioSesion.get(email), incremento);
            if(incremento == 3){
                System.out.println("Te has excedido en el numero de intentos para acceder a la cuenta. \nCuenta bloqueada durante 60 minutos.");
                TimeController timerBloqueo = new TimeController(email);
                timerBloqueo.contar();
                cuentasBloqueadas.put(email, timerBloqueo);
            }
        }
        else{
            intentosDeInicioSesion.put(email, 1);
        }
    }

    private boolean isAccountBlocked(String email){
        if(intentosDeInicioSesion.containsKey(email)) {
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
        }
        else{
            return false;
        }
    }

    void restaurarContraseña(){};

    void introducirNuevaContraseña(String newPassword){
    };




}

