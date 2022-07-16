/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za brisanje zaposlenog iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryWorker
 * koji je tipa klase koje se nalaze na serverskoj strani.
 *
 * @author Simona
 * @version 1.0.0
 */
public class RemoveWorkerSO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryWorker koja uzima, dodaje, azurira i
	 * i brise zaposlene iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker
	 */
    private RepositoryWorker repositoryWorker;

    /**
     * Kontruktor koji inicijalizuje atribut repositoryWorker.
     */
    public RemoveWorkerSO() {
        repositoryWorker=new RepositoryWorker();
    }
    

    /**
     * Metoda proverava da li je uneseni parametar null ili nije instanca klase
     * Worker. Ako je neki uslov ispunjen baca se Exception i prikazuje poruka
     * "Poslati objekat je neodgovarajuceg tipa!" Ako nije kreira se promenljiva
     * worker tipa Worker i prosledjuje se drugim metodama za proveru. 
     */
    @Override
    protected void precondition(Object param) throws Exception {
         if (param == null || !(param instanceof Worker)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
             Worker work=(Worker) param;
            checkValueConstraints(work);
        }
         }

    /**
     * Metoda brise zaposlenog iz baze. Ako dodje do greske baca se Exception.
     * 
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryWorker.delete((Worker)param);
        return null;
          }

    /**
     * Metoda koja proverava da li je vrednost id zaposlenog null.
     * 
     * @param work tipa Worker zaposleni za koga proveravamo da li je id null
     * @throws java.lang.Exception ako je id vrednost zaposlenog null i prikazuje
     * poruku "Id radnika nije poslat!"
     */
    private void checkValueConstraints(Worker work) throws Exception {
         if (work.getId().equals(null)) {
            throw new Exception("Id radnika nije poslat!");
        }
            }
    
}
