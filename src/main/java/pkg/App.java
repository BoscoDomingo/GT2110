package main.java.pkg;

import controllers.UserController;
import main.java.publicaciones.Publicacion;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void run() {
        UserController userController = new UserController();
        ArrayList<Publicacion> allPublicaciones = new ArrayList<>();

        System.out.println("Bienvenido a TwitterFIS, por favor seleccione una opción:");
        System.out.println("1 - Crear nuevo usuario\n2 - Iniciar sesión\n3 - Crear publicacion");
        Scanner scan = new Scanner(System.in);
        int selector = scan.nextInt();
        while(selector != 0) {
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
                    break;
                case 3:
                    allPublicaciones.add(new Publicacion().addPublicacion(allPublicaciones));
                    break;
                case 4:
                    for(int i=0; i<allPublicaciones.size(); i++){
                        allPublicaciones.get(i).show();
                    }
                    break;

                default:
                    System.out.println("Por favor, introduzca un número válido");

            }
            selector = scan.nextInt();
        }
    }
}
