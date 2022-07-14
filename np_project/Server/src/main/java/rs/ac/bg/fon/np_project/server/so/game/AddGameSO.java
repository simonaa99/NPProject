/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.game;

import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;
import rs.ac.bg.fon.np_project.server.so.validator.GameValidator;
import rs.ac.bg.fon.np_project.server.so.validator.ValidationException;


/**
 *
 * @author Simona
 */
public class AddGameSO extends AbstractSO {

    RepositoryGame repositoryGame;
    RepositoryPublisher repositoryPublisher;

    public AddGameSO() {
        repositoryGame = new RepositoryGame();
        repositoryPublisher = new RepositoryPublisher();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Game)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            Game game = (Game) param;
            checkValueConstraints(game);
            checkStructuralConstraints(game);
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Game game = (Game) param;
        repositoryGame.connect();
        List<Publisher> dbPublisher = repositoryPublisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + game.getPublisher().getPublisherName() + "'");
        try {
            if (dbPublisher.size() == 0) {
                repositoryPublisher.add(game.getPublisher());
                
            }
            Publisher p=repositoryPublisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + game.getPublisher().getPublisherName() + "'").get(0);
            game.setPublisher(p);
            repositoryGame.add(game);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom dodavanja igre.", e);
        }

        return null;
    }

    public boolean exists(Game game) throws Exception {
        try {
            List<Game> dbGames = repositoryGame.getAll();
            for (Game dbGame : dbGames) {
                if (dbGame.getGameName().equals(game.getGameName()) && dbGame.getPublisher().getPublisherId().equals(game.getPublisher().getPublisherId())) {
                    return true;
                }

            }
        } catch (Exception ex) {ex.printStackTrace();
            throw new Exception("Greska prilikom provere uslova: AddGameSO");
            
        }
        return false;
    }

    private void checkValueConstraints(Game param) throws ValidationException{
        GameValidator.startValidation().validateValueIsPositive(param.getNumberInStock(), "Kolicina mora biti veca od 0!").throwIfInvalide();

    }

    private void checkStructuralConstraints(Game param) throws ValidationException{
        GameValidator.startValidation().validateNotNull(param.getPublisher(), "Izdavac igre mora biti dodat!")
                .validateAlreadyExists(param, this, "Igra postoji u bazi!")
                .throwIfInvalide();
    }

}
