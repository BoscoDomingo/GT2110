package pkg;


import org.junit.Test;
import publicaciones.Publicacion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class TimelineTest {
    @Test
    public void showTimelineByPages() {
        //Tests de mostrar timelines
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            publicaciones.add(new Publicacion("b00" + i,
                                             new CuentaUsuario("a00" + i, "" + i, i + "@upm.es", null, null, null,
                                                               null, null), "Numero: " + i, null, new ArrayList<>(),
                                             null));
        }
        Timeline timeline = new Timeline(publicaciones);
        int currentPage, numeroDePaginas = 5;
        for (currentPage = 1; currentPage < numeroDePaginas + 1; currentPage++) {
            System.out.println("\nMostrando la pagina #" + currentPage);
            timeline.showPage(currentPage);
            String input = "0";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
        }
    }
    @Test
    public void anyadirYBorrarPublicaciones() {
        //Tests de anyadir y borrar publicaciones
        System.out.println("-----------------Anyadir Publicaciones-----------------");
        CuentaUsuario cuenta = new CuentaUsuario("c0031", "Mario", "mario@upm.es");
        Publicacion publicacion = new Publicacion ("p0004", cuenta, "Usando twitterFIS!" , new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Publicacion publicacion1 = new Publicacion ("p0005", cuenta, "Es muy util" , new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Publicacion publicacion2 = new Publicacion ("p0006", cuenta, "It's a me Mario" , new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Publicacion publicacion3 = new Publicacion ("p0007", cuenta, "It's a me Luigi" , new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        ArrayList<Publicacion> publicaciones2 = new ArrayList<>();
        ArrayList<Publicacion> publicaciones3 = new ArrayList<>();
        publicaciones2.add(publicacion);
        publicaciones3.add(publicacion2);
        publicaciones3.add(publicacion3);
        Timeline timeline2 = new Timeline(publicaciones2);

        timeline2.showLastPage();
        System.out.println("<<<<<<<<<Anyadimos 1 publicacion>>>>>>>>>");
        timeline2.addPublicacion(publicacion1);
        timeline2.showLastPage();

        System.out.println("<<<<<<<<<Anyadimos varias publicaciones>>>>>>>>>");
        timeline2.addPublicaciones(publicaciones3);
        timeline2.showLastPage();

        System.out.println("-----------------Borrar Publicaciones-----------------");
        System.out.println("<<<<<<<<<Borramos varias publicaciones>>>>>>>>>");
        timeline2.removePublicaciones(publicaciones3);
        timeline2.showLastPage();

        System.out.println("<<<<<<<<<Borramos 1 publicacion>>>>>>>>>");
        timeline2.removePublicacion(publicacion1);
        timeline2.showLastPage();
        timeline2.sort();
    }
    @Test
    public void probarCalculateNumeroDePaginas() {
        //test de calculo de numero de paginas
        ArrayList<Publicacion> publicaciones4 = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            publicaciones4.add(new Publicacion("b00" + i,
                    new CuentaUsuario("a00" + i, "" + i, i + "@upm.es", null, null, null,
                            null, null), "Numero: " + i, null, new ArrayList<>(),
                    null));
        }
        System.out.println("Calculando numero de paginas del timeline anterior");
            int numeroDePaginas = (int) (publicaciones4.size() / 50.01 + 1);
        System.out.println("El numero de paginas es " + numeroDePaginas);
    }
    @Test
    public void probarSelectPublicacion() {
        //test de selccion de publicacion
        ArrayList<Publicacion> publicaciones5 = new ArrayList<>();
        for (int i = 0; i < 151; i++) {
            publicaciones5.add(new Publicacion("b00" + i,
                    new CuentaUsuario("a00" + i, "" + i, i + "@upm.es", null, null, null,
                            null, null), "Numero: " + i, null, new ArrayList<>(),
                    null));
        }
        Timeline timeline3 = new Timeline (publicaciones5);
        System.out.println("\nProbamos a seleccionar una publicacion, por ejemplo, la 36");
        int index = 36;
        int numeroDePaginas = (int) (publicaciones5.size() / 50.01 + 1);
        int currentPage = 1;
                if (index >= 1) {
                    if ((currentPage < numeroDePaginas && index <= 50) || (currentPage == numeroDePaginas && 50 * (numeroDePaginas - 1) + (index - 1) < publicaciones5.size())) {
                        //si no es la última página                     Si es última pag, hay hay que tener cuidado de no irse del tamaño
                        System.out.println("\nElegida la publicacion #" + index);
                        publicaciones5.get((currentPage - 1) * 50 + (index - 1)).show();
                        System.out.println("Se ejecutaria ahora el menu de la publicacion seleccioanada, correspondiente a otra clase");
                        //accionValida = !this.publicaciones.get((currentPage - 1) * 50 + (index - 1)).menu();
                        //si currentPage=2, index = 10, iríamos a la 1*50 + 10-1= 59, que sería la 10ª publicacion
                        // que ve el usuario.
                        //si curentPage = 3 = numeroDePaginas, y hay 9 publicaciones (109 en total, 108 el mayor
                        // índice posible), 2*50 + 10-1 = 109, se saldría
                    }
                }

    }

    @Test
    public void probarOpcionesMenu(){
        //test de las opciones del menu
        ArrayList<Publicacion> publicaciones6 = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            publicaciones6.add(new Publicacion("b00" + i,
                    new CuentaUsuario("a00" + i, "" + i, i + "@upm.es", null, null, null,
                            null, null), "Numero: " + i, null, new ArrayList<>(),
                    null));
        }
        Timeline timeline4 = new Timeline(publicaciones6);
        int pgActual=2;

            System.out.println("\n***************MENU***************");
            System.out.println("\nOpciones:\n0 - Ver siguiente página\n1 - Ver página anterior\n2 - Ver mis " +
                    "publicaciones\n3 - Seleccionar una publicacion\n9 - Volver atrás");
            String input = "0";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

                    System.out.println("\nProbando opcion 0: mostrar pagina siguiente a la 2");
                    timeline4.showPage(pgActual + 1);

                    System.out.println("\nProbando opcion 1: mostrar pagina anterior a la 2");
                    timeline4.showPage(pgActual - 1);

                    System.out.println("\nProbando opcion 2: mostrar publicaciones propias");
                    System.out.println("No corresponde a esta clase ese testeo");

                    System.out.println("\nProvando opcion 3: seleccionar una publicacion");
                    probarSelectPublicacion();

    }
}
