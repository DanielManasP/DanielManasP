package Modelo;

public class Restaurante {

	private int idRestaurante;
	private String nombre;
	private String direccion;
	private int maxComensales;
	private int numMesas;
	
	
	
	public Restaurante(int idRestaurante, String nombre, String direccion, int maxComensales, int numMesas) {
		super();
		this.idRestaurante = idRestaurante;
		this.nombre = nombre;
		this.direccion = direccion;
		this.maxComensales = maxComensales;
		this.numMesas = numMesas;
	}
	public int getIdRestaurante() {
		return idRestaurante;
	}
	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getMaxComensales() {
		return maxComensales;
	}
	public void setMaxComensales(int maxComensales) {
		this.maxComensales = maxComensales;
	}
	public int getNumMesas() {
		return numMesas;
	}
	public void setNumMesas(int numMesas) {
		this.numMesas = numMesas;
	}
	
	
	
}
