package main;

import java.util.Scanner;

public class MenuCoche {

	public static int pintarMenu() {

		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("---GESTIÓN DE FLOTA---");
		System.out.println("");
		System.out.println("1.- Añadir nuevo coche");
		System.out.println("2.- Borrar coche");
		System.out.println("3.- Consultar coche");
		System.out.println("4.- Modificar coche");
		System.out.println("5.- Listar todos los coches");
		System.out.println("6.- Gestión de pasajeros");
		System.out.println("7.- Salir del programa");
		System.out.println("");
		System.out.println("Teclea una opción: ");
		opcion = sc.nextInt();
		while (opcion <1 || opcion>7){
			System.out.println("del 1 al 7");
			opcion = sc.nextInt();
			}
		return opcion;
		}
}
