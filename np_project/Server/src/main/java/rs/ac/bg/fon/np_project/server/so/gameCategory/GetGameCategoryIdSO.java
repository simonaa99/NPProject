/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.gameCategory;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGameCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje id kategorije drustvenih
 * igara iz baze. Sadrzi implementaciju metoda precondition i executeOperation iz
 * nadklase AbstractSO, konstruktor i atribut repositoryGameCategory koja je tipa
 * klase koje se nalaze na serverskoj strani..
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetGameCategoryIdSO extends AbstractSO{
	
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryGameCategory koja uzima, dodaje, azurira i
	 * i brise kategorije drustvenih igara iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGameCategory
	 */
    RepositoryGameCategory repositoryGameCategory;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryGameCategory.
     */
    public GetGameCategoryIdSO() {
        repositoryGameCategory=new RepositoryGameCategory();
    }
    

    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase String.
     * Ako ispunjava neke uslove baca se Exception i prikazuje poruka 
     * "Poslati objekat je neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
          if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
          }

    /**
     * Metoda vraca odredjenu kategoriju po id-u iz baze. Ako dodje do greske
     * baca Exception.
     * 
     * @return odredjenu kategoriju igre u zavisnosti od id-a
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
     return repositoryGameCategory.getGameCategoryId((String)param);
    }
    
}
