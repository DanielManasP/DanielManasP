package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VistaMenus extends JInternalFrame {
	
	private Integer[] numComensales= {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
	private JTextField txtNombre;
	private JTextField txtTlf;
	private JTextField txtEmail;
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMenus frame = new VistaMenus();
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
	public VistaMenus() {
		setBounds(100, 100, 1115, 509);
		
		JPanel pnMenu = new JPanel();
		
		getContentPane().add(pnMenu, BorderLayout.CENTER);
		pnMenu.setLayout(null);
		
		JPanel pnButtonsMenu = new JPanel();
		pnButtonsMenu.setBounds(0, 219, 440, 52);
		pnMenu.add(pnButtonsMenu);
		pnButtonsMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		pnButtonsMenu.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("New button");
		pnButtonsMenu.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("New button");
		pnButtonsMenu.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		pnButtonsMenu.add(btnNewButton_3);
		
		JPanel pnFormularioConfig = new JPanel();
		pnFormularioConfig.setBounds(0, 45, 1047, 477);
		pnMenu.add(pnFormularioConfig);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 157, 440, 52);
		pnMenu.add(panel_1);
		panel_1.setLayout(new GridLayout(2,5,0,0));
		
		JDateChooser dateChooser = new JDateChooser();
		panel_1.add(dateChooser);
		
		ButtonGroup bG= new ButtonGroup();
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		panel_1.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		panel_1.add(rdbtnNewRadioButton);
		bG.add(rdbtnNewRadioButton);
		bG.add(rdbtnNewRadioButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<Integer>(numComensales));
		panel_1.add(comboBox);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		panel_1.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		panel_1.add(rdbtnNewRadioButton_3);
		
		

	}
}
