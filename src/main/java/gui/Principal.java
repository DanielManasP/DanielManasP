package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.joda.time.LocalDate;
import org.neodatis.btree.exception.BTreeNodeValidationException;

import Controlador.Gestion;
import Controlador.tablaMenus;

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
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;


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
	private JPanel pnMenu;
	private JPanel pnButtons;
	private JPanel pnInicio;
	private JButton btnAddMenu;
	private JTable tbMenus;
	private JPanel pnTabla;
	private JButton btnRemovMenu;
	private JButton btnModificarMenu;
	private JScrollPane scrollPaneMenu;
	
	private ArrayList<JPanel> listaPaneles=new ArrayList<JPanel>();
	//Componentes Platos
	private JPanel pnPlatos;
	
	//Componentes Reservas
	private JPanel pnReservas;
	
	
	//Componentes Configuracion
	private JPanel pnConfig;
	
	//Tablas
	private tablaMenus modeloMenus;
	private JPanel pnTitulo;
	private JLabel lbTitulo;
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
		modeloMenus.setDatos(gestion.getListaMenus());
		
//		Logeo log= new Logeo(this,true,gestion);
//		log.setVisible(true);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 527);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		layeredPane = new JLayeredPane();
		
		pnFech = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnFech, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(pnFech, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
		);
		//Paneles de los botones
		//Pruebas paneles
		pnMenu = new JPanel();
		pnMenu.setBackground(Color.RED);
		pnMenu.setBounds(0, 45, 854, 383);
		//pnMenu.setVisible(false);
		layeredPane.add(pnMenu,BorderLayout.CENTER);
		pnMenu.setLayout(null);
		
		//Panel Inicio
		pnInicio = new JPanel();
		pnInicio.setBounds(0, 45, 854, 383);
		pnInicio.setBackground(Color.BLUE);
		layeredPane.add(pnInicio,BorderLayout.CENTER);
		pnInicio.setLayout(null);
		
		//Panel Reservas
		pnReservas= new JPanel();
		pnReservas.setBounds(0, 45, 854, 383);
		pnReservas.setBackground(Color.RED);
		layeredPane.add(pnReservas,BorderLayout.CENTER);
		pnReservas.setLayout(null);
		
		//Panel Configuracion
		pnConfig= new JPanel();
		pnConfig.setBounds(0, 45, 854, 383);
		pnConfig.setBackground(Color.BLACK);
		layeredPane.add(pnConfig,BorderLayout.CENTER);
		pnConfig.setLayout(null);
		
		pnButtons = new JPanel();
		pnButtons.setBounds(0, 303, 854, 48);
		pnMenu.add(pnButtons);
		pnButtons.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		btnAddMenu = new JButton("Añadir Menu");
		btnAddMenu.addActionListener(this);
		pnButtons.add(btnAddMenu);
		
		btnRemovMenu = new JButton("Quitar Menu");
		btnAddMenu.addActionListener(this);
		pnButtons.add(btnRemovMenu);
		
		if(gestion.tipoUsuario>1) {
			btnModificarMenu=new JButton("Modificar Menu");
			btnAddMenu.addActionListener(this);
			pnButtons.add(btnModificarMenu);
			
		}
		
		
		//Panel Menu
		pnTabla = new JPanel();
		pnTabla.setBounds(0, 50, 854, 252);
		pnMenu.add(pnTabla);
		pnTabla.setLayout(null);
		
		tbMenus = new JTable();
		tbMenus.setBounds(0, 0, 854, 280);
		tbMenus.setModel(modeloMenus);
		
		tbMenus.addMouseListener(this);
		
		pnTabla.add(tbMenus);
	
		
		pnTitulo = new JPanel();
		pnTitulo.setBounds(0, 0, 854, 48);
		pnMenu.add(pnTitulo);
		pnTitulo.setLayout(null);
		
		lbTitulo = new JLabel("MENU");
		lbTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		lbTitulo.setBounds(267, 5, 284, 32);
		pnTitulo.add(lbTitulo);
		
		//Panel platos
		pnPlatos = new JPanel();
		pnPlatos.setBackground(Color.YELLOW);
		pnPlatos.setBounds(0, 45, 854, 383);
		layeredPane.add(pnPlatos,BorderLayout.CENTER);
		pnPlatos.setLayout(null);
		
		//Fin prueba paneles
		pnButtons = new JPanel();
		pnButtons.setBounds(0, 0, 854, 46);
		layeredPane.add(pnButtons);
		pnButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		//BOTENES DEL MENU 
		btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(this);
		pnButtons.add(btnInicio);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(this);
		pnButtons.add(btnMenu);
	
		
		btnPlatos = new JButton("Platos");
		btnPlatos.addActionListener(this);
		pnButtons.add(btnPlatos);
		
		btnReserva = new JButton("Reservas"); ;
		btnReserva.addActionListener(this);
		pnButtons.add(btnReserva);
		
		btnConfig = new JButton("Configuracion");
		btnConfig.addActionListener(this);
		pnButtons.add(btnConfig);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		pnButtons.add(btnSalir);
	
		
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
		listaPaneles.add(pnPlatos);
		listaPaneles.add(pnReservas);
		listaPaneles.add(pnConfig);
//		Logeo log= new Logeo(this,true);
//		log.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evnt) {
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
			
		}else if(evnt.getSource()==btnRemovMenu){
			
		}else  if(evnt.getSource()==btnModificarMenu) {
			
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
