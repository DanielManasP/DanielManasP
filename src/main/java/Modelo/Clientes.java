package Modelo;

public class Clientes {

	private int idCliente;
	private String nombre;
	private String email;
	private String password;
	private int tlf;
	
	
	public Clientes(int idCliente, String nombre, String email, String password, int tlf) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.tlf = tlf;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTlf() {
		return tlf;
	}
	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	
	public Object getValue(int columna){
        Object value = null;
        
        switch(columna){
        	case 0:
        		value=getIdCliente();
        		break;
            case 1:
                value = getNombre();
                break;
            case 2:
                value = getEmail();
                break;
            case 3:
            	value = getPassword();
            	break;
            case 4:
            	value = getTlf();
            	break;

        }
        
        return value;
    }
}
