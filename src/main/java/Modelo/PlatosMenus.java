package Modelo;

public class PlatosMenus {

	private int idPlatoMenu;
	private int idMenu;
	private int idPlato;
	
	
	public PlatosMenus(int idPlatoMenu, int idMenu, int idPlato) {
		super();
		this.idPlatoMenu = idPlatoMenu;
		this.idMenu = idMenu;
		this.idPlato = idPlato;
	}
	public int getIdPlatoMenu() {
		return idPlatoMenu;
	}
	public void setIdPlatoMenu(int idPlatoMenu) {
		this.idPlatoMenu = idPlatoMenu;
	}
	public int getIdPlato() {
		return idPlato;
	}
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	public int getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
	
	
	
}
