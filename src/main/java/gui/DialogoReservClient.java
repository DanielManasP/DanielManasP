package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Gestion;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
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

public class DialogoReservClient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Gestion gestion;
	private JDateChooser elegirFecha;
	private JComboBox comboComensales;
	private JRadioButton rbPase1;
	private JRadioButton rbPase2;
	private JRadioButton rbTurno1;
	private JRadioButton rbTurno2;
	
	private final int MENSAJE_OK=0;
	private final int MENSAJE_ERROR_MASDUNA=1;
	private final int MENSAJE_ERROR_AFORO=2;
	private final int MENSAJE_ERROR_FECHA=3;
	private final int MENSAJE_ERROR_BBDD=4;
	
	private Integer[] numComensales= {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
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
	public DialogoReservClient(Frame parent, boolean modal,Gestion gest) {
		super(parent,modal);
		gestion=gest;
		setBounds(100, 100, 526, 331);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 526, 287);
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		 setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	     setUndecorated(true);
		
		
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{64, 0, 149, 149, 75, 0};
		gbl_contentPanel.rowHeights = new int[]{89, 0, 0, 0, 13, 16, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		ButtonGroup btnGroupTurno= new ButtonGroup();
		ButtonGroup btnGroupPase= new ButtonGroup();
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
			lbnumComensales.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbnumComensales = new GridBagConstraints();
			gbc_lbnumComensales.insets = new Insets(0, 0, 5, 5);
			gbc_lbnumComensales.gridx = 1;
			gbc_lbnumComensales.gridy = 2;
			contentPanel.add(lbnumComensales, gbc_lbnumComensales);
		}
		{
			comboComensales = new JComboBox();
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
			lbPase.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbPase = new GridBagConstraints();
			gbc_lbPase.insets = new Insets(0, 0, 5, 5);
			gbc_lbPase.gridx = 1;
			gbc_lbPase.gridy = 4;
			
			contentPanel.add(lbPase, gbc_lbPase);
		}
		{
			rbPase1 = new JRadioButton("Mañana");
			GridBagConstraints gbc_rbPase1 = new GridBagConstraints();
			gbc_rbPase1.insets = new Insets(0, 0, 5, 5);
			gbc_rbPase1.gridx = 2;
			gbc_rbPase1.gridy = 4;
			rbPase1.setSelected(true);
			btnGroupPase.add(rbPase1);
			contentPanel.add(rbPase1, gbc_rbPase1);
		}
		{
			rbPase2 = new JRadioButton("Tarde");
			GridBagConstraints gbc_rbPase2 = new GridBagConstraints();
			gbc_rbPase2.insets = new Insets(0, 0, 5, 5);
			gbc_rbPase2.gridx = 3;
			gbc_rbPase2.gridy = 4;
			btnGroupPase.add(rbPase2);
			contentPanel.add(rbPase2, gbc_rbPase2);
		}
		{
			JLabel lbTurno = new JLabel("Turno :");
			GridBagConstraints gbc_lbTurno = new GridBagConstraints();
			gbc_lbTurno.insets = new Insets(0, 0, 5, 5);
			gbc_lbTurno.gridx = 1;
			gbc_lbTurno.gridy = 5;
			contentPanel.add(lbTurno, gbc_lbTurno);
		}
		{
			rbTurno1 = new JRadioButton("Primero");
			GridBagConstraints gbc_rbTurno1 = new GridBagConstraints();
			gbc_rbTurno1.insets = new Insets(0, 0, 5, 5);
			gbc_rbTurno1.gridx = 2;
			gbc_rbTurno1.gridy = 5;
			rbTurno1.setSelected(true);
			btnGroupTurno.add(rbTurno1);
			contentPanel.add(rbTurno1, gbc_rbTurno1);
		}
		{
			rbTurno2 = new JRadioButton("Segundo");
			GridBagConstraints gbc_rbTurno2 = new GridBagConstraints();
			gbc_rbTurno2.insets = new Insets(0, 0, 5, 5);
			gbc_rbTurno2.gridx = 3;
			gbc_rbTurno2.gridy = 5;
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
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	
	}
	private void compruebaCamposYfecha() {
		boolean error=false;
		int pase=4;
		int turno=2;
		int numComen=comboComensales.getSelectedIndex()+1;
		if(rbPase1.isSelected()) {
			pase=0;
		}else {
			pase=1;
		}
		if(rbTurno1.isSelected()) {
			turno=0;
		}else {
			turno=1;
		}
		Date fechahoy=new Date();
		Date fechaReser=elegirFecha.getDate();
		System.out.println(fechaReser);

        long timeInMilliSeconds = fechaReser.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
		
		if(elegirFecha.getDate()!=null) {
			if(fechaReser.compareTo(fechahoy)>0) {
				if(gestion.compruebaSoloUna(date1
						, pase)) {
					if(gestion.compruebaAforo(date1
							, pase, turno, numComen)) {
						if(gestion.crearPedidos(date1)) {
							if(gestion.creaReserv(date1
									, numComen, pase, turno)) {
								error=true;
								
								mensajes(MENSAJE_OK);
								this.dispose();
							}else {
								error=false;
								mensajes(MENSAJE_ERROR_BBDD);
							}
						}else {
							error=false;
							mensajes(MENSAJE_ERROR_BBDD);
						}
						
					}else {
						error=false;
						mensajes(MENSAJE_ERROR_AFORO);
					}
				}else {
					error=false;
					mensajes(MENSAJE_ERROR_MASDUNA);
				}
				
			}else {
				error=false;
				mensajes(MENSAJE_ERROR_FECHA);
			}		
		}else {
			mensajes(MENSAJE_ERROR_FECHA);
			error=false;
		}
		
		
	}

	private void mensajes(int mensaje) {
		switch (mensaje) {
		case MENSAJE_OK: //OK inserta cliente
			JOptionPane.showMessageDialog(this, "RESERVA CREADA",
                    "RESERVA CREADO", JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(this, "La fecha tiene que ser mayor al dia de hoy",
                    "ERROR FECHA", JOptionPane.ERROR_MESSAGE);
			break;
		case MENSAJE_ERROR_BBDD:
			JOptionPane.showMessageDialog(this, "Error al crear la reserva. Intetelo de nuevo",
                    "ERROR CREAR", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
	private void cancelar() {
		this.dispose();
	}
	
}
