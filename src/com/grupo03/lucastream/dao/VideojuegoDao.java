package com.grupo03.lucastream.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.grupo03.lucastream.model.Videojuego;

import java.io.IOException;

public class VideojuegoDao {

	public static final String SEPARATOR = ",";
	public static final String QUOTE = "\"";

	List<Videojuego> Videojuegos = new ArrayList<Videojuego>();

	public static String esNumerico(String str) {
		try {
			//Integer.parseInt(str);
			Double.parseDouble(str);
			return str;
		} catch (NumberFormatException e) {
			return "-1";
		}
	}

	public List<Videojuego> leerFichero() {
		BufferedReader br = null;
		Videojuegos.clear();
		try {
			// Declaramos la variable br que contendra el csv.
			br = new BufferedReader(new FileReader("data/vgsales.csv"));
			// La variable line leerá linea a linea todo lo contenido en br.
			String line = br.readLine();
			line = br.readLine(); // Para que empiece en la segunda linea y saltarse las cabeceras.

			// Recorremos linea por linea toda la variable br.
			while (null != line) {
				String[] fields = line.split(SEPARATOR);

				Videojuego videojuego1 = new Videojuego(Integer.parseInt(esNumerico(fields[0])), fields[1], fields[2],
						Integer.parseInt(esNumerico(fields[3])), fields[4], fields[5], Double.parseDouble(esNumerico(fields[6])),
						Double.parseDouble(esNumerico(fields[7])), Double.parseDouble(esNumerico(fields[8])),
						Double.parseDouble(esNumerico(fields[9])), Double.parseDouble(esNumerico(fields[10])));

				Videojuegos.add(videojuego1);
				line = br.readLine();
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return Videojuegos;
	}

	public boolean existeVideojuego(int rank) {
		// recibe un id y comprueba que existe (que hay algun match). Si hay devuelve
		// true sino devuelve false
		// return Videojuegos.stream().anyMatch(v -> v.getRank()==rank);
		Videojuegos = leerFichero();
		return Videojuegos.stream().anyMatch(v -> v.getRank() == rank);
	}

	public List<Videojuego> listarVideojuegos() {

		List<Videojuego> videojuegos = new ArrayList<Videojuego>();

		try {

			videojuegos = leerFichero();
			return videojuegos;

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Se ha producido un error... - VideojuegoDao");
			return videojuegos;
		}

	}

	public void altaVideojuego(Videojuego v) {
		// simplemente a�adimos el videojuego al array Videojuegos y luego devolvemos
		// el
		// array modificado
		// System.out.println(Videojuegos.isEmpty()); //prueba para ver que esta vac�o
		Videojuegos = leerFichero();
		//v.setRank(Videojuegos.size() + 1);// antes de a�adir el videojuego automatizo el rank o id
		v.setRank(Videojuegos.get(Videojuegos.size()-1).getRank() + 1);
		Videojuegos.add(v);
		// System.out.println(Videojuegos.isEmpty()); //prueba que se a�ade bien
		guardarFichero();

	}

	public boolean borrarVideojuego(int rank) {
		try {
			// Videojuegos = leerFichero(); //Leo el csv y creo el array
			// int size1 = Videojuegos.size();
			// Videojuegos.stream().filter(v -> v.getRank()==rank).forEach(v ->
			// Videojuegos.remove(v));//Filtro y quito ese
			/*
			 * System.out.println("Se hace bien el borrado?"); System.out.println(size1 - 1
			 * == Videojuegos.size());
			 */

			List<Videojuego> videojuegos = new ArrayList<Videojuego>();
			videojuegos = leerFichero();

			for (int i = 0; i < videojuegos.size(); i++) {
				if (videojuegos.get(i).getRank() == rank) {
					videojuegos.remove(i);
					break;
				}
			}

			Videojuegos = videojuegos;

			guardarFichero();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Se ha producido un error... - VideojuegoDao");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Videojuego> listarPorGenero(String genero) {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();
		videojuegos = leerFichero();
		List<Videojuego> videojuegosGenero = new ArrayList<Videojuego>();
		try {
			videojuegos.stream().filter(x -> x.getGenero().equals(genero)).forEach(x -> videojuegosGenero.add(x));
			return videojuegosGenero;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Filtramos por el editor que le pasemos a la funci�n en el controlador
	@SuppressWarnings("unchecked")
	public List<Videojuego> listarPorEditor(String editor) {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();
		videojuegos = leerFichero();
		List<Videojuego> videojuegosEditor = new ArrayList<Videojuego>();
		try {
			videojuegos.stream().filter(x -> x.getEditor().equals(editor)).forEach(x -> videojuegosEditor.add(x));
			return videojuegosEditor;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Filtramos el a�o para que salgan juegos del Siglo XX
	@SuppressWarnings("unchecked")
	public List<Videojuego> listarJuegosSigloXX() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();
		videojuegos = leerFichero();
		List<Videojuego> videojuegosSigloXX = new ArrayList<Videojuego>();
		try {
			videojuegos.stream().filter(x -> x.getAno() >= 1901 & x.getAno() <= 2000)
					.forEach(x -> videojuegosSigloXX.add(x));
			return videojuegosSigloXX;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// ********** JUEGOS AÑOS PARES
	@SuppressWarnings("unchecked")
	public List<Videojuego> listarJuegosAPares() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();
		videojuegos = leerFichero();
		List<Videojuego> videojuegosAPares = new ArrayList<Videojuego>();
		try {
			videojuegos.stream().filter(x -> x.getAno() % 2 == 0).forEach(x -> videojuegosAPares.add(x));
			return videojuegosAPares;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Listado de editores
	public Set<String> listarEditores() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();
		videojuegos = leerFichero();
		Set<String> editores = new HashSet<String>();
		try {// Set para guardar todos los editores sin repetir
			editores = videojuegos.stream().map(Videojuego::getEditor).collect(Collectors.toSet());
			return editores;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Listado de editores
	public Set<String> listarGeneros() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();
		videojuegos = leerFichero();
		Set<String> generos = new HashSet<String>();
		try {// Set para guardar todos los editores sin repetir
			generos = videojuegos.stream().map(Videojuego::getGenero).collect(Collectors.toSet());
			return generos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void guardarFichero() {

		try {
			File f = new File("data/vgsales.csv");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Rank");
			bw.write(",");
			bw.write("Name");
			bw.write(",");
			bw.write("Platform");
			bw.write(",");
			bw.write("Year");
			bw.write(",");
			bw.write("Genre");
			bw.write(",");
			bw.write("Publisher");
			bw.write(",");
			bw.write("NA_Sales");
			bw.write(",");
			bw.write("EU_Sales");
			bw.write(",");
			bw.write("JP_Sales");
			bw.write(",");
			bw.write("Other_Sales");
			bw.write(",");
			bw.write("Global_Sales");
			bw.newLine();

			/*
			 * for (int i = 0; i < Videojuegos.size(); i++) { Videojuegos.toArray(null));
			 * 
			 * }
			 */

			for (Videojuego v : Videojuegos) {
				bw.write(Integer.toString(v.getRank()));
				bw.write(",");
				bw.write(v.getNombre());
				bw.write(",");
				bw.write(v.getPlataforma());
				bw.write(",");
				bw.write(Integer.toString(v.getAno()));
				bw.write(",");
				bw.write(v.getGenero());
				bw.write(",");
				bw.write(v.getEditor());
				bw.write(",");
				bw.write(Double.toString(v.getNa_ventas()));
				bw.write(",");
				bw.write(Double.toString(v.getEu_ventas()));
				bw.write(",");
				bw.write(Double.toString(v.getJp_ventas()));
				bw.write(",");
				bw.write(Double.toString(v.getOtras_ventas()));
				bw.write(",");
				bw.write(Double.toString(v.getGlobal_ventas()));
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public boolean editarVideojuego(Videojuego videojuego) {

		try {
			List<Videojuego> videojuegos = new ArrayList<Videojuego>();
			videojuegos = leerFichero();

			for (int i = 0; i < videojuegos.size(); i++) {
				if (videojuegos.get(i).getRank() == videojuego.getRank()) {
					videojuegos.set(i, videojuego);
					break;
				}
			}

			Videojuegos = videojuegos;

			guardarFichero();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
