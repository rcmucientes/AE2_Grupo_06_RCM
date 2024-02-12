package main;

import java.util.List;
import java.util.Scanner;
import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static GestorCoche gc = new GestorCoche();
	static VistaPasajeros vistaPasajeros = new VistaPasajeros();
	
	public static void main(String[] args) {
		
	switchMenuCoches();		
	
	}	
	public static void switchMenuCoches() {			
		int opcion = 0;
		do {
			opcion= MenuCoche.pintarMenu();
			switch (opcion) {
				case 1:
					altaCoche();
				break;
				case 2:
					borrarCoche();
				break;
				case 3:
					consultarCoche();
				break;
				case 4:
					modificarCoche();
				break;	
				case 5:
					listarCoches();
				break;
				case 6:
					vistaPasajeros.switchMenuPasajeros();
				break;
				case 7:
					salir();
				break;
			}
		}while (opcion!=7);
	}
	
	public static void altaCoche() {
		System.out.println("Introduzca los datos del coche: ");
		System.out.println("Marca: ");
		String marca = sc.nextLine();
		System.out.println("Modelo: ");
		String modelo = sc.nextLine();
		System.out.println("Año fabricación (cuatro dígitos): ");
		int fechaFabricacion = sc.nextInt();
		System.out.println("Kilómetros: ");
		int kilometros = sc.nextInt();
		sc.nextLine();
		
		Coche c = new Coche();
		c.setMarca(marca);
		c.setModelo(modelo);
		c.setFechaFabricacion(fechaFabricacion);
		c.setKilometros(kilometros);
		
		int alta = gc.alta(c);
		if(alta == 0) {
			System.out.println("");
			System.out.println("Coche dada de alta");
		}else if(alta == 1) {
			System.out.println("Error de conexión con la BBDD");
		}else if(alta == 2){
			System.out.println("Marca y Modelo no pueden estar vacíos");
		}
	}
	
	public static void borrarCoche() {
		System.out.println("Introduzca el Id del pasajero que desee borrar");
		int idCoche = sc.nextInt();
		
		boolean baja = gc.baja(idCoche);
		if(baja) {
			System.out.println("");
			System.out.println("Coche borrado");
		}else  {
			System.out.println("Error de conexión con la BBDD");
		}	
	}
	
	public static void consultarCoche() {
		System.out.println("Introduzca el Id del coche que desee consultar");
		int idCoche = sc.nextInt();
		
		Coche c = gc.obtener(idCoche);
		if(c == null ) {
			System.out.println("");
			System.out.println("Error de conexión con la BBDD");
		}else  {
			System.out.println("");
			System.out.println(c);
		}	
	}
	
	public static void modificarCoche() {
		System.out.println("Introduzca id del coche a modificar: ");
		System.out.println("IdCoche: ");
		int idCoche = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduzca los nuevos datos");
		System.out.println("");
		System.out.println("Marca: ");
		String marca = sc.nextLine();
		System.out.println("Modelo: ");
		String modelo = sc.nextLine();
		System.out.println("Año fabricación (cuatro dígitos): ");
		int fechaFabricacion = sc.nextInt();
		System.out.println("Kilómetros: ");
		int kilometros = sc.nextInt();
		sc.nextLine();
		
		Coche c = new Coche();
		c.setIdCoche(idCoche);
		c.setMarca(marca);
		c.setModelo(modelo);
		c.setFechaFabricacion(fechaFabricacion);
		c.setKilometros(kilometros);
		
		int alta = gc.modificar(c);
		if(alta == 0) {
			System.out.println("");
			System.out.println("Coche modificado");
		}else if(alta == 1) {
			System.out.println("Error de conexión con la BBDD");
		}else if(alta == 2){
			System.out.println("Marca y Modelo no pueden estar vacíos");
		}
	}	

	public static void listarCoches() {
		List<Coche> listaCoches = gc.listar();
		if(listaCoches == null ) {
			System.out.println("");
			System.out.println("Error de conexión con la BBDD");
		}else  {
			System.out.println("");
		}
		
		for(Coche c : listaCoches)
		System.out.println(c);
	}
	
	public static void salir() {
		System.out.println("Fin del programa");
		System.exit(0);
	}
}
	
