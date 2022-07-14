/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view.tableModel;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;

/**
 *
 * @author Simona
 */
public class TableModelUserRents extends AbstractTableModel {
    
    private List<Rent> rents;
    private String[] columnNames=new String[]{ "Igra", "Datum iznajmljivanja", "Datum vracanja"};
    private Class[] columnClasses=new Class[]{String.class, LocalDate.class, LocalDate.class};
    
    public TableModelUserRents(List<Rent> i){
        rents=i;
    }
    public void setRents(List<Rent> i){
    this.rents=i;
    fireTableDataChanged();}

    @Override
    public int getRowCount() {
        if(rents==null) return 0;
        return rents.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Rent r=rents.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getGame().getGameName();
            case 1: return r.getRentalDate();
            case 2: return r.getReturnDate()==null ? "Nije vracena" : r.getReturnDate();
        }
        return "n/a";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Rent getRent(int selectedrow) {
       return rents.get(selectedrow);  
    }
    
    public void deleteRent(int rent){
        rents.remove(rent);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
}
