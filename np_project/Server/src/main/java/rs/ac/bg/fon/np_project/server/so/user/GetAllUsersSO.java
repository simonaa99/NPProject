/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.user;

import java.util.List;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetAllUsersSO extends AbstractSO{
    RepositoryUser repositoryUser;

    public GetAllUsersSO() {
        repositoryUser=new RepositoryUser();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no preconditions
         }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        try { 
            String query = "SELECT * FROM clan ORDER BY ime ASC";
            return repositoryUser.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja korisnika.", e);
        }
    }
    
}
