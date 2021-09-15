package Modelo;

import java.util.Date;

public class Empleados {
	private int idEmple;
	private String nombre;
	private int tlf;
	private String password;
	private Date fechaContrata;
	private float salario;
	private int esJefe; //0 es jefe, 1 es empleado
	
	
	
	public Empleados(int idEmple, String nombre, int tlf, String password, Date fechaContrata, float salario,
			int esJefe) {
		super();
		this.idEmple = idEmple;
		this.nombre = nombre;
		this.tlf = tlf;
		this.password = password;
		this.fechaContrata = fechaContrata;
		this.salario = salario;
		this.esJefe = esJefe;
	}
	public int getIdEmple() {
		return idEmple;
	}
	public void setIdEmple(int idEmple) {
		this.idEmple = idEmple;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTlf() {
		return tlf;
	}
	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaContrata() {
		return fechaContrata;
	}
	public void setFechaContrata(Date fechaContrata) {
		this.fechaContrata = fechaContrata;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public int getEsJefe() {
		return esJefe;
	}
	public void setEsJefe(int esJefe) {
		this.esJefe = esJefe;
	}
	public Object getValue(int columna){
        Object value = null;
        
        switch(columna){
        	case 0:
        		value=getIdEmple();
        		break;
            case 1:
                value = getNombre();
                break;
            case 2:
                value = getTlf();
                break;
            case 3:
            	value = getPassword();
            	break;
            case 4:
            	value = getFechaContrata();
            	break;
            case 5:
            	value = getSalario();
            	break;
            case 6:
            	value = getEsJefe();
            	break;

        }
        
        return value;
    }
	
	
}
