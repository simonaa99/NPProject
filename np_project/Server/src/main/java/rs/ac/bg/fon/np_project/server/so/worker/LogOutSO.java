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
 * Predstavlja klasu u kojoj se izvrsavaju metode za izlogovanje zaposlenog iz aplikaciju.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO, konstruktor i atribut repositoryWorker
 * koji je tipa klase koje se nalaze na serverskoj strani.
 *
 * @author Simona
 * @version 1.0.0
 */
public class LogOutSO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryWorker koja uzima, dodaje, azurira i
	 * i brise zaposlene iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker
	 */
    private RepositoryWorker RepositoryWorker;

    /**
     * Kontruktor koji inicijalizuje atribut repositoryWorker.
     */
    public LogOutSO() {
        RepositoryWorker=new RepositoryWorker();
    }
    
    /**
     * Metoda proverava da li je uneseni parametar null ili nije instanca klase
     * Worker. Ako je neki uslov ispunjen baca se Exception i prikazuje poruka
     * "Poslati objekat je neodgovarajuceg tipa!" Ako nije proverava da li je 
     * username ili sifra zaposlenog null. Ako jeste baca Exception i poruku
     *  "Nisu poslati kredencijali!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
    if (param == null || !(param instanceof Worker)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } 
        if(((Worker)param).getUsername().equals(null) || ((Worker)param).getPassword().equals(null))
            throw new Exception("Nisu poslati kredencijali!");
    }

    /**
     * Metoda kreira promenljivu w kao tip klase Worker i prosledjuje da se zadati
     * zaposleni izloguje iz sistema. Ako dodje do greske baca Exception.
     * 
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        Worker w=(Worker) param;
        RepositoryWorker.setUserIsLoggedOut(w);
        return null;
        
            }
    
}
