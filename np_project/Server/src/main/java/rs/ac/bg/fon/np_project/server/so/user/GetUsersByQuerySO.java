/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.np_project.server.so.user;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetUsersByQuerySO extends AbstractSO{
    RepositoryUser repositoryUser;

    public GetUsersByQuerySO() {
        repositoryUser=new RepositoryUser();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
      
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
         List<User> users = new ArrayList<>();
        String query=(String) param;
        try {
            users = repositoryUser.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja igara.", e);
        }
        return users;
        }
    
}
