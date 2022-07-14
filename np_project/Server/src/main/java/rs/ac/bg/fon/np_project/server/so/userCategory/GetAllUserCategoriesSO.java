/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.userCategory;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetAllUserCategoriesSO  extends AbstractSO{
    RepositoryUserCategory repositoryUserCategory;

    public GetAllUserCategoriesSO() {
        repositoryUserCategory= new RepositoryUserCategory();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
       
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        return repositoryUserCategory.getAll();
    }
    
}
