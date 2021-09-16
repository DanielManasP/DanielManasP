package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.Menus;

public class MenuDAO {
	
	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	//Recupera la lista de menus de la BBDD
	public ArrayList<Menus> getMenus(){
		ArrayList<Menus> listaM = new ArrayList<Menus>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from Menus;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				Menus menu= new Menus(res.getInt(1),res.getString(2),res.getFloat(3));
				
				listaM.add(menu);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaM;
	}
	//Inserta un menu
	public Menus insertMenu(String nombre) {
		Menus menu=null;
		
		String sql ="insert into menus values (?,?,?)";
		String sqlID="select max(idmenu) from menus";
		
		try {	
			//Obtenemos el ID, se autoincrementa
			Statement sentId= (Statement) conexion.createStatement();
			ResultSet resId= sentId.executeQuery(sqlID);
			resId.next();
			int id= resId.getInt(1)+1;
			resId.close();
			sentId.close();
			
			//Insertamos
			PreparedStatement sent=  conexion.prepareStatement(sql);
			sent.setInt(1, id);
			sent.setString(2, nombre);
			sent.setFloat(3, 0.0f);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				menu= new Menus(id,nombre, 0.0f);
			}
			
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, e);

		}	
		return menu;
	}
	public boolean eliminarMenu(Menus menu) {
		boolean eleminado=false;
		String sql="delete from Menus where idmenu=?";
		if(elminarMenusTablas(menu.getIdMenu())) {
			try {
				PreparedStatement sent= conexion.prepareStatement(sql);
				sent.setInt(1, menu.getIdMenu());
				
				int filas= sent.executeUpdate();
				if(filas>0) {
					eleminado=true;
				}
				sent.close();
			}catch (SQLException e) {
				Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return eleminado;
		
	}
	public boolean elminarMenusTablas(int idmenu) {
		boolean correcto=false;
		String sql="delete from pedidosmenus where idmenu="+idmenu;
		String sql1="delete from platosmenu where idmenu="+idmenu;
		
		try {
			Statement sent= (Statement) conexion.createStatement();
			int filasPedidos=sent.executeUpdate(sql);
			int filasPlatosM=sent.executeUpdate(sql1);
			if(filasPlatosM>0) {
				if(filasPedidos>0) {
					correcto=true;
				}		
			}
			sent.close();
			
		} catch (SQLException e) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return correcto;
	}
	public boolean modificarPrecioMenu(int id,float precio) {
		boolean modificado=false;
		String sql="update menus set pvp=? where idmenu=?";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setFloat(1, precio);
			sent.setInt(2, id);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				modificado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return modificado;
	}
}
