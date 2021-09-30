package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

import Modelo.Clientes;
import Modelo.Empleados;
	


public class InicioSesion {
	
	private Connection conexion = (Connection) Conexion.conexionmysql();
	
	public Object inicioSesionUsu(String email, String pass) {
		
		Object obj= null;
		try {
			String sql="select * from clientes where email=? and password=?;";
			
			PreparedStatement sentencia=conexion.prepareStatement(sql);
			sentencia.setString(1, email);
			sentencia.setString(2, pass);
			ResultSet res= sentencia.executeQuery();
			if(res.next()) {				//Es cliente
				obj=new Clientes(res.getInt(1),res.getString(2),res.getString(3),
						res.getString(4),res.getInt(5));
				
				
			}else {
				String sql2="select * from empleados where idempleado=? and password=?;";
				PreparedStatement sentencia2=conexion.prepareStatement(sql2);
				sentencia2.setString(1, email);
				sentencia2.setString(2, pass);
				ResultSet res2= sentencia2.executeQuery();
				if(res2.next()) {
//					if(res2.getInt(7)==0) {	//Es jefe
//						obj= new Empleados(res2.getInt(1),res2.getString(2),res2.getInt(3),
//								res2.getString(4),res2.getDate(5),res2.getFloat(6),res2.getInt(7));
//					}else {					//Es empleado
						obj= new Empleados(res2.getInt(1),res2.getString(2),res2.getInt(3),
								res2.getString(4),res2.getDate(5),res2.getFloat(6),res2.getInt(7));
//					}	
				}
				res2.close();
				sentencia2.close();
			}
			res.close();
			sentencia.close();
		
		} catch (SQLException e) {
			Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, e);

			
		}
		return obj;
	}
	public boolean crearCliente(String nombre, String email, String pass, String tlf) {
		boolean correcto=false;
		try {
			String sql="select * from clientes where email=? ;";
			
			PreparedStatement sentencia=conexion.prepareStatement(sql);
			sentencia.setString(1, email);
			ResultSet res= sentencia.executeQuery();
			if(res.next()) {	
				correcto=false;
			}else {
				String id="select max(IDCLIENTE) from clientes";
				Statement sent= conexion.createStatement();
				ResultSet res2=sent.executeQuery(id);
				res2.next();
				int idcliente=res2.getInt(1);
				idcliente++;
				int telf= Integer.parseInt(tlf);
				//INSERT INTO `clientes`(`IDCLIENTE`, `NOMBRE`, `EMAIL`, `PASSWORD`, `TELEFONO`)
				String sqlInsert="INSERT INTO clientes"
						+ " VALUES ("+idcliente+",'"+nombre+"','"+email+"','"+pass+"',"+telf+")";
				
				sent= conexion.createStatement();
				int filas=sent.executeUpdate(sqlInsert);
				if(filas>0) {
					correcto=true;
				}
				res2.close();
				sent.close();
				
			}
			res.close();
			sentencia.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return correcto=false;
		}
		return correcto;
		
	}
}
