package Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TablaSeleccion extends AbstractTableModel{
	
	private String [] columnas= {"Numero","NOMBRE","PRECIO"};
	private ArrayList<Object> listaSelect = new ArrayList<Object>();
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaSelect.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object mn= listaSelect.get(rowIndex);
		if(mn.getClass()==Menus.class) {
			Menus m=(Menus) mn;
			return m.getValue(columnIndex);
		}else {
			if(mn.getClass()==Platos.class) {
				Platos p= (Platos)mn;
				return p.getValue(columnIndex);
			}else {
				return null;
			}	 
		}
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
    public void setDatos(ArrayList<Object> listaSelect) {
    	this.listaSelect=listaSelect;
    }
}
