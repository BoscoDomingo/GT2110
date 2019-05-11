package controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class DatabaseController {

	private ArrayList<ArrayList<String>> responseBD;

	public DatabaseController() {
		responseBD = new ArrayList<>();
	}

	public ArrayList<ArrayList<String>> extraerInfoBD(String direccion) {
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(direccion));
		} catch (FileNotFoundException e) {
			System.out.println("Error abriendo el fichero: " + direccion);
			e.printStackTrace();
		}

		ArrayList<String> crudo = new ArrayList<>();

		// Leer fichero entero
		//crudo es un arraylist de strings sin tratar absolutamente nada. Cada elemento es una linea del txt.
		while (in.hasNextLine()) {
			crudo.add(in.nextLine());
		}

		// datoCrudo = "a001;fernando@gmail.com;10001101010;"
		// datoTratado =(0) "a001" (1) "fernando@gmail.com" (2)....
		// responseBD = (0) usuarioTratado1 (1) usuarioTratado2.......
		// IMPORTANTE, LAS LISTAS NO SE PROCESAN, SI QUEREIS PROCESARLAS UTILIZAD EL METODO tratarLista();
		for (String datoCrudo : crudo) {
			ArrayList<String> datoTratado = new ArrayList<>();
			while (datoCrudo.length() > 0) {
				int index = datoCrudo.indexOf(";");
				datoTratado.add(datoCrudo.substring(0, index - 1));
				if (index + 1 < datoCrudo.length()) {
					datoCrudo = datoCrudo.substring(index + 1, datoCrudo.length() - 1);
				} else datoCrudo = "";
			}
			responseBD.add(datoTratado);
		}
		return responseBD;
	}

	public ArrayList<String> tratarLista(String listaSinTratar) {
		ArrayList<String> responseLista = new ArrayList<>();
		while (listaSinTratar.length() > 0) {
			int index = listaSinTratar.indexOf(";");
			responseLista.add(listaSinTratar.substring(0, index - 1));
			if (index + 1 < listaSinTratar.length()) {
				listaSinTratar = listaSinTratar.substring(index + 1, listaSinTratar.length() - 1);
			} else listaSinTratar = "";
		}
		return responseLista;
	}
}
