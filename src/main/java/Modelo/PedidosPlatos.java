package Modelo;

import java.sql.Date;

public class PedidosPlatos {
	
	private int idPedidoPlato;
	private int idCliente;
	private int idPlato;
	private Date fechareserva;
	private int estado;
	
	
	
	public PedidosPlatos(int idPedidoPlato, int idCliente, int idPlato, Date fechareserva, int estado) {
		super();
		this.idPedidoPlato = idPedidoPlato;
		this.idCliente = idCliente;
		this.idPlato = idPlato;
		this.fechareserva=fechareserva;
		this.estado = estado;
	}
	
	
	public Date getFechareserva() {
		return fechareserva;
	}


	public void setFechareserva(Date fechareserva) {
		this.fechareserva = fechareserva;
	}


	public int getIdPedidoPlato() {
		return idPedidoPlato;
	}
	public void setIdPedidoPlato(int idPedidoPlato) {
		this.idPedidoPlato = idPedidoPlato;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdPlato() {
		return idPlato;
	}
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Object getValue(int columna){
        Object value = null;
        
        switch(columna){
        	case 0:
        		value=getIdPedidoPlato();
        		break;
            case 1:
                value = getIdCliente();
                break;
            case 2:
                value = getIdPlato();
                break;
            case 3:
            	value = getEstado();
            	break;

        }
        
        return value;
    }
	
}
