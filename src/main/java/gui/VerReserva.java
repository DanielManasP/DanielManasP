package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Gestion;
import Modelo.Menus;
import Modelo.Platos;
import Modelo.Reservas;
import Modelo.TablaPlatos;
import Modelo.tablaMenus;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class VerReserva extends JDialog {

	private final JPanel pnTablas = new JPanel();
	private JTable tbMenus;
	private JTable tbPlatos;
	private Gestion gestion;
	private TablaPlatos modeloPlatos;
	private tablaMenus modeloMenus;

		//Mañana Turnos 1 y 2					Tardes Turnos 1 y 2
	private String hora[][]= {{"13:00 a 14:00","14:30 a 15:30"},{"20:30 a 21:30","22:00 a 23:00"}};
	private final int TURNO_1=0;
	private final int TURNO_2=1;
	private final int PASE_1=0;
	private final int PASE_2=1;
	private int pase=0;
	private int turno=0;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VerReserva dialog = new VerReserva();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param reservaSelected 
	 */
	public VerReserva(Frame frame, boolean model,Gestion gestion) {
		super(frame,model);
		getContentPane().setBackground(new Color(128, 128, 128));
		this.gestion=gestion;
		setBounds(900, 100, 866, 480);
		getContentPane().setLayout(null);
		pnTablas.setBackground(new Color(128, 128, 128));
		pnTablas.setBounds(0, 0, 865, 386);
		pnTablas.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnTablas);
		pnTablas.setLayout(new GridLayout(0, 2, 0, 0));
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		
		JScrollPane scrollPmenus = new JScrollPane();
		scrollPmenus.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTablas.add(scrollPmenus);
		scrollPmenus.setBackground(Color.DARK_GRAY);
		
		modeloMenus= new tablaMenus();
		modeloMenus.setDatos(gestion.menusPorClienteYreserva());;
		tbMenus = new JTable();
		tbMenus.setBackground(new Color(72, 209, 204));
		
		scrollPmenus.setViewportView(tbMenus);
		tbMenus.setModel(modeloMenus);
		JScrollPane scrollPplatos = new JScrollPane();
		scrollPplatos.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTablas.add(scrollPplatos);

		
		modeloPlatos= new TablaPlatos();
		modeloPlatos.setDatos(gestion.platosPorClienteYreserva());
		tbPlatos = new JTable();
		tbPlatos.setBackground(new Color(72, 209, 204));
		
		scrollPplatos.setViewportView(tbPlatos);
		scrollPplatos.setBackground(Color.DARK_GRAY);
		tbPlatos.setModel(modeloPlatos);
		{
			JPanel pnBtn = new JPanel();
			pnBtn.setBounds(0, 457, 865, 23);
			getContentPane().add(pnBtn);
			pnBtn.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JButton btnSalir = new JButton("OK");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				btnSalir.setActionCommand("OK");
				pnBtn.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
		
		JPanel pnInfo = new JPanel();
		pnInfo.setBackground(new Color(240, 230, 140));
		pnInfo.setBounds(0, 388, 865, 70);
		getContentPane().add(pnInfo);
		pnInfo.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbFecha = new JLabel("	Fecha reserva : "+gestion.reservaSelected.getFechaReserva());
		lbFecha.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		pnInfo.add(lbFecha);
		
		JLabel lbNumPedidos = new JLabel("Numero de Comensales : "+gestion.reservaSelected.getNumcomensales());
		lbNumPedidos.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		pnInfo.add(lbNumPedidos);
		
		
		pase=gestion.reservaSelected.getPase();
		turno=gestion.reservaSelected.getTurno();
		
		
		JLabel lbHora = new JLabel("	Hora prevista de llegada : "+hora[pase][turno]);
		lbHora.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		pnInfo.add(lbHora);
		
		JLabel lbPrecio = new JLabel("Precio final : "+gestion.reservaSelected.getPrecioTotal());
		lbPrecio.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		pnInfo.add(lbPrecio);
	}
	private void cancelar() {
		this.dispose();
	}
	
}
