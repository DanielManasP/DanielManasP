package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Conexion {
	
	 public static Connection conexionmysql(){
	        
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	Connection conexion =
						DriverManager.getConnection("jdbc:mysql://192.168.1.78:3306/restauranti",
						"pruebas", "AsD123");
	            return conexion;
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (SQLException ex) {
	            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        return null;
	    }
	
}
