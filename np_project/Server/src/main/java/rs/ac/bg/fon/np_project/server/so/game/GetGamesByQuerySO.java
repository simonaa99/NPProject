/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.game;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetGamesByQuerySO extends AbstractSO{
private RepositoryGame repositoryGame;

    public GetGamesByQuerySO() {
        repositoryGame=new RepositoryGame();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Game> games = new ArrayList<>();
        String query=(String) param;
        try {
            games = repositoryGame.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja igara.", e);
        }
        return games;
    }
    
}
