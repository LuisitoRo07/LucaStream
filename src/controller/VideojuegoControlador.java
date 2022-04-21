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
	
	public static void menu(VideojuegoService videojuegoService) {
		
		
		boolean seguir = true;
		int opcion=0;
		Scanner sc = new Scanner (System.in);
		while(seguir) {
			
			System.out.println("Elige una opción:");
			System.out.println("1-- Listar todos los videojuegos");
			System.out.println("2-- Añadir un nuevo videojuego ");
			System.out.println("3-- Informe de videojuegos con género: 'Plataforma'");
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
