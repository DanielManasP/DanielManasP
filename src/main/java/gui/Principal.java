package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.joda.time.LocalDate;

import Controlador.Gestion;

import Modelo.TablaPlatos;
import Modelo.TablaReservas;
import Modelo.TablaSeleccion;
import Modelo.tablaMenus;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class Principal extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane contentPane;
	private Gestion gestion;

	// Controles
	private boolean cargaUnaVez = true;
	//Control de Reservas: Muestra Resrevas del dia True o todas las resevas false
	private boolean cambioReservas = false;
	// Controla la funcionalidad de los botones btn1 y btn2
	// Tambien es el elemento del array seleccionado
	private int pedidoBorrar = -1;

	private float precioTotal = 0.0f;

	// Botones Principales encabezado
	private JPanel pnBtnPrincipales;
	private JButton btnInicio;
	private JButton btnReserva;
	private JButton btnSalir;
	private JButton btnMenu;
	private JButton btnPlatos;
	private JButton btnConfig;
	private JButton btnEmpleados;

	// Panel pie de ventan muestra fecha y usuario
	private JPanel pnFechaYusuario;
	private JLayeredPane layeredPane;

	// Componentes Inicio
	private JPanel pnInicio;

	// Componentes MENU
	private JPanel pnMenu;
	private JTable tbMenus;
	private JPanel pnTablaMenu;

	// Componentes Platos
	private JPanel pnPlato;
	private JTable tbPlatos;
	private JPanel pnTablaPlatos;

	// Componentes Reservas
	private JPanel pnReservas;
	private JPanel pnTablaReservas;
	private JTable tbReservas;

	// Componentes Configuracion
	private JPanel pnConfig;
	private JPanel pnFormularioConfig;
	private JTextField txtNombre;
	private JTextField txtTlf;
	private JTextField txtEmail;
	private JTextField txtPass;
	//Parte Empleado
	private JTextField txtFechaContrata;
	private JTextField txtSalario;

	// Componentes Pedidos Menus
	private JTable tbPedidosMenus;
	private JTable tbPedidosPlatos;

	// Paneles Botones, Titulo, Info
	private JPanel pnTituloOptions;
	private JPanel pnBtnOption;
	private JPanel pnInfoOption;

	// Labels Paneles Options
	private JLabel lbTituloOption;
	private JLabel lbInfoPedidos;
	private JLabel lbInfoPrecio;

	// Boton Añadir Menu/Plato Cancelar Reserva Modificar usuario
	private JButton btn1;

	// Boton Quitar Menu/Plato Ver Reserva Eliminar usuario
	private JButton btn2;
	//Boton de Reservas para Empleados poder ver la reserva
	private JButton btn3;

	// Modelos de las tablas
	private tablaMenus modeloMenus;
	private TablaPlatos modeloPlatos;
	private TablaSeleccion modeloSeleccionM;
	private TablaReservas modeloReservas;

	// Control paneles
	private ArrayList<JPanel> listaPaneles = new ArrayList<JPanel>();
	private int panelVisible = 0;
	private final int PN_MENU = 1;
	private final int PN_PLATO = 2;
	private final int PN_RESERVA = 3;
	private final int PN_CONFIG = 4;
	private final int PN_INICIO = 0;

	// Mensajes
	private final int MENSAJE_OK = 0;
	private final int MENSAJE_ERROR_BBDD = 1;
	private final int MENSAJE_ERROR_MAIL = 2;
	private final int MENSAJE_ERROR_PASS1 = 3;
	private JLabel lbInfoMail;
	private JLabel lbDireccion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		gestion = new Gestion();

		// Modelos de las tablas
		modeloMenus = new tablaMenus();
		modeloPlatos = new TablaPlatos();
		modeloSeleccionM = new TablaSeleccion();
		modeloReservas = new TablaReservas();
		// Carga los datos en los modelos
		modeloMenus.setDatos(gestion.getListaMenus());
		modeloPlatos.setDatos(gestion.getListaPlatos());

		Logeo log = new Logeo(this, true, gestion);
		log.setVisible(true);

		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
//		Dimension rectangulo = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//		double h= rectangulo.getHeight()-rectangulo.getHeight()*0.10;
//		double w= rectangulo.getWidth()-rectangulo.getWidth()*0.10;
//		rectangulo.setSize(w, h);
//		Rectangle a = new Rectangle(rectangulo);
//		setBounds(a);
	     ImageIcon icon = new ImageIcon(getClass().getResource("/gui/resources/portadaCasapedro.jpg"));
	     final Image image = icon.getImage();
	       
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1073, 621);
		contentPane = new JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image, 0,0,getWidth(),getHeight(), this);
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); 
		layeredPane = new JLayeredPane();

		// Carga el nombre del usuario que accedio y la fecha del dia
		cargarFechaYusuario();

		// Prueba Paneles opciones
		cargarPanelesBtnInfoTitulo();

		// Paneles Inicio, Menu, Plato, Reservas, Configuracion
		cargarPanelesDelMenu();

		// Botones Principales encabezado
		cargarBotonesMenusOp();

		// Establece la visibilidad de los paneles
		controlVisibilidadPaneles();

		// Tablas Platos, Menus, Reservas
		inicializarTablas();

		// Inicializar Formulario
		pnFormularioConfig = new JPanel();
		cargarTablas(pnConfig, pnFormularioConfig, null);

	}

	private void controlVisibilidadPaneles() {
		// POnemos todos los paneles a false menos Inicio
		pnMenu.setVisible(false);
		pnPlato.setVisible(false);
		pnInicio.setVisible(true);
		pnReservas.setVisible(false);
		pnConfig.setVisible(false);

		// Carga todos lo paneles en un array para el control de la visibilidad
		listaPaneles.add(pnInicio);
		listaPaneles.add(pnMenu);
		listaPaneles.add(pnPlato);
		listaPaneles.add(pnReservas);
		listaPaneles.add(pnConfig);

	}

	private void inicializarTablas() {
		// Tabla Pedidos Menus y Platos
		if (gestion.tipoUsuario == gestion.USU_CLIENTE) {
			tbPedidosMenus = new JTable();
			tbPedidosMenus.setAutoCreateRowSorter(true);
			tbPedidosMenus.addMouseListener(this);

			tbPedidosPlatos = new JTable();
			tbPedidosPlatos.setAutoCreateRowSorter(true);
			tbPedidosPlatos.addMouseListener(this);
		}

		// Tabla Platos
		pnTablaPlatos = new JPanel();
		tbPlatos = new JTable();
		cargarTablas(pnPlato, pnTablaPlatos, tbPlatos);

		// Tabla Reservas
		pnTablaReservas = new JPanel();
		tbReservas = new JTable();
		cargarTablas(pnReservas, pnTablaReservas, tbReservas);

		// Tabla Menus
		pnTablaMenu = new JPanel();
		tbMenus = new JTable();
		cargarTablas(pnMenu, pnTablaMenu, tbMenus);

		if (gestion.tipoUsuario == gestion.USU_CLIENTE) {
			// Cargamos los modelos de las tablas pedidos y reservas
			tbPedidosPlatos.setModel(modeloSeleccionM);
			tbPedidosMenus.setModel(modeloSeleccionM);
		}

	}

	private void cargarPanelesBtnInfoTitulo() {
		// Panel Titulo
		pnTituloOptions = new JPanel();
		pnTituloOptions.setBounds(0, 45, 1047, 51);
		layeredPane.add(pnTituloOptions);
		pnTituloOptions.setBackground(Color.GRAY);
		pnTituloOptions.setForeground(new Color(105, 105, 105));
		pnTituloOptions.setLayout(new GridLayout(0, 1, 0, 0));

		lbTituloOption = new JLabel("INICIO");
		lbTituloOption.setForeground(Color.WHITE);
		lbTituloOption.setHorizontalAlignment(SwingConstants.CENTER);
		lbTituloOption.setFont(new Font("Verdana", Font.BOLD, 20));
		pnTituloOptions.add(lbTituloOption);

		// Panel Btn y Botones
		pnBtnOption = new JPanel();
		pnBtnOption.setBounds(0, 471, 1047, 51);
		layeredPane.add(pnBtnOption);
		pnBtnOption.setLayout(new GridLayout(1, 0, 0, 0));

		btn1 = new JButton("Añadir");
		btn2 = new JButton("Quitar");
		btn3 = new JButton("Mostrar reservas de hoy");
		btn3.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		pnBtnOption.add(btn1);
		pnBtnOption.add(btn2);
		pnBtnOption.setVisible(false);
		// Info Pedido
		pnInfoOption = new JPanel();
		pnInfoOption.setBounds(0, 423, 1047, 51);
		layeredPane.add(pnInfoOption);

		pnInfoOption.setBackground(new Color(255, 228, 196));
		pnInfoOption.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnInfoOption.setLayout(new GridLayout(1, 0, 0, 0));
		pnInfoOption.setVisible(false);
		lbInfoPedidos = new JLabel("Numero pedidos : ");
		lbInfoPrecio = new JLabel("Total precio: ");
		pnInfoOption.add(lbInfoPedidos);
		pnInfoOption.add(lbInfoPrecio);

	}

	private void cargarPanelesDelMenu() {
		// Paneles de los botones
		// Pruebas paneles
		pnMenu = new JPanel();
		pnMenu.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnMenu.setBackground(Color.RED);
		pnMenu.setBounds(0, 94, 1047, 328);
		layeredPane.add(pnMenu, BorderLayout.CENTER);
		pnMenu.setLayout(null);

		// Panel Inicio
		pnInicio = new JPanel();
		pnInicio.setBounds(0, 94, 1047, 428);
		pnInicio.setBackground(new Color(173, 216, 230));
		pnInicio.setOpaque(false);
		layeredPane.add(pnInicio, BorderLayout.CENTER);
		pnInicio.setLayout(null);
		
		
		JLabel lbNombreRes = new JLabel("CASA PEDRO");
		lbNombreRes.setForeground(new Color(245, 245, 245));
		lbNombreRes.setHorizontalAlignment(SwingConstants.CENTER);
		lbNombreRes.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		lbNombreRes.setBounds(0, 0, 1047, 54);
		pnInicio.add(lbNombreRes);
		
		JLabel lbInfo1 = new JLabel("Tlf: 999888555");
		lbInfo1.setForeground(new Color(0, 0, 0));
		lbInfo1.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo1.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lbInfo1.setBounds(0, 292, 523, 71);
		pnInicio.add(lbInfo1);
		
		lbInfoMail = new JLabel("Email : casapedro@gmail.com");
		lbInfoMail.setForeground(new Color(0, 0, 0));
		lbInfoMail.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfoMail.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lbInfoMail.setBounds(533, 292, 514, 71);
		pnInicio.add(lbInfoMail);
		
		lbDireccion = new JLabel("Direccion : C/San Pedro Alcantara 1 (Toledo)");
		lbDireccion.setForeground(new Color(0, 0, 0));
		lbDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lbDireccion.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lbDireccion.setBounds(0, 374, 1047, 54);
		pnInicio.add(lbDireccion);

		// Panel Reservas
		pnReservas = new JPanel();
		pnReservas.setBounds(0, 94, 1047, 328);
		pnReservas.setBackground(Color.GRAY);
		layeredPane.add(pnReservas, BorderLayout.CENTER);
		pnReservas.setLayout(null);

		// Panel Configuracion
		pnConfig = new JPanel();
		pnConfig.setBounds(0, 94, 1047, 366);
		pnConfig.setBackground(Color.BLACK);
		layeredPane.add(pnConfig, BorderLayout.CENTER);
		pnConfig.setLayout(null);

		// Panel platos
		pnPlato = new JPanel();
		pnPlato.setBackground(Color.YELLOW);
		pnPlato.setBounds(0, 94, 1047, 328);
		layeredPane.add(pnPlato, BorderLayout.CENTER);
		pnPlato.setLayout(null);

	}

	private void cargarBotonesMenusOp() {
		pnBtnPrincipales = new JPanel();
		pnBtnPrincipales.setBackground(new Color(0, 0, 0));
		pnBtnPrincipales.setBounds(0, 0, 1047, 46);
		layeredPane.add(pnBtnPrincipales);
		pnBtnPrincipales.setLayout(new GridLayout(1, 0, 0, 0));

		btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(this);
		pnBtnPrincipales.add(btnInicio);

		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(this);
		pnBtnPrincipales.add(btnMenu);

		btnPlatos = new JButton("Platos");
		btnPlatos.addActionListener(this);
		pnBtnPrincipales.add(btnPlatos);

		btnReserva = new JButton("Reservas");
		btnReserva.addActionListener(this);
		pnBtnPrincipales.add(btnReserva);

		btnConfig = new JButton("Configuracion");
		btnConfig.addActionListener(this);
		pnBtnPrincipales.add(btnConfig);
		if(gestion.tipoUsuario==gestion.USU_JEFE) {
			btnEmpleados = new JButton("EMPLEADOS");
			btnEmpleados.addActionListener(this);
			pnBtnPrincipales.add(btnEmpleados);
		}
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		pnBtnPrincipales.add(btnSalir);
		

	}

	private void cargarFechaYusuario() {
		pnFechaYusuario = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnFechaYusuario, GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnFechaYusuario, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)));
		GridBagLayout gbl_pnFech = new GridBagLayout();
		gbl_pnFech.columnWidths = new int[] { 351, 48, 99, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnFech.rowHeights = new int[] { 14, 0, 0 };
		gbl_pnFech.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnFech.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnFechaYusuario.setLayout(gbl_pnFech);

		JLabel lbUsuario = new JLabel();
		lbUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbUsuario.setText("Usuario:  " + gestion.nombreUsu + "  |  ");
		lbUsuario.setBounds(405, 389, 159, 14);
		GridBagConstraints gbc_lbUsuario = new GridBagConstraints();
		gbc_lbUsuario.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_lbUsuario.gridx = 2;
		gbc_lbUsuario.gridy = 1;
		pnFechaYusuario.add(lbUsuario, gbc_lbUsuario);

		JLabel lbFecha = new JLabel();
		lbFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lbFecha.setVerticalAlignment(SwingConstants.BOTTOM);
		lbFecha.setText("Fecha :  " + LocalDate.now().toString());
		lbFecha.setBounds(669, 389, 106, 14);
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbFecha.gridx = 8;
		gbc_lbFecha.gridy = 1;
		pnFechaYusuario.add(lbFecha, gbc_lbFecha);

		contentPane.setLayout(gl_contentPane);

	}

	private void cargarFormulario() {

		GridBagLayout gbl_pnFormularionConfig = new GridBagLayout();
		gbl_pnFormularionConfig.columnWidths = new int[] { 185, 117, 218, 0, 148, 0, 192, 0, 0, 0 };
		gbl_pnFormularionConfig.rowHeights = new int[] { 50, 0, 50, 0,50 };
		gbl_pnFormularionConfig.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_pnFormularionConfig.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnFormularioConfig.setLayout(gbl_pnFormularionConfig);

		JLabel lbNombre = new JLabel("Nombre :");
		lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbNombre = new GridBagConstraints();
		gbc_lbNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lbNombre.gridx = 1;
		gbc_lbNombre.gridy = 1;
		pnFormularioConfig.add(lbNombre, gbc_lbNombre);

		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 1;
		pnFormularioConfig.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lbTlf = new JLabel("Telefono :");
		GridBagConstraints gbc_lbTlf = new GridBagConstraints();
		gbc_lbTlf.insets = new Insets(0, 0, 5, 5);
		gbc_lbTlf.gridx = 4;
		gbc_lbTlf.gridy = 1;
		pnFormularioConfig.add(lbTlf, gbc_lbTlf);

		txtTlf = new JTextField();
		GridBagConstraints gbc_txtTlf = new GridBagConstraints();
		gbc_txtTlf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTlf.insets = new Insets(0, 0, 5, 5);
		gbc_txtTlf.gridx = 6;
		gbc_txtTlf.gridy = 1;
		txtTlf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (txtTlf.getText().length() > 8) {
					e.consume();
				}
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) /* corresponde a BACK_SPACE */
				{
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		pnFormularioConfig.add(txtTlf, gbc_txtTlf);
		txtTlf.setColumns(10);

		JLabel lbMail = new JLabel("Email :");
		lbMail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbMail = new GridBagConstraints();
		gbc_lbMail.insets = new Insets(0, 0, 0, 5);
		gbc_lbMail.gridx = 1;
		gbc_lbMail.gridy = 3;
		pnFormularioConfig.add(lbMail, gbc_lbMail);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.insets = new Insets(0, 0, 0, 5);
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 3;
		pnFormularioConfig.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		JLabel lbPass = new JLabel("Contraseña :");
		GridBagConstraints gbc_lbPass = new GridBagConstraints();
		gbc_lbPass.insets = new Insets(0, 0, 0, 5);
		gbc_lbPass.gridx = 4;
		gbc_lbPass.gridy = 3;
		pnFormularioConfig.add(lbPass, gbc_lbPass);

		txtPass = new JTextField();
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.insets = new Insets(0, 0, 0, 5);
		gbc_txtPass.gridx = 6;
		gbc_txtPass.gridy = 3;
		pnFormularioConfig.add(txtPass, gbc_txtPass);
		txtPass.setColumns(10);
		if(!esCliente()) {
			
			lbMail.setText("Usuario login: ");
			txtEmail.setEditable(false);
			
			JLabel lbFecha= new JLabel("Fecha contratacion: ");
			GridBagConstraints gbc_lbFecha = new GridBagConstraints();
			gbc_lbFecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbFecha.insets = new Insets(0, 0, 0, 5);
			gbc_lbFecha.gridx = 1;
			gbc_lbFecha.gridy = 4;
			pnFormularioConfig.add(lbFecha, gbc_lbFecha);
		
			txtFechaContrata= new JTextField();
			txtFechaContrata.setEditable(false);
			GridBagConstraints gbc_txtFecha = new GridBagConstraints();
			gbc_txtFecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFecha.insets = new Insets(0, 0, 0, 5);
			gbc_txtFecha.gridx = 2;
			gbc_txtFecha.gridy = 4;
			pnFormularioConfig.add(txtFechaContrata, gbc_txtFecha);
			txtFechaContrata.setColumns(10);
			
			
			JLabel lbSalario= new JLabel("Salario : ");
			lbSalario.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbSal = new GridBagConstraints();
			gbc_lbSal.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbSal.insets = new Insets(0, 0, 0,5);
			gbc_lbSal.gridx = 4;
			gbc_lbSal.gridy = 4;
			pnFormularioConfig.add(lbSalario, gbc_lbSal);
			
			
			txtSalario= new JTextField();
			txtSalario.setEditable(false);
			GridBagConstraints gbc_txtSal = new GridBagConstraints();
			gbc_txtSal.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSal.insets = new Insets(0, 0, 0, 5);
			gbc_txtSal.gridx = 6;
			gbc_txtSal.gridy = 4;
			pnFormularioConfig.add(txtSalario, gbc_txtSal);
			txtSalario.setColumns(10);
			
		}
	}

	private void cargarTablas(JPanel pnPadre, JPanel pnTabla, JTable tabla) {

		pnTabla.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTabla.setBounds(0, 0, 1047, 328);

		if (tabla != null) {
			JScrollPane panelScrollm = new JScrollPane();
			tabla.addMouseListener(this);
			pnTabla.setLayout(new GridLayout(1, 0, 0, 0));
			panelScrollm.setViewportView(tabla);
			pnTabla.add(panelScrollm);

			if (pnTabla == pnTablaPlatos) {
				if (esCliente()) {
					JScrollPane panelScrolln = new JScrollPane();
					panelScrolln.setViewportView(tbPedidosPlatos);
					pnTabla.add(panelScrolln);
				}
				tabla.setModel(modeloPlatos);
			} else if (pnTabla == pnTablaMenu) {
				if (esCliente()) {
					JScrollPane panelScrolln = new JScrollPane();
					panelScrolln.setViewportView(tbPedidosMenus);
					pnTabla.add(panelScrolln);
				}
				tabla.setModel(modeloMenus);
			} else if (pnTabla == pnTablaReservas) {
				tabla.setModel(modeloReservas);
			}
		} else {

			cargarFormulario();
		}

		pnPadre.add(pnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent evnt) {

		// Boton Salir del programa
		if (evnt.getSource() == btnSalir) {
			System.exit(0);

			// Menu de opciones Inicio, Menu, Platos, Reservas, Configuracion

		} else if (evnt.getSource() == btnReserva) {// Rerservas
			gestion.actualizaDatos();
			pnInfoOption.setVisible(false);
			pnBtnOption.setVisible(true);
			if (esCliente()) {
				if(pnBtnOption.getComponentCount()>2) {
					pnBtnOption.remove(btn3);
				}
				cambioNombresBtnyTitulo("RESERVAS", "Cancelar reserva", "Ver reserva", Color.BLUE, Color.WHITE);
				if (gestion.getListaSeleccionMenu().size() > 0) {
					DialogoReservClient dialog = new DialogoReservClient(this, true, gestion);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					gestion.limpiarListaSeleccion();
				}
				if (cargaUnaVez) {
					modeloReservas.setDatos(gestion.getListaReservas());
					cargaUnaVez = false;
				} else {
					modeloReservas.setDatos(gestion.getMisReservas());
				}

			} else {
				btnReserva.setText("RESERVAS ");
				gestion.actualizaDatos();
				btn3.setText("Mostrar reservas hoy");
				cambioNombresBtnyTitulo("RESERVAS", "Completar reserva", "Ver reserva", Color.BLUE,
						Color.WHITE);
				pnBtnOption.add(btn3);
				modeloReservas.setDatos(gestion.getTodasLasReservas());
			}

			pedidoBorrar = -1;

			modeloReservas.fireTableDataChanged();
			modeloSeleccionM.fireTableDataChanged();
			ponerPanelVisible(pnReservas);

		} else if (evnt.getSource() == btnInicio) {// Inicio
			if(pnBtnOption.getComponentCount()>2) {
				pnBtnOption.remove(btn3);
			}
			
			if(!esCliente()) {
				btnInicio.setText("Inicio (Actauliza datos)");
				gestion.actualizaDatos();
			}
			ponerPanelVisible(pnInicio);
			pnBtnOption.setVisible(false);
			pnInfoOption.setVisible(false);
			cambioNombresBtnyTitulo("INICIO", "", "", Color.GRAY, Color.WHITE);
			

		} else if (evnt.getSource() == btnMenu) { // EL PRECIO ESTA MAL EN pnInfo Menus
			if(pnBtnOption.getComponentCount()<=2) {
				pnBtnOption.add(btn3);
			}
			btn3.setText("Ver platos del menú");
			
			pnInfoOption.setVisible(true);
			pnBtnOption.setVisible(true);
			
			ponerPanelVisible(pnMenu);

			cambioNombresBtnyTitulo("MENUS", "Añadir Menu", "Quitar Menu", Color.YELLOW, Color.BLACK);

			pedidoBorrar = -1;
			compruebaTamañoSelecion();
			actualizaInfoPedidos(lbInfoPedidos, lbInfoPrecio);

		} else if (evnt.getSource() == btnPlatos) { // EL PRECIO ESTA MAL EN pnInfo Platos
			if(pnBtnOption.getComponentCount()>2) {
				pnBtnOption.remove(btn3);
			}
			pnInfoOption.setVisible(true);
			pnBtnOption.setVisible(true);
			ponerPanelVisible(pnPlato);
			cambioNombresBtnyTitulo("PLATOS", "Añadir Plato", "Quitar Plato", Color.YELLOW, Color.BLACK);

			compruebaTamañoSelecion();
			actualizaInfoPedidos(lbInfoPedidos, lbInfoPrecio);
			pedidoBorrar = -1;

		} else if (evnt.getSource() == btnConfig) {// Configuracion
			if(pnBtnOption.getComponentCount()>2) {
				pnBtnOption.remove(btn3);
			}
			pnInfoOption.setVisible(false);
			pnBtnOption.setVisible(true);
			ponerPanelVisible(pnConfig);
			if(esCliente()) {
				cambioNombresBtnyTitulo("CONFIGURACION", "Modificar cuenta", "Eliminar cuenta", Color.black, Color.white);
				cargarCliente();
			}else {
				cambioNombresBtnyTitulo("CONFIGURACION", "Modificar cuenta", "Eliminar empleado", Color.black, Color.white);
				
				cargarEmpleado();
			}
			
		}else if(evnt.getSource()==btnEmpleados) {
			VerEmpleados verEmp= new VerEmpleados(this, true, gestion);
			verEmp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			verEmp.setVisible(true);
		} else if (evnt.getSource() == btn1) {// Boton 1

			// Controla si hay algo seleccionado, sino los botones no funcionan
			if (pedidoBorrar != -1 || !esCliente()) {
				// Selecionamos por el panel visible actual
				switch (panelVisible) {

				case PN_MENU:// Panel Menu
					if (esCliente()) {
						addMenuOplato();
					} else {
						addMenu();
					}
					break;
				case PN_PLATO:// Panel Plato
					if (esCliente()) {
						addMenuOplato();
					} else {
						addOModifiPlato();
					}
					break;
				case PN_RESERVA:// Panel reserva
					if (esCliente()) {
						eliminarReserva();
					} else {
						reservaCompletada();
					}

					break;
				case PN_CONFIG: // Panel Config
					if (esCliente()) {
						modificarCliente();
					} else {
						modificarEmpleado();
					}

					break;
				case PN_INICIO: // Panel Inicio

					break;

				default:
					break;
				}
				
			} else {
				// BOTONES OFF
			}

		} else if (evnt.getSource() == btn2) { // Boton 2

			if (pedidoBorrar != -1 ) {
				switch (panelVisible) {

				case PN_MENU:// Panel Menu
					if (esCliente()) {
						eliminarMenuOplato();
					} else {
						System.out.println("ENTRA EN BTN2");
						eliminarMenu();
					}

					break;
				case PN_PLATO:// Panel Plato
					if (esCliente()) {
						eliminarMenuOplato();
					} else {
						eliminarPlato();
					}

					break;
				case PN_RESERVA:// Panel reserva
					
						verReserva();
					
					break;
				case PN_CONFIG:// Panel Config
					if(gestion.tipoUsuario==gestion.USU_JEFE) {
						pnBtnOption.remove(btn2);
					}
						// Confirmacion de que se quiere borrar el usuario
						int borra = JOptionPane.showConfirmDialog(this,
								"¿Seguro que quiere borrar la cuenta? El programa se cerrara");
						if (borra == 0) {
							if(esCliente()) {
								if(gestion.borrarClient()) {
									System.exit(0);
								}
							}else {
							
								if(gestion.borrarEmpl(0)) {
									System.exit(0);
								}
							}
							
							
						}
					
					break;
				case PN_INICIO:
					// Panel Inicio
					break;
					
					

				default:
					break;
				}
			} else {
				// Botones OFF
			}
		}else if(evnt.getSource()==btn3) {
			if(panelVisible==PN_RESERVA) {
				gestion.actualizaDatos();
				cambiarReservasFechaHoy();
			}else {
				mostrarPlatosMenu();
			}
			
		}
	}

	private void mostrarPlatosMenu() {
		VerPlatosMenu dialogoP = null;
		if (pedidoBorrar != -1) {
			String[] datos = gestion.devuelveNombreYprecio();
			dialogoP = new VerPlatosMenu(this, true, gestion, datos[0], datos[1],datos[2]);
			dialogoP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialogoP.setVisible(true);
			
		} 
		pedidoBorrar = -1;
		modeloPlatos.fireTableDataChanged();
		
	}

	private void modificarEmpleado() {

		// Comprueba que no haya campos vacios
		if (txtEmail.getText().isEmpty() || txtPass.getText().isEmpty() || txtTlf.getText().isEmpty() ||txtNombre.getText().isEmpty()
				) {
			JOptionPane.showMessageDialog(this, "Ningun campo puede estar vacio, compruebe que los campos : /n "
					+ " Email, Password y Tlf estan completos", "CAMPOS VACIOS", JOptionPane.WARNING_MESSAGE);
		} else {
			// Comprueba si es un correo electronico correcto
			
				// Comprueba si la contraseña es mayor de 7 caracteres
				if (gestion.compruebaPass(txtPass.getText())) {
					// Comprueba si hay error en la BBDD
					if (gestion.modificarEmple(0,txtNombre.getText(), txtPass.getText(),
							Integer.parseInt(txtTlf.getText()),"")) {

						

						mensajes(MENSAJE_OK);

					} else {
						mensajes(MENSAJE_ERROR_BBDD);
					}

				} else {
					mensajes(MENSAJE_ERROR_PASS1);

				}
		}
		
	}

	private void reservaCompletada() {
		
		if (pedidoBorrar != -1) {
			int idReserva=(int) tbReservas.getValueAt(tbReservas.getSelectedRow(), 0);
			if (gestion.cambiarEstadoReserva(idReserva,cambioReservas)) {

				JOptionPane.showMessageDialog(this, "Reserva finalizada. Se cambio el estado de la reserva",
						"RESERVA COMPLETADA", JOptionPane.INFORMATION_MESSAGE);
				modeloReservas.fireTableDataChanged();

			}
		
			pedidoBorrar = -1;
		}

	}

	

	private void cambiarReservasFechaHoy() {
		if (!cambioReservas) {
			btn3.setText("Ver todas las reservas (Actualizar reservas)");
			String localDate = LocalDate.now().toString();
			modeloReservas.setDatos(gestion.getReservasPorFecha(localDate));
			modeloReservas.fireTableDataChanged();
			cambioReservas = true;
		} else {
			btn3.setText("Ver las reservas de hoy (Actualizar reservas)");
			modeloReservas.setDatos(gestion.getTodasLasReservas());
			modeloReservas.fireTableDataChanged();
			cambioReservas = false;
		}

	}

	private void eliminarPlato() {
		if (gestion.compruebaPlatoNoMenu()) {
			if (gestion.compruebaPlatoPedido()) {
				if (gestion.eliminaPlato(pedidoBorrar)) {
					JOptionPane.showMessageDialog(this, "Se borro el plato seleccionado", "PLATO BORRADO",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				if (gestion.eleminarPedidoPlato(pedidoBorrar)) {
					if (gestion.eliminaPlato(pedidoBorrar)) {
						JOptionPane.showMessageDialog(this, "Se borro el plato seleccionado", "PLATO BORRADO",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

		} else {
			JOptionPane.showMessageDialog(this, "No se puede borrar el plato, pertenece a un Menú", "PLATO NO BORRADO",
					JOptionPane.ERROR_MESSAGE);
		}

		pedidoBorrar = -1;
		modeloPlatos.fireTableDataChanged();
	}

	private void addOModifiPlato() {
		AddModifiPlato dialogoP = null;
		if (pedidoBorrar != -1) {// Modifica el plato
			String[] datos = gestion.devuelveNombreYprecio();
			dialogoP = new AddModifiPlato(this, true, gestion, datos[0], datos[1]);
		} else { // Añade un plato
			dialogoP = new AddModifiPlato(this, true, gestion, "", "");
		}

		dialogoP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogoP.setVisible(true);
		pedidoBorrar = -1;
		modeloPlatos.fireTableDataChanged();
	}

	// Empleado añade , quita menu
	private void eliminarMenu() {
		if (gestion.eliminarMenu(pedidoBorrar)) {
			JOptionPane.showMessageDialog(this, "Se borro el menu seleccionado", "MENU BORRADO",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println("ELIMINA PRINCIPAL");
		}
		pedidoBorrar = -1;
		modeloMenus.fireTableDataChanged();

	}

	private void addMenu() {

		AddMenuEmpleado dialogM = new AddMenuEmpleado(this, true, gestion, modeloPlatos);
		dialogM.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogM.setVisible(true);
		gestion.precioTotalAcero();
		gestion.limpiarListaSeleccion();
		modeloMenus.fireTableDataChanged();
	}

	private void eliminarMenuOplato() {
		if(gestion.getListaSeleccionMenu().size()>0) {
			precioTotal = gestion.quitaSeleccion(pedidoBorrar);
			pedidoBorrar = -1;
			actualizaInfoPedidos(lbInfoPedidos, lbInfoPrecio);

			modeloSeleccionM.fireTableDataChanged();
			System.out.println("BORRO MENU");
		}
		

	}

	public void verReserva() {
		if(esCliente()) {
			if (gestion.getListaReservas().size() > 0) {

				VerReserva dialog = new VerReserva(this, true, gestion);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
		}else {
			if (gestion.getTodasLasReservas().size() > 0) {

				VerReserva dialog = new VerReserva(this, true, gestion);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
		}
		
		pedidoBorrar = -1;
	}

	// Añade Platos y/o Menus a la lista
	public void addMenuOplato() {
		// Cargar el precio
		precioTotal = gestion.cargarSeleccion();

		// Actualiza la informacion de Tabla Info
		actualizaInfoPedidos(lbInfoPedidos, lbInfoPrecio);

		// Actualiza los datos de la Tabla Seleccion
		modeloSeleccionM.setDatos(gestion.getListaSeleccionMenu());
		modeloSeleccionM.fireTableDataChanged();

		// Control de los botones, OFF
		pedidoBorrar = -1;
		System.out.println("ADDMENU");
	}

	private void eliminarReserva() {
		if (gestion.compruebaFecheHoy(pedidoBorrar)) {
			if (gestion.borrarReser(pedidoBorrar)) {
				modeloReservas.fireTableDataChanged();
			} else {
				JOptionPane.showMessageDialog(this, "No  pudo borrar la reserva", "ERROR BORRADO",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "No se puede borrar la RESERVA el mismo dia que se tiene la reserva",
					"ERROR BORRADO", JOptionPane.ERROR_MESSAGE);
		}
		pedidoBorrar = -1;

	}

	private void cambioNombresBtnyTitulo(String titulo, String btnAdd, String btnRemov, Color color, Color color2) {
		pnTituloOptions.setBackground(color);
		lbTituloOption.setForeground(color2);
		lbTituloOption.setText(titulo);
		btn1.setText(btnAdd);
		btn2.setText(btnRemov);

	}

	// Rellena los campos del cliente en Configuracion
	private void cargarCliente() {
		txtNombre.setText(gestion.nombreUsu);
		txtEmail.setText(gestion.emailCliente);
		txtPass.setText(gestion.passCliente);
		txtTlf.setText(String.valueOf(gestion.tlfCliente));

	}
	private void cargarEmpleado() {
		txtNombre.setText(gestion.nombreUsu);
		txtEmail.setText(String.valueOf(gestion.idEmpleado));
		txtPass.setText(gestion.passCliente);
		txtTlf.setText(String.valueOf(gestion.tlfCliente));
		txtFechaContrata.setText(gestion.fechaContrata);
		txtSalario.setText(gestion.salario);
	}

	// Recupera los campos del cliente cuando actualiza sus datos
	private void actulizaCliente(String nombre, String email, String pass, int tlf) {
		gestion.nombreUsu = nombre;
		gestion.emailCliente = email;
		gestion.passCliente = pass;
		gestion.tlfCliente = tlf;
	}

	private void modificarCliente() {

		// Comprueba que no haya campos vacios
		if (txtEmail.getText().isEmpty() || txtPass.getText().isEmpty() || txtTlf.getText().isEmpty() ||txtNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ningun campo puede estar vacio, compruebe que los campos : /n "
					+ " Email, Password y Tlf estan completos", "CAMPOS VACIOS", JOptionPane.WARNING_MESSAGE);
		} else {
			// Comprueba si es un correo electronico correcto
			if (gestion.esMail(txtEmail.getText())) {
				// Comprueba si la contraseña es mayor de 7 caracteres
				if (gestion.compruebaPass(txtPass.getText())) {
					// Comprueba si hay error en la BBDD
					if (gestion.modificaClient(txtNombre.getText(), txtEmail.getText(), txtPass.getText(),
							Integer.parseInt(txtTlf.getText()))) {

						actulizaCliente(txtNombre.getText(), txtEmail.getText(), txtPass.getText(),
								Integer.parseInt(txtTlf.getText()));

						mensajes(MENSAJE_OK);

					} else {
						mensajes(MENSAJE_ERROR_BBDD);
					}

				} else {
					mensajes(MENSAJE_ERROR_PASS1);

				}
			} else {
				mensajes(MENSAJE_ERROR_MAIL);

			}

		}
	}

	// Esto tengo que revisarlo
	public void compruebaTamañoSelecion() {
		if (gestion.getListaSeleccionMenu().size() > 0) {

			precioTotal = gestion.precioTotalAcero();
		} else {
			precioTotal = 0.0f;
		}
	}

	public void actualizaInfoPedidos(JLabel pedidos, JLabel precios) {
		pedidos.setText("Total pedidos: " + gestion.totalPedidosMenu());
		precios.setText("Total a pagar: " + String.valueOf(precioTotal));
	}

	public void ponerPanelVisible(JPanel pnVisible) {
		for (int i = 0; i < listaPaneles.size(); i++) {

			if (listaPaneles.get(i) == pnVisible) {

				// Pone el panel elegido a visible
				listaPaneles.get(i).setVisible(true);

				// Guarda el panel visible para controlar los botones
				panelVisible = i;
			} else {
				listaPaneles.get(i).setVisible(false);
			}
		}
	}

	// ACTION LISTENER
	@Override // Probar añadir con click
	public void mouseClicked(MouseEvent evt) {
		System.out.println("Clicker");

		if (evt.getSource() == tbMenus) {
			int i = tbMenus.getSelectedRow();
			gestion.menuOplatoSelccionado(i, true);
			pedidoBorrar = i;
			System.out.println("ENTRAN en TBMENUS");

		}
		if (evt.getSource() == tbPedidosMenus) {
			int i = tbPedidosMenus.getSelectedRow();
			;

			gestion.pedidoSeleccionado(i);
			pedidoBorrar = i;
			System.out.println("ENTRAN en clicker TBPEDIDOS");
		}
		if (evt.getSource() == tbPedidosPlatos) {
			int i = tbPedidosPlatos.getSelectedRow();
			;

			gestion.pedidoSeleccionado(i);
			pedidoBorrar = i;

			System.out.println("ENTRAN en clicker TBPEDIDOS");
		}
		if (evt.getSource() == tbPlatos) {
			int i = tbPlatos.getSelectedRow();
			pedidoBorrar = i;
			gestion.menuOplatoSelccionado(i, false);
			System.out.println("ENTRAN en clicker Platos");

		}
		if (evt.getSource() == tbReservas) {
			int idReserva=(int) tbReservas.getValueAt(tbReservas.getSelectedRow(), 0);
			System.out.println(idReserva);
			int i = tbReservas.getSelectedRow();
			if (esCliente()) {
				gestion.reservaSelected(i);
			} else {
				if (!cambioReservas) {
					gestion.reservaSelectedEmple(i);
				} else {
					gestion.reservaSelectedHoy(i);
				}

			}

			pedidoBorrar = i;
			System.out.println("ENTRAN en clicker tbReservas");
		}
		System.out.println("cLICKER");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("Entered");
	}

	@Override
	public void mouseExited(MouseEvent evt) {
		System.out.println("Exited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("PressedE");

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("RELEASE");
	}

	

	// Mensajes del sistema
	private void mensajes(int mensaje) {
		switch (mensaje) {
		case MENSAJE_OK: // OK inserta cliente
			JOptionPane.showMessageDialog(this, "Usuario modificado con exito", "USUARIO CREADO",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case MENSAJE_ERROR_BBDD:// El usuario ya existe
			JOptionPane.showMessageDialog(this, "Error inesperado en la Base de datos. Por favor vuelva a intentarlo",
					"ERROR AL CREAR", JOptionPane.ERROR_MESSAGE);
		
			break;
		case MENSAJE_ERROR_MAIL:// El email no es correcto
			
			JOptionPane.showMessageDialog(this, "Introduzca un correo electronico valido. Ejem: xxx@gmail.com",
					"ERROR EMAIL", JOptionPane.ERROR_MESSAGE);
			
			break;
		case 3:// EL PASSWORD debe ser mayor a 8 caracteres
			JOptionPane.showMessageDialog(this, "La contraseña tiene que tener al menos 8 caracteres", "ERROR PASS",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

	private boolean esCliente() {
		if (gestion.tipoUsuario == gestion.USU_CLIENTE) {
			return true;
		} else {
			return false;
		}
	}
}
