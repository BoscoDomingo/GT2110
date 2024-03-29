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
    private int currentPage;

    public Timeline(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
        this.numeroDePaginas = (int) (publicaciones.size() / 50.01 + 1); //Porque si no los multiplos de 50 salen con
        // una página más de lo debido: 50/50 + 1 = 2, cuando debería ser 1
        this.currentPage = numeroDePaginas;
    }

    public void showLastPage() {
        System.out.println("\n********************TIMELINE**********************\n");
        this.currentPage = numeroDePaginas;
        int publicacionesMostradas = 0,
                currentIndex = (currentPage - 1) * 50; //pagina 1 va del 0 al 49, p2 del 50 al 99...
        while (currentIndex < publicaciones.size() && publicacionesMostradas < 50) {
            publicaciones.get(currentIndex).show();
            publicacionesMostradas++;
            currentIndex++;
        }
        if (currentIndex == 0) { //creo que nunca se da este caso
            System.out.println("No hay publicaciones disponibles en esta página");
        }
        System.out.println("\n**************************************************\n");

    }

    public void showPage(int pageNumber) { //las páginas van de 1 en adelante
        System.out.println("\n********************TIMELINE**********************\n");
        if (pageNumber <= numeroDePaginas && pageNumber >= 1) {
            this.currentPage = pageNumber;
            int publicacionesMostradas = 0,
                    currentIndex = (pageNumber - 1) * 50; //pagina 1 va del 0 al 49, p2 del 50 al 99...
            while (currentIndex < publicaciones.size() && publicacionesMostradas < 50) {
                publicaciones.get(currentIndex).show();
                publicacionesMostradas++;
                currentIndex++;
            }
            if (currentIndex == 0) { //creo que nunca se da este caso
                System.out.println("No hay publicaciones disponibles en esta página");
            }
        } else {
            System.out.println("Lo siento, este Timeline no dispone de tantas páginas.\nVOLVIENDO AL MENU PREVIO");
        }
        System.out.println("\n**************************************************\n");
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

    private boolean selectPublicacion() {
        boolean accionValida = false, goBack = false;
        while (!accionValida) {
            System.out.println(
                    "\nIntroduzca el número de publicación (1 para la primera, 50 para la 50ª) que desea ver. " +
                            ".\nSi desea salir, introduzca cualquier caracter no numérico");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int index = scan.nextInt();
                if (index >= 1) {
                    if ((currentPage < numeroDePaginas && index <= 50) || (currentPage == numeroDePaginas && 50 * (numeroDePaginas - 1) + (index - 1) < publicaciones.size())) {
                        //si no es la última página                     Si es última pag, hay hay que tener cuidado de no irse del tamaño
                        System.out.println("\nElegida la publicacion #" + index);
                        this.publicaciones.get((currentPage - 1) * 50 + (index - 1)).show();
                        accionValida = !this.publicaciones.get((currentPage - 1) * 50 + (index - 1)).menu();
                        //si currentPage=2, index = 10, iríamos a la 1*50 + 10-1= 59, que sería la 10ª publicacion
                        // que ve el usuario.
                        //si curentPage = 3 = numeroDePaginas, y hay 9 publicaciones (109 en total, 108 el mayor
                        // índice posible), 2*50 + 10-1 = 109, se saldría
                    } else {
                        System.out.println("\nNo hay suficientes publicaciones, por favor escoja un número más bajo, " +
                                                   "o un caracter para salir");
                    }
                } else {
                    System.out.println("\nPor favor, introduzca un número válido, o un caracter para salir");
                }
            } else {
                accionValida = true;
                goBack = true;
            }
        }
        return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    }

    @Override
    public boolean menu() {
        boolean accionValida = false, goBack = false;
        while (!accionValida) {
            showLastPage();
            System.out.println("\n***************MENU***************");
            System.out.println("\nOpciones:\n0 - Ver siguiente página\n1 - Ver página anterior\n2 - Ver mis " +
                                       "publicaciones\n3 - Seleccionar una publicacion\n9 - Volver atrás");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 0:
                    showPage(currentPage + 1);
                    break;
                case 1:
                    showPage(currentPage - 1);
                    break;
                case 2: // Mostrar mis publicaciones
                    accionValida = Sistema.getCurrentUser().mostrarPropiasPublicaciones();
                    break;
                case 3:
                    accionValida = !selectPublicacion();
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

    //GETTERS & SETTERS

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }
}