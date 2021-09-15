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

import Modelo.Empleados;

public class EmpleadosDAO {
Connection conexion=  (Connection) Conexion.conexionmysql();
	
	//Recupera la lista de clientes de la BBDD
	public ArrayList<Empleados> getEmpleados(){
		ArrayList<Empleados> listaE = new ArrayList<Empleados>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from empleados;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				Empleados empleado= 
						new Empleados(res.getInt(1),res.getString(2)
								,res.getInt(3),
								res.getString(4),res.getDate(5),res.getFloat(6),
								res.getInt(7));
				
				listaE.add(empleado);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaE;
	}
	//Inserta un plato
	public Empleados insertaEmple(String nombre, int tlf, String password,
			float salario) {
		
		Empleados empleado=null;
		int esjefe=1;
		String sql ="insert into empleados values (?,?,?,?,CURDATE(),?,?)";
		String sqlID="select max(idempleado) from empleados";	
		try {	
			//Obtenemos el ID, se autoincrementa
			Statement sentId= (Statement) conexion.createStatement();
			ResultSet resId= sentId.executeQuery(sqlID);
			resId.next();
			
			int id= resId.getInt(1)+1;
			resId.close();
			sentId.close();
			
			if(!existeEmple(id)) {
				//Insertamos
				PreparedStatement sent=  conexion.prepareStatement(sql);
				sent.setInt(1, id);
				sent.setString(2, nombre);
				sent.setInt(3, tlf);
				sent.setString(4, password);
				sent.setFloat(5, salario);
				sent.setInt(6, esjefe);
				
				int filas= sent.executeUpdate();
				if(filas>0) {
					empleado= new Empleados(id,nombre, tlf,password,null,salario,esjefe);
				}			
				sent.close();
			}		
		} catch (SQLException e) {
			Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, e);
		}	
		return empleado;
	}
	public boolean eliminarEmple(Empleados empleado) {
		boolean eleminado=false;
		if(existeEmple(empleado.getIdEmple())) {
			if(cambiarEmpleReservas(empleado.getIdEmple())) {
				
				String sql="delete from empleados where idempleado=?";
				
				try {
					PreparedStatement sent= conexion.prepareStatement(sql);
					sent.setInt(1, empleado.getIdEmple());
					
					int filas= sent.executeUpdate();
					if(filas>0) {
						eleminado=true;
					}
					sent.close();
				}catch (SQLException e) {
					Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, e);
				}
				
			}
		}
		return eleminado;
		
	}
	
	public boolean modificarEmple(int id, String nombre,int tlf, String pass,
			float salario) {
		boolean modificado=false;
		if(existeEmple(id)) {
			String sql="update empleados set "
					+ "nombre=?,tlf=?,password=?,salario=? where idEmpleado=?";
			
			try {
				PreparedStatement sent= conexion.prepareStatement(sql);
				sent.setString(1,nombre);
				sent.setInt(2, tlf);
				sent.setString(3, pass);
				sent.setFloat(4, salario);
				sent.setInt(5, id);
				
				int filas= sent.executeUpdate();
				if(filas>0) {
					modificado=true;
				}
				sent.close();
			}catch (SQLException e) {
				Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
		
		
		return modificado;
	}
	//Metodos  
	public boolean existeEmple(int idemple) {
		boolean existe=false;
		String sql= "select * from empleados where idempleado="+idemple;
		try {
			Statement sent= (Statement) conexion.createStatement();
			ResultSet res =sent.executeQuery(sql);
			if(res.next()) {
				existe=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}
	private boolean cambiarEmpleReservas(int idemple) {
		boolean cambia=false;
		if(compruebaReservas(idemple)) {
			String jefe="select idempleado from empleados where esjefe=0";
			String sqlCambiarReservasJefe="update reserva set idempleado=? where idempleado=?";
			
			try {
				Statement sentJefe= (Statement) conexion.createStatement();
				ResultSet resJefe= sentJefe.executeQuery(jefe);
				resJefe.next();
				int idjefe=resJefe.getInt(1);
				resJefe.close();
				sentJefe.close();
				
				PreparedStatement sent= conexion.prepareStatement(sqlCambiarReservasJefe);
				sent.setInt(1, idjefe);
				sent.setInt(2, idemple);
				int filas= sent.executeUpdate();
				if(filas>0) {
					cambia=true;
				}else {
					cambia=false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			cambia=true;
		}
		
		return cambia;
	}
	private boolean compruebaReservas(int idemple) {
		boolean tiene=false;
		String sql="select * from reserva where idempleado="+idemple;
		
		try {
			Statement sentencia= (Statement) conexion.createStatement();
			ResultSet res =sentencia.executeQuery(sql);
			if(res.next()) {
				tiene=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tiene;
	}
}
