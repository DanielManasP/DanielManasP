package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controlador.Gestion;
import Modelo.MiRender;
import Modelo.Platos;
import Modelo.TablaPlatos;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class AddMenuEmpleado extends JDialog implements MouseListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTable tbPlatos;
	private Gestion gest;
	private int contadorClick = 0;
	private int filaSeleccionada = 0;
	private DefaultTableCellRenderer mirender;
	private TablaPlatos modeloPlatos;
	private JPanel pnLbPlatos;

	private JLabel lbInfo;
	private JLabel plato1;
	private JLabel plato2;
	private JLabel plato3;
	private JLabel plato4;
	private ArrayList<JLabel> listaLbPlatos = new ArrayList<JLabel>();

	private int platoSeleccionado = -1;
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
	public AddMenuEmpleado(Frame frame, boolean modelo, Gestion gestion, TablaPlatos modeloPlatos) {
		super(frame, modelo);
		gest = gestion;
		this.modeloPlatos = modeloPlatos;

		mirender = new MiRender();

		inicializaLbPlatos(plato1);
		inicializaLbPlatos(plato2);
		inicializaLbPlatos(plato3);
		inicializaLbPlatos(plato4);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		setBounds(100, 100, 879, 586);
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

		JLabel lbTitulo = new JLabel("CREAR MENU");
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

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		pnFormulario.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		lbInfo = new JLabel("*Para añadir un plato haga doble click en la lista ");
		lbInfo.setForeground(Color.RED);
		lbInfo.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		GridBagConstraints gbc_lbInfo = new GridBagConstraints();
		gbc_lbInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lbInfo.gridx = 4;
		gbc_lbInfo.gridy = 1;
		pnFormulario.add(lbInfo, gbc_lbInfo);

		JLabel lbPrecio = new JLabel("Precio : ");
		lbPrecio.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.insets = new Insets(0, 0, 0, 5);
		gbc_lbPrecio.gridx = 1;
		gbc_lbPrecio.gridy = 3;
		pnFormulario.add(lbPrecio, gbc_lbPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.anchor = GridBagConstraints.WEST;
		gbc_txtPrecio.insets = new Insets(0, 0, 0, 5);
		gbc_txtPrecio.gridx = 3;
		gbc_txtPrecio.gridy = 3;
		pnFormulario.add(txtPrecio, gbc_txtPrecio);
		txtPrecio.setColumns(10);
		txtPrecio.addKeyListener(new KeyAdapter() {
			private int soloUnPunto = 0;

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

		JPanel pnTabla = new JPanel();
		pnTabla.setBounds(0, 131, 879, 248);
		contentPanel.add(pnTabla);
		pnTabla.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnTabla.add(scrollPane);

		tbPlatos = new JTable();
		tbPlatos.setBackground(new Color(245, 222, 179));
		scrollPane.setViewportView(tbPlatos);
		tbPlatos.addMouseListener(this);
		tbPlatos.setDefaultRenderer(Platos.class, mirender);
		// modeloPlatos.setDatos(gest.getListaPlatos());
		tbPlatos.setModel(modeloPlatos);

		pnLbPlatos = new JPanel();
		pnLbPlatos.setBackground(new Color(176, 196, 222));
		pnLbPlatos.setBounds(0, 378, 879, 136);
		contentPanel.add(pnLbPlatos);
		pnLbPlatos.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel pnBtnOper = new JPanel();
		pnBtnOper.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnBtnOper.setBounds(0, 513, 879, 47);
		contentPanel.add(pnBtnOper);
		pnBtnOper.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnQuitarPlato = new JButton("Quitar plato");
		btnQuitarPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarPlato();
			}

		});
		btnQuitarPlato.setBackground(new Color(192, 192, 192));
		btnQuitarPlato.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		pnBtnOper.add(btnQuitarPlato);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton btnCrear = new JButton("Crear menu");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						insertarMenu();
					}

				});
				btnCrear.setBackground(new Color(144, 238, 144));
				btnCrear.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}

				});
				cancelButton.setBackground(new Color(255, 182, 193));
				cancelButton.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void insertarMenu() {
		if (!txtNombre.getText().isEmpty() && !txtPrecio.getText().isEmpty()) {
			if (gest.getListaSeleccionMenu().size() >= 2) {
				if (!gest.comprubeMenuExiste(txtNombre.getText())) {
					if (gest.crearMenu(txtNombre.getText(), Float.parseFloat(txtPrecio.getText()))) {
						JOptionPane.showMessageDialog(this, "Menu " + txtNombre.getText() + " ha sido creado",
								"MAXIMO DE PLATOS", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(this,
								"Menu " + txtNombre.getText() + " no ha sido creado. Intentelo de nuevo",
								"MAXIMO DE PLATOS", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"Menu " + txtNombre.getText() + " ya existe con ese nombre, por favor cambie le nombre",
							"MAXIMO DE PLATOS", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Hay que escoger mas de un plato",
						"ERROR PLATOS ELEGIDOS INSUFICIENTEs", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(this, "Los campos estan vacios ", "CAMPOS VACIOS", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void quitarPlato() {
		if (gest.getListaSeleccionMenu().size() > 0) {
			String platoUltimo = null;
			for (int i = 0; i < listaLbPlatos.size(); i++) {
				if (listaLbPlatos.get(i).getText().isEmpty()) {
					platoUltimo = listaLbPlatos.get(i - 1).getText();
					gest.quitaPlatoMenuEmpleado(platoUltimo);
					listaLbPlatos.get(i - 1).setText("");
					System.out.println("ENTRA PRIMERO BUVLE");
					break;
				} else {
					if (!listaLbPlatos.get(listaLbPlatos.size() - 1).getText().isEmpty()) {
						platoUltimo = listaLbPlatos.get(listaLbPlatos.size() - 1).getText();
						gest.quitaPlatoMenuEmpleado(platoUltimo);
						listaLbPlatos.get(listaLbPlatos.size() - 1).setText("");
						System.out.println("ENTRA Segundo BUVLE");
						break;
					}
				}
			}
			txtPrecio.setText(String.valueOf(gest.precioT));
			totalPlatos--;
			System.out.println(totalPlatos + "  LABEL QUE VA");
		}

//	

	}

	private void inicializaLbPlatos(JLabel plato) {
		plato = new JLabel();
		plato.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		plato.setHorizontalAlignment(SwingConstants.CENTER);
		// plato.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null,
		// null));
		// plato.addMouseListener(this);
		listaLbPlatos.add(plato);

	}

	@Override
	public void mouseClicked(MouseEvent event) {
		System.out.println("Clickea");
		int i = tbPlatos.getSelectedRow();
		float precio = 0.0f;
		if (event.getSource() == tbPlatos) {
			JLabel plato = null;
			if (contadorClick == 1 && filaSeleccionada == tbPlatos.getSelectedRow()) {
				if (!gest.yaSeleccionado(i)) {
					if (totalPlatos <= 3) {
						lbInfo.setVisible(false);
						precio = gest.cargarSeleccion();
						txtPrecio.setText(String.valueOf(precio - precio * 0.1));
						System.out.println("JLABEL DEL PLATo " + totalPlatos);
						plato = listaLbPlatos.get(totalPlatos);
						pnLbPlatos.add(plato);
						plato.setText(gest.getListaPlatos().get(tbPlatos.getSelectedRow()).getNombre());

						totalPlatos++;

						System.out.println("Carga el jlabel");
					} else {
						JOptionPane.showMessageDialog(this, "El numero maximo de platos por menú es 4",
								"MAXIMO DE PLATOS", JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(this, "El plato ya esta seleccionado", "PLATO YA SELECCIONADO",
							JOptionPane.WARNING_MESSAGE);
				}
				contadorClick = 0;
				System.out.println("Entra en doble clcik");
			} else {
				if (contadorClick > 1) {
					contadorClick = 0;
				}
				System.out.println("Cargar el menu");

				filaSeleccionada = i;
				gest.menuOplatoSelccionado(i, false);
				contadorClick++;
			}
		}

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

	// Metodos
	private void cancelar() {
		this.dispose();

	}
}
