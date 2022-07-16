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
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih drustvenih igara iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryGame 
 * koja je tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetAllGamesSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryGame koja uzima, dodaje, azurira i
	 * i brise drustvene igre iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame
	 */
    RepositoryGame repositoryGame;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryGame.
     */
    public GetAllGamesSO() {
        repositoryGame = new RepositoryGame();
    }
    

    /**
     * Metoda nema nikakve uslove da proverava.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        //no precondidion to check

    }

    /**
     * Metoda kreira listu games i u tu listu ubacuje i vraca sve igre iz baze. 
     * Ako dodje do greske javlja se Exception i pojavljuje poruka "Greska prilikom ucitavanja igra."
     * 
     * @return lista svih drustvenih igri iz baze
     */
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
