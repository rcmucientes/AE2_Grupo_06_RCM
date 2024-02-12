package modelo.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajeros;

public class DaoPasajerosMySql implements DaoPasajeros{
	
private Connection conexion;
private Properties properties;
	
	public boolean abrirConexion(){
		
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File("config.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			conexion = DriverManager.getConnection(properties.getProperty("url"),
												   properties.getProperty("usuario"),
												   properties.getProperty("password"));
		} catch (SQLException e) {	
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alta(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajeros (NOMBRE,EDAD,PESO) "
				+ " values(?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setFloat(3,p.getPeso());
			;
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean baja(int idPasajero) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("No se ha podido dar de baja"
					+ " el id " + idPasajero);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public Pasajero obtener(int idPasajero) {
		if(!abrirConexion()){
			return null;
		}	
		
		Pasajero pasajero = null;
	
		
		String query = "select ID,NOMBRE,EDAD,PESO,IDCOCHE from pasajeros "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				Coche coche =new Coche();
				DaoFlotaCochesMySql daoFlotaCoches = new DaoFlotaCochesMySql();
				
				pasajero.setIdPasajero(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				int idCoche = rs.getInt(5);
				coche = daoFlotaCoches.obtener(idCoche);
				pasajero.setCoche(coche);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al obtener el pasajero " + idPasajero);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		if(!abrirConexion()){
			return null;
		}
		Pasajero pasajero = null;
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "select ID,NOMBRE,EDAD,PESO,IDCOCHE from pasajeros";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				pasajero = new Pasajero();
				Coche coche =new Coche();
				DaoFlotaCochesMySql daoFlotaCoches = new DaoFlotaCochesMySql();
				
				pasajero.setIdPasajero(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				int idCoche = rs.getInt(5);
				coche = daoFlotaCoches.obtener(idCoche);
				pasajero.setCoche(coche);
				
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el listado");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}

	@Override
	public boolean AñadirPasajeroCoche(int idPasajero, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "update pasajeros set idcoche=? where id = ? ";
				
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("Error al insertar al pasajero " + idPasajero);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean eliminarPasajeroCoche(int idPasajero) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "UPDATE pasajeros SET idcoche=null WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("No se ha podido eleminar el pasajero selecionado");
			e.printStackTrace();
			borrado = false;
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public List<Pasajero> listarPasajerosCoche(int idCoche) {
		if(!abrirConexion()){
			return null;
		}
		Pasajero pasajero = null;
		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "select * from pasajeros "
				+ "where idcoche=?";		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				Coche coche = new Coche();
				DaoFlotaCochesMySql daoFlotaCoches = new DaoFlotaCochesMySql();
				
				pasajero.setIdPasajero(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				int idCoche2 = rs.getInt(5);
				coche = daoFlotaCoches.obtener(idCoche2);
				pasajero.setCoche(coche);
				
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el listado");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}

}
