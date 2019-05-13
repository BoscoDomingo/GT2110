package controllers;

import pkg.CuentaUsuario;

import java.util.Scanner;

public class UserController {

    private CuentaUsuario usuario;

    public boolean crearCuenta(String email, String password){
        boolean exito = false;
        return exito;
    }

    public void cerrarSesion(){};

    public boolean iniciarSesion(String email, String password){
        boolean success = true;
        return success;
    };

    void restaurarContraseña(){};
    
    void introducirNuevaContraseña(String newPassword){
    };

    private String introducirCorreo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Por favor, introduzca el correo");
        return scan.nextLine();
    };

    private String introducirContraseña(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Por favor, introduzca la contraseña");
        return scan.nextLine();
    };



    

}
