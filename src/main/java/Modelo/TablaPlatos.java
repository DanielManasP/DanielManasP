package Modelo;


import java.util.ArrayList;


import javax.swing.table.AbstractTableModel;

public class TablaPlatos extends AbstractTableModel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String [] columnas= {"Numero","PLATO","PRECIO"};
	private ArrayList<Platos> listaPlatos = new ArrayList<Platos>();
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaPlatos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Platos mn= listaPlatos.get(rowIndex);		
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
    public void setDatos(ArrayList<Platos> listaPlatos) {
    	this.listaPlatos=listaPlatos;
    }
}
