package Modelo;

public class Menus {

	private int idMenu;
	private String nombre;
	private Float precio;
	

	
	public Menus(int idMenu, String nombre, Float precio) {
		super();
		this.idMenu= idMenu;
		this.nombre = nombre;
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
	
	public Object getValue(int columna){
        Object value = null;
        
        switch(columna){
        	case 0:
        		value=getIdMenu();
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
