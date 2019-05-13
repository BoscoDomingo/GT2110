package controllers;

import pkg.CuentaUsuario;

import java.util.Scanner;

public class UserController {

    private CuentaUsuario usuario;
    private DatabaseController database;

    public boolean crearCuenta(String email, String password) {
        boolean exito = false;
        return exito;
    }

    public UserController() {
        database = new DatabaseController();
    }

    public boolean iniciarSesion(String email){
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        String respuesta = database.consultaPassword(email);
        if(respuesta.equals("error")){
            System.out.println("Email no encontrado en la BD. ¿Estás seguro de que ese es tu correo?");
        }
        else {
            System.out.println("Introduce tu contraseña");
            String password = scan.nextLine();
            if (password.equals(respuesta)) {//las contraseñas son de mínimo 8 caracteres
                success = true;
            } else {
                System.out.println("Contraseña erronea, vuelve a intentarlo.");
            }
        }
        return success;
    };
    public void cerrarSesion() {
    }

    void restaurarContraseña() {
    }

    void introducirNuevaContraseña(String newPassword) {
    }

    private String introducirCorreo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Por favor, introduzca el correo");
        return scan.nextLine();
    }

    private String introducirContraseña() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Por favor, introduzca la contraseña");
        return scan.nextLine();
    }

    public boolean iniciarSesion(String email, String password) {
        boolean success = false;
        String respuesta = database.consultaPassword(email);
        if (password.equals(respuesta)) {//las contraseñas son de mínimo 8 caracteres
            success = true;
        }
        return success;
    }
}

