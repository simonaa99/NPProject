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
 * Predstavlja klasu u kojoj se izvrsavaju metode za dodavanje drustvene igre u bazu.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryGame i repositoryPublisher koje su tipa
 * klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class AddGameSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryGame koja uzima, dodaje, azurira i
	 * i brise drustvene igre iz baze. 
	 */
    RepositoryGame repositoryGame;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryPublisher koja uzima, dodaje, azurira i
	 * i brise izdavace iz baze. 
	 */
    RepositoryPublisher repositoryPublisher;

    
    /**
     * Konstruktor koji inicijalizuje atribute repositoryGame i repositoryPublisher.
     */
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

    /**
     * Metoda koja proverava da li drustvena igra vec postoji u listi igara u bazi.
     * 
     * @param Game game koji predstavlja drustvenu igru
     * @returns  boolean kao odgovor da li igra vec postoji u listi
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja i vraca
     * poruku "Greska prilikom provere uslova: AddGameSO"
     * */
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

    
    /**
     * Metoda koja proverava da li uneta kolicina drustvenih igara veca od 0.
     * 
     * @param Game param koji predstavlja drustvenu igru
     * @throws rs.ac.bg.fon.np_project.server.so.validator.ValidationException ako se desi
     * slucaj da je unet broj manji od 0 i izbacuje odgovarajucu poruku
     * */
    private void checkValueConstraints(Game param) throws ValidationException{
        GameValidator.startValidation().validateValueIsPositive(param.getNumberInStock(), "Kolicina mora biti veca od 0!").throwIfInvalide();

    }

    /**
     * Metoda koja proverava da li unet izdavac i da li igra vec postoji u bazi.
     * 
     * @param Game param koji predstavlja drustvenu igru
     * @throws rs.ac.bg.fon.np_project.server.so.validator.ValidationException ako se desi
     * slucaj da je izdavac nije unesen ili da igra vec postoji u bazi i izbacuje odgovarajucu poruku
     * */
    private void checkStructuralConstraints(Game param) throws ValidationException{
        GameValidator.startValidation().validateNotNull(param.getPublisher(), "Izdavac igre mora biti dodat!")
                .validateAlreadyExists(param, this, "Igra postoji u bazi!")
                .throwIfInvalide();
    }

}
