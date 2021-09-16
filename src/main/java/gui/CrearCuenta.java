package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;



import Controlador.Gestion;
import DAOs.Conexion;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class CrearCuenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JTextField txtPass;
	private JTextField txtTlf;
	private Gestion gestion;
	private Connection conexion=Conexion.conexionmysql();
	private JTextField txtNombre;
	private JTextField txtConfirmaPass;
	
	private final int MENSAJE_OK=0;
	private final int MENSAJE_ERROR_BBDD=1;
	private final int MENSAJE_ERROR_MAIL=2;
	private final int MENSAJE_ERROR_PASS1=3;
	private final int MENSAJE_ERROR_PASS2=4;
	
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public CrearCuenta(Dialog parent, boolean modal) {
		super(parent,modal);
		gestion= new Gestion();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lbNombre = new JLabel("Nombre :");
			GridBagConstraints gbc_lbNombre = new GridBagConstraints();
			gbc_lbNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lbNombre.gridx = 1;
			gbc_lbNombre.gridy = 1;
			contentPanel.add(lbNombre, gbc_lbNombre);
		}
		{
			txtNombre = new JTextField();
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 3;
			gbc_txtNombre.gridy = 1;
			txtNombre.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e)
				   {
				      char caracter = e.getKeyChar();

				      // Verificar si la tecla pulsada no es un digito
				      if(((caracter < '0') ||
				         (caracter > '9')) &&
				         (caracter != '\b' /*corresponde a BACK_SPACE*/))
				      {
				          
				      }else {
				    	  e.consume();
				      }
				   }
			});
			contentPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lbEmail = new JLabel("Email :");
			GridBagConstraints gbc_lbEmail = new GridBagConstraints();
			gbc_lbEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lbEmail.gridx = 1;
			gbc_lbEmail.gridy = 2;
			contentPanel.add(lbEmail, gbc_lbEmail);
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridx = 3;
			gbc_txtEmail.gridy = 2;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JLabel lbPass = new JLabel("Password :");
			GridBagConstraints gbc_lbPass = new GridBagConstraints();
			gbc_lbPass.insets = new Insets(0, 0, 5, 5);
			gbc_lbPass.gridx = 1;
			gbc_lbPass.gridy = 3;
			contentPanel.add(lbPass, gbc_lbPass);
		}
		{
			txtPass = new JPasswordField();
			GridBagConstraints gbc_txtPass = new GridBagConstraints();
			gbc_txtPass.insets = new Insets(0, 0, 5, 5);
			gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPass.gridx = 3;
			gbc_txtPass.gridy = 3;
			contentPanel.add(txtPass, gbc_txtPass);
			txtPass.setColumns(10);
		}
		{
			JLabel lbConfirma = new JLabel("Confirma pass :");
			GridBagConstraints gbc_lbConfirma = new GridBagConstraints();
			gbc_lbConfirma.insets = new Insets(0, 0, 5, 5);
			gbc_lbConfirma.gridx = 1;
			gbc_lbConfirma.gridy = 4;
			contentPanel.add(lbConfirma, gbc_lbConfirma);
		}
		{
			txtConfirmaPass = new JPasswordField();
			GridBagConstraints gbc_txtConfirmaPass = new GridBagConstraints();
			gbc_txtConfirmaPass.insets = new Insets(0, 0, 5, 5);
			gbc_txtConfirmaPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtConfirmaPass.gridx = 3;
			gbc_txtConfirmaPass.gridy = 4;
			contentPanel.add(txtConfirmaPass, gbc_txtConfirmaPass);
			txtConfirmaPass.setColumns(10);
		}
		{
			JLabel lbTlf = new JLabel("Tlf :");
			GridBagConstraints gbc_lbTlf = new GridBagConstraints();
			gbc_lbTlf.insets = new Insets(0, 0, 0, 5);
			gbc_lbTlf.gridx = 1;
			gbc_lbTlf.gridy = 5;
			contentPanel.add(lbTlf, gbc_lbTlf);
		}
		{
			txtTlf = new JTextField();
			GridBagConstraints gbc_txtTlf = new GridBagConstraints();
			gbc_txtTlf.anchor = GridBagConstraints.NORTH;
			gbc_txtTlf.insets = new Insets(0, 0, 0, 5);
			gbc_txtTlf.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTlf.gridx = 3;
			gbc_txtTlf.gridy = 5;
	
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
			contentPanel.add(txtTlf, gbc_txtTlf);
			txtTlf.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
							crearCliente();
					}
		
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancela();
					}

					
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	private void crearCliente() {
		
		if(txtEmail.getText().isEmpty() || txtPass.getText().isEmpty() || txtTlf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ningun campo puede estar vacio, compruebe que los campos : /n "
					+ " Email, Password y Tlf estan completos",
                    "CAMPOS VACIOS", JOptionPane.WARNING_MESSAGE);
		}else {
			if(esMail(txtEmail.getText())) {
				if(compruebaPass(txtPass.getText())) {
					if(txtPass.getText().equals(txtConfirmaPass.getText())){
						if(gestion.crearCliente(txtNombre.getText(),txtEmail.getText(), txtPass.getText(), txtTlf.getText())) {
							
							mensajes(MENSAJE_OK);
							this.dispose();
						}else {
							mensajes(MENSAJE_ERROR_BBDD);
							txtEmail.setText("");
							txtNombre.setText("");
							txtPass.setText("");
							txtTlf.setText("");
						}
					}else {
						mensajes(MENSAJE_ERROR_PASS2);
						txtPass.setText("");
						txtConfirmaPass.setText("");
						}
				}else {
					mensajes(MENSAJE_ERROR_PASS1);
					txtPass.setText("");
					txtConfirmaPass.setText("");
				}	
			}else {
				txtEmail.setBackground(Color.RED);
				txtEmail.setForeground(Color.WHITE);
				mensajes(MENSAJE_ERROR_MAIL);
				txtEmail.setText("");
				txtEmail.setBackground(Color.WHITE);
				
			}
			
		}		
	}
	private void cancela() {
		this.dispose();
		
	}
	private void mensajes(int mensaje) {
		switch (mensaje) {
		case 0: //OK inserta cliente
			JOptionPane.showMessageDialog(this, "Usuario creado con exito",
                    "USUARIO CREADO", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 1://El usuario ya existe
			JOptionPane.showMessageDialog(this, "El usuario ya existe, no se ha podido registrar",
                    "ERROR AL CREAR", JOptionPane.ERROR_MESSAGE);
			txtEmail.setText("");
			txtNombre.setText("");
			txtEmail.setBackground(Color.WHITE);
			txtPass.setText("");
			txtTlf.setText("");
			break;
		case 2:
			//El email no es correcto
			txtEmail.setBackground(Color.RED);
			txtEmail.setForeground(Color.WHITE);
			JOptionPane.showMessageDialog(this, "Introduzca un correo electronico valido. Ejem: xxx@gmail.com",
                    "ERROR EMAIL", JOptionPane.ERROR_MESSAGE);
			txtEmail.setText("");
			txtEmail.setBackground(Color.WHITE);
			break;
		case 3://EL PASSWORD debe ser mayor a 8 caracteres
			JOptionPane.showMessageDialog(this, "La contraseña tiene que tener al menos 8 caracteres",
                    "ERROR PASS", JOptionPane.ERROR_MESSAGE);
			break;
		case 4://PASSWORD no coincide
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden",
                    "ERROR PASS", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}
	//Password no menor a 8
	private boolean compruebaPass(String pass) {
		boolean correcto=false;
		if(pass.length()>7) {
			correcto=true;
		}
		return correcto;
	}
	private boolean esMail (String mail) {
			   // Patrón para validar el email
	  Pattern pattern = Pattern
	          .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                  + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	  // El email a validar
	 	
	  Matcher mather = pattern.matcher(mail);
	
	  if (mather.find() == true) {
	      return true;
	  } else {
	     return false;
	  }
	}

}
