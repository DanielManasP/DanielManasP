package Modelo;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiRender extends DefaultTableCellRenderer {
	 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
                                                   boolean isSelected, 
                                                   boolean hasFocus, 
                                                   int row, 
                                                   int column) {
    	
    	
    	this.setBackground(Color.CYAN);
    	if(table.isRowSelected(row)) {
    		this.setBackground(Color.RED);
    	}
    	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
          
          return this;
          
         
        
 
       
    }
 
}
