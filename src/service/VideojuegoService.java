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
	
	
}
