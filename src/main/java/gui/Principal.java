package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.joda.time.LocalDate;
import org.neodatis.btree.exception.BTreeNodeValidationException;

import Controlador.Gestion;
import Modelo.Menus;
import Modelo.TablaPlatos;
import Modelo.TablaSeleccion;
import Modelo.tablaMenus;

import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.CardLayout;


public class Principal extends JFrame implements ActionListener, MouseListener{

	private JDesktopPane contentPane;
	private String btnReservas="";
	private Gestion gestion;
	private JButton btnInicio;
	private JButton btnReserva;
	private JButton btnSalir;
	private JButton btnMenu;
	private JButton btnPlatos;
	private JButton btnConfig;
	private JPanel pnFech;
	private JLayeredPane layeredPane;
	//Prueba panesles
	
	//Componentes MENU
	private Menus menus;
	private JPanel pnMenu;
	private JPanel pnButtons;
	private JPanel pnButtons_1;
	private JPanel pnInicio;
	private JButton btnAddMenu;
	private JTable tbMenus;
	private JPanel pnTabla;
	private JButton btnRemovMenu;
	private JButton btnModificarMenu;
	private JScrollPane scrollPaneMenu;
	
	private int pedidoBorrar=0;
	private int control=0;
	private float precioTotal=0.0f;
	private int TIPO_PABLA=0;
	
	private ArrayList<JPanel> listaPaneles=new ArrayList<JPanel>();
	//Componentes Platos
	private JPanel pnPlatos;
	
	//Componentes Reservas
	private JPanel pnReservas;
	
	
	//Componentes Configuracion
	private JPanel pnConfig;
	
	//Tablas
	private tablaMenus modeloMenus;
	private TablaPlatos modeloPlatos;
	private TablaSeleccion modeloSeleccionM;
	private JPanel pnTitulo;
	private JLabel lbTitulo;
	private JLabel lbPedidos;
	private JTable tbPedidos;
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
		gestion=new Gestion();
		
		//Datos Menu
		modeloMenus=new tablaMenus();
		modeloSeleccionM= new TablaSeleccion();
		
		modeloMenus.setDatos(gestion.getListaMenus());

		
//		Logeo log= new Logeo(this,true,gestion);
//		log.setVisible(true);
//		Dimension rectangulo = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//		double h= rectangulo.getHeight()-rectangulo.getHeight()*0.10;
//		double w= rectangulo.getWidth()-rectangulo.getWidth()*0.10;
//		
//		
//		rectangulo.setSize(w, h);
//		Rectangle a = new Rectangle(rectangulo);
//		setBounds(a);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1073,621);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		layeredPane = new JLayeredPane();
		
		pnFech = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnFech, GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnFech, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
		);
		//Paneles de los botones
		//Pruebas paneles
		pnMenu = new JPanel();
		pnMenu.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnMenu.setBackground(Color.RED);
		pnMenu.setBounds(0, 45, 1047, 477);
		//pnMenu.setVisible(false);
		layeredPane.add(pnMenu,BorderLayout.CENTER);
		pnMenu.setLayout(null);
		
		//Panel Inicio
		pnInicio = new JPanel();
		pnInicio.setBounds(0, 45, 1047, 477);
		pnInicio.setBackground(Color.BLUE);
		layeredPane.add(pnInicio,BorderLayout.CENTER);
		pnInicio.setLayout(null);
		
		
		//Panel Reservas
		pnReservas= new JPanel();
		pnReservas.setBounds(0, 45, 1047, 477);
		pnReservas.setBackground(Color.RED);
		layeredPane.add(pnReservas,BorderLayout.CENTER);
		pnReservas.setLayout(null);
		
		//Panel Configuracion
		pnConfig= new JPanel();
		pnConfig.setBounds(0, 45, 1047, 477);
		pnConfig.setBackground(Color.BLACK);
		layeredPane.add(pnConfig,BorderLayout.CENTER);
		pnConfig.setLayout(null);
		
//		pnButtons = new JPanel();
//		pnButtons.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		pnButtons.setBackground(new Color(0, 0, 0));
//		pnButtons.setBounds(0, 406, 1047, 71);
//		pnMenu.add(pnButtons);
//		pnButtons.setLayout(new GridLayout(0, 2, 0, 0));
//		
//		
//		
//		btnAddMenu = new JButton("Añadir Menu");
//		btnAddMenu.addActionListener(this);
//		pnButtons.add(btnAddMenu);
//		btnAddMenu.addActionListener(this);
//		
//		
//		btnRemovMenu = new JButton("Quitar Menu");
//		btnRemovMenu.addActionListener(this);
//		pnButtons.add(btnRemovMenu);
//		
//		if(gestion.tipoUsuario>1) {
//			btnModificarMenu=new JButton("Modificar Menu");
//			btnAddMenu.addActionListener(this);
//			pnButtons.add(btnModificarMenu);
//			
//		}
//		
//		
//		//Panel Menu
//		pnTabla = new JPanel();
//		pnTabla.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		pnTabla.setBounds(0, 50, 1047, 307);
//		
//		JScrollPane panelScrollm= new JScrollPane();
//		
//		pnMenu.add(pnTabla);
//		
//		tbMenus = new JTable();
//		tbMenus.setModel(modeloMenus);
//		
//		tbMenus.addMouseListener(this);
//		pnTabla.setLayout(new GridLayout(1, 0, 0, 0));
//		panelScrollm.setViewportView(tbMenus);
//		
//		pnTabla.add(panelScrollm);
//		
//		tbPedidos = new JTable();
//		//tbPedidos.setModel(modeloSeleccion);
//		tbPedidos.setAutoCreateRowSorter(true);
//		tbPedidos.addMouseListener(this);
//		JScrollPane panelScrolln=new JScrollPane();
//		panelScrolln.setViewportView(tbPedidos);
//		pnTabla.add(panelScrolln);
//	
//		
//		pnTitulo = new JPanel();
//		pnTitulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
//		pnTitulo.setBackground(new Color(255, 160, 122));
//		pnTitulo.setForeground(new Color(105, 105, 105));
//		pnTitulo.setBounds(0, 0, 1047, 52);
//		pnMenu.add(pnTitulo);
//		pnTitulo.setLayout(null);
//		
//		lbTitulo = new JLabel("MENUS");
//		lbTitulo.setForeground(new Color(0, 0, 255));
//		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
//		lbTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
//		lbTitulo.setBounds(366, 5, 185, 32);
//		pnTitulo.add(lbTitulo);
//		
		cargarMenu();
		
		//Panel platos
		pnPlatos = new JPanel();
		pnPlatos.setBackground(Color.YELLOW);
		pnPlatos.setBounds(0, 45, 1047, 477);
		layeredPane.add(pnPlatos,BorderLayout.CENTER);
		pnPlatos.setLayout(null);
		
		//Fin prueba paneles
		pnButtons_1 = new JPanel();
		pnButtons_1.setBackground(new Color(0, 0, 0));
		pnButtons_1.setBounds(0, 0, 1047, 46);
		layeredPane.add(pnButtons_1);
		pnButtons_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		//BOTENES DEL MENU 
		btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(this);
		pnButtons_1.add(btnInicio);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(this);
		pnButtons_1.add(btnMenu);
	
		
		btnPlatos = new JButton("Platos");
		btnPlatos.addActionListener(this);
		pnButtons_1.add(btnPlatos);
		
		btnReserva = new JButton("Reservas"); ;
		btnReserva.addActionListener(this);
		pnButtons_1.add(btnReserva);
		
		btnConfig = new JButton("Configuracion");
		btnConfig.addActionListener(this);
		pnButtons_1.add(btnConfig);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		pnButtons_1.add(btnSalir);
	
		
		//FINAL BOTONES MENU
		
		
//		JScrollPane scrollPaneMenu = new JScrollPane();
//		scrollPaneMenu.setBounds(0, 0, 2, 2);
//		layeredPane.add(scrollPaneMenu);
//		
		GridBagLayout gbl_pnFech = new GridBagLayout();
		gbl_pnFech.columnWidths = new int[]{351, 48, 99, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnFech.rowHeights = new int[]{14, 0, 0};
		gbl_pnFech.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnFech.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnFech.setLayout(gbl_pnFech);
		
		JLabel lbUsuario = new JLabel();
		lbUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbUsuario.setText("Usuario:  "+gestion.nombreUsu+"  |  ");
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
		lbFecha.setText("Fecha :  "+LocalDate.now().toString());
		lbFecha.setBounds(669, 389, 106, 14);
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbFecha.gridx = 8;
		gbc_lbFecha.gridy = 1;
		pnFech.add(lbFecha, gbc_lbFecha);
		
	
		contentPane.setLayout(gl_contentPane);
		
		//POnemos todos los paneles a false menos Inicio
		pnMenu.setVisible(false);
		pnPlatos.setVisible(false);
		pnInicio.setVisible(true);
		pnReservas.setVisible(false);
		pnConfig.setVisible(false);
		//Cargo todos lo paneles para el control de los paneles
		listaPaneles.add(pnInicio);
		listaPaneles.add(pnMenu);
		
		JPanel pnInfoPedido = new JPanel();
		pnInfoPedido.setBackground(new Color(255, 228, 196));
		pnInfoPedido.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnInfoPedido.setBounds(0, 356, 1047, 52);
		pnMenu.add(pnInfoPedido);
		pnInfoPedido.setLayout(new GridLayout(1, 0, 0, 0));
		
		lbPedidos = new JLabel("Numero total pedidos: ");
		pnInfoPedido.add(lbPedidos);
		
		lblTotalAPagar = new JLabel("Total a pagar: ");
		pnInfoPedido.add(lblTotalAPagar);
		listaPaneles.add(pnPlatos);
		listaPaneles.add(pnReservas);
		listaPaneles.add(pnConfig);
//		Logeo log= new Logeo(this,true);
//		log.setVisible(true);
		
	}
	public void cargarMenu() {
		pnButtons = new JPanel();
		pnButtons.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnButtons.setBackground(new Color(0, 0, 0));
		pnButtons.setBounds(0, 406, 1047, 71);
		pnMenu.add(pnButtons);
		pnButtons.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		btnAddMenu = new JButton("Añadir Menu");
		btnAddMenu.addActionListener(this);
		pnButtons.add(btnAddMenu);
		btnAddMenu.addActionListener(this);
		
		
		btnRemovMenu = new JButton("Quitar Menu");
		btnRemovMenu.addActionListener(this);
		pnButtons.add(btnRemovMenu);
		
		if(gestion.tipoUsuario>1) {
			btnModificarMenu=new JButton("Modificar Menu");
			btnAddMenu.addActionListener(this);
			pnButtons.add(btnModificarMenu);
			
		}
		
		
		//Panel Menu
		pnTabla = new JPanel();
		pnTabla.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTabla.setBounds(0, 50, 1047, 307);
		
		JScrollPane panelScrollm= new JScrollPane();
		
		pnMenu.add(pnTabla);
		
		tbMenus = new JTable();
		tbMenus.setModel(modeloMenus);
		
		tbMenus.addMouseListener(this);
		pnTabla.setLayout(new GridLayout(1, 0, 0, 0));
		panelScrollm.setViewportView(tbMenus);
		
		pnTabla.add(panelScrollm);
		
		tbPedidos = new JTable();
		//tbPedidos.setModel(modeloSeleccion);
		tbPedidos.setAutoCreateRowSorter(true);
		tbPedidos.addMouseListener(this);
		JScrollPane panelScrolln=new JScrollPane();
		panelScrolln.setViewportView(tbPedidos);
		pnTabla.add(panelScrolln);
	
		
		pnTitulo = new JPanel();
		pnTitulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
		pnTitulo.setBackground(new Color(255, 160, 122));
		pnTitulo.setForeground(new Color(105, 105, 105));
		pnTitulo.setBounds(0, 0, 1047, 52);
		pnMenu.add(pnTitulo);
		pnTitulo.setLayout(null);
		
		lbTitulo = new JLabel("MENUS");
		lbTitulo.setForeground(new Color(0, 0, 255));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		lbTitulo.setBounds(366, 5, 185, 32);
		pnTitulo.add(lbTitulo);
	}
	boolean unaVez=true;
	private JLabel lblTotalAPagar;
	@Override
	public void actionPerformed(ActionEvent evnt) {
		if(control==0) {
			if(evnt.getSource()==btnSalir) {
				System.exit(0);
				
			}else if(evnt.getSource()==btnReserva) {
				reiniciarPanelInicio(pnReservas);
				
			}else if(evnt.getSource()==btnInicio) {
				reiniciarPanelInicio(pnInicio);
				
			}else if(evnt.getSource()==btnMenu) {
				reiniciarPanelInicio(pnMenu);
				
			}else if(evnt.getSource()==btnPlatos) {
				reiniciarPanelInicio(pnPlatos);
				
			}else if(evnt.getSource()==btnConfig) {
				reiniciarPanelInicio(pnConfig);
				
			//Panel Menu	
			}else if(evnt.getSource()==btnAddMenu) {
				
				precioTotal=gestion.cargarSeleccionMenu();
				
				
				modeloSeleccionM.setDatos(gestion.getListaSeleccionMenu());
				//tbPedidos.removeAll();
			
				modeloSeleccionM.fireTableDataChanged();
				lbPedidos.setText("Total pedidos: "+gestion.totalPedidosMenu());
				lblTotalAPagar.setText("Total a pagar: "+String.valueOf(precioTotal));;
				tbPedidos.setModel(modeloSeleccionM);
				
				System.out.println("ADDMENU");
				control++;
			}else if(evnt.getSource()==btnRemovMenu){
				if(gestion.esUltimo()) {
					//tbPedidos.removeAll();
					//tbPedidos.setVisible(false);
					System.out.println("ES ULTIMO");
				}else {
					
					precioTotal=gestion.removMenu(pedidoBorrar);
					lbPedidos.setText("Total pedidos: "+gestion.totalPedidosMenu());
					lblTotalAPagar.setText("Total a pagar: "+String.valueOf(precioTotal));;
					modeloSeleccionM.fireTableDataChanged();
					System.out.println("BORRO MENU");
				}
				
				
			}else  if(evnt.getSource()==btnModificarMenu) {
				
			}
		}else {
			control=0;
		}
		
		
		//Menu
		
	}
	public void reiniciarPanelInicio(JPanel pnVisible) {
		for(JPanel pn: listaPaneles) {
			if(pn==pnVisible) {
				pn.setVisible(true);
			}else {
				pn.setVisible(false);
			}
		}
	}

	//ACTION LISTENER
	@Override
	public void mouseClicked(MouseEvent evt) {
		System.out.println("Clicker");	
	
		if(evt.getSource()==tbMenus) {
			int i= tbMenus.getSelectedRow();
			gestion.menuSeleccionado(i);	
			System.out.println("ENTRAN en clicker");
			TIPO_PABLA=2;
		}
		if(evt.getSource()==tbPedidos) {
			int i= tbPedidos.getSelectedRow();
			pedidoBorrar=i;
			TIPO_PABLA=1;
			System.out.println("ENTRAN en clicker TBPEDIDOS");
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
		int i= tbMenus.getSelectedRow();
		gestion.menuSeleccionado(i);
		
		
	}
}
