package pkg;

import interfaces.IMenu;
import interfaces.IPerfil;
import publicaciones.Publicacion;

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
                    System.out.println("Siendo la numero 1 la primera publicacion que ves, introduce el número de publicación para seleccionarla" +
                            "\nIntroduce -1 para salir");
                    Scanner scanPublicacion = new Scanner(System.in);
                    int numPublicacion = scanPublicacion.nextInt();
                    if(numPublicacion != -1){
                        Scanner scanOpcion = new Scanner(System.in);
                        Publicacion publicacionSeleccionada = Sistema.getCurrentUser().getPublicaciones().get(numPublicacion-1);
                        System.out.println("Si deseas verla completa, introduce la palabra 'ver'." +
                                "\nSi deseas eliminarla introduce la palabra 'eliminar'");
                        String opcion = scanOpcion.nextLine();
                        switch(opcion){
                            case "eliminar":
                                Sistema.getCurrentUser().borrarPublicacion(numPublicacion-1);
                                System.out.println("Eliminada correctamente");
                                break;
                            case "ver":
                                Sistema.getCurrentUser().getPublicaciones().get(numPublicacion-1).show();//TODO ver
                                // comentarios
                                break;
                        }
                    }
                    accionValida = true;
                    break;
                case 9:
                    accionValida = true;
                    goBack = true;
                    break;
                default:
                    System.out.println("Por favor, introduzca un número válido");
            }
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
