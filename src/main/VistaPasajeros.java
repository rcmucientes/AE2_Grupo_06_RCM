package main;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;

public class VistaPasajeros {

	static Scanner sc = new Scanner(System.in);
	static GestorPasajero gp = new GestorPasajero();
	static GestorCoche gc = new GestorCoche();
	
	public void switchMenuPasajeros() {
		int opcion = 0;
		
		do {
			opcion= MenuPasajero.pintarMenu();
			switch (opcion) {
				case 1:
					altaPasajero();
				break;
				case 2:
					borrarPasajero();
				break;
				case 3:
					consultarPasajero();
				break;
				case 4:
					listarPasajero();
				break;	
				case 5:
					AñadirPasajeroCoche();
				break;
				case 6:
					eliminarPasajeroCoche();
				break;	
				case 7:
					listarPasajeroscoche();
				break;	
				case 8:
					volverMenuFlota();
				break;	
				case 9:
					salir();
				break;
			}
		}while (opcion!=9);
	}
	
	public static void altaPasajero() {
		System.out.println("Introduzca los datos del pasajero.");
		System.out.println("");
		System.out.println("nombre: ");
		String nombre = sc.next();
		System.out.println("Edad: ");
		int edad = sc.nextInt();
		System.out.println("Peso: ");
		float peso = sc.nextFloat();
		
		Pasajero p = new Pasajero();
		p.setNombre(nombre);
		p.setEdad(edad);
		p.setPeso(peso);
		
		boolean alta = gp.alta(p);
		if(alta) {
			System.out.println("");
			System.out.println("Pasajero dada de alta");
			System.out.println("");
		}else{
			System.out.println("Error de conexión con la BBDD");
		}
	}
	
	public static void borrarPasajero() {
		System.out.println("Introduzca el Id del pasajero que desee borrar:");
		int idPasajero = sc.nextInt();
		
		boolean baja = gp.baja(idPasajero);
		if(baja) {
			System.out.println("");
			System.out.println("Pasajero borrado");
			System.out.println("");
		}else  {
			System.out.println("Error de conexión con la BBDD");
		}	
	}
	
	public static void consultarPasajero() {
		System.out.println("Introduzca el Id del pasajero que desee consultar");
		int idPasajero = sc.nextInt();
		
		Pasajero p = gp.obtener(idPasajero);
		if(p == null ) {
			System.out.println("");
			System.out.println("Error de conexión con la BBDD");
		}else  {
			System.out.println("");
		}
		System.out.println(p);
		System.out.println("");
	}


	public static void listarPasajero() {
		List<Pasajero> listaPasajeros = gp.listar();
		if(listaPasajeros == null ) {
			System.out.println("");
			System.out.println("Error de conexión con la BBDD");
		}else  {
			System.out.println("");
		}
		
		for(Pasajero p : listaPasajeros)
		System.out.println(p);
		System.out.println("");
		
	}

	public static void	AñadirPasajeroCoche() {
		System.out.println("Introduzca el id del pasajero:");
		int idPasajero = sc.nextInt();
		
		System.out.println("Estos son los coches disponibles: ");
		List<Coche> listaCoches = gc.listar();
		for(Coche c : listaCoches)
			System.out.println(c);
		System.out.println("");
		System.out.println("Introduzca el id del coche:");
		int idCoche = sc.nextInt();
	
		
		boolean alta = gp.añadirPasajero(idPasajero, idCoche);
		if(alta) {
			System.out.println("");
			System.out.println("Pasajero " + idPasajero + " asignado al coche " + idCoche);
			System.out.println("");
		}else{
			System.out.println("Error de conexión con la BBDD");
		}
	}
	
	public static void eliminarPasajeroCoche() {
		System.out.println("Estos son los pasajeros disponibles: ");
		List<Pasajero> listaPasajeros = gp.listar();
		for(Pasajero p : listaPasajeros)
			System.out.println(p);
			
		System.out.println("");
		System.out.println("Introduzca el Id del pasajero que desee borrar:");
		int idPasajero = sc.nextInt();
		
		boolean baja = gp.EliminarPasajero(idPasajero);
		if(baja) {
			System.out.println("");
			System.out.println("Pasajero borrado");
			System.out.println("");
		}else  {
			System.out.println("Error de conexión con la BBDD");
		}
	}
	
	public static void listarPasajeroscoche() {
		System.out.println("Introduzca el Id del coche que desea listar sus ocupantes:");
		int idCoche = sc.nextInt();
		List<Pasajero> listaPasajeros = gp.listarPasajerosCoche(idCoche);
		if(listaPasajeros == null ) {
			System.out.println("");
			System.out.println("Error de conexión con la BBDD");
		}else  {
			System.out.println("");
		for(Pasajero p : listaPasajeros)
		System.out.println(p);
		System.out.println("");
		}
	}
	
	public static void volverMenuFlota() {
		Main.switchMenuCoches();		
	}
	
	public static void salir() {
		System.out.println("Fin del programa");
		System.exit(0);
	}
}
