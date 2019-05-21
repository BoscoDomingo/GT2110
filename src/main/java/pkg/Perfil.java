package pkg;

import interfaces.IMenu;
import interfaces.IPerfil;

import java.util.ArrayList;
import java.util.Scanner;

public class Perfil implements IPerfil, IMenu {
    private Timeline timeline;

    public Perfil() {
        this.timeline = new Timeline(new ArrayList<>());
    }

    @Override
    public boolean menu() {
        boolean accionValida = false, goBack = false;
        System.out.println("\nOpciones:\n0 - Seleccionar una publicacion\n9 - Volver atrás");
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                //Irían todas las llamadas a Sistema.getCurrentUser.borrar(), Sistema.getCurrentUser.publicar(), etc..
                case 0:
                    accionValida = true;//!selectPublicacion(); si devuelve true es que volvemos a mostrar
                    //vuestro código aqui
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido (del 0 al ");//VUESTRO NUMERO.SI HAY 3
                    //OPCIONES, PONÉIS EL 2
            }
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
