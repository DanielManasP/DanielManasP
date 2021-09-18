package Controlador;

import java.net.PortUnreachableException;
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

	public Connection conexion = Conexion.conexionmysql();

	public int tipoUsuario = 0;// Si es cliente 1, si es empleado 2 y si es jefe 3
	public String nombreUsu = "";
	public int idCliente = 0;
	public int idEmpleado = 0;
	public String emailCliente="";
	public int tlfCliente=0;
	public String passCliente="";
	float precioT = 0.0f;
	float precioInsertReser = 0.0f;

	// Menus
	public Object menuSelccionado;
	public MenuDAO menuDAO = new MenuDAO();
	ArrayList<Menus> listaMenus = new ArrayList<Menus>();

	ArrayList<Object> listaSeleccionM = new ArrayList<Object>();

	// Platos
	public PlatosDAO platosDAO = new PlatosDAO();
	ArrayList<Platos> listaP = new ArrayList<Platos>();

	// Clientes
	public ClientesDAO clientDAO = new ClientesDAO();
	ArrayList<Clientes> listaC = new ArrayList<Clientes>();

	// PEdidos Menu
	public PedidosM_DAO pedidosMDAO = new PedidosM_DAO();
	ArrayList<PedidosMenus> listaPedidosM = new ArrayList<PedidosMenus>();

	// Pedidos Platos
	public PedidosPlatosDAO pedidosPDAO = new PedidosPlatosDAO();
	ArrayList<PedidosPlatos> listaPedidosP = new ArrayList<PedidosPlatos>();

	// Empleados
	public EmpleadosDAO empleDAO = new EmpleadosDAO();
	ArrayList<Empleados> listaEmpleados = new ArrayList<Empleados>();

	// Reservas
	public ReservasDAO reservDAO = new ReservasDAO();
	ArrayList<Reservas> listaReservas = new ArrayList<Reservas>();
	ArrayList<Reservas> misReservas = new ArrayList<Reservas>();
	public Reservas reservaSelected;

	// PlatosMenus
	public PlatosMenusDAO platosMenusDAO = new PlatosMenusDAO();
	ArrayList<PlatosMenus> listaPlatosMenu = new ArrayList<PlatosMenus>();
	// Inicio session
	public InicioSesion inicioSess = new InicioSesion();

	// Variables finals
	private final int ESTADO_ACTIVO = 1;
	private final int ESTADO_FINALIZADO = 0;
	private final int USUARIO_JEFE = 3;
	private final int USUARIO_EMPLEADO = 2;
	private final int USUARIO_CLIENTE = 1;
	private final int MENSAJE_CORRECTO_1 = 1;
	private final int MENSAJE_ERORR1 = 2;
	private final int MENSAJE_ERORR2 = 3;

	public Gestion() {
		cargarMenus();
		cargaTotal();
		// pruebaEmple();
		// pruevaCli();
		// pruebaReserva();
		modificarEstadoPedidosM();
		// opeMenu();
		// operacionesPruebas();
		// operacionesPedidos();
//		cargarPLatos();
//		cargarClientes();
	}

	private void cargaTotal() {
		listaP = platosDAO.getPlatos();
		listaC = clientDAO.getClientes();
		listaPedidosM = pedidosMDAO.getPedidosMenu();
		listaPedidosP = pedidosPDAO.getPedidosPlatos();
		listaEmpleados = empleDAO.getEmpleados();
		listaReservas = reservDAO.getReservas();
		listaPlatosMenu = platosMenusDAO.getPlatosMenus();
	}

	// Pruebas metodos DAOS
	private void pruebaPedidosR() {

	}

	private void pruebaEmple() {
		insertaEmpleado("Ramon", 3213, "ramon", 1300f);
		modificaEmp(1111, "Daniel MP", 321234, "manas", 2345.4f);
		// borrarEmpleado(null);

	}

	private void pruevaCli() {
		// insertaClient("JAvier Hernandez", "javierH@gmail.com", "javier", 11234567);
		//modificaClient(10, "Modificado", "modificado", 1234144);
		//borrarClient(null);
	}

	private void pruebaReserva() {
		Date fecha = Date.valueOf("2021-09-15");
		// insertarReserva(10, 5, fecha, 0, 1);
		// (idCliente, numcomensales, fechaReserva, pase, turno);
		// insertarReserva(6, 1, fecha, 0, 0);
		Reservas reser = listaReservas.get(listaReservas.size() - 1);
		elminarReserva(reser);
	}

	private void opeMenu() {
		Menus mn = menuDAO.insertMenu("Todo por el deporte");
		compruebaDatoPedidos(mn.getIdMenu());
		System.out.println();
		System.out.println();

		if (menuDAO.modificarPrecioMenu(1, 11.9f)) {
			listaMenus = menuDAO.getMenus();

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

	private void operacionesPedidos() {
//		
//		PedidosPlatos pedidosP =pedidosPDAO.insertPedidoPlato(1, 1);
//		listaPedidosP=pedidosPDAO.getPedidosPlatos();
//		
//		compruebaDatoPedidos(pedidosP.getIdPedidoPlato());
		System.out.println();
		System.out.println();

		if (pedidosPDAO.modificarPedidoPlato(1, 8, 7)) {
			listaPedidosP = pedidosPDAO.getPedidosPlatos();

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

		// PedidosMenus pedidosM = pedidosMDAO.insertPedidoMenu(1, 1);
		// compruebaDatoPedidos(pedidosM.getIdPedidoMenu());
		System.out.println();
		System.out.println();

		if (pedidosMDAO.modificarEstadoFinalizado(3, Date.valueOf("2021-12-14"))) {
			listaPedidosM = pedidosMDAO.getPedidosMenu();

			compruebaDatoPedidos(1);
			System.out.println();
		}
		PedidosMenus pm = listaPedidosM.get(listaPedidosM.size() - 1);

		if (pedidosMDAO.eliminaPedidorMenu(pm)) {
			System.out.println("Prueba Elimina :");
			listaPedidosM = pedidosMDAO.getPedidosMenu();

			compruebaDatoPedidos(pm.getIdPedidoMenu());
			System.out.println();
		}
	}

	// PedidosMenus Operaciones BBDD
	public void modificarEstadoPedidosM() {
		if (pedidosMDAO.modificarEstadoFinalizado(3, Date.valueOf("2021-12-14"))) {
			listaPedidosM = pedidosMDAO.getPedidosMenu();
		}
	}

	private void operacionesPruebas() {
		// Pruebas PLATOS

		Platos plato = platosDAO.insertPlato("Remenescu", 13.5f);

		System.out.println("Prueba Inserta - PLATO : " + plato.getNombre());
		System.out.println();
		System.out.println();
		listaP = platosDAO.getPlatos();

		if (platosDAO.modificarPrecioPlato(26, 23.6f)) {
			System.out.println("Prueba Modifica PLATO :  23.6");
		}

		System.out.println();
		compruebaDato(26);
		System.out.println();

		if (platosDAO.eliminarPlato(null, 41)) {
			System.out.println("Prueba Elimina  PLATO : " + plato.getNombre());
		}
		listaP = platosDAO.getPlatos();
		System.out.println();
		compruebaDato(plato.getIdPlatos());

	}

	// Gestion Menus
	private void cargarMenus() {
		listaMenus = menuDAO.getMenus();
	}

	public ArrayList<Menus> getListaMenus() {
		return listaMenus;
	}

	public ArrayList<Platos> getListaPlatos() {
		return listaP;

	}

	public ArrayList<Reservas> getListaReservas() {

		for (Reservas r : listaReservas) {
			if (r.getIdCliente() == this.idCliente) {
				misReservas.add(r);
			}
		}

		return misReservas;
	}

	public ArrayList<Reservas> getMisReservas() {
		return misReservas;
	}
	public void actualizaMisReservas() {
		
	}

	// Abra que ver si se usa
	public void setListaMenus(ArrayList<Menus> listaMenus) {
		this.listaMenus = listaMenus;
	}

	// Reservas Operaciones BBDD
	public void insertarReserva(int idCliente, int numcomensales, Date fechaReserva, int pase, int turno) {
		float preciototal = calcularPrecioTotal(idCliente);
		Empleados empleado = obtenerEmpleado();
		Reservas res = reservDAO.insertaReserva(idCliente, empleado.getIdEmple(), numcomensales, fechaReserva,
				preciototal, pase, turno);

	}

	private void elminarReserva(Reservas reser) {
		reservDAO.eliminarReserva(reser);

	}

	public float calcularPrecioTotal(int idCliente) {
		float preciototal = 0;
		int idMenu = 0;
		int idPlato = 0;

		for (PedidosMenus pedidoM : listaPedidosM) {
			if (pedidoM.getIdCliente() == idCliente && pedidoM.getEstado() == ESTADO_ACTIVO) {
				for (Menus m : listaMenus) {
					if (pedidoM.getIdMenu() == m.getIdMenu()) {
						preciototal = preciototal + m.getPrecio();
					}
				}
			}
		}
		for (PedidosPlatos pedidoP : listaPedidosP) {
			if (pedidoP.getIdCliente() == idCliente && pedidoP.getEstado() == ESTADO_ACTIVO) {
				for (Platos p : listaP) {
					if (pedidoP.getIdPlato() == p.getIdPlatos()) {
						preciototal = preciototal + p.getPrecio();
					}
				}
			}
		}

		return preciototal;
	}

	// Clientes Operaciones BBDD
	public void insertaClient(String nombre, String email, String pass, int tlf) {
		if (!clientDAO.compruebaEmailCliente(email)) {
			Clientes cli = clientDAO.insertCliente(nombre, email, pass, tlf);
			listaC = clientDAO.getClientes();
		} else {
			System.out.println("EL EMAIL YA EXISTE");
		}

	}

	// El cliente no deberia poder moficiar el mail
	public boolean modificaClient(String nombre,String email, String pass, int tlf) {
		boolean modifica=false;
		if (clientDAO.modificarCliente(this.idCliente, nombre,email, pass, tlf)) {
			System.out.println("MODIFICA CLIENTE");
			modifica=true;
		}
		return modifica;
	}

	public boolean borrarClient() {
		boolean borra=false;
		if (clientDAO.eliminarCliente(this.idCliente)) {
			System.out.println("BORRA CLIENTE");
			borra=true;
		}
		return borra;
	}

	// Empleados Operaciones BBDD
	public void insertaEmpleado(String nombre, int tlf, String pass, float salario) {

		Empleados emp = empleDAO.insertaEmple(nombre, tlf, pass, salario);
		listaEmpleados = empleDAO.getEmpleados();
	}

	public void modificaEmp(int id, String nombre, int tlf, String pass, float salario) {
		if (empleDAO.modificarEmple(id, nombre, tlf, pass, salario)) {
			System.out.println("MODFICA EMPLEADO");
		}
	}

	public void borrarEmpleado(Empleados emp) {
		emp = listaEmpleados.get(listaEmpleados.size() - 1);
		if (empleDAO.eliminarEmple(emp)) {
			System.out.println("BORRO EMPLEADO");
		}
	}

	// Menu Operacioones BBDD

	public void actualizaPrecioMenu(int id, float precio) {
		menuDAO.modificarPrecioMenu(id, precio);
	}

	public void eleminarMenu(Menus menu) {
		menuDAO.eliminarMenu(menu);
	}

	// Fin pruebas Operaciones BBDD

	// Metodos REVISAR
	public void compruebaDato(int id) {
		String sql = "select * from platos where idplato=" + id;

		Statement sent;
		try {
			sent = conexion.createStatement();
			ResultSet res = sent.executeQuery(sql);
			if (res.next()) {
				System.out.println("COMPRUEBA DATOS = " + id);
				System.out.println(" NOMBRE: " + res.getString(2));
				System.out.println(" PVP: " + res.getFloat(3));
			} else {
				System.out.println("NO HA ENCONTRADO NINGUN REGISTRO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void compruebaDatoPedidos(int id) {
		String sql = "select * from pedidosplatos where idpedidoplato=" + id;

		Statement sent;
		try {
			sent = conexion.createStatement();
			ResultSet res = sent.executeQuery(sql);
			if (res.next()) {
				System.out.println("COMPRUEBA DATOS = " + id);
				System.out.println(" IDcliente: " + res.getInt(2));
				System.out.println(" idplato: " + res.getInt(3));
				System.out.println(" Estado: " + res.getInt(4));
			} else {
				System.out.println("NO HA ENCONTRADO NINGUN REGISTRO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listaMenus(int id) {
		String sql = "select * from menus where idmenu=" + id;

		Statement sent;
		try {
			sent = conexion.createStatement();
			ResultSet res = sent.executeQuery(sql);
			if (res.next()) {
				System.out.println(" " + res.getInt(1));
			} else {
				System.out.println("NO HA ENCONTRADO NINGUN REGISTRO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Empleados obtenerEmpleado() {
		Empleados empleado = null;
		boolean mismoEmpleado = true;

		do {
			int empleAleatorio = (int) (Math.random() * listaEmpleados.size());
			System.out.println("EMPLEADO ALEATORIO ID= " + empleAleatorio);
			empleado = listaEmpleados.get(empleAleatorio);

			if (listaReservas.get(listaReservas.size() - 1).getIdEmple() != empleado.getIdEmple()) {
				mismoEmpleado = false;
				System.out.println("EL EMPLEADO ES DISTINTO AL ULTIMO");
			}
		} while (mismoEmpleado);

		return empleado;
	}

	// Menus
	public void menuSeleccionado(int i, boolean esMenu) {
		menuSelccionado = null;
		if (esMenu) {
			menuSelccionado = listaMenus.get(i);
		} else {
			menuSelccionado = listaP.get(i);
		}
	}

	public void pedidoSeleccionado(int i) {
		menuSelccionado = listaSeleccionM.get(i);

	}

	public void reservaSelected(int i) {
		reservaSelected = misReservas.get(i);
	}

	public ArrayList<Object> getListaSeleccionMenu() {
		return listaSeleccionM;

	}

	// Operaciones Reservas
	// Estos metodos van a crear una reserva con los datos de listaSeleccion
	public boolean crearPedidos(Date fecha) {
		boolean esCreada = false;
		PedidosPlatos pPlatos = null;
		PedidosMenus pMenus = null;
		for (Object ob : listaSeleccionM) {
			if (ob.getClass() == Menus.class) {
				Menus m = (Menus) ob;
				pMenus = pedidosMDAO.insertPedidoMenu(this.idCliente, m.getIdMenu(), fecha);
				if (pMenus != null) {
					esCreada = true;
					precioInsertReser = precioInsertReser + m.getPrecio();
					listaPedidosM.add(pMenus);
				}
			}
			if (ob.getClass() == Platos.class) {
				Platos p = (Platos) ob;
				pPlatos = pedidosPDAO.insertPedidoPlato(this.idCliente, p.getIdPlatos(), fecha);
				if (pPlatos != null) {
					esCreada = true;
					precioInsertReser = precioInsertReser + p.getPrecio();
					listaPedidosP.add(pPlatos);
				}
			}
		}
		if (esCreada) {
			
			listaSeleccionM.clear();
		}

		return esCreada;

	}

	public boolean compruebaFecheHoy(int id) {
		boolean esHoy = true;
		Reservas r = null;
		for (int i = 0; i < misReservas.size(); i++) {
			if (misReservas.get(i).getIdReserva() == misReservas.get(id).getIdReserva()) {
				r = misReservas.get(i);
			}
		}
		java.util.Date fechaHoy = new java.util.Date();
		long timeInMilliSeconds = fechaHoy.getTime();
		Date fechaSQL = new Date(timeInMilliSeconds);
		if (r.getFechaReserva().compareTo(fechaSQL) <= 0) {
			esHoy = false;
		}

		return esHoy;
	}

	public boolean borrarReser(int id) {
		boolean borra = false;
		Reservas r = null;
		for (int i = 0; i < misReservas.size(); i++) {
			if (misReservas.get(i).getIdReserva() == misReservas.get(id).getIdReserva()) {
				r = misReservas.get(i);

				if (reservDAO.eliminarReserva(r)) {
					borra = true;
					misReservas.remove(i);

				}
			}
		}
		System.out.println("REserva a borrar "+r.getIdCliente());
		System.out.println("FECHA RESERVA "+r.getFechaReserva());
		if (borra) {
			for (PedidosPlatos pPlato : listaPedidosP) {
				
				
				if (pPlato.getIdCliente() == r.getIdCliente()
						&& pPlato.getFechareserva().compareTo(r.getFechaReserva()) == 0) {
					System.out.println("		FECHA PEDIDOS "+pPlato.getFechareserva());
					pedidosPDAO.eliminaPedidorPlato(pPlato);
				}
			}
			for (PedidosMenus pMenus : listaPedidosM) {
				if (pMenus.getIdCliente() == r.getIdCliente()
						&& pMenus.getFechareserva().compareTo(r.getFechaReserva()) == 0) {
					System.out.println("		FECHA PEDIDOS "+pMenus.getFechareserva());
					pedidosMDAO.eliminaPedidorMenu(pMenus);
				}
			}
			
		}

		return borra;
	}

	public boolean creaReserv(Date fecha, int numcomensales, int pase, int turno) {
		boolean esCreada = false;
		Reservas res = null;

		Empleados empleado = obtenerEmpleado();
		;
		res = reservDAO.insertaReserva(this.idCliente, empleado.getIdEmple(), numcomensales, fecha, precioInsertReser,
				pase, turno);
		if (res != null) {
			esCreada = true;
			misReservas.add(res);

			precioInsertReser = 0;
		}

		return esCreada;
	}

	public boolean compruebaAforo(Date fecha, int pase, int turno, int numcomensales) {
		boolean esCreada = false;
		if (!reservDAO.compruebaAforo(fecha, pase, turno, numcomensales)) {
			esCreada = true;
		}
		return esCreada;
	}

	public boolean compruebaSoloUna(Date fecha, int pase) {
		boolean esCreada = false;
		if (reservDAO.compruebaSoloUnaReserva(fecha, pase, this.idCliente)) {
			esCreada = true;
		}
		return esCreada;
	}

	// Operaciones Menu Vista y Platos
	public float removMenu(int i) {
		if (menuSelccionado.getClass() == Menus.class) {
			Menus m = (Menus) menuSelccionado;
			precioT = precioT - m.getPrecio();
		}
		if (menuSelccionado.getClass() == Platos.class) {
			Platos p = (Platos) menuSelccionado;
			precioT = precioT - p.getPrecio();
		}
		listaSeleccionM.remove(i);
		if (listaSeleccionM.size() <= 0) {
			precioTotalAcero();
		}
		return precioT;
	}

	public boolean esUltimo() {
		if (listaSeleccionM.size() < 1) {
			System.out.println("ES MENOR A 1");
			return true;
		} else {
			return false;
		}
	}

	public float precioTotalAcero() {

		return precioT;
	}

	public int totalPedidosMenu() {

		return listaSeleccionM.size();
	}

	public float cargarSeleccionMenu() {
		listaSeleccionM.add(menuSelccionado);
		System.out.println("Tamaño seleccion una vez añadido: " + listaSeleccionM.size());
		if (menuSelccionado.getClass() == Menus.class) {
			Menus m = (Menus) menuSelccionado;
			System.out.println("Precio antes add MENU : " + m.getPrecio());
			System.out.println("Precio antes add PRECIOTOTAL: " + precioT);
			precioT = precioT + m.getPrecio();
			System.out.println("Precio antes add PRECIOTOTAL: " + precioT);

		}
		if (menuSelccionado.getClass() == Platos.class) {
			Platos p = (Platos) menuSelccionado;
			precioT = precioT + p.getPrecio();
		}

		return precioT;
	}

	// Crear Menu emplado ESTO NO SE USA AHORA
	public boolean addMenu(String nombre, int idPlato, int idPlato2, int idPlato3, int idPlato4) {
		boolean creaMenu = false;

		float precioMenu = 0;
		Menus m = menuDAO.insertMenu(nombre);
		if (m != null) {
			if (platosMenusDAO.insertaPlatosMenu(m.getIdMenu(), idPlato, idPlato2, idPlato3, idPlato4)) {
				listaPlatosMenu = platosMenusDAO.getPlatosMenus();
				for (PlatosMenus pm : listaPlatosMenu) {
					if (pm.getIdMenu() == m.getIdMenu()) {
						for (Platos p : listaP) {
							if (p.getIdPlatos() == pm.getIdPlato()) {
								precioMenu = precioMenu + p.getPrecio();
							}
						}
					}
				}
				precioMenu = precioMenu - precioMenu * 0.1f;
				if (menuDAO.modificarPrecioMenu(m.getIdMenu(), precioMenu)) {

				}
				creaMenu = true;
			}
		}

		return creaMenu;
	}

	// Inicio Session
	public boolean inicioSesion(String email, String pass) {
		boolean correcto = false;
		Object obj = inicioSess.inicioSesionUsu(email, pass);

		if (obj != null) {
			if (obj.getClass() == Empleados.class) {
				Empleados emple = (Empleados) obj;
				tipoUsuario = USUARIO_EMPLEADO;

				if (emple.getEsJefe() == 0) { // Es Jefe
					tipoUsuario = USUARIO_JEFE;
				}
				idEmpleado = emple.getIdEmple();
				nombreUsu = emple.getNombre();
			} else {
				// Cliente
				Clientes cli = (Clientes) obj;
				tipoUsuario = USUARIO_CLIENTE;
				nombreUsu = cli.getNombre();
				idCliente = cli.getIdCliente();
				emailCliente= cli.getEmail();
				passCliente= cli.getPassword();
				tlfCliente= cli.getTlf();
				

			}
			correcto = true;
		}
		return correcto;
	}

	public boolean crearCliente(String nombre, String email, String pass, String tlf) {
		boolean correcto = false;

		if (inicioSess.crearCliente(nombre, email, pass, tlf)) {
			correcto = true;
		}

		return correcto;

	}

	public boolean esMail(String mail) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// El email a validar
		String email = "info@programacionextrema.com";

		Matcher mather = pattern.matcher(mail);

		if (mather.find() == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean compruebaPass(String pass) {
		boolean correcto=false;
		if(pass.length()>7) {
			correcto=true;
		}
		return correcto;
	}
	

}
