package test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.grupo03.lucastream.dao.VideojuegoDao;
import com.grupo03.lucastream.model.Videojuego;
import com.grupo03.lucastream.service.VideojuegoService;

public class VideojuegoTest03 {
	/*
	 * @BeforeClass public void iniciarDatos() { VideojuegoDao videojuegoDao = new
	 * VideojuegoDao(); }
	 */
	@Test
	public void testLeer() {
		System.out.println("Probando test leer");
		// GIVEN
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		// WHEN
		List<Videojuego> resultado = videojuegoDao.leerFichero();
		System.out.println(resultado.isEmpty());
		// THEN
		Assert.assertFalse("Comprobando que no es NULL", resultado.isEmpty());
	}

	@Test
	public void testListar() {
		System.out.println("Test de listar");
		// GIVEN
		VideojuegoService videojuegoService = new VideojuegoService();
		// WHEN
		List<Videojuego> resultado = videojuegoService.listarVideojuegos();
		// THEN
		Assert.assertNotNull("Comprobando que no es NULL", resultado);

	}

	@Test
	public void testGeneroPlat() {
		System.out.println("Test informe genero platform");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		Videojuego esp = new Videojuego(2, "Super Mario Bros.", "NES", 1985, "Platform", "Nintendo");
		// WHEN
		List<Videojuego> resul = videojuegosService.listarPorGenero("Platform");
		// THEN
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}

	@Test
	public void testEditorNintendo() {
		System.out.println("Test informe editor Nintendo");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		Videojuego esp = new Videojuego(1, "Wii Sports", "Wii", 2006, "Sports", "Nintendo");
		// WHEN
		List<Videojuego> resul = videojuegosService.listarPorEditor("Nintendo");
		// THEN
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}

	@Test
	public void testJuegoSigloXX() {
		System.out.println("Test informe siglo XX");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		Videojuego esp = new Videojuego(2, "Super Mario Bros.", "NES", 1985, "Platform", "Nintendo");
		// WHEN
		List<Videojuego> resul = videojuegosService.listarJuegosSigloXX();
		// THEN
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}

	@Test
	public void testJuegosAPares() {
		System.out.println("Test informe Años Pares");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		Videojuego esp = new Videojuego(1, "Wii Sports", "Wii", 2006, "Sports", "Nintendo");
		// WHEN
		List<Videojuego> resul = videojuegosService.listarJuegosAPares();
		// THEN
		Assert.assertEquals(esp.getNombre(), resul.get(0).getNombre());
	}

	@Test
	public void deberíaSerLucasArtsPrimerEditor() {
		System.out.println("Test informe editores");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		String esp = "LucasArts";
		// WHEN
		Set<String> resul = videojuegosService.listarEditores();
		// THEN
		Assert.assertEquals(esp, resul.iterator().next());
	}

	@Test
	public void deberíaHaberEditores() {
		System.out.println("Test de listar");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		// WHEN
		Set<String> resul = videojuegosService.listarEditores();
		// THEN
		Assert.assertNotNull("Comprobando que no es NULL", resul);
	}

	@Test
	public void NOdeberíaHaberEditores() {
		System.out.println("Test de listar");
		// GIVEN
		VideojuegoService videojuegosService = new VideojuegoService();
		// WHEN
		Set<String> resul = videojuegosService.listarEditores();
		// THEN
		Assert.assertNull("Comprobando que no es NULL", resul);
	}

	@Test
	public void testAnadir() {
		System.out.println("Probando test añadir");

		// GIVEN
		// Creamos un nuevo objeto Videojuego
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		Videojuego v1 = new Videojuego("Prueba", "Prueba", 9999, "Prueba", "Prueba");

		// WHEN
		// Le damos de alta
		videojuegoDao.altaVideojuego(v1);
		// leemos el fichero
		List<Videojuego> lista = videojuegoDao.leerFichero();
		// Comprobamos que se añadio
		boolean resultado = false;
		if (lista.get(lista.size() - 1).getRank() == v1.getRank()) {
			resultado = true;
		}
		// Borramos la insercion de prueba
		videojuegoDao.borrarVideojuego(v1.getRank());

		// THEN
		Assert.assertTrue("Comprobando la Edicion y Borrado", resultado);
	}

	@Test
	public void testEdicionyBorrado() {
		System.out.println("Probando test edicion y borrado");
		// GIVEN
		// Creamos un nuevo objeto Videojuego
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		Videojuego v1 = new Videojuego("Prueba", "Prueba", 9999, "Prueba", "Prueba");

		// WHEN
		// Le damos de alta
		videojuegoDao.altaVideojuego(v1);
		// leemos el fichero
		List<Videojuego> lista = videojuegoDao.leerFichero();
		// modificamos el objeto que acabamos de introducir en el fichero
		Videojuego v3 = lista.get(lista.size() - 1);
		v3.setNombre("Comprobacion");
		boolean resultado = videojuegoDao.editarVideojuego(v3);
		// leemos el fichero
		lista = videojuegoDao.leerFichero();
		// Comprobamos que se edito
		boolean resultado2 = false;
		if (lista.get(lista.size() - 1).getNombre().equals("Comprobacion")) {
			resultado2 = true;
		}
		// Borramos la insercion de prueba
		boolean resultado3 = videojuegoDao.borrarVideojuego(v3.getRank());
		// leemos el fichero
		lista = videojuegoDao.leerFichero();
		// Comprobamos que se ha borrado
		boolean resultado4 = true;
		for (Videojuego v : lista) {
			if (v.getRank() == v3.getRank()) {
				resultado4 = false;
				break;
			}
		}
		// Comprobamos que todas las respuestas anteriores son correctas
		boolean res_final = false;
		if (resultado && resultado2 && resultado3 && resultado4) {
			res_final = true;
		}

		// THEN
		Assert.assertTrue("Comprobando la Edicion y Borrado", res_final);
	}

	@Test
	public void testRescrituraFichero() {
		System.out.println("Probando test reescritura");
		// GIVEN
		VideojuegoDao videojuegoDao = new VideojuegoDao();

		// WHEN
		List<Videojuego> lista1 = videojuegoDao.leerFichero();
		videojuegoDao.guardarFichero();
		List<Videojuego> lista2 = videojuegoDao.leerFichero();

		// THEN
		Assert.assertFalse("Comprobando la reescritura del fichero", lista1.size() != lista2.size());
	}
}
