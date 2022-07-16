/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih iznajmljivanja drustvenih
 * igara iz baze. Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryRent 
 * koji je tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetAllRentsSO extends AbstractSO{
	
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryRent koja uzima, dodaje, azurira i
	 * i brise iznajmljivanja iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent
	 */
    RepositoryRent repositoryRent;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryRent.
     */
    public GetAllRentsSO() {
        repositoryRent=new RepositoryRent();
    }
    

    /**
     * Ne proverava nikakve uslove
     */
    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
    }

    /**
     * Metoda vraca sva iznajmljivanja iz baze. Ako dodje do greske baca Exception.
     * 
     * @return sva iznajmljivanja iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
       return repositoryRent.getAll();
    }
    
}
