package Modelo;

public class Platos {

	private int idPlatos;
	private String nombre;
	private float precio;
	
	
	
	public Platos(int idPlatos, String nombre, float precio) {
		super();
		this.idPlatos = idPlatos;
		this.nombre = nombre;
		this.precio = precio;
	}
	public int getIdPlatos() {
		return idPlatos;
	}
	public void setIdPlatos(int idPlatos) {
		this.idPlatos = idPlatos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Object getValue(int columna){
        Object value = null;
        
        switch(columna){
        	case 0:
        		value=getIdPlatos();
        		break;
            case 1:
                value = getNombre();
                break;
            case 2:
                value = getPrecio();
                break;

        }
        
        return value;
    }
}
