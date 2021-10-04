package gui;


import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import Controlador.Gestion;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DialogoReservClient extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Gestion gestion;
	private JDateChooser elegirFecha;
	private JComboBox<Integer> comboComensales;
	private JRadioButton rbPase1;
	private JRadioButton rbPase2;
	private JRadioButton rbTurno1;
	private JRadioButton rbTurno2;
	private ButtonGroup btnGroupPase;
	private ButtonGroup btnGroupTurno;
	
	//Informacion de los Pases y Turnos
	private JLabel lbInfo;
	private JLabel lbFechasTurnos;
								//Mañana Turnos 1 y 2					Tardes Turnos 1 y 2
	private String hora[][]= {{"13:00 a 14:00","14:30 a 15:30"},{"20:30 a 21:30","22:00 a 23:00"}};
	private final int TURNO_1=0;
	private final int TURNO_2=1;
	private final int PASE_1=0;
	private final int PASE_2=1;
	private int pase=0;
	private int turno=0;
	
	private final int MENSAJE_OK = 0;
	private final int MENSAJE_ERROR_MASDUNA = 1;
	private final int MENSAJE_ERROR_AFORO = 2;
	private final int MENSAJE_ERROR_FECHA = 3;
	private final int MENSAJE_ERROR_BBDD = 4;

	private Integer[] numComensales = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DialogoReservClient frame = new DialogoReservClient();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the dialog.
	 */
	public DialogoReservClient(Frame parent, boolean modal, Gestion gest) {
		super(parent, modal);
		gestion = gest;
		setBounds(100, 100, 526, 331);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(135, 206, 250));
		contentPanel.setBounds(0, 0, 526, 226);
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 41, 138, 149, 149, 75, 0 };
		gbl_contentPanel.rowHeights = new int[] { 89, 0, 0, 0, 13, 16, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		btnGroupTurno = new ButtonGroup();
		btnGroupPase = new ButtonGroup();
		
		
		{
			JLabel lbTitulo = new JLabel("RESERVAR");
			lbTitulo.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
			GridBagConstraints gbc_lbTitulo = new GridBagConstraints();
			gbc_lbTitulo.insets = new Insets(0, 0, 5, 5);
			gbc_lbTitulo.gridx = 2;
			gbc_lbTitulo.gridy = 0;
			contentPanel.add(lbTitulo, gbc_lbTitulo);
		}
		{
			JLabel lbFecha = new JLabel("Fecha reserva :");
			lbFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbFecha.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbFecha = new GridBagConstraints();
			gbc_lbFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lbFecha.fill = GridBagConstraints.BOTH;
			gbc_lbFecha.gridx = 1;
			gbc_lbFecha.gridy = 1;
			contentPanel.add(lbFecha, gbc_lbFecha);
		}
		{
			elegirFecha = new JDateChooser();
			GridBagConstraints gbc_elegirFecha = new GridBagConstraints();
			gbc_elegirFecha.insets = new Insets(0, 0, 5, 5);
			gbc_elegirFecha.fill = GridBagConstraints.BOTH;
			gbc_elegirFecha.gridx = 2;
			gbc_elegirFecha.gridy = 1;
			contentPanel.add(elegirFecha, gbc_elegirFecha);
		}
		{
			JLabel lbnumComensales = new JLabel("Numero comensales :");
			lbnumComensales.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbnumComensales.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbnumComensales = new GridBagConstraints();
			gbc_lbnumComensales.insets = new Insets(0, 0, 5, 5);
			gbc_lbnumComensales.gridx = 1;
			gbc_lbnumComensales.gridy = 2;
			contentPanel.add(lbnumComensales, gbc_lbnumComensales);
		}
		{
			comboComensales = new JComboBox<Integer>();
			comboComensales.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
			comboComensales.setBackground(new Color(176, 224, 230));
			GridBagConstraints gbc_comboComensales = new GridBagConstraints();
			gbc_comboComensales.insets = new Insets(0, 0, 5, 5);
			gbc_comboComensales.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboComensales.gridx = 2;
			gbc_comboComensales.gridy = 2;
			comboComensales.setModel(new DefaultComboBoxModel<Integer>(numComensales));
			comboComensales.setSelectedIndex(0);
			contentPanel.add(comboComensales, gbc_comboComensales);
		}
		{
			JLabel lbPase = new JLabel("Pase : ");
			lbPase.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbPase.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbPase = new GridBagConstraints();
			gbc_lbPase.insets = new Insets(0, 0, 5, 5);
			gbc_lbPase.gridx = 1;
			gbc_lbPase.gridy = 4;

			contentPanel.add(lbPase, gbc_lbPase);
		}
		{
			rbPase1 = new JRadioButton("Mañana");
			rbPase1.setBackground(new Color(135, 206, 250));
			GridBagConstraints gbc_rbPase1 = new GridBagConstraints();
			gbc_rbPase1.insets = new Insets(0, 0, 5, 5);
			gbc_rbPase1.gridx = 2;
			gbc_rbPase1.gridy = 4;
			rbPase1.setSelected(true);
			btnGroupPase.add(rbPase1);
			rbPase1.addActionListener(this);
			contentPanel.add(rbPase1, gbc_rbPase1);
		}
		{
			rbPase2 = new JRadioButton("Tarde");
			rbPase2.setBackground(new Color(135, 206, 250));
			GridBagConstraints gbc_rbPase2 = new GridBagConstraints();
			gbc_rbPase2.insets = new Insets(0, 0, 5, 5);
			gbc_rbPase2.gridx = 3;
			gbc_rbPase2.gridy = 4;
			btnGroupPase.add(rbPase2);
			rbPase2.addActionListener(this);
			contentPanel.add(rbPase2, gbc_rbPase2);
		}
		{
			JLabel lbTurno = new JLabel("Turno :");
			lbTurno.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lbTurno = new GridBagConstraints();
			gbc_lbTurno.insets = new Insets(0, 0, 0, 5);
			gbc_lbTurno.gridx = 1;
			gbc_lbTurno.gridy = 5;
			contentPanel.add(lbTurno, gbc_lbTurno);
		}
		{
			rbTurno1 = new JRadioButton("Primero");
			rbTurno1.setBackground(new Color(135, 206, 250));
			GridBagConstraints gbc_rbTurno1 = new GridBagConstraints();
			gbc_rbTurno1.insets = new Insets(0, 0, 0, 5);
			gbc_rbTurno1.gridx = 2;
			gbc_rbTurno1.gridy = 5;
			rbTurno1.setSelected(true);
			btnGroupTurno.add(rbTurno1);
			rbTurno1.addActionListener(this);
			contentPanel.add(rbTurno1, gbc_rbTurno1);
		}
		{
			rbTurno2 = new JRadioButton("Segundo");
			rbTurno2.setBackground(new Color(135, 206, 250));
			GridBagConstraints gbc_rbTurno2 = new GridBagConstraints();
			gbc_rbTurno2.insets = new Insets(0, 0, 0, 5);
			gbc_rbTurno2.gridx = 3;
			gbc_rbTurno2.gridy = 5;
			rbTurno2.addActionListener(this);
			btnGroupTurno.add(rbTurno2);
			contentPanel.add(rbTurno2, gbc_rbTurno2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 285, 526, 46);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton okButton = new JButton("RESERVAR");
				okButton.setBackground(new Color(152, 251, 152));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						compruebaCamposYfecha();
					}

				});
				buttonPane.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setBackground(new Color(250, 128, 114));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		JPanel pnInfo = new JPanel();
		pnInfo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(25, 25, 112), new Color(178, 34, 34), new Color(250, 128, 114), new Color(0, 255, 127)));
		pnInfo.setBackground(new Color(127, 255, 212));
		pnInfo.setBounds(0, 224, 526, 62);
		getContentPane().add(pnInfo);
		pnInfo.setLayout(new GridLayout(0, 1, 2, 0));

		lbFechasTurnos = new JLabel("Hora prevista de la llegada : "+hora[0][0]);
		lbFechasTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		lbFechasTurnos.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		pnInfo.add(lbFechasTurnos);

		lbInfo = new JLabel("LAS RESERVAS HAN DE CANCELARSE COMO TARDE EL DIA ANTES DE LA MISMA");
		lbInfo.setBackground(new Color(135, 206, 235));
		lbInfo.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		lbInfo.setForeground(new Color(220, 20, 60));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		pnInfo.add(lbInfo);

	}

	private void compruebaCamposYfecha() {
		
		int numComen = comboComensales.getSelectedIndex() + 1;

		Date fechahoy = new Date();
		Date fechaReser = elegirFecha.getDate();
		System.out.println(fechaReser);

		long timeInMilliSeconds = fechaReser.getTime();
		java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
		gestion.actualizaDatos();
		if (elegirFecha.getDate() != null) {
			if (fechaReser.compareTo(fechahoy) > 0) {
				if (gestion.compruebaSoloUnaReserva(date1, pase)) {
					if (gestion.compruebaAforo(date1, pase, turno, numComen)) {
						if (gestion.crearPedidos(date1)) {
							if (gestion.creaReserv(date1, numComen, pase, turno)) {
							

								mensajes(MENSAJE_OK);
								this.dispose();
							} else {
							
								mensajes(MENSAJE_ERROR_BBDD);
							}
						} else {
						
							mensajes(MENSAJE_ERROR_BBDD);
						}

					} else {
					
						mensajes(MENSAJE_ERROR_AFORO);
					}
				} else {
					
					mensajes(MENSAJE_ERROR_MASDUNA);
				}

			} else {
			
				mensajes(MENSAJE_ERROR_FECHA);
			}
		} else {
			mensajes(MENSAJE_ERROR_FECHA);
		
		}

	}

	private void mensajes(int mensaje) {
		switch (mensaje) {
		case MENSAJE_OK: // OK inserta cliente
			JOptionPane.showMessageDialog(this, "RESERVA CREADA", "RESERVA CREADO", JOptionPane.INFORMATION_MESSAGE);
			break;
		case MENSAJE_ERROR_MASDUNA:
			JOptionPane.showMessageDialog(this, "Solo se permite crear una reserva al dia",
					"ERROR EXCESO DE RESERVAS DIARIAS", JOptionPane.ERROR_MESSAGE);

			break;
		case MENSAJE_ERROR_AFORO:
			JOptionPane.showMessageDialog(this, "Aforo completo para esa fecha, pase y turno. Pruebe otra fecha",
					"ERROR AFORO", JOptionPane.ERROR_MESSAGE);
			break;
		case MENSAJE_ERROR_FECHA:
			JOptionPane.showMessageDialog(this, "La fecha tiene que ser mayor al dia de hoy", "ERROR FECHA",
					JOptionPane.ERROR_MESSAGE);
			break;
		case MENSAJE_ERROR_BBDD:
			JOptionPane.showMessageDialog(this, "Error al crear la reserva. Intetelo de nuevo", "ERROR CREAR",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

	private void cancelar() {
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	
		if(event.getSource()==rbPase1) {
			pase=PASE_1;	
		}else if(event.getSource()==rbPase2){
			pase=PASE_2;
		}else if(event.getSource()==rbTurno1){
			turno=TURNO_1;
		}else if(event.getSource()==rbTurno2){
			turno=TURNO_2;
		}
		
		actualizaLabelHoras(pase,turno);
		
	}

	private void actualizaLabelHoras(int pase, int turno) {
		lbFechasTurnos.setText("Hora prevista de la llegada : "+hora[pase][turno]);
		
	}
}
