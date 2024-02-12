package main;

import java.util.Scanner;

public class MenuPasajero {
	public static int pintarMenu() {

		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("---GESTIÓN DE PASAJEROS---");
		System.out.println("");
		System.out.println("1.- Crear nuevo pasajero");
		System.out.println("2.- Borrar pasajero");
		System.out.println("3.- Consultar pasajero");
		System.out.println("4.- Listar todos los pasajeros");
		System.out.println("5.- Añadir pasajero a un coche");
		System.out.println("6.- Eliminar pasajero de un coche");
		System.out.println("7.- Listar todos los pasajeros de un coche");
		System.out.println("8.- Volver a la gestión de la flota");
		System.out.println("9.- Salir del programa");
		System.out.println("");
		System.out.println("Teclea una opción: ");
		opcion = sc.nextInt();
		while (opcion <1 || opcion>9){
			System.out.println("del 1 al 9");
			opcion = sc.nextInt();
			}
		return opcion;
		}
}
