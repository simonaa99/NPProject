/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.userCard;

import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class DeleteUserCardSO extends AbstractSO{
    RepositoryUserCard repositoryUserCard;

    public DeleteUserCardSO() {
        repositoryUserCard=new RepositoryUserCard();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof UserCard))
            throw new Exception("Postati parametar je neodgovarajuceg tipa!");
        
        }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryUserCard.delete((UserCard) param);
        return null;
        
        
    }
    
}
