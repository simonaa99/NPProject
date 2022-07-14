/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.tableModel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np_project.server.controller.Controller;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;

/**
 *
 * @author Simona
 */
public class TableModelWorkers extends AbstractTableModel {

    private List<Worker> workers;
    private String[] columnNames = {"Ime", "Prezime", "Korisnicko ime", "Sifra", "Prijavljen"};
    private Class[] colClasses = {String.class, String.class, String.class, String.class, Boolean.class};

    public TableModelWorkers() throws Exception {
        workers = Controller.getInstance().getWorkers();

    }

    @Override
    public int getRowCount() {
        return workers == null ? 0 : workers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Worker worker = workers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return worker.getFirstName();
            case 1:
                return worker.getLastName();
            case 2:
                return worker.getUsername();
            case 3:
                return worker.getPassword();
            case 4:
                return worker.isLoggedIn();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void removeWorker(int selectedRow) throws Exception {
        Worker worker = workers.get(selectedRow);
        try {
            if(worker.isLoggedIn()==true)
                throw new Exception("Radnik je prijavljen na sistem, ne moze se obrisati.");
            Controller.getInstance().removeWorker(worker);
            workers.remove(selectedRow);
            fireTableDataChanged();
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public void updateWorker(Worker dbUser, boolean loogedIn) {
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getUsername().equals(dbUser.getUsername())) {
                workers.get(i).setLoggedIn(loogedIn);
                fireTableDataChanged();
                break;
            }
        }

    }
    public void refreshView() throws Exception{
        workers = Controller.getInstance().getWorkers();
        fireTableDataChanged();
        
    }

}
