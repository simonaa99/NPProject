/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.game;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;


/**
 *
 * @author Simona
 */
public class DeleteGameSO extends AbstractSO {

    private RepositoryGame repositoryGame;
    private RepositoryPublisher repositoryPublisher;
    private RepositoryRent repositoryRent;

    public DeleteGameSO() {
        repositoryGame = new RepositoryGame();
        repositoryPublisher = new RepositoryPublisher();
        repositoryRent = new RepositoryRent();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Game)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
            
        } else {
            Game game = (Game) param;
            try {
                checkStructuralConstraints(game);
              
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Game g = (Game) param;
        repositoryRent.deleteGameRents(g);
        repositoryGame.delete(g);
//        try {
//            removePublisher(b.getPublisher());
//        } catch (Exception e) {
//            System.out.println("Publisher nije obrisan.");
//        }
        return null;
    }

    private void checkStructuralConstraints(Game game) throws Exception {
        checkIfRentsExist(game);
    }

    private void checkIfRentsExist(Game game) throws Exception {
        try {
            String query = "SELECT * FROM iznajmljivanje WHERE igraId=" + game.getGameid() + " AND datumVracanja IS NULL";
            List<Rent> rents = repositoryRent.getByQuery(query);
            if (rents!=null && rents.size() > 0) {
                throw new Exception("Primerci igre su zaduzeni. Nije moguce dovrsiti operaciju brisanja.");
            }
        } catch (Exception ex) {
            throw ex;
        }

    }
 // zanemari jer ne treba da se brise i izdavac od igre vec samo igra a izdavac ostaje u bazi
    private void removePublisher(Publisher publisher) throws Exception {

        boolean exist = repositoryGame.checkIfGamesExist(publisher);
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();

        if (exist) {
            throw new Exception("Igre ovog izdavaca postoje na stanju. Nije moguce dovrsiti operaciju brisanja.");
        } else {
            repositoryPublisher.delete(publisher);
        }
    }

}
