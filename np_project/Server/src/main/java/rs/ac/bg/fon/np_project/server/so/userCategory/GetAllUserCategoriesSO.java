/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.userCategory;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za uzimanje svih kategorija korisnika iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryUserCategory
 * koja je tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetAllUserCategoriesSO  extends AbstractSO{
	
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUserCategory koja uzima, dodaje, azurira i
	 * i brise kategorije korisnika iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory
	 */
    RepositoryUserCategory repositoryUserCategory;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryUserCategory.
     */
    public GetAllUserCategoriesSO() {
        repositoryUserCategory= new RepositoryUserCategory();
    }
    

    /**
     * Metoda nema nikakve uslove da proverava.
     */
    @Override
    protected void precondition(Object param) throws Exception {
       
    }

    /**
     * Metoda vraca iz baze sve kategorije korisnika. Ako dodje do greske baca exception.
     * @return sve kategorije korisnika iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        return repositoryUserCategory.getAll();
    }
    
}
