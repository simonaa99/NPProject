/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetAllRentsSO extends AbstractSO{
    RepositoryRent repositoryRent;

    public GetAllRentsSO() {
        repositoryRent=new RepositoryRent();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
       return repositoryRent.getAll();
    }
    
}
