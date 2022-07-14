/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import java.io.IOException;
import java.sql.SQLException;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class RestoreGameSO extends AbstractSO {

    RepositoryRent repositoryRent;
    private RepositoryGame repositoryGame;

    public RestoreGameSO() {
        repositoryRent = new RepositoryRent();
        repositoryGame=new RepositoryGame();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Rent)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");

        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Rent rent = (Rent) param;
        try {
            repositoryRent.restoreGame(rent);
            updateGameCount(rent.getGame(),+1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return null;
    }

    private void updateGameCount(Game game, int i) throws SQLException, IOException {
        repositoryGame.updateGameCount(game, i);
        
          }

}
