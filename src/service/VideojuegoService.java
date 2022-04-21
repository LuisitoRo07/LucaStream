package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.VideojuegoDao;
import model.Videojuego;

public class VideojuegoService {

	VideojuegoDao videojuegoDao = new VideojuegoDao();

	public void altaVideojuego(Videojuego v) {
		videojuegoDao.altaVideojuego(v);
	}

	public List<Videojuego> leerCSV() throws IOException {

		VideojuegoDao videojuegoDao = new VideojuegoDao();
		List<Videojuego> Videojuegos = new ArrayList<Videojuego>();
		videojuegoDao.leerFichero();
		System.out.println(Videojuegos.size());
		return videojuegoDao.leerFichero();
	}

	public List<Videojuego> listarVideojuegos() {

		try {
			VideojuegoDao videojuegodao = new VideojuegoDao();
			List<Videojuego> videojuegos = videojuegodao.listarVideojuegos();
			return videojuegos;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Hubo un error - VideojuegoService");
			return null;
		}

	}

	public List<Videojuego> listarPorGenero(String genero) {
		VideojuegoDao videojuegodao = new VideojuegoDao();
		try {
			List<Videojuego> videojuegos = videojuegodao.listarPorGenero(genero);
			return videojuegos;
		} catch (Exception e) {
			System.out.println("Algo salio mal Service");
			return null;
		}
	}

}
