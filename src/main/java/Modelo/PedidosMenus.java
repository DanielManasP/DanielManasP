package Modelo;

public class PedidosMenus {
	
	private int idPedidoMenu;
	private int idCliente;
	private int idMenu;
	private int estado; // EStado 0 pedido Finalizado, estado 1 pedido Activo
	
	
	
	public PedidosMenus(int idPedidoMenu, int idCliente, int idMenu, int estado) {
		super();
		this.idPedidoMenu = idPedidoMenu;
		this.idCliente = idCliente;
		this.idMenu = idMenu;
		this.estado = estado;
	}
	public int getIdPedidoMenu() {
		return idPedidoMenu;
	}
	public void setIdPedidoMenu(int idPedidoMenu) {
		this.idPedidoMenu = idPedidoMenu;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
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
        		value=getIdPedidoMenu();
        		break;
            case 1:
                value = getIdCliente();
                break;
            case 2:
                value = getIdMenu();
                break;
            case 3:
            	value = getEstado();
            	break;

        }
        
        return value;
    }
	
	
	
}
