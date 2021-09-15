package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controlador.Gestion;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class Logeo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JPasswordField textPass;
	private String em;
	private String pas;
	private Gestion gestion;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Logeo(Frame parent, boolean modal,Gestion gest) {
		super(parent,modal);
		gestion= gest;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		 setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	     setUndecorated(true);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{43, 64, 0, 207, 32, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{38, 0, 23, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lbInicio = new JLabel("INICIO DE SESION");
			GridBagConstraints gbc_lbInicio = new GridBagConstraints();
			gbc_lbInicio.insets = new Insets(0, 0, 5, 5);
			gbc_lbInicio.gridx = 3;
			gbc_lbInicio.gridy = 1;
			contentPanel.add(lbInicio, gbc_lbInicio);
		}
		{
			JLabel email = new JLabel("Usuario :");
			GridBagConstraints gbc_email = new GridBagConstraints();
			gbc_email.insets = new Insets(0, 0, 5, 5);
			gbc_email.gridx = 1;
			gbc_email.gridy = 3;
			contentPanel.add(email, gbc_email);
		}
		{
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_textEm = new GridBagConstraints();
			gbc_textEm.insets = new Insets(0, 0, 5, 5);
			gbc_textEm.fill = GridBagConstraints.HORIZONTAL;
			gbc_textEm.gridx = 3;
			gbc_textEm.gridy = 3;
			contentPanel.add(txtEmail, gbc_textEm);
			txtEmail.setColumns(10);
		}
		{
			JLabel pass = new JLabel("Password :");
			GridBagConstraints gbc_pass = new GridBagConstraints();
			gbc_pass.insets = new Insets(0, 0, 5, 5);
			gbc_pass.gridx = 1;
			gbc_pass.gridy = 5;
			contentPanel.add(pass, gbc_pass);
		}
		{
			textPass = new JPasswordField();
			GridBagConstraints gbc_textPass = new GridBagConstraints();
			gbc_textPass.anchor = GridBagConstraints.NORTH;
			gbc_textPass.insets = new Insets(0, 0, 5, 5);
			gbc_textPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_textPass.gridx = 3;
			gbc_textPass.gridy = 5;
			contentPanel.add(textPass, gbc_textPass);
			textPass.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("¿No tienes cuenta? ");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 3;
			gbc_lblNewLabel.gridy = 7;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		JButton btnNewButton = new JButton("Crear cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtEmail.setText("");
				textPass.setText("");
				crearCuenta();
			}

		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 8;
		contentPanel.add(btnNewButton, gbc_btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						compruebaCredenciales();
					}

				
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void crearCuenta() {
		
		CrearCuenta dialog = new CrearCuenta(this,true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
	}
	private void compruebaCredenciales() {
		if(gestion.inicioSesion(txtEmail.getText(), textPass.getText())) {
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "El usuario o contraseña no coinciden",
                    "CONSTRASEÑA INCORRECTA", JOptionPane.ERROR_MESSAGE);
			txtEmail.setText("");
			textPass.setText("");
			
		}
//		try {
//			Connection conexion =
//					DriverManager.getConnection("jdbc:mysql://192.168.1.78:3306/pruebas",
//					"pruebas", "AsD123");
//			String sql="select * from cliente where email=? and pass=?;";
//			
//			PreparedStatement sentencia=conexion.prepareStatement(sql);
//			sentencia.setString(1, textEm.getText());
//			sentencia.setString(2, textPass.getText());
//			ResultSet res= sentencia.executeQuery();
//			if(res.next()) {
//				this.dispose();
//			}else {
//				 JOptionPane.showMessageDialog(this, "Compruebe su constraseña, usuario o contraseÃ±a no coinciden",
//	                        "CONSTRASEÃ‘A INCORRECTA", JOptionPane.ERROR_MESSAGE);
//			}	
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
	}

}
