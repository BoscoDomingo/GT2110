package pkg;

import org.junit.Test;
import publicaciones.Publicacion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class TimelineTest {

    /*@Test
    public void showTimelineByPages() {
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
            timeline.showPage(currentPage);
            String input = "0";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
        }
    }*/
}