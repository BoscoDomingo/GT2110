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
        while (!accionValida) {
            System.out.println("\nOpciones de Perfil:\n0 - Seleccionar una publicacion\n9 - Volver atrás");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                //Irían todas las llamadas a Sistema.getCurrentUser.borrar(), Sistema.getCurrentUser.publicar(), etc..
                case 0:
                    System.out.println(
                            "Siendo la numero 1 la primera publicacion que ves, introduce el número de publicación para seleccionarla" +
                                    "\nIntroduce -1 para salir");
                    Scanner scanPublicacion = new Scanner(System.in);
                    int numPublicacion = scanPublicacion.nextInt();
                    if (numPublicacion != -1 && numPublicacion <= Sistema.getCurrentUser().getPublicaciones().size()) {
                        Scanner scanOpcion = new Scanner(System.in);
                        Publicacion publicacionSeleccionada = Sistema.getCurrentUser().getPublicaciones().get(
                                numPublicacion - 1);
                        System.out.println("\n1 - Ver publicación en detalle\n2 - Eliminar publicación");
                        int opcion = scanOpcion.nextInt();
                        switch (opcion) {
                            case 1:
                                Sistema.getCurrentUser().getPublicaciones().get(numPublicacion - 1).show();
                                accionValida = !Sistema.getCurrentUser().getPublicaciones().get(
                                        numPublicacion - 1).menu();
                                break;
                            case 2:
                                Sistema.getCurrentUser().borrarPublicacion(numPublicacion - 1);
                                System.out.println("Eliminada correctamente");
                                break;
                        }
                    }
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

    @Override
    public Timeline getTimeline() {
        return timeline;
    }
}
