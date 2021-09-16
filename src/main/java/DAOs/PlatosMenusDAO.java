package DAOs;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.PlatosMenus;
import Modelo.Reservas;

public class PlatosMenusDAO {
	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	public ArrayList<PlatosMenus> getPlatosMenus(){
		ArrayList<PlatosMenus> listaPM = new ArrayList<PlatosMenus>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from platosmenu;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				PlatosMenus reserva= 
						new PlatosMenus(res.getInt(1),res.getInt(2)
								,res.getInt(3));
				
				listaPM.add(reserva);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PlatosMenusDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaPM;
	}
	
	public boolean insertaPlatosMenu(int idmenu, int idplato, int idplato2, 
			int idplato3, int idplato4) {
		boolean correcto=false;
		ArrayList<Integer> platos= new ArrayList<Integer>();
		platos.add(idplato);
		platos.add(idplato2);
		if(idplato3!=0) {
			platos.add(idplato3);
			if(idplato4!=0) {
				platos.add(idplato4);
			}
		}
		for(Integer id: platos) {
			if(addPlatoMenu(idmenu, id)) {
				correcto=true;
			}
		}
		return correcto;
		
	}
	private boolean addPlatoMenu(int idmenu, int idplato) {
		boolean correcto=false;
		
		String sql="insert into platosmenu set idplatosmenu=?, idmenu=?, idplato=? ";
		String sqlID="select max(idplatosmenu) from platosmenu";
		try {
			//Obtenemos el ID, se autoincrementa
			Statement sentId= (Statement) conexion.createStatement();
			ResultSet resId= sentId.executeQuery(sqlID);
			resId.next();
			int id= resId.getInt(1)+1;
			resId.close();
			sentId.close();
			
			
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setInt(1, id);
			sent.setInt(2, idmenu);
			sent.setInt(3, idplato);
			int filas=sent.executeUpdate();
			if(filas>0) {
				correcto=true;
			}
			
			sent.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correcto;
	}
}
