package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.Platos;

public class PlatosDAO {
	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	//Recupera la lista de platos de la BBDD
	public ArrayList<Platos> getPlatos(){
		ArrayList<Platos> listaP = new ArrayList<Platos>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from Platos;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				Platos plato= new Platos(res.getInt(1),res.getString(2),res.getFloat(3));
				
				listaP.add(plato);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaP;
	}
	//Inserta un plato
	public Platos insertPlato(String nombre, float precio) {
		Platos plato=null;
		
		String sql ="insert into Platos values (?,?,?)";
		String sqlID="select max(idplato) from Platos";
		
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
			sent.setFloat(3, precio);
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				plato= new Platos(id,nombre, precio);
			}
			
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(PlatosDAO.class.getName()).log(Level.SEVERE, null, e);

		}	
		return plato;
	}
	public boolean eliminarPlato(Platos plato,int idPlato) {
		boolean eleminado=false;
		String sql="delete from platos where idplato=?";
		
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			if (plato!=null){
				sent.setInt(1, plato.getIdPlatos());
			}else {
				sent.setInt(1,idPlato);
			}
			
			
			int filas= sent.executeUpdate();
			if(filas>0) {
				eleminado=true;
			}
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(PlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return eleminado;
		
	}
	public boolean modificarPrecioPlato(int id,float precio) {
		boolean modificado=false;
		String sql="update platos set pvp=? where idplato=?";
		
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
			Logger.getLogger(PlatosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		return modificado;
	}
	
}
