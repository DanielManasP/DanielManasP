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

	// Control del usuario conectado
	public int tipoUsuario = 0;// Si es cliente 1, si es empleado 2 y si es jefe 3
	public final int USU_CLIENTE = 1;
	public final int USU_EMPLEADO = 2;
	public final int USU_JEFE = 3;

	// Datos usuario conectado
	public String nombreUsu = "";
	public int idCliente = 0;
	public int idEmpleado = 0;
	public String emailCliente = "";
	public int tlfCliente = 0;
	public String passCliente = "";

	// Precios de la reserva
	public float precioT = 0.0f;
	float precioInsertReser = 0.0f;

	// Inicializacion y Listas de objetos
	// Menus
	public MenuDAO menuDAO = new MenuDAO();
	ArrayList<Menus> listaMenus = new ArrayList<Menus>();

	// Lista de paltos y menus que seleccione el cliente
	public Object menuPlatoSeleccionado;
	ArrayList<Object> listaSeleccionMP = new ArrayList<Object>();

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
	ArrayList<Reservas> reservasHoy=new ArrayList<Reservas>();
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
		cargaTotal();
	}

	private void cargaTotal() {
		listaP = platosDAO.getPlatos();
		listaMenus = menuDAO.getMenus();
		listaC = clientDAO.getClientes();
		listaPedidosM = pedidosMDAO.getPedidosMenu();
		listaPedidosP = pedidosPDAO.getPedidosPlatos();
		listaEmpleados = empleDAO.getEmpleados();
		listaReservas = reservDAO.getReservas();
		listaPlatosMenu = platosMenusDAO.getPlatosMenus();
	}

	// LISTAS GET
	public ArrayList<Menus> getListaMenus() {
		return listaMenus;
	}

	public ArrayList<Platos> getListaPlatos() {
		return listaP;
	}
	public ArrayList<Reservas> getTodasLasReservas() {
		
		return listaReservas;
	}
	public  ArrayList<Reservas> getReservasPorFecha(String fecha){
		Date dat= Date.valueOf(fecha);
		for(Reservas r: listaReservas) {
			if(r.getFechaReserva().compareTo(dat)==0) {
				if(reservasHoy.size()>0) {
					for(Reservas rHoy: reservasHoy) {
						if(r.getIdReserva()==rHoy.getIdReserva()) {
							
						}else {
							reservasHoy.add(r);
						}
					}		
				}else {
					reservasHoy.add(r);
				}
			
			}
		}
		return reservasHoy;
		
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

	public ArrayList<Object> getListaSeleccionMenu() {
		return listaSeleccionMP;
	}
	// Fin LISTAS

	// Trabajando
	private float precioReserva = 0.0f;
	private int pedidosReserva = 0;
	private Date fechaReserva = null;

	public ArrayList<Menus> menusPorClienteYreserva() {

		ArrayList<Menus> listM = new ArrayList<Menus>();

		for (PedidosMenus pM : listaPedidosM) {
			if (pM.getFechareserva().compareTo(reservaSelected.getFechaReserva()) == 0
					&& pM.getIdCliente() == reservaSelected.getIdCliente()) {
				for (Menus m : listaMenus) {
					if (m.getIdMenu() == pM.getIdMenu()) {
						listM.add(m);
					}
				}
			}
		}

		return listM;
	}

	public ArrayList<Platos> platosPorClienteYreserva() {
		ArrayList<Platos> listP = new ArrayList<Platos>();

		for (PedidosPlatos pP : listaPedidosP) {
			if ((pP.getFechareserva().compareTo(reservaSelected.getFechaReserva()) == 0)
					&& pP.getIdCliente() == reservaSelected.getIdCliente()) {
				for (Platos p : listaP) {
					if (p.getIdPlatos() == pP.getIdPlato()) {
						listP.add(p);
					}
				}
			}
		}

		return listP;
	}

	// Gestion JTables interfaz
	public void menuOplatoSelccionado(int i, boolean esMenu) {
		menuPlatoSeleccionado = null;
		if (esMenu) {
			menuPlatoSeleccionado = listaMenus.get(i);
		} else {
			menuPlatoSeleccionado = listaP.get(i);
		}
	}

	public void pedidoSeleccionado(int i) {
		menuPlatoSeleccionado = listaSeleccionMP.get(i);

	}

	public void limpiarListaSeleccion() {
		listaSeleccionMP.clear();
		precioT = 0;

	}

	public float cargarSeleccion() {// Añade Menu-Plato a la lista de seleccionl
		listaSeleccionMP.add(menuPlatoSeleccionado);
		if (menuPlatoSeleccionado.getClass() == Menus.class) {
			Menus m = (Menus) menuPlatoSeleccionado;
			precioT = precioT + m.getPrecio();
		}
		if (menuPlatoSeleccionado.getClass() == Platos.class) {
			Platos p = (Platos) menuPlatoSeleccionado;
			precioT = precioT + p.getPrecio();
		}

		return precioT;
	}

	// Parte del empleado Tablas
	public boolean yaSeleccionado(int pos) {
		boolean seleccionado = false;
		Platos plato = null;
		Platos platoSeleccionado = (Platos) menuPlatoSeleccionado;
		for (int i = 0; i < listaSeleccionMP.size(); i++) {
			plato = (Platos) listaSeleccionMP.get(i);
			if (plato.getIdPlatos() == platoSeleccionado.getIdPlatos()) {
				System.out.println("El plato ya esta seleccionad");
				seleccionado = true;
			}
		}
		return seleccionado;
	}

	public void quitaPlatoMenuEmpleado(String nombreP) {
		for (int i = 0; i < listaSeleccionMP.size(); i++) {
			Platos p = (Platos) listaSeleccionMP.get(i);
			if (p.getNombre().contains(nombreP)) {
				listaSeleccionMP.remove(i);
				precioT = precioT - p.getPrecio();
			}
		}

	}

	public boolean comprubeMenuExiste(String nombre) {
		boolean existe = false;
		for (Menus menuCompara : listaMenus) {
			if (menuCompara.getNombre().contains(nombre)) {
				existe = true;
			}
		}
		return existe;
	}

	public boolean crearMenu(String nombre, float precio) {
		boolean crear = false;

		Platos p = null;
	
		Menus m = menuDAO.insertMenu(nombre, precio);
		if (m != null) {
			crear = true;
		}
		if (crear) {
			for (int i = 0; i < listaSeleccionMP.size(); i++) {
				p = (Platos) listaSeleccionMP.get(i);
				if (platosMenusDAO.addPlatoMenu(m.getIdMenu(), p.getIdPlatos())) {
					
					crear = true;
				} else {
					System.out.println("FALLO AÑADIR A PLATOSMENUS");
				}

			}
		}
		listaMenus.add(m);
		listaPlatosMenu=platosMenusDAO.getPlatosMenus();
		
		return crear;
	}
	public boolean eliminarMenu(int id) {
		boolean eliminado=false;
		Menus m=(Menus) menuPlatoSeleccionado;
		System.out.println("MENU SELECCIONADO : "+m.getNombre());
		if(menuDAO.eliminarMenu(m)) {
			eliminado=true;
			listaMenus.remove(id);
			System.out.println("ESLMINA GESTION");
		}else {
			System.out.println("NO ELIMNA");
		}
	
		return eliminado;
	}
	//Empleado Platos
	public String [] devuelveNombreYprecio() {
		String [] datos = new String [2];
		Platos p=(Platos) menuPlatoSeleccionado;
		datos[0] =p.getNombre();
		datos[1] = String.valueOf(p.getPrecio());
		
		return datos;
	}
	public boolean modificaAddPlato(String nombre, String precio,boolean modifica) {
		boolean opera=false;
		Platos p2=null;
		Platos p=null;
		
		if(modifica) {
			p=(Platos) menuPlatoSeleccionado;
			if(platosDAO.modificarPrecioPlato(p.getIdPlatos(), Float.parseFloat(precio))) {
				opera=true;
				for(int i=0;i<listaP.size();i++) {
					if(listaP.get(i).getIdPlatos()==p.getIdPlatos()) {
						listaP.get(i).setPrecio(Float.parseFloat(precio));
					}
				}
				 
			}
		}else {
			p2=platosDAO.insertPlato(nombre, Float.parseFloat(precio));
		
		}
		if(p2!=null) {
			opera=true;
			listaP.add(p2);
		}
		
		return opera;
		
	}
	public boolean compruebaPlatoNoMenu() {
		boolean sinMenu=true;
		Platos p=(Platos) menuPlatoSeleccionado;
		for(PlatosMenus pm: listaPlatosMenu) {
			if(pm.getIdPlato()==p.getIdPlatos()) {
				System.out.println("Tiene platos en  un menu");
				sinMenu=false;
			}else {
				System.out.println("No tiene platos en  un menu "+pm.getIdPlato()+ " el id que busco es : "+p.getIdPlatos());
			}
		}
		return sinMenu;
	}
	public boolean compruebaPlatoPedido() {
		boolean sinPedido=true;
		Platos p=(Platos) menuPlatoSeleccionado;
		for(PedidosPlatos pm: listaPedidosP) {
			if(pm.getIdPlato()==p.getIdPlatos()) {
				sinPedido=false;
			}
		}
		return sinPedido;
	}
	public boolean eleminarPedidoPlato(int id) {
		boolean eliminado=false;
		Platos p=(Platos) menuPlatoSeleccionado;
		System.out.println("Plato SELECCIONADO : "+p.getNombre());
		if(platosDAO.elminarPlatoTablas(p.getIdPlatos())) {
			eliminado=true;
			
			System.out.println("ESLMINA GESTION");
		}else {
			System.out.println("NO ELIMNA");
		}
		return eliminado;
	}
	public boolean eliminaPlato(int id) {
		boolean eliminado=false;
		Platos p=(Platos) menuPlatoSeleccionado;
		System.out.println("Plato SELECCIONADO : "+p.getNombre());
		if(platosDAO.eliminarPlato(p)) {
			eliminado=true;
			listaP.remove(id);
			System.out.println("ESLMINA GESTION");
		}else {
			System.out.println("NO ELIMNA");
		}
		
			
	
		return eliminado;
	}

	// Final parte Empleado tablas
	public float quitaSeleccion(int i) {// Quita Menu-Plato de la listaSeleccion
		if (menuPlatoSeleccionado.getClass() == Menus.class) {
			Menus m = (Menus) menuPlatoSeleccionado;
			precioT = precioT - m.getPrecio();
		}
		if (menuPlatoSeleccionado.getClass() == Platos.class) {
			Platos p = (Platos) menuPlatoSeleccionado;
			precioT = precioT - p.getPrecio();
		}
		listaSeleccionMP.remove(i);
		if (listaSeleccionMP.size() <= 0) {
			precioTotalAcero();
		}
		return precioT;
	}
	public void reservaSelectedEmple(int i) {
		reservaSelected=listaReservas.get(i);
		
	}
	public void reservaSelectedHoy(int i) {
		reservaSelected=reservasHoy.get(i);
	}
	public void reservaSelected(int i) {
		reservaSelected = misReservas.get(i);
		System.out.println("REserva numero : " + reservaSelected.getIdReserva());
	}
	// Fin Gestion JTables

	// Clientes Operaciones BBDD
	public void insertaClient(String nombre, String email, String pass, int tlf) {
		if (!clientDAO.compruebaEmailCliente(email)) {
			Clientes cli = clientDAO.insertCliente(nombre, email, pass, tlf);
			listaC = clientDAO.getClientes();
		} else {
			System.out.println("EL EMAIL YA EXISTE");
		}

	}

	public boolean modificaClient(String nombre, String email, String pass, int tlf) {
		boolean modifica = false;
		if (clientDAO.modificarCliente(this.idCliente, nombre, email, pass, tlf)) {
			System.out.println("MODIFICA CLIENTE");
			modifica = true;
		}
		return modifica;
	}

	public boolean borrarClient() {
		boolean borra = false;
		if (clientDAO.eliminarCliente(this.idCliente)) {
			System.out.println("BORRA CLIENTE");
			borra = true;
		}
		return borra;
	}
	// Fin OP Clientes

	// Operaciones Reservas
	// Crea los registros de la reserva en PedidosPlatos y PedidosMenus
	public boolean crearPedidos(Date fecha) {
		boolean esCreada = false;
		PedidosPlatos pPlatos = null;
		PedidosMenus pMenus = null;
		for (Object ob : listaSeleccionMP) {
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

			listaSeleccionMP.clear();
		}

		return esCreada;

	}

	// Crea la reserva en Reservas
	public boolean creaReserv(Date fecha, int numcomensales, int pase, int turno) {
		boolean esCreada = false;
		Reservas res = null;

		Empleados empleado = obtenerEmpleado();

		res = reservDAO.insertaReserva(this.idCliente, empleado.getIdEmple(), numcomensales, fecha, precioInsertReser,
				pase, turno);
		if (res != null) {
			esCreada = true;
			misReservas.add(res);

			precioInsertReser = 0;
		}

		return esCreada;
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
		System.out.println("REserva a borrar " + r.getIdCliente());
		System.out.println("FECHA RESERVA " + r.getFechaReserva());
		if (borra) {
			for (PedidosPlatos pPlato : listaPedidosP) {

				if (pPlato.getIdCliente() == r.getIdCliente()
						&& pPlato.getFechareserva().compareTo(r.getFechaReserva()) == 0) {
					System.out.println("		FECHA PEDIDOS " + pPlato.getFechareserva());
					pedidosPDAO.eliminaPedidorPlato(pPlato);
				}
			}
			for (PedidosMenus pMenus : listaPedidosM) {
				if (pMenus.getIdCliente() == r.getIdCliente()
						&& pMenus.getFechareserva().compareTo(r.getFechaReserva()) == 0) {
					System.out.println("		FECHA PEDIDOS " + pMenus.getFechareserva());
					pedidosMDAO.eliminaPedidorMenu(pMenus);
				}
			}

		}

		return borra;
	}
	// Fin Operaciones Reservas

	// Inicio Session y Crear Cliente
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
				emailCliente = cli.getEmail();
				passCliente = cli.getPassword();
				tlfCliente = cli.getTlf();

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
	// Fin inicio session

	// Metodos

	// Obtiene un empleado de manera aleatoria para asignar a la reseva
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

	public boolean compruebaPass(String pass) {
		boolean correcto = false;
		if (pass.length() > 7) {
			correcto = true;
		}
		return correcto;
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

	public boolean compruebaAforo(Date fecha, int pase, int turno, int numcomensales) {
		boolean esCreada = false;
		if (!reservDAO.compruebaAforo(fecha, pase, turno, numcomensales)) {
			esCreada = true;
		}
		return esCreada;
	}

	public boolean esUltimo() {
		if (listaSeleccionMP.size() < 1) {
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
		return listaSeleccionMP.size();
	}

	public boolean compruebaSoloUnaReserva(Date fecha, int pase) { // MIRAR SI PUEDE RESERVAS DOS VECES, MAÑAN Y TARDE,
																	// EN UN DIA; de ser asi esta mal el codigo,
																	// posible solucion: Añadir columna: PASE en tabla
																	// PedidosMenus y PedidosPlatos, preguntar
																	// segun el pase, ademas de la fecha y el cliente
		boolean esCreada = false;
		if (reservDAO.compruebaSoloUnaReserva(fecha, pase, this.idCliente)) {
			esCreada = true;
		}
		return esCreada;
	}

	public boolean esMail(String mail) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// El email a valida
		Matcher mather = pattern.matcher(mail);

		if (mather.find() == true) {
			return true;
		} else {
			return false;
		}
	}

	// pòr ahora no se usa, posible metodo para VER RESERVA del menu Resrevas
	public float calcularPrecioTotal(int idCliente) {
		float preciototal = 0;

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

	// Crear Menu emplado ESTO NO SE USA AHORA
	public boolean addMenu(String nombre, int idPlato, int idPlato2, int idPlato3, int idPlato4) {
		boolean creaMenu = false;

		float precioMenu = 0;
		Menus m = menuDAO.insertMenu(nombre, precioMenu);
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

}
