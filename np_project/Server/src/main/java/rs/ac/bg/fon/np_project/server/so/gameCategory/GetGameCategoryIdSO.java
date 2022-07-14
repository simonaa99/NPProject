/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.gameCategory;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGameCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetGameCategoryIdSO extends AbstractSO{
    RepositoryGameCategory repositoryGameCategory;

    public GetGameCategoryIdSO() {
        repositoryGameCategory=new RepositoryGameCategory();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
          if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
          }

    @Override
    protected Object executeOperation(Object param) throws Exception {
     return repositoryGameCategory.getGameCategoryId((String)param);
    }
    
}
