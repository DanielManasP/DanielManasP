package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.Empleados;
import Modelo.Reservas;

public class ReservasDAO {

	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	public ArrayList<Reservas> getReservas(){
		ArrayList<Reservas> listaR = new ArrayList<Reservas>();
		
		try {
			Statement sent=  (Statement) conexion.createStatement();
			String sql="select * from reserva;";
			ResultSet res= sent.executeQuery(sql);
			while(res.next()) {
				Reservas reserva= 
						new Reservas(res.getInt(1),res.getInt(2)
								,res.getInt(3),
								res.getInt(4),res.getDate(5),res.getFloat(6),
								res.getInt(7),res.getInt(8));
				
				listaR.add(reserva);
			}
			
			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return listaR;
	}
	public Reservas insertaReserva(int idCliente, int idEmple,int numcomensales,Date fecha, float preciototal,
			int pase,int turno) {
		Reservas reserva= null;
		int haceReserva=0;
		String insertReserva="insert into reserva values(?,?,?,?,?,?,?,?)";
		String sqlID="select max(idreserva) from reserva";
		
		try {	
			//Obtenemos el ID, se autoincrementa
			Statement sentId= (Statement) conexion.createStatement();
			ResultSet resId= sentId.executeQuery(sqlID);
			resId.next();
			
			int id= resId.getInt(1)+1;
			resId.close();
			sentId.close();
			
			if(!compruebaAforo(fecha, turno, pase, numcomensales)) {
				haceReserva=1;
				PreparedStatement sent= conexion.prepareStatement(insertReserva);
				sent.setInt(1,id);
				sent.setInt(2, idCliente);
				sent.setInt(3, idEmple); //Metodo en gestion que me de emple Aleatorio
				sent.setInt(4,numcomensales);
				sent.setDate(5, fecha);
				sent.setFloat(6, preciototal);//Metodo en gestion calcula precio
				sent.setInt(7, pase);
				sent.setInt(8, turno);
				
				int filas= sent.executeUpdate();
				if(filas>0) {
					System.out.println("SE HA INSERTADO RESERVA");
					reserva= new Reservas(id,idCliente,idEmple,numcomensales,
							fecha,preciototal,pase,turno);
				}
				sent.close();
			}
			
			
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);
		}	
		return reserva;
	}
	
	public boolean compruebaAforo(Date fecha,int turno,int pase, int numcomensales){
		boolean lleno=false;
		String sqlFecha="select sum(numcomensales) from reserva where fechareserva=? "
				+ " and pase=? and turno=?";
		String maxComensales= "select maximocomensales from restaurante";
		try {
			//MAximo comensales
			Statement sentMax= (Statement) conexion.createStatement();
			ResultSet resMax= sentMax.executeQuery(maxComensales);
			resMax.next();
			int maxComen= resMax.getInt(1);
			//Extraemos reservas del dia con su turno y pase
			PreparedStatement sent= conexion.prepareStatement(sqlFecha);
			sent.setDate(1, fecha);
			sent.setInt(2, pase);
			sent.setInt(3, turno);

			ResultSet res= sent.executeQuery();
			res.next();
			int totalComensales=res.getInt(1)+numcomensales;
			if(totalComensales > maxComen ) {
				System.out.println("AFORO COMPLETO, MAX COMENSALES: "+maxComen);
				System.out.println("AFORO COMPLETO, NO SE PUEDE RESERVAAR "+totalComensales);
				lleno=true;
			}
			
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);

		}
		
		
		return lleno;
	}
	//Las reservas con fecha del mismo dia no se pueden borrar
	public boolean eliminarReserva(Reservas reserva) {
		boolean elimina=false;
		
		
		
		
		return elimina;
	}
	public boolean existeReserva(int idreserva) {
		String sql= "select * from reservas where idreserva="+idreserva;
		
		return true;
		
	}
}
