/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.userCard;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih clanskih kartica iz baze 
 * pomocu upita. Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryUserCard
 * koji je tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetUserCardsByQuerySO extends AbstractSO{
	
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUserCard koja uzima, dodaje, azurira i
	 * i brise clanske kartice iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard
	 */
    RepositoryUserCard repositoryUserCard;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryUserCard.
     */
    public GetUserCardsByQuerySO() {
        repositoryUserCard=new RepositoryUserCard();
    }
    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase String.
     * Ako je neki uslov ispunjen baca Exception i prikazuje poruku
     * "Poslati parametar je neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
    }

    /**
     * Metoda kreira listu svih clanski kartica iz baze. Ako dodje do greske prilikom ucitavanja
     * baca Exception i prikazuje poruku "Greska prilikom ucitavanja igara."
     * 
     * @return sve clanske kartice iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<UserCard> cards = new ArrayList<>();
        String query=(String) param;
        try {
            cards = repositoryUserCard.getByQuery(query);
            return cards;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja igara.", e);
        }
        
    }
    
}
