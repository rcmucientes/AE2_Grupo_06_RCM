package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoFlotaCochesMySql;
import modelo.persistencia.interfaces.DaoFlotaCoches;

public class GestorCoche {

	private DaoFlotaCoches daoFlotaCoches = new DaoFlotaCochesMySql();
	
	public int alta(Coche c){
		if(c.getMarca().isEmpty() || c.getModelo().isEmpty()) {
			return 2;	
		}else {
			boolean alta = daoFlotaCoches.alta(c);
			if(alta) {
				return 0;
			}else {
				return 1;
			}	
		}
	}
	
	public boolean baja(int id){
		boolean baja = daoFlotaCoches.baja(id);
		return baja;
	}
	
	public Coche obtener(int id){
		Coche coche = daoFlotaCoches.obtener(id);
		return coche;
	}
	
	public int modificar(Coche c){
		if(c.getMarca().isEmpty() || c.getModelo().isEmpty()) {
			return 2;	
		}else {
			boolean alta = daoFlotaCoches.modificar(c);
			if(alta) {
				return 0;
			}else {
				return 1;
			}	
		}
	}

	public List<Coche> listar(){
		List<Coche> listaCoches = daoFlotaCoches.listar();
		return listaCoches;
	}	
}
