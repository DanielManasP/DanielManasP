package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Modelo.Clientes;

public class ClientesDAO {
	Connection conexion = (Connection) Conexion.conexionmysql();

	// Recupera la lista de clientes de la BBDD
	public ArrayList<Clientes> getClientes() {
		ArrayList<Clientes> listaC = new ArrayList<Clientes>();

		try {
			Statement sent = (Statement) conexion.createStatement();
			String sql = "select * from clientes;";
			ResultSet res = sent.executeQuery(sql);
			while (res.next()) {
				Clientes cliente = new Clientes(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getInt(5));

				listaC.add(cliente);
			}

			res.close();
			sent.close();
		} catch (SQLException e) {
			Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return listaC;
	}

	// Inserta un plato
	public Clientes insertCliente(String nombre, String email, String password, int tlf) {
		Clientes cliente = null;
		if (!compruebaEmailCliente(email)) {
			String sql = "insert into clientes values (?,?,?,?,?)";
			String sqlID = "select max(idcliente) from clientes";
			try {

				// Obtenemos el ID, se autoincrementa
				Statement sentId = (Statement) conexion.createStatement();
				ResultSet resId = sentId.executeQuery(sqlID);
				resId.next();
				int id = resId.getInt(1) + 1;
				resId.close();
				sentId.close();

				// Insertamos
				PreparedStatement sent = conexion.prepareStatement(sql);
				sent.setInt(1, id);
				sent.setString(2, nombre);
				sent.setString(3, email);
				sent.setString(4, password);
				sent.setInt(5, tlf);

				int filas = sent.executeUpdate();
				if (filas > 0) {
					cliente = new Clientes(id, nombre, email, password, tlf);
				}

				sent.close();
			} catch (SQLException e) {
				Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);

			}
		}

		return cliente;
	}

	public boolean eliminarCliente(Clientes cliente) {
		boolean eleminado = false;
		if (existeClient(cliente.getIdCliente())) {

			String sql = "delete from clientes where idcliente=?";

			try {
				PreparedStatement sent = conexion.prepareStatement(sql);
				sent.setInt(1, cliente.getIdCliente());

				cambiarClienteReservas(cliente.getIdCliente());
				cambiarPedidosCliente(cliente.getIdCliente());
				int filas = sent.executeUpdate();
				if (filas > 0) {
					eleminado = true;
				}
				sent.close();
			} catch (SQLException e) {
				Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);
			}

		}else {
			System.out.println("El cliente no EXISTE, no se BORRA");
		}
		return eleminado;
	}

	public boolean modificarCliente(int id, String nombre, String pass, int tlf) {
		boolean modificado = false;
		if (existeClient(id)) {
			String sql = "update clientes set " + "nombre=?,password=?,telefono=? where idcliente=?";

			try {
				PreparedStatement sent = conexion.prepareStatement(sql);
				sent.setString(1, nombre);
				sent.setString(2, pass);
				sent.setInt(3, tlf);
				sent.setInt(4, id);

				int filas = sent.executeUpdate();
				if (filas > 0) {
					modificado = true;
				}
				sent.close();
			} catch (SQLException e) {
				Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);
			}

		}

		return modificado;
	}

	// Metodos
	public boolean existeClient(int idclient) {
		boolean existe = false;
		String sql = "select * from clientes where idcliente=" + idclient;
		try {
			Statement sent = (Statement) conexion.createStatement();
			ResultSet res = sent.executeQuery(sql);
			if (res.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}

	public boolean compruebaEmailCliente(String email) {

		String sqlCompruebaEmail = "select email from clientes where email='" + email + "'";
		Statement sentComprueba;
		boolean existe = false;
		try {
			sentComprueba = (Statement) conexion.createStatement();
			ResultSet resC = sentComprueba.executeQuery(sqlCompruebaEmail);
			if (resC.next()) {
				existe = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}

	// Cambia los pedidos del CLIENTE que se va a borra al Cliente CONTROL
	// IDCLIENTE = 1 es el control
	private void cambiarPedidosCliente(int idcliente) {

		String updateMenu = "";
		String updatePlato = "";
		try {

			updateMenu = "update pedidosmenus set idcliente=1 where idcliente= "+idcliente;
//			PreparedStatement sentM = conexion.prepareStatement(updateMenu);
//			sentM.setInt(1, 1);
//			sentM.setInt(2, idcliente);
			Statement sentM= (Statement) conexion.createStatement();
			
			int filasM = sentM.executeUpdate(updateMenu);
			if (filasM > 0) {
				System.out.println("Actualiza PedidosMenus del cliente - filas : " + filasM);
			}
			sentM.close();

			updatePlato = "update pedidosplatos set idcliente=1 where idcliente="+idcliente;
//			PreparedStatement sentP = conexion.prepareStatement(updatePlato);
//			sentP.setInt(1, 1);
//			sentP.setInt(2, idcliente);
			Statement sentP= (Statement) conexion.createStatement();
			int filasP = sentP.executeUpdate(updatePlato);
			if (filasP > 0) {
				System.out.println("Actualiza PedidosPlatos del cliente - filas : " + filasP);
			}
			sentP.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Por ahora en deshuso
	public boolean compruebaPedidosCliente(int idcliente) {
		boolean tiene = false;
		String tienePediso = "select count(m.idmenu),count(p.idplato) from pedidosmenus m," + " pedidosplatos p"
				+ " where m.idcliente=? and p.idcliente=?";

		try {
			PreparedStatement sentPedidos = conexion.prepareStatement(tienePediso);
			sentPedidos.setInt(1, idcliente);
			sentPedidos.setInt(2, idcliente);
			ResultSet resPedidos = sentPedidos.executeQuery();
			resPedidos.next();

			int numMenus = resPedidos.getInt(1);
			int numPlatos = resPedidos.getInt(2);

			resPedidos.close();
			sentPedidos.close();

			// Comprueba si tienen pedidos de Menus y/o Platos
			if (numMenus > 0) {
				tiene = true;
			}
			if (numPlatos > 0) {
				tiene = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tiene;
	}

	public void cambiarClienteReservas(int idcliente) {
		
		String updateReser = "update reserva set idcliente=? where idcliente=?";

		try {
			PreparedStatement sent = conexion.prepareStatement(updateReser);
			sent.setInt(1, 1);
			sent.setInt(2, idcliente);
			int filas = sent.executeUpdate();
			if (filas > 0) {
				System.out.println("Actuliza Reservas del cliente - filas : "+filas);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
