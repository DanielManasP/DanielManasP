package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.PedidosMenus;

public class PedidosM_DAO {

	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	//Recupera la lista de menus de la BBDD
	public ArrayList<PedidosMenus> getPedidosMenu(){
		ArrayList<PedidosMenus> listaM = new ArrayList<PedidosMenus>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from PedidosMenus;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				PedidosMenus pedidosMenus= new PedidosMenus(res.getInt(1),
						res.getInt(2),res.getInt(3),res.getInt(4));
				
				listaM.add(pedidosMenus);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PedidosM_DAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaM;
	}
	//Inserta un menu
	public PedidosMenus insertPedidoMenu(int idCliente,int idMenu) {
		PedidosMenus pMenus=null;
		int estado=1;
		String sql ="insert into PedidosMenus values (?,?,?,?)";
		String sqlID="select max(idpedidoMenu) from pedidosmenus";
		
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
			sent.setInt(2, idCliente);
			sent.setInt(3, idMenu);
			sent.setInt(4, estado);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				pMenus= new PedidosMenus(id,idCliente, idMenu,estado);
			}
			
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PedidosM_DAO.class.getName()).log(Level.SEVERE, null, e);

		}	
		return pMenus;
	}
	public boolean eliminaPedidorMenu(PedidosMenus pMenu) {
		boolean eleminado=false;
		String sql="delete from pedidosmenus where idpedidomenu=?";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setInt(1, pMenu.getIdPedidoMenu());
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				eleminado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PedidosM_DAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return eleminado;
		
	}
	//Modifica el MENU  en funcion del cliente y el id del pedido
	public boolean modificarPedidoMenu(int idCliente, int idPedidoM, int idMenu) {
		boolean modificado=false;
		String sql="update pedidosmenus set"
				+ "  idMenu=? where idcliente=? and idpedidomenu=?"
				+ " and estado = 1";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setInt(1, idMenu);
			sent.setInt(2, idCliente);
			sent.setInt(3, idPedidoM);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				modificado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PedidosM_DAO.class.getName()).log(Level.SEVERE, null, e);
		}
		//El mensaje deberia cogerse con el return, en caso falso se dice
		// el pedido no existe o no esta activo
		
		return modificado;
	}
	//Modifica el estado del menu
	public boolean modificarEstadoFinalizado(int idCliente) {
		boolean modificado=false;
		String sql="update pedidosmenus set"
				+ "  estado=0 where idcliente=? and estado=1";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			
			sent.setInt(1, idCliente);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				modificado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PedidosM_DAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		return modificado;
	}
}
