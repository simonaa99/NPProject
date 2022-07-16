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
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih drustvenih igara iz baze pomocu upita.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryGame 
 * koja je tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetGamesByQuerySO extends AbstractSO{
	
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryGame koja uzima, dodaje, azurira i
	 * i brise drustvene igre iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame
	 */
	private RepositoryGame repositoryGame;

	/**
     * Konstruktor koji inicijalizuje atribut repositoryGame.
     */
    public GetGamesByQuerySO() {
        repositoryGame=new RepositoryGame();
    }

    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase String.
     * Ako je neki od ovih uslova ispunjen baca Exception i prikazuje poruku
     * "Poslati objekat je neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
    }

    /**
     * Metoda kreira listu igara i u nju ubacuje i vraca sve igre iz baze. 
     * Ako dodje do greske baca Exception i prikazuje poruku 
     * "Greska prilikom ucitavanja igara."
     * 
     * @return lista svih drustvenih igri iz baze
     */
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
