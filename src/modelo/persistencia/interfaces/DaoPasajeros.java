package modelo.persistencia.interfaces;

import java.util.List;


import modelo.entidad.Pasajero;

public interface DaoPasajeros {
	
	boolean alta(Pasajero p);
	boolean baja(int idPasajero);
	Pasajero obtener(int idPasajero);
	List<Pasajero> listar();
	boolean AñadirPasajeroCoche(int idPasajero, int idCoche);
	boolean eliminarPasajeroCoche(int idPasajero);
	List<Pasajero>listarPasajerosCoche(int idCoche);
}
