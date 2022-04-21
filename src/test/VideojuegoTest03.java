package test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.VideojuegoDao;
import model.Videojuego;
import service.VideojuegoService;

public class VideojuegoTest03 {

	VideojuegoService videojuegosService = new VideojuegoService();

	@Test
	public void testLeer() {
		System.out.println("Probando test leer");
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		List<Videojuego> resultado = videojuegoDao.leerFichero();
		System.out.println(resultado.isEmpty());
		Assert.assertFalse("Comprobando que no es NULL", resultado.isEmpty());
	}

	@Test
	public void testListar() {
		VideojuegoService videojuegoService = new VideojuegoService();
		List<Videojuego> resultado = videojuegoService.listarVideojuegos();
		System.out.println("Javier" + resultado);
		Assert.assertNotNull("Comprobando que no es NULL", resultado);
		
	}

	@Test
	public void testGeneroPlat() {
		List<Videojuego> resul = videojuegosService.listarPorGenero("Platform");
		Videojuego esp = new Videojuego (2,"Super Mario Bros.","NES",1985,"Platform","Nintendo");
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}
	
	@Test
	public void testEditorNintendo() {
		List<Videojuego> resul = videojuegosService.listarPorEditor("Nintendo");
		Videojuego esp = new Videojuego (1,"Wii Sports","Wii",2006,"Sports","Nintendo");
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}
	
	@Test
	public void testJuegoSigloXX() {
		List<Videojuego> resul = videojuegosService.listarJuegosSigloXX();
		Videojuego esp = new Videojuego (2,"Super Mario Bros.","NES",1985,"Platform","Nintendo");
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}
}
