/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih zaposlenih iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryWorker
 * koji je tipa klase koje se nalaze na serverskoj strani.
 *
 * @author Simona
 * @version 1.0.0
 */
public class GetWorkersSO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryWorker koja uzima, dodaje, azurira i
	 * i brise zaposlene iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker
	 */
   private RepositoryWorker repositoryWorker;

   /**
    * Kontruktor koji inicijalizuje atribut repositoryWorker.
    */
    public GetWorkersSO() {
        repositoryWorker=new RepositoryWorker();
    }
   

    /**
     * Metoda nema nikakve uslove da proverava.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        //no recondition to check
           }

    /**
     * Metoda vraca sve zaposlene iz baze. Ako dodje do greske baca Exception.
     * 
     * @return svi zaposleni iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        return repositoryWorker.getAll();
           }
    
}
