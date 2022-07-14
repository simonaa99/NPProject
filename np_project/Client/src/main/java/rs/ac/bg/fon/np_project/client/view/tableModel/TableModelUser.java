/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view.tableModel;

import rs.ac.bg.fon.np_project.client.communication.Communication;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Operations;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Request;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Response;
import rs.ac.bg.fon.np_project.client.controller.ControllerUI;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;

/**
 *
 * @author Simona
 */
public class TableModelUser extends AbstractTableModel{
    
    private List<User> users;
    private String[] columnNames=new String[]{"ID", "Ime", "Prezime", "Telefon", "Adresa", "Kategorija", "Broj clanske karte", "Clanska karta vazi do"};
    private Class[] columnClasses=new Class[]{Long.class, String.class, String.class, String.class, String.class, UserCategory.class, UserCard.class, LocalDate.class};
    
    public TableModelUser() {
        try {
            Request request=new Request();
            request.setOperation(Operations.GET_USERS);
            Response response=Communication.getInstance().sendRequest(request);
            users=(List<User>)response.getResult();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if(users==null) return 0;
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User clan=users.get(rowIndex);
        switch(columnIndex){
            case 0: return clan.getUserId();
            case 1: return clan.getName();
            case 2: return clan.getLastName();
            case 3: return clan.getPhoneNumber();
            case 4: return clan.getAddress();
            case 5: return clan.getUserCategory();
            case 6: return clan.getUsercard();
            case 7: return clan.getUsercard().getExpiryDate();
        }
        return "n/a";
    }

    @Override
    public String getColumnName(int column) {
       return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      return columnClasses[columnIndex];
    }

    public void removeClan(int selectedRow) throws Exception {
        User user=users.get(selectedRow);
        ControllerUI.getInstance().deleteUser(user);
        users.remove(user);
         fireTableDataChanged();
    }
       
    public List<User> getUsers() {
        return users; 
    }
    
    public User getUser(int selecterRow){
        return users.get(selecterRow);
    }
    
}
