package dao;

import model.Videojuego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;

public class VideojuegoDao {

	public static final String SEPARATOR = ",";
	public static final String QUOTE = "\"";

	List<Videojuego> Videojuegos = new ArrayList<Videojuego>();

	public List<Videojuego> leerFichero() {
		BufferedReader br = null;
		try {
			// Declaramos la variable br que contendra el csv.
			br = new BufferedReader(new FileReader("data/vgsales.csv"));
			// La variable line leerÃ¡ linea a linea todo lo contenido en br.
			String line = br.readLine();
			line = br.readLine(); // Para que empiece en la segunda linea y saltarse las cabeceras.

			// Recorremos linea por linea toda la variable br.
			while (null != line) {
				String[] fields = line.split(SEPARATOR);
				Videojuego videojuego1 = new Videojuego(Integer.parseInt(fields[0]), fields[1], fields[2],
						Integer.parseInt(fields[3]), fields[4], fields[5], Double.parseDouble(fields[6]),
						Double.parseDouble(fields[7]), Double.parseDouble(fields[8]), Double.parseDouble(fields[9]),
						Double.parseDouble(fields[10]));

				Videojuegos.add(videojuego1);
				line = br.readLine();
			}

		} catch (Exception e) {
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
		// simplemente aï¿½adimos el videojuego al array Videojuegos y luego devolvemos
		// el
		// array modificado
		// System.out.println(Videojuegos.isEmpty()); //prueba para ver que esta vacï¿½o
		Videojuegos = leerFichero();
		v.setRank(Videojuegos.size() + 1);// antes de aï¿½adir el videojuego automatizo el rank o id
		Videojuegos.add(v);
		// System.out.println(Videojuegos.isEmpty()); //prueba que se aï¿½ade bien
		guardarFichero();

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

	// Filtramos por el editor que le pasemos a la función en el controlador
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

	// Filtramos el año para que salgan juegos del Siglo XX
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
				System.out.println(v);
			}

			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
