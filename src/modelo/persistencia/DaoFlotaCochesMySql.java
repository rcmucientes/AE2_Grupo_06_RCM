package modelo.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoFlotaCoches;

public class DaoFlotaCochesMySql implements DaoFlotaCoches {
	
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
	public boolean alta(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coches (MARCA,MODELO,FABRICACION,KILOMETROS) "
				+ " values(?,?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getFechaFabricacion());
			ps.setInt(4, c.getKilometros());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean baja(int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coches where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + idCoche);
			borrado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}
	
	@Override
	public Coche obtener(int idCoche) {
		if(!abrirConexion()){
			return null;
		}	
		
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,FABRICACION,KILOMETROS from coches "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setIdCoche(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setFechaFabricacion(rs.getInt(4));
				coche.setKilometros(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println(" error al obtener el "
					+ "coche con id " + idCoche);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return coche;
	}

	@Override
	public boolean modificar(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coches set MARCA=?, MODELO=?, FABRICACION=?, KILOMETROS=? "
				+  "WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getFechaFabricacion());
			ps.setInt(4, c.getKilometros());
			ps.setInt(5, c.getIdCoche());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al modificar el "
					+ " coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}
		Coche coche = null;
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MARCA,MODELO,FABRICACION,KILOMETROS from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				coche = new Coche();
				coche.setIdCoche(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setFechaFabricacion(rs.getInt(4));
				coche.setKilometros(rs.getInt(5));
				
				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("error al obtener el listado");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaCoches;
	}	
}
	
	
	
	

