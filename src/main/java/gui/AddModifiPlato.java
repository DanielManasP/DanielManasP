package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Gestion;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddModifiPlato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private Gestion gest;
	private String nombreP = "";
	private String precioP = "";
	private boolean modifica = false;
	private int soloUnPunto = 0;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddModifiPlato dialog = new AddModifiPlato();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddModifiPlato(Frame frame, boolean model, Gestion gestion, String nombrePlato, String precio) {
		super(frame, model);
		gest = gestion;
		nombreP = nombrePlato;
		precioP = precio;

		setBounds(100, 100, 605, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 359, 106, 25, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lbTitulo = new JLabel("PLATOS");
			lbTitulo.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
			GridBagConstraints gbc_lbTitulo = new GridBagConstraints();
			gbc_lbTitulo.insets = new Insets(0, 0, 5, 5);
			gbc_lbTitulo.gridx = 2;
			gbc_lbTitulo.gridy = 0;
			contentPanel.add(lbTitulo, gbc_lbTitulo);
		}
		{
			JLabel lbNombre = new JLabel("Nombre:");
			lbNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
			GridBagConstraints gbc_lbNombre = new GridBagConstraints();
			gbc_lbNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lbNombre.gridx = 1;
			gbc_lbNombre.gridy = 2;
			contentPanel.add(lbNombre, gbc_lbNombre);
		}
		{

		}
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 2;
		contentPanel.add(txtNombre, gbc_txtNombre);
		txtNombre.setText(nombrePlato);
		txtNombre.setColumns(10);
		if (!nombrePlato.isEmpty() && !precioP.isEmpty()) {
			modifica = true;
			txtNombre.setEditable(false);
		}
		{
			JLabel lbPrecio = new JLabel("Precio :");
			lbPrecio.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
			GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
			gbc_lbPrecio.anchor = GridBagConstraints.EAST;
			gbc_lbPrecio.insets = new Insets(0, 0, 0, 5);
			gbc_lbPrecio.gridx = 2;
			gbc_lbPrecio.gridy = 4;
			contentPanel.add(lbPrecio, gbc_lbPrecio);
		}
		{
			txtPrecio = new JTextField();
			GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
			gbc_txtPrecio.anchor = GridBagConstraints.EAST;
			gbc_txtPrecio.insets = new Insets(0, 0, 0, 5);
			gbc_txtPrecio.gridx = 3;
			gbc_txtPrecio.gridy = 4;
			contentPanel.add(txtPrecio, gbc_txtPrecio);
			txtPrecio.setText(String.valueOf(precioP));
			txtPrecio.setColumns(10);
			txtPrecio.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char caracter = e.getKeyChar();
					if (soloUnPunto == 1) {
						if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')
								&& caracter == '.') /* corresponde a BACK_SPACE */
						{
							e.consume(); // ignorar el evento de teclado
						}
					} else {
						if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')
								&& caracter != '.') /* corresponde a BACK_SPACE */
						{
							e.consume(); // ignorar el evento de teclado
						}
					}
					// Verificar si la tecla pulsada no es un digito
					if (txtPrecio.getText().length() > 3) {
						e.consume();
					}

					if (caracter == '.') {
						soloUnPunto = 1;
					}
				}
			});
		}
		{
			JLabel lbEuro = new JLabel("€");
			lbEuro.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lbEuro = new GridBagConstraints();
			gbc_lbEuro.anchor = GridBagConstraints.WEST;
			gbc_lbEuro.gridx = 4;
			gbc_lbEuro.gridy = 4;
			contentPanel.add(lbEuro, gbc_lbEuro);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton btnAddMod = new JButton("Añadir/Modificar plato");
				btnAddMod.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						operarPlato();
					}

				});
				btnAddMod.setActionCommand("OK");
				btnAddMod.setBackground(new Color(144, 238, 144));
				btnAddMod.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
				buttonPane.add(btnAddMod);
				getRootPane().setDefaultButton(btnAddMod);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}

				});
				btnCancelar.setBackground(new Color(255, 182, 193));
				btnCancelar.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	private void cancelar() {

		this.dispose();
	}

	private void operarPlato() {
		if (!txtNombre.getText().isEmpty() && !txtPrecio.getText().isEmpty()) {
			if (modifica) {// Modificamos el plato(solo precio)
				gest.modificaAddPlato(txtNombre.getText(), txtPrecio.getText(), modifica);
				JOptionPane.showMessageDialog(this,
						"Se ha actualizado el plato " + txtNombre.getText() + " al precio " + txtPrecio.getText(),
						"PLATO MODIFICADO", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} else {// Añadimos un plato nuevo
				gest.modificaAddPlato(txtNombre.getText(), txtPrecio.getText(), modifica);
				JOptionPane.showMessageDialog(this,
						"Se ha creado el plato " + txtNombre.getText() + " con precio " + txtPrecio.getText(),
						"PLATO CREADO", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacios", "CAMPOS VACIOS",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
