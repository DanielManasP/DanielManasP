package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Gestion;
import Modelo.Empleados;
import Modelo.TablaEmpleados;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class VerEmpleados extends JDialog implements MouseListener {

	private final JPanel pnFormulario = new JPanel();
	private JTextField txtId;
	private JTextField txtPass;
	private JTextField txtFecha;
	private JTextField txtNombre;
	private JTextField txtTlf;
	private JTextField txtSalario;
	private JTable tbEmpleados;
	private Gestion gestion;
	private TablaEmpleados modeloEmple;
	private boolean datosLimpios=false;
	private int empleSeleccionado=-1;
	private int soloUnPunto = 0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VerEmpleados dialog = new VerEmpleados();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VerEmpleados(Frame frame, boolean model, Gestion gest) {
		super(frame,model);
		gestion=gest;
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		modeloEmple=new TablaEmpleados();
		modeloEmple.setDatos(gestion.getListaEmpleados());
		setBounds(100, 100, 1002, 524);
		getContentPane().setLayout(null);
		pnFormulario.setBackground(new Color(255, 218, 185));
		pnFormulario.setBounds(0, 46, 986, 207);
		pnFormulario.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnFormulario);
		GridBagLayout gbl_pnFormulario = new GridBagLayout();
		gbl_pnFormulario.columnWidths = new int[]{0, 116, 196, 52, 68, 238, 63, 65, 0};
		gbl_pnFormulario.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnFormulario.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnFormulario.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnFormulario.setLayout(gbl_pnFormulario);
		
		JLabel lbId = new JLabel("Numero empleado:");
		lbId.setHorizontalAlignment(SwingConstants.LEFT);
		lbId.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		GridBagConstraints gbc_lbId = new GridBagConstraints();
		gbc_lbId.insets = new Insets(0, 0, 5, 5);
		gbc_lbId.gridx = 1;
		gbc_lbId.gridy = 1;
		pnFormulario.add(lbId, gbc_lbId);
		
		txtId = new JTextField();
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.gridx = 2;
		gbc_txtId.gridy = 1;
		txtId.setEditable(false);
		pnFormulario.add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		JLabel lbNombre = new JLabel("Nombre :");
		lbNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		GridBagConstraints gbc_lbNombre = new GridBagConstraints();
		gbc_lbNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lbNombre.anchor = GridBagConstraints.EAST;
		gbc_lbNombre.gridx = 4;
		gbc_lbNombre.gridy = 1;
		pnFormulario.add(lbNombre, gbc_lbNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 5;
		gbc_txtNombre.gridy = 1;
		pnFormulario.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarDatos();
			}

			
		});
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 0);
		gbc_btnLimpiar.gridx = 7;
		gbc_btnLimpiar.gridy = 1;
		pnFormulario.add(btnLimpiar, gbc_btnLimpiar);
		
		JLabel lbPass = new JLabel("Contraseña :");
		lbPass.setHorizontalAlignment(SwingConstants.LEFT);
		lbPass.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		GridBagConstraints gbc_lbPass = new GridBagConstraints();
		gbc_lbPass.insets = new Insets(0, 0, 5, 5);
		gbc_lbPass.anchor = GridBagConstraints.EAST;
		gbc_lbPass.gridx = 1;
		gbc_lbPass.gridy = 3;
		pnFormulario.add(lbPass, gbc_lbPass);
		
		txtPass = new JTextField();
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.insets = new Insets(0, 0, 5, 5);
		gbc_txtPass.gridx = 2;
		gbc_txtPass.gridy = 3;
		pnFormulario.add(txtPass, gbc_txtPass);
		txtPass.setColumns(10);
		
		JLabel lbTlf = new JLabel("Telefono :");
		lbTlf.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		GridBagConstraints gbc_lbTlf = new GridBagConstraints();
		gbc_lbTlf.insets = new Insets(0, 0, 5, 5);
		gbc_lbTlf.anchor = GridBagConstraints.EAST;
		gbc_lbTlf.gridx = 4;
		gbc_lbTlf.gridy = 3;
		pnFormulario.add(lbTlf, gbc_lbTlf);
		
		txtTlf = new JTextField();
		GridBagConstraints gbc_txtTlf = new GridBagConstraints();
		gbc_txtTlf.anchor = GridBagConstraints.NORTH;
		gbc_txtTlf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTlf.insets = new Insets(0, 0, 5, 5);
		gbc_txtTlf.gridx = 5;
		gbc_txtTlf.gridy = 3;
		pnFormulario.add(txtTlf, gbc_txtTlf);
		txtTlf.setColumns(10);
		txtTlf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			   {
			      char caracter = e.getKeyChar();
			      if(txtTlf.getText().length()>8) {
			    	  e.consume();
			      }
			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/))
			      {
			    	  e.consume();
			      }else {
			    	 
			      }
			   }
		});
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarEmple();
			}

			
		});
		btnEliminar.setBackground(new Color(240, 128, 128));
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminar.anchor = GridBagConstraints.NORTH;
		gbc_btnEliminar.gridx = 7;
		gbc_btnEliminar.gridy = 3;
		pnFormulario.add(btnEliminar, gbc_btnEliminar);
		
		JLabel lbFecha = new JLabel("Fecha contratacion :");
		lbFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lbFecha.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lbFecha.anchor = GridBagConstraints.EAST;
		gbc_lbFecha.gridx = 1;
		gbc_lbFecha.gridy = 5;
		pnFormulario.add(lbFecha, gbc_lbFecha);
		
		txtFecha = new JTextField();
		GridBagConstraints gbc_txtFecha = new GridBagConstraints();
		gbc_txtFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFecha.insets = new Insets(0, 0, 5, 5);
		gbc_txtFecha.gridx = 2;
		gbc_txtFecha.gridy = 5;
		txtFecha.setEditable(false);
		pnFormulario.add(txtFecha, gbc_txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lbSalario = new JLabel("Salario :");
		lbSalario.setFont(new Font("Tw Cen MT", Font.BOLD, 13));
		GridBagConstraints gbc_lbSalario = new GridBagConstraints();
		gbc_lbSalario.insets = new Insets(0, 0, 5, 5);
		gbc_lbSalario.anchor = GridBagConstraints.EAST;
		gbc_lbSalario.gridx = 4;
		gbc_lbSalario.gridy = 5;
		pnFormulario.add(lbSalario, gbc_lbSalario);
		
		txtSalario = new JTextField();
		GridBagConstraints gbc_txtSalario = new GridBagConstraints();
		gbc_txtSalario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSalario.insets = new Insets(0, 0, 5, 5);
		gbc_txtSalario.gridx = 5;
		gbc_txtSalario.gridy = 5;
		pnFormulario.add(txtSalario, gbc_txtSalario);
		txtSalario.setColumns(10);
		txtSalario.addKeyListener(new KeyAdapter() {
		

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
				if (txtSalario.getText().length() > 9) {
					e.consume();
				}
			
				if (caracter == '.') {
					soloUnPunto = 1;
				}
			}
		});
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificaEmple();
				
			}

		
		});
		btnModificar.setBackground(new Color(176, 224, 230));
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModificar.insets = new Insets(0, 0, 5, 0);
		gbc_btnModificar.gridx = 7;
		gbc_btnModificar.gridy = 5;
		pnFormulario.add(btnModificar, gbc_btnModificar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 456, 986, 29);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton btnCrear = new JButton("Añadir Empleado");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						addEmple();
					}

					
				});
				btnCrear.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton cancelButton = new JButton("SALIR");
				cancelButton.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}

				
				});
				cancelButton.setBackground(new Color(250, 128, 114));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JPanel pnTabla = new JPanel();
		pnTabla.setBackground(new Color(255, 218, 185));
		pnTabla.setBounds(0, 253, 986, 204);
		getContentPane().add(pnTabla);
		pnTabla.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnTabla.add(scrollPane);
		
		tbEmpleados = new JTable();
		tbEmpleados.setBackground(new Color(255, 228, 196));
		tbEmpleados.addMouseListener(this);
		tbEmpleados.setModel(modeloEmple);
		scrollPane.setViewportView(tbEmpleados);
		
		JPanel pnTitulo = new JPanel();
		pnTitulo.setBackground(new Color(255, 218, 185));
		pnTitulo.setBounds(0, 0, 986, 46);
		getContentPane().add(pnTitulo);
		pnTitulo.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbTitulo = new JLabel("EMPLEADOS");
		lbTitulo.setBackground(new Color(255, 218, 185));
		lbTitulo.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		pnTitulo.add(lbTitulo);
		
		limpiarDatos();
	}
	
	private void cancelar() {
		this.dispose();
		
	}
	private void modificaEmple() {
		if(empleSeleccionado!=-1) {
			if(!txtNombre.getText().isEmpty()&&!txtPass.getText().isEmpty()&&!txtSalario.getText().isEmpty()&&!txtTlf.getText().isEmpty()) {
				if(gestion.compruebaPass(txtPass.getText())) {
					if(gestion.modificarEmple(Integer.parseInt(txtId.getText()), txtNombre.getText(),
							txtPass.getText(),Integer.parseInt(txtTlf.getText()), txtSalario.getText())) {
						empleSeleccionado=-1;
						limpiarDatos();
						JOptionPane.showMessageDialog(this, "Se modifico el empleado "+txtNombre.getText(),
								"SE MODIFICO EMPLEADO", JOptionPane.INFORMATION_MESSAGE);
						modeloEmple.fireTableDataChanged();
					}
				}else {
					JOptionPane.showMessageDialog(this, "La contraseña ha de ser mayor a 8 caracteres",
							"ERROR, PASSWORD", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Ningun campo puede estar vacio",
						"ERROR, CAMPOS VACIOS", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		
	}
	private void eliminarEmple() {
		if(empleSeleccionado!=-1) {
			int borra = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quiere borrar el empleado?");
			if (borra == 0) {
				if(gestion.borrarEmpl(Integer.parseInt(txtId.getText()))) {
					JOptionPane.showMessageDialog(this, "Se BORRO el empleado "+txtNombre.getText(),
							"SE BORRO EMPLEADO", JOptionPane.INFORMATION_MESSAGE);
					
					modeloEmple.fireTableDataChanged();
				}
				limpiarDatos();
				empleSeleccionado=-1;
			}
			
		}
		
		
	}
	private void addEmple() {
		if(datosLimpios) {
			if(!txtNombre.getText().isEmpty() || !txtPass.getText().isEmpty() || !txtSalario.getText().isEmpty() || !txtTlf.getText().isEmpty()) {
				if(gestion.compruebaPass(txtPass.getText())) {
					if(gestion.crearEmpleado(txtNombre.getText(),txtPass.getText(),txtTlf.getText(),txtSalario.getText())){
						JOptionPane.showMessageDialog(this, "Se CREO el empleado  "+txtNombre.getText(),
								"SE CREO EMPLEADO", JOptionPane.INFORMATION_MESSAGE);
						limpiarDatos();
						modeloEmple.fireTableDataChanged();
					}else {
						JOptionPane.showMessageDialog(this, "Error inesperado, no creo empleado",
								"ERROR BASE DE DATOS", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(this, "La contraseña ha de ser mayor a 8 caracteres",
							"ERROR, PASSWORD", JOptionPane.WARNING_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(this, "Ningun campo puede estar vacio",
						"ERROR, CAMPOS VACIOS", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Por favor, de al boton Limpiar para poder crear un empleado nuevo",
					"LIMPIE LOS DATOS DEL FORMULARIO", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	private void limpiarDatos() {
		empleSeleccionado=-1;
		txtId.setText("");
		txtNombre.setText("");
		txtPass.setText("");
		txtTlf.setText("");
		txtFecha.setText("");
		txtSalario.setText("");
		datosLimpios=true;
		soloUnPunto=0;
		
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource()==tbEmpleados) {
			datosLimpios=false;
			
			empleSeleccionado=1;
			int id=(int) tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 0);
			String nombre= (String) tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 1);
			int tlf=  (int) tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 2);
			String pass= (String) tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 3);
			Date fecha= (Date) tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 4);
			float salario= (float) tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 5);
			txtId.setText(""+id);
			txtNombre.setText(nombre);
			txtPass.setText(pass);
			txtTlf.setText(""+tlf);
			txtFecha.setText(fecha.toString());
			txtSalario.setText(""+salario);
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
}
