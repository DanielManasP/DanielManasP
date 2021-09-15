package Modelo;

import java.sql.Date;

public class Reservas {
	
	private int idReserva;
	private int	idCliente;
	private int idEmple;
	private int numcomensales;
	private Date fechaReserva;
	private float precioTotal;
	private int pase;
	private int turno;
	
	
	
	
	public Reservas(int idReserva, int idCliente, int idEmple, int numcomensales, Date fechaReserva, float precioTotal,
			int pase, int turno) {
		super();
		this.idReserva = idReserva;
		this.idCliente = idCliente;
		this.idEmple = idEmple;
		this.numcomensales = numcomensales;
		this.fechaReserva = fechaReserva;
		this.precioTotal = precioTotal;
		this.pase = pase;
		this.turno = turno;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdEmple() {
		return idEmple;
	}
	public void setIdEmple(int idEmple) {
		this.idEmple = idEmple;
	}
	public int getNumcomensales() {
		return numcomensales;
	}
	public void setNumcomensales(int numcomensales) {
		this.numcomensales = numcomensales;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public int getPase() {
		return pase;
	}
	public void setPase(int tarde) {
		this.pase = tarde;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	public Object getValue(int columna){
        Object value = null;
        
        switch(columna){
        	case 0:
        		value=getIdReserva();
        		break;
            case 1:
                value = getIdCliente();
                break;
            case 2:
                value = getIdEmple();
                break;
            case 3:
            	value = getNumcomensales();
            	break;
            case 4:
            	value = getFechaReserva();
            	break;
            case 5:
            	value = getPrecioTotal();
            	break;
            case 6:
            	value = getPase();
            	break;
            case 7:
            	value = getTurno();
            	break;

        }
        
        return value;
    }
	
	
}
