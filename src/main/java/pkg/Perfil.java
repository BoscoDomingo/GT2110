package pkg;

import interfaces.IMenu;
import interfaces.IPerfil;

import java.util.Scanner;

public class Perfil implements IPerfil, IMenu {
    private Timeline timeline;

    @Override
    public boolean menu() {
        boolean accionValida = false, goBack = false;
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                //METED CASES SÓLO DEL 0 AL 8
                case 0:
                    accionValida = true;
                    //vuestro código aqui
                    break;

                //...
                case 8:
                    accionValida = true;
                    //LLAMADA A CUALQUIER OTRO MENU QUE SE PUEDA MOSTRAR SI LO NECEISTAIS, INCLUIDAS MÁS ACCIONES SI 8 NO
                    //SON SUFICIENTES
                    if(otraCosa.menu() = true) accionValida = false; //Significa que vuelve del otro menu a este
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
}
