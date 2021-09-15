package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridLayout;

public class VistaMenus extends JInternalFrame {
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
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
		setBounds(100, 100, 450, 300);
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 440, 219);
		pnMenu.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 440, 219);
		panel.add(table);
		
	

	}
}
