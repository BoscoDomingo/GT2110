package pkg;

import controllers.LoginController;

import java.util.Scanner;

public class App {
    public static void run() {
        LoginController loginController = new LoginController();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBienvenido a TwitterFIS, por favor seleccione una opción:");
            System.out.println("\n1 - Crear nuevo usuario (NO SE ESCRIBE EN BD DADO QUE NO HABÍA QUE IMPLEMENTARLO)" +
                                       "\n2 - Iniciar sesión (use email: a, password: a ó email: email@upm.es, " +
                                       "password: 12345678)\n\n9 - Salir de la aplicación");
            String email = "";
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 9:
                    System.out.println("Gracias por usar TwitterFIS!");
                    exit = true;
                    break;
                case 1:
                    scan.nextLine();
                    System.out.println("Introduzca su correo para crear la cuenta.");
                    email = scan.nextLine();
                    System.out.println("Introduzca la conteraseña de su cuenta.");
                    String password = scan.nextLine();
                    loginController.crearCuenta(email, password);
                    break;
                case 2:
                    scan.nextLine();
                    boolean valido = false;
                    boolean abandonar = false;

                    while (!valido && !abandonar) {
                        System.out.println(" \n--- Para abandonar el incio de sesion introduce 'exit' en el primer " +
                                                   "campo. --- ");
                        System.out.println(
                                " --- Si has olvidado tu contraseña introduce 'change' en el primer campo. --- ");
                        System.out.println(" --- Si quieres iniciar sesion introduce tu mail en el primer campo. ---");
                        System.out.println(
                                "Primer campo: "); // Comienzo de posible inicio de sesion o "he olvidado mi contraseña"
                        email = scan.nextLine();
                        if (email.equals("exit")) {
                            abandonar = true;
                        } else if (email.equals("change")) {
                            System.out.println("Introduce tu email: ");
                            email = scan.nextLine();
                            loginController.olvidadoPass(email);
                        } else {
                            valido = loginController.iniciarSesion(email);

                            if (valido) {//Entrar a la app
                                while (!abandonar) {
                                    abandonar = Sistema.getTimeline().menu();
                                }
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido");
                    break;
            }
        }
    }
}