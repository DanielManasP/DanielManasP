package DAOs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.PedidosPlatos;

public class PedidosPlatosDAO {
	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	//Recupera la lista de menus de la BBDD
	public ArrayList<PedidosPlatos> getPedidosPlatos(){
		ArrayList<PedidosPlatos> listaP = new ArrayList<PedidosPlatos>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from pedidosplatos;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				PedidosPlatos pedidosPlatos= new PedidosPlatos(res.getInt(1),
						res.getInt(2),res.getInt(3),res.getDate(4),res.getInt(5));
				
				listaP.add(pedidosPlatos);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PedidosPlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaP;
	}
	//Inserta un menu
	public PedidosPlatos insertPedidoPlato(int idCliente,int idPlato,Date fecha) {
		PedidosPlatos pPlato=null;
		int estado=1;
		String sql ="insert into pedidosplatos values (?,?,?,?,?)";
		String sqlID="select max(idpedidoplato) from pedidosplatos";
		
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
			sent.setInt(3, idPlato);
			sent.setDate(4, fecha);
			sent.setInt(5, estado);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				pPlato= new PedidosPlatos(id,idCliente, idPlato,fecha,estado);
			}
			
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PedidosPlatosDAO.class.getName()).log(Level.SEVERE, null, e);

		}	
		return pPlato;
	}
	public boolean eliminaPedidorPlato(PedidosPlatos pPlato) {
		boolean eleminado=false;
		String sql="delete from pedidosplatos where idpedidoplato=?";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setInt(1, pPlato.getIdPedidoPlato());
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				eleminado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PedidosPlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return eleminado;
		
	}
	
	//Modifica el MENU  en funcion del cliente y el id del pedido
	public boolean modificarPedidoPlato(int idCliente, int idPedidoP, int idPlato) {
		boolean modificado=false;
		String sql="update pedidosplatos set"
				+ "  idPlato=? where idcliente=? and idpedidoplato=?"
				+ " and estado = 1";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setInt(1, idPlato);
			sent.setInt(2, idCliente);
			sent.setInt(3, idPedidoP);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				modificado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PedidosPlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		//El mensaje deberia cogerse con el return, en caso falso se dice
		// el pedido no existe o no esta activo
		
		return modificado;
	}
	//Modifica el estado del menu
	public boolean modificarEstadoFinalizado(int idCliente,Date fecha) {
		boolean modificado=false;
		String sql="update pedidosplatos set"
				+ "  estado=0 where idcliente=? and fechareserva=? and estado=1";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			
			sent.setInt(1, idCliente);
			sent.setDate(2, fecha);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				modificado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PedidosPlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		return modificado;
	}
}
