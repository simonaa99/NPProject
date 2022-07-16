/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.userCard;

import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za brisanje clanske kartice iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryUserCard
 * koji je tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class DeleteUserCardSO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUserCard koja uzima, dodaje, azurira i
	 * i brise clanske kartice iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard
	 */
    RepositoryUserCard repositoryUserCard;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryUserCard.
     */
    public DeleteUserCardSO() {
        repositoryUserCard=new RepositoryUserCard();
    }
    

    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase UserCard.
     * Ako je neki uslov ispunjen baca Exception i prikazuje poruku
     * "Poslati parametar je neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof UserCard))
            throw new Exception("Poslati parametar je neodgovarajuceg tipa!");
        
        }

    /**
     * Metoda brise clansku karticu iz baze.
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryUserCard.delete((UserCard) param);
        return null;
        
        
    }
    
}
