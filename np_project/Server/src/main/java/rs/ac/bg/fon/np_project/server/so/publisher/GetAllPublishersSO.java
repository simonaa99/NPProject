/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.publisher;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih izdavaca drustvenih
 * igara iz baze. Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryPublisher 
 * koja je tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetAllPublishersSO extends AbstractSO{
	
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryPublisher koja uzima, dodaje, azurira i
	 * i brise izdavace iz baze. 
	 */
    RepositoryPublisher repositoryPublisher;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryPublisher.
     */
    public GetAllPublishersSO() {
        repositoryPublisher=new RepositoryPublisher();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
           }

    @Override
    protected Object executeOperation(Object param) throws Exception {
       return repositoryPublisher.getAll();
    }
    
}
