/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;
import rs.ac.bg.fon.np_project.server.so.game.GetGamesByQuerySO;


/**
 *
 * @author Simona
 */
public class RentGameSO extends AbstractSO {

    RepositoryRent repositoryRent;
    RepositoryGame repositoryGame;

    public RentGameSO() {
        repositoryRent = new RepositoryRent();
        repositoryGame=new RepositoryGame();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || ((List<Object>) param).size() < 2) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        }
        if (!(((List<Object>) param).get(0) instanceof User) || !(((List<Object>) param).get(1) instanceof Game)) {
            throw new Exception("Poslati argumenti su neodgovarajuceg tipa!");
        } else {
            User user = (User) ((List<Object>) param).get(0);
            Game game = (Game) ((List<Object>) param).get(1);
            checkValueConstraints(user, game);
            checkStructuralConstraints(user, game);
        }
    }

    private void checkValueConstraints(User user, Game game) throws Exception {
        //TO DO -proveriti da li je clanska karta istekla
        checkUserCardIsValid(user.getUsercard());
    }

    private void checkStructuralConstraints(User user, Game game) throws Exception {
        int userRents;
        int gamesOnStorage;
        userRents = checkUserRents(user);
        if (userRents == 2) {
            throw new Exception("Korisnik ima dve zadužene igre!");
        }

        gamesOnStorage = checkGameCount(game);
        if (gamesOnStorage <= 0) {
            throw new Exception("Nema igra na stanju!");
        }

    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        User u = (User) ((List<Object>) param).get(0);
        Game g = (Game) ((List<Object>) param).get(1);
        try {
            repositoryRent.rentGame(u, g);
            updateGameCount(g,-1);
        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        }
        return null;
    }

    private int checkUserRents(User u) throws Exception {
        return repositoryRent.getUserRents(u).size();
    }

    private int checkGameCount(Game g) throws Exception {
        GetGamesByQuerySO getGamesByQuerySO = new GetGamesByQuerySO();
        try {
            String query = "SELECT * FROM igra WHERE id=" + g.getGameid();
            Game game = ((List<Game>) getGamesByQuerySO.execute(query)).get(0);
            return game.getNumberInStock();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere stanja na skladistu.", e);
        }

    }

    private void checkUserCardIsValid(UserCard usercard) throws Exception {
       if(usercard.getExpiryDate().isBefore(LocalDate.now()))
           throw new Exception("Clanska karta korisnika je istekla. Nije moguce iznajmiti igru.");
    }

    private void updateGameCount(Game g, int i) throws SQLException, IOException {
        repositoryGame.updateGameCount(g,i);
    }
    
}
