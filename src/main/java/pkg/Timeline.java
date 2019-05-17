package pkg;

import interfaces.IMenu;
import interfaces.ITimeline;
import publicaciones.Publicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Timeline implements ITimeline, IMenu {
    private ArrayList<Publicacion> publicaciones;
    private int numeroDePaginas;

    public Timeline(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
        this.numeroDePaginas = (int) (publicaciones.size() / 50.01 + 1); //Porque si no los multiplos de 50 salen con
        // una página más de lo debido: 50/50 + 1 = 2, cuando debería ser 1
    }

    public void showPage(int pageNumber) { //las páginas van de 1 en adelante
        if (pageNumber <= numeroDePaginas) {
            int publicacionesMostradas = 0,
                    currentIndex = (pageNumber - 1) * 50; //pagina 1 va del 0 al 49, p2 del 50 al 99...
            while (currentIndex < publicaciones.size() && publicacionesMostradas < 50) {
                publicaciones.get(currentIndex).show();
                publicacionesMostradas++;
                currentIndex++;
            }
            if (currentIndex == 0) { //creo que nunca se da este caso
                System.out.println("No hay más publicaciones disponibles");
            }
        } else {
            System.out.println("Lo siento, este Timeline no dispone de tantas páginas");
        }
        this.menu();
    }

    public void sort() {
        Collections.sort(this.publicaciones);
    }

    private void calculateNumeroDePaginas() {
        try {
            this.numeroDePaginas = (int) (this.publicaciones.size() / 50.01 + 1);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. Por favor inténtelo de nuevo " + e);
        }
    }

    public void addPublicacion(Publicacion publicacion) {
        try {
            this.publicaciones.add(publicacion);
            calculateNumeroDePaginas();
            sort();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. Por favor inténtelo de nuevo " + e);
        }

    }

    public void addPublicaciones(ArrayList<Publicacion> publicacionesAdd) {
        try {
            this.publicaciones.addAll(publicacionesAdd);
            calculateNumeroDePaginas();
            sort();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. Por favor inténtelo de nuevo " + e);
        }

    }

    public void removePublicacion(Publicacion publicacion) {
        try {
            this.publicaciones.remove(publicacion);
            calculateNumeroDePaginas();
            sort();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. Por favor inténtelo de nuevo " + e);
        }
    }

    public void removePublicaciones(ArrayList<Publicacion> publicaciones) {
        try {
            this.publicaciones.removeAll(publicaciones);
            calculateNumeroDePaginas();
            sort();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. Por favor inténtelo de nuevo " + e);
        }
    }

    @Override
    public boolean menu() {
        boolean accionValida = false, goBack = false;
        System.out.println("\nOpciones:\n0-Ver siguiente página\n1-Ver página anterior\n2-Seleccionar una " +
                                   "publicacion\n9 - Volver atrás");
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    accionValida = true;
                    showPage(0 + 1);
                    break;
                case 1:
                    accionValida = true;
                    showPage(1 - 1);
                    break;
                case 2:
                    accionValida = true;


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
}