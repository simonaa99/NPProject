/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.game;

import java.util.ArrayList;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetAllGamesSO extends AbstractSO {

    RepositoryGame repositoryGame;

    public GetAllGamesSO() {
        repositoryGame = new RepositoryGame();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondidion to check

    }

    @Override
    protected Object executeOperation(Object games) throws Exception {
        games = new ArrayList<>();
        try {
            String query = "SELECT * FROM igra ORDER BY naziv ASC";
            repositoryGame.connect();
            games = repositoryGame.getByQuery(query);
            return games;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja igra.", e);
        }

    }

}
