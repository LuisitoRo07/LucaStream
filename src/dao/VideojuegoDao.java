package dao;

import model.Videojuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;

public class VideojuegoDao {

	public static final String SEPARATOR = ",";
	public static final String QUOTE = "\"";

	List<Videojuego> Videojuegos = new ArrayList<Videojuego>();

	public List<Videojuego> leerFichero() throws IOException {
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("data/vgsales.csv"));
			String line = br.readLine();
			line = br.readLine();
			while (null != line) {
				String[] fields = line.split(SEPARATOR);
				Videojuego videojuego1 = new Videojuego(Integer.parseInt(fields[0]), fields[1], fields[2],
						Integer.parseInt(fields[3]), fields[4], fields[5]);

				Videojuegos.add(videojuego1);
				line = br.readLine();
			}

		} catch (Exception e) {
		} finally {
			if (null != br) {
				br.close();
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

	public List<Videojuego> altaVideojuego(Videojuego v) {
		// simplemente añadimos el videojuego al array Videojuegos y luego devolvemos el
		// array modificado
		// System.out.println(Videojuegos.isEmpty()); //prueba para ver que esta vacío
		v.setRank(Videojuegos.size() + 1); // antes de añadir el videojuego automatizo el rank o id
		Videojuegos.add(v);
		// System.out.println(Videojuegos.isEmpty()); //prueba que se añade bien
		return Videojuegos;
	}

	@SuppressWarnings("unchecked")
	public List<Videojuego> listarPorGenero(String genero) throws IOException {
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
}
