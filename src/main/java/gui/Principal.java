package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.joda.time.LocalDate;
import org.neodatis.btree.exception.BTreeNodeValidationException;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Controlador.Gestion;
import Modelo.Clientes;
import Modelo.Menus;
import Modelo.Platos;
import Modelo.Reservas;
import Modelo.TablaPlatos;
import Modelo.TablaReservas;
import Modelo.TablaSeleccion;
import Modelo.tablaMenus;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.CardLayout;

public class Principal extends JFrame implements ActionListener, MouseListener {

	private JDesktopPane contentPane;
	private String btnReservas = "";
	private Gestion gestion;
	private JButton btnInicio;
	private JButton btnReserva;
	private JButton btnSalir;
	private JButton btnMenu;
	private JButton btnPlatos;
	private JButton btnConfig;
	private JPanel pnFech;
	private JLayeredPane layeredPane;
	// Prueba panesles

	// Componentes MENU
	private Menus menus;
	private JPanel pnMenu;
	private JPanel pnBtnMenus;
	private JPanel pnBtnPrincipales;
	private JPanel pnInicio;
	private JButton btnAddMenu;
	private JTable tbMenus;
	private JPanel pnTablaMenu;
	private JButton btnRemovMenu;
	private JButton btnModificarMenu;
	private JScrollPane scrollPaneMenu1;
	private JPanel pnInfoMenus;
	private JPanel pnTituloMenu;
	private JLabel lbTitulo;
	private JLabel lbTotalPagarMenus;
	private JLabel lbTotalPedidosMenus;

	// Componentes Platos
	private Platos plato;
	private JPanel pnPlato;
	private JPanel pnBtnPlato;
	private JPanel pnBtnPlato_1;
	private JPanel pnInicioPlato;
	private JButton btnAddPlato;
	private JTable tbPlatos;
	private JPanel pnTablaPlatos;
	private JButton btnRemovPlatos;
	private JButton btnModificarPlato;
	private JScrollPane scrollPaneMenu;
	private JPanel pnTituloPlatos;
	private JLabel lbTituloPlatos;
	private JPanel pnInfoPlatos;
	private JLabel lbTotalPagarPlatos;
	private JLabel lbTotalPedidosPlatos;

	// Componentes Reservas
	private JPanel pnReservas;
	private JPanel pnBtnReservas;
	private JPanel pnTablaReservas;
	private JTable tbReservas;
	private JButton btnCancelarReserva;
	private JButton btnVerReserva;
	private JPanel pnTitutloResv;
	private JLabel lbTituloResv;

	// Componentes Configuracion

	private JPanel pnConfig;
	private JPanel pnBtnConfig;
	private JPanel pnFormularioConfig;
	private JPanel pnTituloConfig;
	private JButton btnModificarConfig;
	private JButton btnBorrarConfig;
	private JLabel lbTituloConfig;
	private JTextField txtNombre;
	private JTextField txtTlf;
	private JTextField txtEmail;
	private JTextField txtPass;

	// tbPedidos , contigne platos y menus
	private JTable tbPedidosPlatos;
	private int pedidoBorrar = -1;
	private int control = 0;
	private float precioTotal = 0.0f;
	private int TIPO_PABLA = 0;
	private boolean menuAdd = false;

	// Sirve para controlaar el panel que se visualiza
	private ArrayList<JPanel> listaPaneles = new ArrayList<JPanel>();

	private boolean cargaUnaVez = true;

	// Tablas
	private tablaMenus modeloMenus;
	private TablaPlatos modeloPlatos;
	private TablaSeleccion modeloSeleccionM;
	private TablaReservas modeloReservas;

	private JLabel lbPedidos;
	private JTable tbPedidosMenus;

	// Mensajes
	private final int MENSAJE_OK = 0;
	private final int MENSAJE_ERROR_BBDD = 1;
	private final int MENSAJE_ERROR_MAIL = 2;
	private final int MENSAJE_ERROR_PASS1 = 3;
	private final int MENSAJE_ERROR_PASS2 = 4;

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

		// Datos Menu
		modeloMenus = new tablaMenus();
		modeloPlatos = new TablaPlatos();
		modeloSeleccionM = new TablaSeleccion();
		modeloReservas = new TablaReservas();

		modeloMenus.setDatos(gestion.getListaMenus());
		modeloPlatos.setDatos(gestion.getListaPlatos());

		Logeo log= new Logeo(this,true,gestion);
		log.setVisible(true);

//		Dimension rectangulo = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//		double h= rectangulo.getHeight()-rectangulo.getHeight()*0.10;
//		double w= rectangulo.getWidth()-rectangulo.getWidth()*0.10;
//		rectangulo.setSize(w, h);
//		Rectangle a = new Rectangle(rectangulo);
//		setBounds(a);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1073, 621);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		layeredPane = new JLayeredPane();

		pnFech = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnFech, GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnFech, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)));
		GridBagLayout gbl_pnFech = new GridBagLayout();
		gbl_pnFech.columnWidths = new int[] { 351, 48, 99, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnFech.rowHeights = new int[] { 14, 0, 0 };
		gbl_pnFech.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnFech.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnFech.setLayout(gbl_pnFech);

		JLabel lbUsuario = new JLabel();
		lbUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbUsuario.setText("Usuario:  " + gestion.nombreUsu + "  |  ");
		lbUsuario.setBounds(405, 389, 159, 14);
		GridBagConstraints gbc_lbUsuario = new GridBagConstraints();
		gbc_lbUsuario.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_lbUsuario.gridx = 2;
		gbc_lbUsuario.gridy = 1;
		pnFech.add(lbUsuario, gbc_lbUsuario);

		JLabel lbFecha = new JLabel();
		lbFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lbFecha.setVerticalAlignment(SwingConstants.BOTTOM);
		lbFecha.setText("Fecha :  " + LocalDate.now().toString());
		lbFecha.setBounds(669, 389, 106, 14);
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbFecha.gridx = 8;
		gbc_lbFecha.gridy = 1;
		pnFech.add(lbFecha, gbc_lbFecha);

		contentPane.setLayout(gl_contentPane);

		// Paneles de los botones
		// Pruebas paneles
		pnMenu = new JPanel();
		pnMenu.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnMenu.setBackground(Color.RED);
		pnMenu.setBounds(0, 45, 1047, 477);
		layeredPane.add(pnMenu, BorderLayout.CENTER);
		pnMenu.setLayout(null);

		// Panel Inicio
		pnInicio = new JPanel();
		pnInicio.setBounds(0, 45, 1047, 477);
		pnInicio.setBackground(Color.BLUE);
		layeredPane.add(pnInicio, BorderLayout.CENTER);
		pnInicio.setLayout(null);

		// Panel Reservas
		pnReservas = new JPanel();
		pnReservas.setBounds(0, 45, 1047, 477);
		pnReservas.setBackground(Color.GRAY);
		layeredPane.add(pnReservas, BorderLayout.CENTER);
		pnReservas.setLayout(null);
		// cargarReservas();

		// Panel Configuracion
		pnConfig = new JPanel();
		pnConfig.setBounds(0, 45, 1047, 477);
		pnConfig.setBackground(Color.BLACK);
		layeredPane.add(pnConfig, BorderLayout.CENTER);
		pnConfig.setLayout(null);

		// Tabla Pedidos Menus y Platos
		tbPedidosMenus = new JTable();
		tbPedidosMenus.setAutoCreateRowSorter(true);
		tbPedidosMenus.addMouseListener(this);

		tbPedidosPlatos = new JTable();
		tbPedidosPlatos.setAutoCreateRowSorter(true);
		tbPedidosPlatos.addMouseListener(this);

		// Panel platos
		pnPlato = new JPanel();
		pnPlato.setBackground(Color.YELLOW);
		pnPlato.setBounds(0, 45, 1047, 477);
		layeredPane.add(pnPlato, BorderLayout.CENTER);
		pnPlato.setLayout(null);

		// Botones Principales
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

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		pnBtnPrincipales.add(btnSalir);

		// FINAL BOTONES MENU

		// POnemos todos los paneles a false menos Inicio
		pnMenu.setVisible(false);
		pnPlato.setVisible(false);
		pnInicio.setVisible(true);
		pnReservas.setVisible(false);
		pnConfig.setVisible(false);

		// Cargo todos lo paneles para el control de ser visibles
		listaPaneles.add(pnInicio);
		listaPaneles.add(pnMenu);
		listaPaneles.add(pnPlato);
		listaPaneles.add(pnReservas);
		listaPaneles.add(pnConfig);
//		Logeo log= new Logeo(this,true);
//		log.setVisible(true);

	
		

		// Panel Platos componentes
		lbTituloPlatos = new JLabel("PLATOS");
		pnTituloPlatos = new JPanel();
		pnBtnPlato = new JPanel();
		btnAddPlato = new JButton("Añadir plato");
		btnRemovPlatos = new JButton("Quitar plato");
		pnTablaPlatos = new JPanel();
		tbPlatos = new JTable();
		pnInfoPlatos = new JPanel();
		lbTotalPedidosPlatos = new JLabel("Numero total pedidos: ");
		lbTotalPagarPlatos = new JLabel("Total a pagar: ");
		cargarBotones(pnBtnPlato, pnPlato, btnAddPlato, btnRemovPlatos, pnTablaPlatos, tbPlatos);
		cargarPanelInfo(pnInfoPlatos, pnPlato, lbTotalPedidosPlatos, lbTotalPagarPlatos);
		cargarTitulos(lbTituloPlatos, pnTituloPlatos, pnPlato, new Color(255, 160, 122));

		// Panel Reservas componentes
		lbTituloResv = new JLabel("RESERVAS");
		pnTitutloResv = new JPanel();
		pnBtnReservas = new JPanel();
		btnCancelarReserva = new JButton("Cancelar Reserva");
		btnVerReserva= new JButton("Ver reserva");
		pnTablaReservas = new JPanel();
		tbReservas = new JTable();
		cargarBotones(pnBtnReservas, pnReservas, btnVerReserva, btnCancelarReserva, pnTablaReservas, tbReservas);
		cargarTitulos(lbTituloResv, pnTitutloResv, pnReservas, new Color(240, 230, 140));

		// Panel Menus componentes
		lbTitulo = new JLabel("MENUS");
		pnTituloMenu = new JPanel();
		pnBtnMenus = new JPanel();
		btnAddMenu = new JButton("Añadir menu");
		btnRemovMenu = new JButton("Quitar menu");
		pnTablaMenu = new JPanel();
		tbMenus = new JTable();
		pnInfoMenus = new JPanel();
		lbTotalPedidosMenus = new JLabel("Numero total pedidos: ");
		lbTotalPagarMenus = new JLabel("Total a pagar: ");
		cargarBotones(pnBtnMenus, pnMenu, btnAddMenu, btnRemovMenu, pnTablaMenu, tbMenus);
		cargarPanelInfo(pnInfoMenus, pnMenu, lbTotalPedidosMenus, lbTotalPagarMenus);
		cargarTitulos(lbTitulo, pnTituloMenu, pnMenu, new Color(255, 160, 122));

		// Panel Configuracion componentes
		lbTituloConfig = new JLabel("CONFIGURACION");
		pnBtnConfig = new JPanel();
		pnTituloConfig = new JPanel();
		btnModificarConfig = new JButton("Modificar cuenta");
		btnBorrarConfig = new JButton("Borrar cuenta");
		pnFormularioConfig = new JPanel();

		cargarBotones(pnBtnConfig, pnConfig, btnModificarConfig, btnBorrarConfig, pnFormularioConfig, null);

		cargarTitulos(lbTituloConfig, pnTituloConfig, pnConfig, new Color(0, 128, 128));

		// Cargamos los modelos de las tablas pedidos y reservas
		tbPedidosPlatos.setModel(modeloSeleccionM);
		tbPedidosMenus.setModel(modeloSeleccionM);
		tbReservas.setModel(modeloReservas);
		
	}

	private void cargarFormulario() {

		GridBagLayout gbl_pnFormularionConfig = new GridBagLayout();
		gbl_pnFormularionConfig.columnWidths = new int[]{185, 117, 218, 0, 148, 0, 192, 0, 0, 0};
		gbl_pnFormularionConfig.rowHeights = new int[]{104, 0, 90, 0, 0};
		gbl_pnFormularionConfig.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnFormularionConfig.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			public void keyTyped(KeyEvent e)
			   {
			      char caracter = e.getKeyChar();
			      
			      // Verificar si la tecla pulsada no es un digito
			      if(txtTlf.getText().length()>8) {
			    	  e.consume();
			      }
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b'))  /*corresponde a BACK_SPACE*/
			      {
			         e.consume();  // ignorar el evento de teclado
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
	}

	private void cargarPanelInfo(JPanel pnInfo, JPanel padre, JLabel pedidos, JLabel pagar) {
		pnInfo = new JPanel();
		pnInfo.setBackground(new Color(255, 228, 196));
		pnInfo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnInfo.setBounds(0, 356, 1047, 52);
		padre.add(pnInfo);
		pnInfo.setLayout(new GridLayout(1, 0, 0, 0));
		pnInfo.add(pedidos);
		pnInfo.add(pagar);
	}

	private void cargarTitulos(JLabel titulo, JPanel panelTitulo, JPanel padre, Color color) {
		// panelTitulo = new JPanel();
		panelTitulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
		panelTitulo.setBackground(color);
		panelTitulo.setForeground(new Color(105, 105, 105));
		panelTitulo.setBounds(0, 0, 1047, 52);
		padre.add(panelTitulo);
		panelTitulo.setLayout(null);

		titulo.setForeground(new Color(0, 0, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 20));
		titulo.setBounds(366, 5, 200, 32);
		panelTitulo.add(titulo);
	}

	private void cargarBotones(JPanel pnBotones, JPanel pnPadre, JButton btnAdd, JButton btnRemov, JPanel pnTabla,
			JTable tabla) {
		pnBotones.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnBotones.setBackground(new Color(0, 0, 0));
		pnBotones.setBounds(0, 406, 1047, 71);
		pnPadre.add(pnBotones);
		pnBotones.setLayout(new GridLayout(0, 2, 0, 0));
		

		btnAdd.addActionListener(this);
		pnBotones.add(btnAdd);
		
		btnRemov.addActionListener(this);
		pnBotones.add(btnRemov);

		pnTabla.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTabla.setBounds(0, 50, 1047, 307);

		if (tabla != null) {
			JScrollPane panelScrollm = new JScrollPane();
			tabla.addMouseListener(this);
			pnTabla.setLayout(new GridLayout(1, 0, 0, 0));
			panelScrollm.setViewportView(tabla);
			pnTabla.add(panelScrollm);

			if (pnTabla == pnTablaPlatos) {
				JScrollPane panelScrolln = new JScrollPane();
				panelScrolln.setViewportView(tbPedidosPlatos);
				pnTabla.add(panelScrolln);
				tabla.setModel(modeloPlatos);
			} else if (pnTabla == pnTablaMenu) {
				JScrollPane panelScrolln = new JScrollPane();
				panelScrolln.setViewportView(tbPedidosMenus);
				pnTabla.add(panelScrolln);
				tabla.setModel(modeloMenus);
			} else if (pnTabla == pnTablaReservas) {
				tabla.setModel(modeloReservas);
			}
		} else {
//			GridBagLayout gbl_pnFormularionConfig = new GridBagLayout();
//			gbl_pnFormularionConfig.columnWidths = new int[]{0, 120, 164, 0, 148, 0, 165, 0, 0, 0};
//			gbl_pnFormularionConfig.rowHeights = new int[]{0, 0, 0, 0, 0};
//			gbl_pnFormularionConfig.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_pnFormularionConfig.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//			pnTabla.setLayout(gbl_pnFormularionConfig);
			cargarFormulario();
		}

		pnPadre.add(pnTabla);
	}

	boolean unaVez = true;
	@Override
	public void actionPerformed(ActionEvent evnt) {

		if (evnt.getSource() == btnSalir) {
			System.exit(0);

		} else if (evnt.getSource() == btnReserva) {

			if (gestion.getListaSeleccionMenu().size() > 0) {
				DialogoReservClient dialog = new DialogoReservClient(this, true, gestion);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
			if (cargaUnaVez) {
				modeloReservas.setDatos(gestion.getListaReservas());
				cargaUnaVez = false;
			} else {
				modeloReservas.setDatos(gestion.getMisReservas());
			}

			pedidoBorrar = -1;

			modeloReservas.fireTableDataChanged();
			modeloSeleccionM.fireTableDataChanged();
			reiniciarPanelInicio(pnReservas);

		} else if (evnt.getSource() == btnInicio) {
			reiniciarPanelInicio(pnInicio);

		} else if (evnt.getSource() == btnMenu) {

			reiniciarPanelInicio(pnMenu);

//			JPanel pnInfoPedidoMenu = new JPanel();
//			pnInfoPedidoMenu.setBackground(new Color(255, 228, 196));
//			pnInfoPedidoMenu.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
//			pnInfoPedidoMenu.setBounds(0, 356, 1047, 52);
//			pnMenu.add(pnInfoPedidoMenu);
//			pnInfoPedidoMenu.setLayout(new GridLayout(1, 0, 0, 0));

//			lbPedidos = new JLabel("Numero total pedidos: " + gestion.totalPedidosMenu());
//			lblTotalAPagar = new JLabel("Total a pagar: " + precioTotal);

			pedidoBorrar = -1;
			compruebaTamañoSelecion();
			actualizaInfoPedidos(lbTotalPedidosMenus, lbTotalPagarMenus);

		} else if (evnt.getSource() == btnPlatos) {

			reiniciarPanelInicio(pnPlato);
//			JPanel pnInfoPedidoMenu = new JPanel();
//			pnInfoPedidoMenu.setBackground(new Color(255, 228, 196));
//			pnInfoPedidoMenu.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
//			pnInfoPedidoMenu.setBounds(0, 356, 1047, 52);
//			pnPlato.add(pnInfoPedidoMenu);
//			pnInfoPedidoMenu.setLayout(new GridLayout(1, 0, 0, 0));

			compruebaTamañoSelecion();
			actualizaInfoPedidos(lbTotalPedidosPlatos, lbTotalPagarPlatos);
			pedidoBorrar = -1;
		} else if (evnt.getSource() == btnConfig) {

			reiniciarPanelInicio(pnConfig);
			cargarCliente();

			// Btn Añadir menu
		} else if (evnt.getSource() == btnAddMenu) {

			if (pedidoBorrar != -1) {
				precioTotal = gestion.cargarSeleccionMenu();
				pedidoBorrar = -1;
				actualizaInfoPedidos(lbTotalPedidosMenus, lbTotalPagarMenus);
				btnAddMenu();
			} else {
				// El boton no hace nada si no hay fila tbMenus seleccionada
			}
			// Btn Quitar menu
		} else if (evnt.getSource() == btnRemovMenu) {
			if (gestion.esUltimo()) {
				// tbPedidos.removeAll();
				// tbPedidos.setVisible(false);
				System.out.println("ES ULTIMO");
			} else {
				if (pedidoBorrar != -1) {
					precioTotal = gestion.removMenu(pedidoBorrar);
					pedidoBorrar = -1;
					actualizaInfoPedidos(lbTotalPedidosMenus, lbTotalPagarMenus);

					;
					modeloSeleccionM.fireTableDataChanged();
					System.out.println("BORRO MENU");
				} else {
					// El boton no hace nada si no hay fila seleccionada de pedidos

				}

			}
			// Btn Añadir Plato
		} else if (evnt.getSource() == btnAddPlato) {
			if (pedidoBorrar != -1) {
				// tbPedidosPlatos.setModel(modeloSeleccionM);
				precioTotal = gestion.cargarSeleccionMenu();
				if (gestion.getListaSeleccionMenu().size() > 0) {

					modeloSeleccionM.setDatos(gestion.getListaSeleccionMenu());
					// tbPedidos.removeAll();

					modeloSeleccionM.fireTableDataChanged();
					actualizaInfoPedidos(lbTotalPedidosPlatos, lbTotalPagarPlatos);
					;

					System.out.println("ADD PLATO");
					pedidoBorrar = -1;
				}

			}
			// Btn Quitar Platos
		} else if (evnt.getSource() == btnRemovPlatos) {
			if (pedidoBorrar != -1) {
				precioTotal = gestion.removMenu(pedidoBorrar);
				actualizaInfoPedidos(lbTotalPedidosPlatos, lbTotalPagarPlatos);
				;
				modeloSeleccionM.fireTableDataChanged();
				System.out.println("BORRO PALTOS");
				pedidoBorrar = -1;
			}
			// Btn Cancelar Reserva
		} else if (evnt.getSource() == btnCancelarReserva) {
			if (pedidoBorrar != -1) {
				if (gestion.compruebaFecheHoy(pedidoBorrar)) {
					if (gestion.borrarReser(pedidoBorrar)) {
						modeloReservas.fireTableDataChanged();
					} else {
						JOptionPane.showMessageDialog(this, "No  pudo borrar la reserva", "ERROR BORRADO",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "No se puede borrar la RESERVA el mismo dia de la misma",
							"ERROR BORRADO", JOptionPane.ERROR_MESSAGE);
				}
				pedidoBorrar = -1;
			}
		}else if(evnt.getSource()==btnVerReserva) {
			if(pedidoBorrar!=-1) {
				if (gestion.getListaReservas().size() > 0) {
					DialogoReservClient dialog = new DialogoReservClient(this, true, gestion);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				}
				pedidoBorrar=-1;
			}
		
//			if (cargaUnaVez) {
//				modeloReservas.setDatos(gestion.getListaReservas());
//				cargaUnaVez = false;
//			} else {
//				modeloReservas.setDatos(gestion.getMisReservas());
//			}

			

//			modeloReservas.fireTableDataChanged();
//			modeloSeleccionM.fireTableDataChanged();
			
		} else if (evnt.getSource() == btnModificarConfig) {
			modificarCliente();
		}else if(evnt.getSource()==btnBorrarConfig) {
			
			int borra=JOptionPane.showConfirmDialog(this, "¿Seguro que quiere borrar la cuenta? El programa se cerrara");
			if(borra==0) {
				gestion.borrarClient();
				System.exit(0);
			}
		}
	}
	private void cargarCliente() {
		txtNombre.setText(gestion.nombreUsu);
		txtEmail.setText(gestion.emailCliente);
		txtPass.setText(gestion.passCliente);
		txtTlf.setText(String.valueOf(gestion.tlfCliente));

	}
	private void actulizaCliente(String nombre, String email, String pass, int tlf) {
		gestion.nombreUsu=nombre;
		gestion.emailCliente=email;
		gestion.passCliente=pass;
		gestion.tlfCliente=tlf;
	}
	private void modificarCliente() {

		if (txtEmail.getText().isEmpty() || txtPass.getText().isEmpty() || txtTlf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ningun campo puede estar vacio, compruebe que los campos : /n "
					+ " Email, Password y Tlf estan completos", "CAMPOS VACIOS", JOptionPane.WARNING_MESSAGE);
		} else {
			if (gestion.esMail(txtEmail.getText())) {
				if (gestion.compruebaPass(txtPass.getText())) {

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

	public void btnAddMenu() {
		// tbPedidosMenus.setModel(modeloSeleccionM);

		modeloSeleccionM.setDatos(gestion.getListaSeleccionMenu());
		// tbPedidos.removeAll();

		modeloSeleccionM.fireTableDataChanged();

		System.out.println("ADDMENU");
	}

	public void reiniciarPanelInicio(JPanel pnVisible) {
		for (JPanel pn : listaPaneles) {
			if (pn == pnVisible) {
				pn.setVisible(true);
			} else {
				pn.setVisible(false);
			}
		}
	}

	// ACTION LISTENER
	@Override
	public void mouseClicked(MouseEvent evt) {
		System.out.println("Clicker");

		if (evt.getSource() == tbMenus) {
			int i = tbMenus.getSelectedRow();
			gestion.menuSeleccionado(i, true);
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
			gestion.menuSeleccionado(i, false);
			System.out.println("ENTRAN en clicker Platos");

		}
		if (evt.getSource() == tbReservas) {
			int i = tbReservas.getSelectedRow();
			gestion.reservaSelected(i);
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

	private void tablaMouseClicked(MouseEvent evt) {
//		if(evt.getSource()==tbMenus){
//			int i= tbMenus.getSelectedRow();
//			gestion.menuSeleccionado(itrue);
//		}

	}

	private void mensajes(int mensaje) {
		switch (mensaje) {
		case 0: // OK inserta cliente
			JOptionPane.showMessageDialog(this, "Usuario creado con exito", "USUARIO CREADO",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 1:// El usuario ya existe
			JOptionPane.showMessageDialog(this, "El usuario ya existe, no se ha podido registrar", "ERROR AL CREAR",
					JOptionPane.ERROR_MESSAGE);
			txtEmail.setText("");
			txtNombre.setText("");
			txtEmail.setBackground(Color.WHITE);
			txtPass.setText("");
			txtTlf.setText("");
			break;
		case 2:
			// El email no es correcto
			txtEmail.setBackground(Color.RED);
			txtEmail.setForeground(Color.WHITE);
			JOptionPane.showMessageDialog(this, "Introduzca un correo electronico valido. Ejem: xxx@gmail.com",
					"ERROR EMAIL", JOptionPane.ERROR_MESSAGE);
			txtEmail.setText("");
			txtEmail.setBackground(Color.WHITE);
			break;
		case 3:// EL PASSWORD debe ser mayor a 8 caracteres
			JOptionPane.showMessageDialog(this, "La contraseña tiene que tener al menos 8 caracteres", "ERROR PASS",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 4:// PASSWORD no coincide
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "ERROR PASS",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
}
