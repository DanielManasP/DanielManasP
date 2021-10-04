package Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TablaEmpleados extends AbstractTableModel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String [] columnas= {"Numero","NOMBRE","TLF","","FECHA CONTRATACION","SALARIO","ESJEFE"};
	
	private ArrayList<Empleados> listaEmples = new ArrayList<Empleados>();
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaEmples.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Empleados mn= listaEmples.get(rowIndex);		
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
    public void setDatos(ArrayList<Empleados> listaEmples) {
    	this.listaEmples=listaEmples;
    }
}
