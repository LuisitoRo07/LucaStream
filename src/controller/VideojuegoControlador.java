package controller;

import java.util.Scanner;

import model.Videojuego;
import service.VideojuegoService;

public class VideojuegoControlador {

	public static void altaOption(Scanner sc, VideojuegoService videojuegoService) {

		System.out.println("--------------------------------------------------------------");
		System.out.println("Introduzca el nombre del Videojuego:");
		String nombre = sc.next();
		System.out.println("--------------------------------------------------------------");
		System.out.println("Introduzca el año de publicación del Videojuego:");
		int year = sc.nextInt();
		System.out.println("--------------------------------------------------------------");
		System.out.println("Introduzca la plataforma para la que está creado el Videojuego:");
		String plataforma = sc.next();
		System.out.println("--------------------------------------------------------------");
		System.out.println("Introduzca el género del Videojuego:");
		String gender = sc.next();
		System.out.println("Introduzca el publisher:");
		String publisher = sc.next();

		Videojuego v = new Videojuego(nombre, plataforma, year, gender, publisher);

		videojuegoService.altaVideojuego(v);

	}

	public static void deleteOption(Scanner sc, VideojuegoService videojuegoService) {
		System.out.println("--------------------------------------------------------------");
		System.out.println("Introduzca el numero correspondiente al rank o id del videojuego que desea eliminar:");
		int rank = sc.nextInt();
		boolean done = false;

		if (videojuegoService.existeVideojuego(rank)) {
			done = borrarVideojuego(rank, videojuegoService);
		} else {
			System.out.println("El rango o id introducido no se corresponde con ningun videojuego");
		}

		if (done) {
			System.out.println("Videojuego eliminado correctamente");
		} else {
			System.out.println("Fallo al eliminar videojuego");
		}
	}

	public static boolean editarVideojuego(Scanner sc, VideojuegoService videojuegoService) {

		System.out.println("--------------------------------------------------------------");
		System.out.println("Introduzca el rango del Videojuego:");
		int rank = sc.nextInt();

		if (videojuegoService.existeVideojuego(rank)) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca el nuevo nombre del Videojuego:");
			String nombre = sc.next();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca el año de publicación del Videojuego:");
			int year = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca la plataforma para la que está creado el Videojuego:");
			String plataforma = sc.next();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca el género del Videojuego:");
			String gender = sc.next();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca el publisher:");
			String publisher = sc.next();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca las NA_Sales:");
			double NA_Sales = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca las EU_Sales:");
			double EU_Sales = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca las JP_Sales:");
			double JP_Sales = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca las Other_Sales:");
			double Other_Sales = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Introduzca las Global_Sales:");
			double Global_Sales = sc.nextInt();

			Videojuego v = new Videojuego(rank, nombre, plataforma, year, gender, publisher, NA_Sales, EU_Sales,
					JP_Sales, Other_Sales, Global_Sales);
			v.setRank(rank);
			if (videojuegoService.editarVideojuego(v)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	public static boolean borrarVideojuego(int rank, VideojuegoService videojuegoService) {
		return videojuegoService.borrarVideojuego(rank);
	}

	public static void menu(VideojuegoService videojuegoService) {
		
		
		boolean seguir = true;
		int opcion=0;
		Scanner sc = new Scanner (System.in);
		while(seguir) {
			
			System.out.println("Elige una opción:");
			System.out.println("1-- Listar todos los videojuegos");
			System.out.println("2-- Añadir un nuevo videojuego ");
			System.out.println("3-- Informe de videojuegos con género: 'Plataforma'");
			System.out.println("4-- Informe de videojuegos con editor: 'Nintendo'");
			System.out.println("5-- Informe de videojuegos del siglo XX");
			System.out.println("6-- Editar un videojuego");
			System.out.println("7-- Eliminar un videojuego");
			System.out.println("0-- Salir del programa ");
			opcion = sc.nextInt();
			switch(opcion) {
				
				case 0:
					seguir = false;
					break;
				case 1:
					  try {
						  System.out.println(videojuegoService.listarVideojuegos());
						} catch (Exception e) {
							System.out.println(e);
							System.out.println("Se ha producido un error... - Controller");
						}
				    break;
				case 2:
					altaOption(sc, videojuegoService);
					break;
				case 3:
					System.out.println(videojuegoService.listarPorGenero("Platform"));
					break;
				case 4:
					System.out.println(videojuegoService.listarPorEditor("Nintendo"));
					break;
				case 5:
					System.out.println(videojuegoService.listarJuegosSigloXX());
					break;
				case 6:
					if (editarVideojuego(sc, videojuegoService)) {
						System.out.println("Editado correctamente.");
					} else {
						System.out.println("No se pudo editar.");
					}
					
					break;
				case 7:
					
					deleteOption(sc, videojuegoService);
					
					break;
				default: 
					System.out.println("Opcion incorrecta. Marca una nueva opcion.");	
			}
			
		}
		System.out.println("Fin de programa. ¡Hasta pronto!");
	}

	public static void main(String[] args) {

		VideojuegoService videojuegoService = new VideojuegoService();

		menu(videojuegoService);
	}

}
