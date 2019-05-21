package pkg;

import controllers.LoginController;
import interfaces.IMenu;

import java.util.Scanner;

public class App {
    public static void run() {
        LoginController loginController = new LoginController();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBienvenido a TwitterFIS, por favor seleccione una opción:");
            System.out.println("0 - Salir de la aplicación\n1 - Crear nuevo usuario\n2 - Iniciar sesión");
            String email = "";
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    System.out.println("Gracias por usar TwitterFIS!");
                    exit = true;
                    break;
                case 1:
                    scan.nextLine();
                    email = scan.nextLine();
                    String password = scan.nextLine();
                    loginController.crearCuenta(email, password);
                    break;
                case 2:
                    scan.nextLine();
                    boolean valido = false; //TODO: CAMBIAR A FALSE PARA PROBAR INICIO DE SESION
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
                                Sistema.getSistema().setCurrentUser(email);
                                menu();
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

    public static void menu() {
        boolean accionValida = false, goBack = false;
        System.out.println("Opciones:\n0 - Ver Perfil \n9 - Salir");
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                //METED CASES SÓLO DEL 0 AL 8
                case 0:
                    accionValida = true;
                    Sistema.getCurrentUser().getPerfil().menu();
                    break;

                //...
                case 8:
                    //LLAMADA A CUALQUIER OTRO MENU QUE SE PUEDA MOSTRAR SI LO NECEISTAIS, INCLUIDAS MÁS ACCIONES SI 8 NO
                    //SON SUFICIENTES
                    accionValida = true;//!otraCosa.menu(); //Significa que vuelve del otro menu a este. ESTA BIEN ASI
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido");
            }
        }
    }
}
