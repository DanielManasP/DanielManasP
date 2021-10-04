package gui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controlador.Gestion;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

public class Logeo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField txtEmail;
	private JPasswordField textPass;

	private Gestion gestion;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Logeo(Frame parent, boolean modal,Gestion gest) {
		super(parent,modal);
		 ImageIcon icon = new ImageIcon(getClass().getResource("/gui/resources/imagenInicioSes.jpg"));
	     final Image image = icon.getImage();
	       
		setIconImage(image);
		gestion= gest;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPanel= new JPanel() {
			  public void paintComponent(Graphics g){
	                g.drawImage(image, 0,0,getWidth(),getHeight(), this);
	            }
		};
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	    setUndecorated(true);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{81, 83, 12, 191, 32, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{38, 0, 23, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			JLabel lbInicio = new JLabel("INICIO DE SESION");
			lbInicio.setForeground(new Color(224, 255, 255));
			lbInicio.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
			GridBagConstraints gbc_lbInicio = new GridBagConstraints();
			gbc_lbInicio.insets = new Insets(0, 0, 5, 5);
			gbc_lbInicio.gridx = 3;
			gbc_lbInicio.gridy = 1;
			contentPanel.add(lbInicio, gbc_lbInicio);
		}
		{
			JLabel email = new JLabel("Usuario :");
			email.setForeground(new Color(224, 255, 255));
			email.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
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
			txtEmail.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
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
			pass.setForeground(new Color(224, 255, 255));
			pass.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
			GridBagConstraints gbc_pass = new GridBagConstraints();
			gbc_pass.insets = new Insets(0, 0, 5, 5);
			gbc_pass.gridx = 1;
			gbc_pass.gridy = 5;
			contentPanel.add(pass, gbc_pass);
		}
		{
			textPass = new JPasswordField();
			textPass.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
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
			lblNewLabel.setForeground(new Color(224, 255, 255));
			lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 3;
			gbc_lblNewLabel.gridy = 7;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		JButton btnNewButton = new JButton("Crear cuenta");
		btnNewButton.setFont(new Font("Tw Cen MT", Font.BOLD, 11));
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Iniciar sesion");
				okButton.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						compruebaCredenciales();
					}

				
				});
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
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
	}

}
