package pkg;

import controllers.UserController;

import java.util.Scanner;

public class App {
    public static void run() {
        UserController userController = new UserController();
        boolean exit = false;
        while (!exit) {
            System.out.println("Bienvenido a TwitterFIS, por favor seleccione una opción:");
            System.out.println("0- Salir de la aplicación\n1 - Crear nuevo usuario\n2 - Iniciar sesión");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    System.out.println("Gracias por usar TwitterFIS!");
                    exit = true;
                    break;
                case 1:
                    scan.nextLine();
                    String email = scan.nextLine();
                    String password = scan.nextLine();
                    userController.crearCuenta(email, password);
                    break;
                case 2:
                    scan.nextLine();
                    boolean valido = false;
                    boolean abandonar = false;
                    while (!valido && !abandonar) {
                        System.out.println(" \n--- Para abandonar el incio de sesion introduce 'exit' en el primer " +
                                                   "campo. --- ");
                        System.out.println(" --- Si has olvidado tu contraseña introduce 'change' en el primer campo. --- ");
                        System.out.println(" --- Si quieres iniciar sesion introduce tu mail en el primer campo. ---");
                        System.out.println("Primer campo: "); // Comienzo de posible inicio de sesion o "he olvidado mi contraseña"
                        email = scan.nextLine();
                        if (email.equals("exit")) {
                            abandonar = true;

                        } else if(email.equals("change")){
                            System.out.println("Introduce tu email: ");
                            email = scan.nextLine();
                            userController.olvidadoPass(email);
                        } else
                            valido = userController.iniciarSesion(email);
                    }
                    if (valido) {//Entrar a la app
                    }
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido");
                    break;

            }
        }
    }
}