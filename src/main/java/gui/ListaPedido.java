package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ListaPedido extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPedido frame = new ListaPedido();
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
	public ListaPedido() {
		setBounds(100, 100, 450, 300);

	}

}
