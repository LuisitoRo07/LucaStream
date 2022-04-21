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
	
	@Test
	public void tesListarEditor() {
		System.out.println("Probando test editor");
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		List<Videojuego> resultado = videojuegoDao.listarPorEditor("Nintendo");
		System.out.println(resultado.isEmpty());
		Assert.assertFalse("Comprobando que no es NULL", resultado.isEmpty());
	}
	
	@Test
	public void testEdicionyBorrado() {
		System.out.println("Probando test edicion y borrado");
		//Creamos un nuevo objeto Videojuego
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		Videojuego v1 = new Videojuego(999999, "Prueba", "Prueba", 9999, "Prueba", "Prueba",
				0, 0, 0, 0, 0);
		//Le damos de alta
		videojuegoDao.altaVideojuego(v1);
		//leemos el fichero
		List<Videojuego> lista = videojuegoDao.leerFichero();
		//modificamos el objeto que acabamos de introducir en el fichero
		Videojuego v3 = lista.get(lista.size()-1);
		v3.setNombre("Comprobacion");
		boolean resultado = videojuegoDao.editarVideojuego(v3);
		//leemos el fichero
		lista = videojuegoDao.leerFichero();
		//Comprobamos que se edito
		boolean resultado2 = false;
		if(lista.get(lista.size()-1).getNombre().equals("Comprobacion")){
			resultado2 = true;
		}
		
		//Borramos la insercion de prueba
		boolean resultado3 = videojuegoDao.borrarVideojuego(v1.getRank());
		//leemos el fichero
		lista = videojuegoDao.leerFichero();
		//Comprobamos que se ha borrado
		boolean resultado4 = true;
		for (Videojuego v : lista) {
			if (v.getRank() == v1.getRank()) {
				resultado2 = false;
				break;
			}
		}
		
		//Comprobamos que todas las respuestas anteriores son correctas
		boolean res_final = false;
		if(resultado && resultado2 && resultado3 && resultado4) {
			res_final = true;
		}
		Assert.assertFalse("Comprobando la Edicion y Borrado", res_final);
	}
	
	@Test
	public void rescrituraFichero() {
		System.out.println("Probando test reescritura");
		VideojuegoDao videojuegoDao = new VideojuegoDao();
		List<Videojuego> lista1 = videojuegoDao.leerFichero();
		videojuegoDao.guardarFichero();
		List<Videojuego> lista2 = videojuegoDao.leerFichero();
		boolean resultado1 = false;
		if(lista1.size() == lista2.size()) {
			resultado1 = true;
		}
		boolean resultado2 = true;
		for(int i = 0; i < lista1.size(); i++) {
			if(!lista1.get(i).equals(lista2.get(i))) {
				resultado2 = false;
				break;
			}
		}
		boolean res_final = false;
		if(resultado1 && resultado2) {
			res_final = true;
		}
		
		System.out.println(resultado2);
		Assert.assertFalse("Comprobando la reescritura del fichero", res_final);
	}
}
