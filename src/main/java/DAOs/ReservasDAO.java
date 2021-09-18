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

import Modelo.Reservas;

public class ReservasDAO {

	private int mensaje=0;
	Connection conexion=  (Connection) Conexion.conexionmysql();
	
	
	//Get Reservas, Inserta Reserva, Elimina Reserva
	// por ahora no modifica, se elimina y hace otra
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
			if(compruebaSoloUnaReserva(fecha, pase, idCliente)) {
				if(!compruebaAforo(fecha, turno, pase, numcomensales)) {
					
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
			}else {
				System.out.println("TIENE MAS DE UNA RESERVA PARA EL DIA Y EL PASE");
			}
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);
		}	
		return reserva;
	}
	public boolean compruebaSoloUnaReserva(Date fecha, int pase,int idCliente) {
		boolean soloUna=true;
		String sql="select * from reserva where fechareserva=? and pase=? "
				+ " and idcliente=?";
		try {
			PreparedStatement sent= conexion.prepareStatement(sql);
			sent.setDate(1, fecha);
			sent.setInt(2, pase);
			sent.setInt(3, idCliente);
			ResultSet res= sent.executeQuery();
			if(res.next()) {
				soloUna=false;
				System.out.println("TIENE MAS DE UNA RESERVA PARA EL DIA Y EL PASE");
				System.out.println("PASE : "+pase);
			}
			res.close();
			sent.close();
		}catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return soloUna;
		
		
	}
	public boolean compruebaAforo(Date fecha,int turno,int pase, int numcomensales){
		boolean lleno=false;
		System.out.println("LA fecha en bbdd ="+fecha);
		String sqlFecha="select sum(numcomensales) from reserva where fechareserva=? "
				+ " and pase=? and turno=?";
		String maxComensales= "select maximocomensales from restaurante";
		try {
			//MAximo comensales
			Statement sentMax= (Statement) conexion.createStatement();
			ResultSet resMax= sentMax.executeQuery(maxComensales);
			resMax.next();
			int maxComen= resMax.getInt(1);
			sentMax.close();
			resMax.close();
			
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
			res.close();
			sent.close();
			
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);

		}
		
		
		return lleno;
	}
	//Las reservas con fecha del mismo dia no se pueden borrar
	public boolean eliminarReserva(Reservas reserva) {
		boolean elimina=false;
		String sql="delete from reserva where fechareserva not in (CURDATE()) "
				+ " and idreserva="+reserva.getIdReserva();
		
		
		try {
			if(existeReserva(reserva.getIdReserva())) {
				Statement sent = (Statement) conexion.createStatement();
				int filas= sent.executeUpdate(sql);
				if(filas>0) {
					elimina=true;
					System.out.println("SE ELIMINA LA RESERVA "+reserva.getIdReserva());
				}else {
					System.out.println("NO ELIMINA RESERVA");
				}
				sent.close();
			}else {
				elimina=false;
				System.out.println("NO ELIMINA LA RESERVA");
			}
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);

		}	
		return elimina;
	}
	public boolean existeReserva(int idreserva) {
		String sql= "select * from reserva where idreserva="+idreserva;
		boolean existe=false;
		try {
			Statement sent= (Statement) conexion.createStatement();
			ResultSet res= sent.executeQuery(sql);
			if(res.next()) {
				existe=true;
			}
			res.close();
			sent.close();
			
		} catch (SQLException e) {
			Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return existe;
	}
}
