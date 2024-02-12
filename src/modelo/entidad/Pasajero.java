package modelo.entidad;

public class Pasajero {

	private int idPasajero;
	private String nombre;
	private int edad;
	private float peso;
	private Coche coche;
	
	public Pasajero() {
		super();
	}

	public Pasajero(int idPasajero, String nombre, int edad, float peso, Coche coche) {
		super();
		this.idPasajero = idPasajero;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.coche = coche;
	}

	public int getIdPasajero() {
		return idPasajero;
	}

	public void setIdPasajero(int idPasajero) {
		this.idPasajero = idPasajero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	@Override
	public String toString() {
		return "Pasajero [idPasajero=" + idPasajero + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso
				+ ", coche=" + coche + "]";
	}
}
