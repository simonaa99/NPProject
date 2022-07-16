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
 * Predstavlja klasu u kojoj se izvrsavaju metode za proces vracanja iznajmljene igre i unosenje
 * toga u bazu. Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryRent i 
 * repositoryGame koje su tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class RestoreGameSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryRent koja uzima, dodaje, azurira i
	 * i brise iznajmljivanja iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent
	 */
    RepositoryRent repositoryRent;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryRent koja uzima, dodaje, azurira i
	 * i brise drustvene igre iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame
	 */
    private RepositoryGame repositoryGame;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryRent i repositoryGame.
     */
    public RestoreGameSO() {
        repositoryRent = new RepositoryRent();
        repositoryGame=new RepositoryGame();
    }

    /**
     * Metoda proverava da li je uneti objekat null ili nije instanca klase Rent. 
     * Ako je neki od ovih uslova ispunjen baca Exception i poruku
     * "Poslati objekat je neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Rent)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");

        }
    }

    /**
     * Metoda koja pravi promenljivu rent tipa Rent koja predstavlja vracenu igru.
     * Poziva metode za azuriranje kolicine igara na stanju. Ako dodje do greske baca Exception
     * sa odgovarajucom porukom.
     * 
     */
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

    /**
     * Metoda koja azurira dostupne kolicine drustvene igre nakon vracanja.
     * 
     * @param game tipa Game koja predstavlja drustvenu igru 
     * @param  i tipa int koja predstavlja koliki je broj odredjene igre vraceno
     * @throws java.sql.SQLException kada se desi neka vrsta greske u radu sa bazom prilikom
     * azuriranja kolicine igre
     * @throws java.io.IOException kada se desi neka vrsta I/O exception-a
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     */
    private void updateGameCount(Game game, int i) throws SQLException, IOException {
        repositoryGame.updateGameCount(game, i);
        
          }

}
