package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modelo.*;
import DAOs.*;


public class Gestion {
	
	public Connection conexion=Conexion.conexionmysql();
	public int tipoUsuario=0;// Si es cliente 1, si es empleado 2 y si es jefe 3
	public String nombreUsu="";
	
	//Menus
	MenuDAO menuDAO = new MenuDAO();
	ArrayList<Menus> listaMenus = new ArrayList<Menus>();
	
	//Platos
	public PlatosDAO platosDAO= new PlatosDAO();
	ArrayList<Platos> listaP = new ArrayList<Platos>();
	
	//Clientes
	public ClientesDAO clientDAO= new ClientesDAO();
	ArrayList<Clientes> listaC = new ArrayList<Clientes>();
	
	//PEdidos Menu
	public PedidosM_DAO pedidosMDAO = new PedidosM_DAO();
	ArrayList<PedidosMenus> listaPedidosM = new ArrayList<PedidosMenus>();
	
	//Pedidos Platos
	public PedidosPlatosDAO pedidosPDAO= new PedidosPlatosDAO();
	ArrayList<PedidosPlatos> listaPedidosP = new ArrayList<PedidosPlatos>();
	
	//Empleados
	public EmpleadosDAO empleDAO = new EmpleadosDAO();
	ArrayList<Empleados> listaEmpleados = new ArrayList<Empleados>();
	
	//Reservas
	public ReservasDAO reservDAO= new ReservasDAO();
	ArrayList<Reservas> listaReservas= new ArrayList<Reservas>();
	private final int ESTADO_ACTIVO=1;
	private final int ESTADO_FINALIZADO=0;
	
	public Gestion() {
		cargarMenus();
		cargaTotal();
		pruebaEmple();
		//pruevaCli();
		//pruebaReserva();
		//opeMenu();
	//	operacionesPruebas();
	//	operacionesPedidos();
//		cargarPLatos();
//		cargarClientes();
	}
	private void cargaTotal() {
		listaP=platosDAO.getPlatos();
		listaC=clientDAO.getClientes();
		listaPedidosM=pedidosMDAO.getPedidosMenu();
		listaPedidosP=pedidosPDAO.getPedidosPlatos();
		listaEmpleados=empleDAO.getEmpleados();
		listaReservas= reservDAO.getReservas();
	}
	private void pruebaEmple() {
		insertaEmpleado("Ramon", 3213, "ramon", 1300f);
		modificaEmp(1111, "Daniel MP", 321234, "manas", 2345.4f);
		//borrarEmpleado(null);
	
	}
	private void pruevaCli() {
	//	insertaClient("JAvier Hernandez", "javierH@gmail.com", "javier", 11234567);
		modificaClient(10, "Modificado", "modificado", 1234144);
		borrarClient(null);
	}
	private void pruebaReserva() {
		Date fecha=  Date.valueOf("2021-12-12");
		//insertarReserva(10, 5, fecha, 0, 1);
		insertarReserva(6, 1, fecha, 0, 1);
	}
	private void opeMenu() {
		Menus mn = menuDAO.insertMenu("Todo por el deporte", 11.4f);
		compruebaDatoPedidos(mn.getIdMenu());
		System.out.println();
		System.out.println();
		
		if(menuDAO.modificarPrecioMenu(1, 11.9f)) {
			listaMenus=menuDAO.getMenus();
			
			compruebaDatoPedidos(1);
			System.out.println();
		}
		
//		
//		Menus pm= listaMenus.get(listaMenus.size()-1);
//
//		if(menuDAO.eliminarMenu(pm)) {
//			System.out.println("Prueba Elimina :");
//			listaMenus=menuDAO.getMenus();
//			
//			compruebaDatoPedidos(pm.getIdMenu());
//			System.out.println();
//		}
	}
	public void listaMenus(int id) {
		String sql= "select * from menus where idmenu="+id;
		
		Statement sent;
		try {
			sent = conexion.createStatement();
			ResultSet res= sent.executeQuery(sql);
			if(res.next()) {
				System.out.println(" "+res.getInt(1));
			}else {
				System.out.println("NO HA ENCONTRADO NINGUN REGISTRO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void operacionesPedidos() {
//		
//		PedidosPlatos pedidosP =pedidosPDAO.insertPedidoPlato(1, 1);
//		listaPedidosP=pedidosPDAO.getPedidosPlatos();
//		
//		compruebaDatoPedidos(pedidosP.getIdPedidoPlato());
		System.out.println();
		System.out.println();
		
		if(pedidosPDAO.modificarPedidoPlato(1, 8, 7)) {
			listaPedidosP=pedidosPDAO.getPedidosPlatos();
			
			compruebaDatoPedidos(1);
			System.out.println();
		}
//		
//		if(pedidosPDAO.modificarEstadoFinalizado(1)) {
//			listaPedidosP=pedidosPDAO.getPedidosPlatos();
//			
//			compruebaDatoPedidos(1);
//			System.out.println();
//		}


//		if(pedidosPDAO.eliminaPedidorPlato(pedidosP)) {
//			System.out.println("Prueba Elimina :");
//			listaPedidosP=pedidosPDAO.getPedidosPlatos();
//			
//			compruebaDatoPedidos(pedidosP.getIdPedidoPlato());
//			System.out.println();
//		}
//		
	
		
		//PedidosMenus pedidosM = pedidosMDAO.insertPedidoMenu(1, 1);
		//compruebaDatoPedidos(pedidosM.getIdPedidoMenu());
		System.out.println();
		System.out.println();
		
		if(pedidosMDAO.modificarPedidoMenu(3, 6, 7)) {
			listaPedidosM=pedidosMDAO.getPedidosMenu();
			
			compruebaDatoPedidos(1);
			System.out.println();
		}
		
		if(pedidosMDAO.modificarEstadoFinalizado(3)) {
			listaPedidosM=pedidosMDAO.getPedidosMenu();
			
			compruebaDatoPedidos(1);
			System.out.println();
		}
		PedidosMenus pm= listaPedidosM.get(listaPedidosM.size()-1);

		if(pedidosMDAO.eliminaPedidorMenu(pm)) {
			System.out.println("Prueba Elimina :");
			listaPedidosM=pedidosMDAO.getPedidosMenu();
			
			compruebaDatoPedidos(pm.getIdPedidoMenu());
			System.out.println();
		}
	}
	public void compruebaDatoPedidos(int id) {
		String sql= "select * from pedidosplatos where idpedidoplato="+id;
		
		Statement sent;
		try {
			sent = conexion.createStatement();
			ResultSet res= sent.executeQuery(sql);
			if(res.next()) {
				System.out.println("COMPRUEBA DATOS = "+id);
				System.out.println(" IDcliente: "+res.getInt(2));
				System.out.println(" idplato: "+res.getInt(3));
				System.out.println(" Estado: "+res.getInt(4));
			}else {
				System.out.println("NO HA ENCONTRADO NINGUN REGISTRO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Pruebas
	private void operacionesPruebas() {
		//Pruebas PLATOS
		
		Platos plato=platosDAO.insertPlato("Remenescu", 13.5f );
		
		System.out.println("Prueba Inserta - PLATO : "+plato.getNombre());
		System.out.println();
		System.out.println();
		listaP=platosDAO.getPlatos();
		
		if(platosDAO.modificarPrecioPlato(26,23.6f)) {
			System.out.println("Prueba Modifica PLATO :  23.6");
		}
		
		System.out.println();
		compruebaDato(26);
		System.out.println();
		
		if(platosDAO.eliminarPlato(null,41)) {
			System.out.println("Prueba Elimina  PLATO : "+plato.getNombre());
		}
		listaP=platosDAO.getPlatos();
		System.out.println();
		compruebaDato(plato.getIdPlatos());
		
	
		
	}
	public void compruebaDato(int id) {
		String sql= "select * from platos where idplato="+id;
		
		Statement sent;
		try {
			sent = conexion.createStatement();
			ResultSet res= sent.executeQuery(sql);
			if(res.next()) {
				System.out.println("COMPRUEBA DATOS = "+id);
				System.out.println(" NOMBRE: "+res.getString(2));
				System.out.println(" PVP: "+res.getFloat(3));
			}else {
				System.out.println("NO HA ENCONTRADO NINGUN REGISTRO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Gestion Menus
	private void cargarMenus() {
		listaMenus=menuDAO.getMenus();		
	}	
	
	public ArrayList<Menus> getListaMenus() {
		return listaMenus;
	}
	
	//Abra que ver si se usa
	public void setListaMenus(ArrayList<Menus> listaMenus) {
		this.listaMenus = listaMenus;
	}
	//Reservas Operaciones BBDD
	public void insertarReserva(int idCliente, int numcomensales,
			Date fechaReserva, int pase, int turno) {
		float preciototal=calcularPrecioTotal(idCliente);
		Empleados empleado=obtenerEmpleado();
		Reservas res= reservDAO.insertaReserva(idCliente, empleado.getIdEmple(), numcomensales, fechaReserva, preciototal, pase, turno);
		
	}
	public Empleados obtenerEmpleado() {
		Empleados empleado=null;
		boolean mismoEmpleado=true;
		
		do {
			int empleAleatorio= (int) (Math.random()*listaEmpleados.size());
			System.out.println("EMPLEADO ALEATORIO ID= "+empleAleatorio);
			empleado=listaEmpleados.get(empleAleatorio);
			
			if(listaReservas.get(listaReservas.size()-1).getIdEmple()!=empleado.getIdEmple()) {
				mismoEmpleado=false;
				System.out.println("EL EMPLEADO ES DISTINTO AL ULTIMO");
			}
		}while(mismoEmpleado);
		
		return empleado;
	}
	public float calcularPrecioTotal(int idCliente) {
		float preciototal=0;
		int idMenu=0;
		int idPlato=0;
		
		for(PedidosMenus pedidoM: listaPedidosM) {
			if(pedidoM.getIdCliente()==idCliente && pedidoM.getEstado()==ESTADO_ACTIVO) {
				for(Menus m: listaMenus) {
					if(pedidoM.getIdMenu()==m.getIdMenu()) {
						preciototal=preciototal+m.getPrecio();
					}
				}
			}
		}
		for(PedidosPlatos pedidoP: listaPedidosP) {
			if(pedidoP.getIdCliente()==idCliente && pedidoP.getEstado()==ESTADO_ACTIVO) {
				for(Platos p: listaP) {
					if(pedidoP.getIdPlato()==p.getIdPlatos()) {
						preciototal=preciototal+p.getPrecio();
					}
				}
			}
		}
		
		return preciototal;
	}
	//Clientes Operaciones BBDD
	public void insertaClient(String nombre, String email, String pass,
			int tlf) {
		if(!clientDAO.compruebaEmailCliente(email)) {
			Clientes cli= clientDAO.insertCliente(nombre, email, pass, tlf);
			listaC=clientDAO.getClientes();
		}else {
			System.out.println("EL EMAIL YA EXISTE");
		}
		
	}
	//El cliente no deberia poder moficiar el mail
	public void modificaClient(int id, String nombre, String pass,int tlf) {
		if(clientDAO.modificarCliente(id, nombre, pass, tlf)) {
			System.out.println("MODIFICA CLIENTE");
		}	
	}
	public void borrarClient(Clientes cli) {
		cli= listaC.get(9);
		if(clientDAO.eliminarCliente(cli)) {
			System.out.println("BORRA CLIENTE");
		}
	}
	
	//Empleados Operaciones BBDD
	public void insertaEmpleado(String nombre, int tlf, String pass,
			 float salario) {
		
		Empleados emp=empleDAO.insertaEmple(nombre, tlf, pass, salario);
		listaEmpleados=empleDAO.getEmpleados();
	}
	public void modificaEmp(int id, String nombre, int tlf, String pass,
			float salario) {
		if(empleDAO.modificarEmple(id, nombre, tlf, pass, salario)) {
			System.out.println("MODFICA EMPLEADO");
		}
	}
	public void borrarEmpleado(Empleados emp) {
		emp=listaEmpleados.get(listaEmpleados.size()-1);
		if( empleDAO.eliminarEmple(emp)) {
			System.out.println("BORRO EMPLEADO");
		}
	}
	
	
	//Menu Operacioones BBDD
	public void insertarMenu(String nombre, float precio) {
		Menus menu=menuDAO.insertMenu(nombre, precio); 
		listaMenus.add(menu);
	}
	public void actualizaPrecioMenu(int id,float precio) {
		menuDAO.modificarPrecioMenu(id, precio);
	}
	public void eleminarMenu(Menus menu) {
		menuDAO.eliminarMenu(menu);
	}
	
	
	
	
	//Inicio Session
	public boolean inicioSesion(String email, String pass) {
		boolean correcto=false;
		try {
			String sql="select nombre from clientes where email=? and password=?;";
			
			PreparedStatement sentencia=conexion.prepareStatement(sql);
			sentencia.setString(1, email);
			sentencia.setString(2, pass);
			ResultSet res= sentencia.executeQuery();
			if(res.next()) {				//Es cliente
				correcto=true;
				nombreUsu=res.getString(1);
				tipoUsuario=1;
			}else {
				String sql2="select esjefe, nombre from empleados where numemple=? and password=?;";
				PreparedStatement sentencia2=conexion.prepareStatement(sql2);
				sentencia2.setString(1, email);
				sentencia2.setString(2, pass);
				ResultSet res2= sentencia2.executeQuery();
				if(res2.next()) {
					if(res2.getInt(1)==0) {	//Es jefe
						correcto=true;
						tipoUsuario=3;
					}else {					//Es empleado
						correcto=true;
						tipoUsuario=2;
					}
					nombreUsu=res2.getString(2);
				}else{
					correcto=false;
				}
				res2.close();
				sentencia2.close();
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
//				PreparedStatement senten2= conexion.prepareStatement(sqlInsert);
//				senten2.setString(1, email);
//				senten2.setString(2, pass);
//				senten2.setInt(3, telf);
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
	private boolean esMail (String mail) {
		   // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
        String email = "info@programacionextrema.com";
 
        Matcher mather = pattern.matcher(mail);
 
        if (mather.find() == true) {
            return true;
        } else {
           return false;
        }
	}
}
