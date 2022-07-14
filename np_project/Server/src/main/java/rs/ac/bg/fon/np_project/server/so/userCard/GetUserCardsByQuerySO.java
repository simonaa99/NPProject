/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.userCard;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetUserCardsByQuerySO extends AbstractSO{
    RepositoryUserCard repositoryUserCard;

    public GetUserCardsByQuerySO() {
        repositoryUserCard=new RepositoryUserCard();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<UserCard> cards = new ArrayList<>();
        String query=(String) param;
        try {
            cards = repositoryUserCard.getByQuery(query);
            return cards;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja igara.", e);
        }
        
    }
    
}
