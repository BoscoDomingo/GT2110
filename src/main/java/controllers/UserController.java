package controllers;

import pkg.CuentaUsuario;

import java.util.Scanner;

public class UserController {

    private DatabaseController database;

    public UserController(){
        database = new DatabaseController();
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

    void restaurarContraseña(){};

    void introducirNuevaContraseña(String newPassword){
    };






}

