package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoFlotaCoches {
	
	boolean alta(Coche c);
	boolean baja(int idCoche);
	boolean modificar(Coche c);
    Coche obtener(int idCoche);
	List<Coche> listar();
}
