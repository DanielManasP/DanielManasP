package Modelo;

public class PlatosMenus {

	private int idPlatoMenu;
	private int idPlato;
	private int idMenu;
	
	
	
	public PlatosMenus(int idPlatoMenu, int idPlato, int idMenu) {
		super();
		this.idPlatoMenu = idPlatoMenu;
		this.idPlato = idPlato;
		this.idMenu = idMenu;
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
