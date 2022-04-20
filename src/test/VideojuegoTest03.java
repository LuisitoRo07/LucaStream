package test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Videojuego;
import service.VideojuegoService;

public class VideojuegoTest03 {

	VideojuegoService videojuegosService = new VideojuegoService();

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testListar() {
		VideojuegoService videojuegoService = new VideojuegoService();
		List<Videojuego> resultado = videojuegoService.listarVideojuegos();
		System.out.println(resultado);

		Assert.assertNotNull("Comprobando que no es NULL", resultado);
	}

	@Test
	public void testGeneroPlat() {
		List<Videojuego> resul = videojuegosService.listarPorGenero("Platform");
		System.out.println(resul);
		List<Videojuego> esp = new ArrayList<Videojuego>();
		System.out.println(esp);
		Assert.assertArrayEquals(esp.toArray(), resul.toArray());
	}
}
