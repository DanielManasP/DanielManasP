package Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class tablaMenus extends AbstractTableModel {

	private String [] columnas= {"Numero","MENU","PRECIO"};
	private ArrayList<Menus> listaMenus = new ArrayList<Menus>();
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaMenus.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Menus mn= listaMenus.get(rowIndex);		
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
    public void setDatos(ArrayList<Menus> listaMenus) {
    	this.listaMenus=listaMenus;
    }
}
