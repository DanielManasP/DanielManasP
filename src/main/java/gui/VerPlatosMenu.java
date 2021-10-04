package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controlador.Gestion;
import Modelo.Platos;
import Modelo.TablaPlatos;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VerPlatosMenu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTable tbPlatos;
	private Gestion gest;
	private int filaSeleccionada = 0;
	private TablaPlatos modeloPlatos;

	private ArrayList<JLabel> listaLbPlatos = new ArrayList<JLabel>();


	private int totalPlatos = 0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddMenu dialog = new AddMenu();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VerPlatosMenu(Frame frame, boolean modelo, Gestion gestion, String nombreMenu, String precioMenu, String idMenu) {
		super(frame, modelo);
		gest = gestion;
		modeloPlatos= new TablaPlatos();
		

		setLocationRelativeTo(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		setBounds(100, 100, 879, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel pnFormulario = new JPanel();
		pnFormulario.setBackground(new Color(176, 196, 222));
		pnFormulario.setBounds(0, 0, 879, 129);
		contentPanel.add(pnFormulario);
		GridBagLayout gbl_pnFormulario = new GridBagLayout();
		gbl_pnFormulario.columnWidths = new int[] { 42, 119, 28, 298, 0, 0 };
		gbl_pnFormulario.rowHeights = new int[] { 47, 0, 0, 0, 0 };
		gbl_pnFormulario.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnFormulario.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnFormulario.setLayout(gbl_pnFormulario);

		JLabel lbTitulo = new JLabel("MENU");
		lbTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		GridBagConstraints gbc_lbTitulo = new GridBagConstraints();
		gbc_lbTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lbTitulo.gridx = 3;
		gbc_lbTitulo.gridy = 0;
		pnFormulario.add(lbTitulo, gbc_lbTitulo);

		JLabel lbNombre = new JLabel("Nombre del menu :");
		lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_lbNombre = new GridBagConstraints();
		gbc_lbNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lbNombre.gridx = 1;
		gbc_lbNombre.gridy = 1;
		pnFormulario.add(lbNombre, gbc_lbNombre);

		txtNombre = new JTextField(nombreMenu);
		txtNombre.setEditable(false);
		txtNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		pnFormulario.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lbPrecio = new JLabel("Precio : ");
		lbPrecio.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.insets = new Insets(0, 0, 0, 5);
		gbc_lbPrecio.gridx = 1;
		gbc_lbPrecio.gridy = 3;
		pnFormulario.add(lbPrecio, gbc_lbPrecio);

		txtPrecio = new JTextField(precioMenu);
		txtPrecio.setEditable(false);
		txtPrecio.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.anchor = GridBagConstraints.WEST;
		gbc_txtPrecio.insets = new Insets(0, 0, 0, 5);
		gbc_txtPrecio.gridx = 3;
		gbc_txtPrecio.gridy = 3;
		pnFormulario.add(txtPrecio, gbc_txtPrecio);
		txtPrecio.setColumns(10);
		

		JPanel pnTabla = new JPanel();
		pnTabla.setBounds(0, 131, 879, 248);
		contentPanel.add(pnTabla);
		pnTabla.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnTabla.add(scrollPane);

		tbPlatos = new JTable();
		tbPlatos.setBackground(new Color(245, 222, 179));
		scrollPane.setViewportView(tbPlatos);
		
		modeloPlatos.setDatos(gest.getPlatosDelMenu(idMenu));
		// modeloPlatos.setDatos(gest.getListaPlatos());
		tbPlatos.setModel(modeloPlatos);

	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
				
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				buttonPane.setLayout(new GridLayout(0, 1, 0, 0));
				cancelButton.setBackground(new Color(255, 182, 193));
				cancelButton.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	


	private void cancelar() {
		this.dispose();
	}

}
