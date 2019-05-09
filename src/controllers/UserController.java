package controllers;

import java.util.Scanner;

public class UserController {
    public boolean crearCuenta(String email, String password){
        boolean exito = false;
        
        return exito;
    }
    public void cerrarSesion(){};
    public boolean iniciarSesion(){
        boolean success = true;
        String email = introducirCorreo();
        String password = introducirContraseña();
        //metemos el fileScanner y la comprobación de que el email existe y la contraseña
        //es correcta
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
