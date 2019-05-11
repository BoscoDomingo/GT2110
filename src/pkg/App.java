package pkg;

import controllers.UserController;

import java.util.Scanner;

public class App {
    public static void run() {
        UserController userController = new UserController();
        System.out.println("Bienvenido a TwitterFIS, por favor seleccione una opción:");
        System.out.println("1 - Crear nuevo usuario\n2 - Iniciar sesión");
        Scanner scan = new Scanner(System.in);
        int selector = scan.nextInt();
        switch (selector) {
            case 1:
                String email = scan.nextLine();
                String password = scan.nextLine();
                userController.crearCuenta(email, password);
                break;
            case 2:
                email = scan.nextLine();
                password = scan.nextLine();
                userController.iniciarSesion(email, password);
                
            default:
                System.out.println("Por favor, introduzca un número válido");
            
        }
    }
}
