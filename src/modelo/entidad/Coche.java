package modelo.entidad;

public class Coche {

	private int idCoche;
	private String marca;
	private String modelo;
	private int fechaFabricacion;
	private int kilometros;
	
	public Coche() {
		super();
	}

	public Coche(int idCoche, String marca, String modelo, int fechaFabricacion, int kilometros) {
		super();
		this.idCoche = idCoche;
		this.marca = marca;
		this.modelo = modelo;
		this.fechaFabricacion = fechaFabricacion;
		this.kilometros = kilometros;
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(int fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	@Override
	public String toString() {
		return "Coche [idCoche=" + idCoche + ", marca=" + marca + ", modelo=" + modelo + ", fechaFabricacion="
				+ fechaFabricacion + ", kilometros=" + kilometros + "]";
	}
}
