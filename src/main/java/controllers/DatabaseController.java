package controllers;

import pkg.CuentaUsuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class DatabaseController {

	private ArrayList<ArrayList<String>> responseBD;

	public DatabaseController(String direccion) {
		responseBD = new ArrayList<>();
		responseBD = extraerInfoBD(direccion);
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
				if(index>0) {
					datoTratado.add(datoCrudo.substring(0, index));
					if (index + 1 < datoCrudo.length()) {
						datoCrudo = datoCrudo.substring(index + 1, datoCrudo.length());
					} else datoCrudo = "";
				}
			}
			responseBD.add(datoTratado);
		}
		return responseBD;
	}

	public ArrayList<String> tratarLista(String listaSinTratar) {
		ArrayList<String> responseLista = new ArrayList<>();
		while (listaSinTratar.length() > 0) {
			int index = listaSinTratar.indexOf(",");
			responseLista.add(listaSinTratar.substring(0, index));
			if (index + 1 < listaSinTratar.length()) {
				listaSinTratar = listaSinTratar.substring(index + 1, listaSinTratar.length());
			} else listaSinTratar = "";
		}
		return responseLista;
	}


	//Este metodo comprueba, en caso de existir el mail en nuestra bd, cualquier elemento asociado al mismo.
	//En el caso de que no exista, devuelve la palabra "error". Tenedlo en cuenta a la hora de programar.

	public String consultasParaUsuario(String email, int posicion){
		String response = "";
		Boolean encontrado = false;
		for (ArrayList<String> usuario: responseBD){
			if(usuario.get(1).equals(email)){
				response = usuario.get(posicion);
				encontrado = true;
				break;
			}
		}
		if(!encontrado)
			response = "error";

		return response;
	}

	public void cambiosEnBDUsuario(String email, int posicion, String contenido){ //Cualquier cambio en la BD del usuario, utilizad este metodo.
		Boolean encontrado = false;
		for (ArrayList<String> usuario: responseBD){
			if(usuario.get(1).equals(email)){
				usuario.set(posicion, contenido);
			}
		}
	}


	public ArrayList<ArrayList<String>> getResponseBD() {
		return responseBD;
	}

	public void setResponseBD(ArrayList<ArrayList<String>> responseBD) {
		this.responseBD = responseBD;
	}
}