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
            int publicacionesMostradas = 0, currentIndex = (pageNumber - 1) * 50; //si es pagina 1, va del 0 al 49
            while (currentIndex < publicaciones.size() && publicacionesMostradas < 50) {
                publicaciones.get(currentIndex).show();
                publicacionesMostradas++;
                currentIndex++;
            }
            if (currentIndex == 0) {
                System.out.println("No hay más publicaciones disponibles");
            }
        } else {
            System.out.println("Lo siento, este Timeline no dispone de tantas páginas");
        }
    }

    public void sort(){
        Collections.sort(this.publicaciones);
    }

    private void calculateNumeroDePaginas() {
        this.numeroDePaginas = (int) (this.publicaciones.size() / 50.01 + 1);
    }

    public void addPublicaciones(ArrayList<Publicacion> publicacionesAdd){
        this.publicaciones.addAll(publicacionesAdd);
        calculateNumeroDePaginas();
        sort();
    }

    public void removePublicaciones(ArrayList<Publicacion> publicacionesRemove){
        this.publicaciones.removeAll(publicacionesRemove);
        calculateNumeroDePaginas();
        sort();
    }

    @Override
    public boolean menu() {
        boolean accionValida = false, goBack = false;
        System.out.println("Opciones:\n0-");
        Scanner scan = new Scanner(System.in);
        while (!accionValida) {
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    accionValida = true;
                    //vuestro código aqui
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
}