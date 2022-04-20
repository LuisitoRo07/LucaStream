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
			
			System.out.println("Elige una opcion:");
			opcion = sc.nextInt();
			switch(opcion) {
				
				case 0:
					seguir = false;
					break;
				case 2:
					altaOption(sc, videojuegoService);
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
