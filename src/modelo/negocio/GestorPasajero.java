package modelo.negocio;

import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajerosMySql;
import modelo.persistencia.interfaces.DaoPasajeros;

public class GestorPasajero {

	private DaoPasajeros daoPasajeros = new DaoPasajerosMySql();
	
	public boolean alta(Pasajero p){
		boolean alta = daoPasajeros.alta(p);
		return alta;
	}
	
	public boolean baja(int id){
		boolean baja = daoPasajeros.baja(id);
		return baja;
	}
	
	public Pasajero obtener(int id){
		Pasajero pasajero = daoPasajeros.obtener(id);
		return pasajero;
	}
	
	public List<Pasajero> listar(){
		List<Pasajero> listaPasajeros = daoPasajeros.listar();
		return listaPasajeros;
	}
	
	
	public boolean añadirPasajero(int IdPasajero, int IdCoche){
		boolean alta = daoPasajeros.AñadirPasajeroCoche(IdPasajero,IdCoche);
		return alta;
	}
	
	public boolean EliminarPasajero(int id){
		boolean baja = daoPasajeros.eliminarPasajeroCoche(id);
		return baja;
	}
	
	public List<Pasajero> listarPasajerosCoche(int idCoche){
		List<Pasajero> listaPasajerosCoche = daoPasajeros.listarPasajerosCoche(idCoche);
		return listaPasajerosCoche;
	}
}
