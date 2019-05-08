package pkg.general;

import java.util.Scanner;

public class App {
    public static void run(){
        System.out.println("Bienvenido a TwitterFIS, por favor seleccione una opción:");
        System.out.println("1 - Crear nuevo usuario\n2 - Iniciar sesión");
        Scanner scan = new Scanner(System.in);
        int selector = scan.nextInt();
        switch (selector){
            case 1:

            default:
                System.out.println("Por favor, introduzca un número válido");
        
        }
    }
}
