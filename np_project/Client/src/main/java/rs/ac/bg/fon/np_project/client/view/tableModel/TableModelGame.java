/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view.tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;

/**
 *
 * @author Simona
 */
public class TableModelGame extends AbstractTableModel {
    private List<Game> igre;
    private String[] columnNames=new String[]{"ID", "Naziv", "Izdavac", "Max broj igraca", "Kategorija", "Na stanju"};
    private Class[] columnClasses=new Class[]{Long.class, String.class, Publisher.class, Integer.class, GameCategory.class, Integer.class};
    
    public TableModelGame(List<Game> k){
        igre=k;
    }
    
    public void setIgre(List<Game> igre){
    this.igre=igre;
    fireTableDataChanged();}

    @Override
    public int getRowCount() {
        if(igre==null) return 0;
        return igre.size();
           }

    @Override
    public int getColumnCount() {
       return columnNames.length;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Game g=igre.get(rowIndex);
        switch(columnIndex){
            case 0: return g.getGameid();
            case 1: return g.getGameName();
            case 2: return g.getPublisher();
            case 3: return g.getNumPlayers();
            case 4: return g.getGameCategory();
            case 5: return g.getNumberInStock();
        }
        return "n/a";
         
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Game getIgra(int selectedrow) {
       return igre.get(selectedrow);  
    }
    
    public void deleteIgra(Game igra){
        igre.remove(igra);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    
}
