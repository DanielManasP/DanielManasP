package Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TablaReservas extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String [] columnas= {"Numero","NumCliente","NumeroEmpleado","Num Comensales","Fecha reserva","Precio total","Mañana=0/Tarde=1","Turno"};
	private ArrayList<Reservas> listaReserv = new ArrayList<Reservas>();
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaReserv.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reservas mn= listaReserv.get(rowIndex);		
		return mn.getValue(columnIndex);
	}

   
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
    //Metodos
    public void setDatos(ArrayList<Reservas> listaPlatos) {
    	this.listaReserv=listaPlatos;
    }
}
