/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za dodavanje zaposlenog u bazu.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryWorker
 * i dbBroker koje su tipa klase koje se nalaze na serverskoj strani.
 *
 * @author Simona
 * @version 1.0.0
 */
public class AddWorkerSO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryWorker koja uzima, dodaje, azurira i
	 * i brise zaposlene iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker
	 */
    private RepositoryWorker repositoryWorker;
    
    /**
	 * Predstavlja atribut koji je tipa klase DatabaseBroker koja sluzi za izvrsenje 
	 * sistemskih operacija u bazi podataka.
	 * @see rs.ac.bg.fon.np_project.server.broker.DatabaseBroker
	 */
    private DatabaseBroker dbBroker;

    /**
     * Kontruktor koji inicijalizuje atribute repositoryWorker i dbBroker.
     */
    public AddWorkerSO() {
        repositoryWorker=new RepositoryWorker();
        dbBroker=new DatabaseBroker();
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
            Worker worker=(Worker) param;
            //   checkValueConstraints(user);
            checkStructuralConstraints(worker);
        }
        }

    /**
     * Metoda dodaje zaposlenog u bazu. Ako dodje do greske baca Exception.
     * 
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryWorker.add((Worker)param);
        return null;
       }

    /**
     * Metoda proverava da li postoji zaposleni u bazi sa istim korisnickim imenom.
     * 
     * @param worker tipa Worker zaposlen koga zelimo da dodamo
     * @throws java.lang.Exception kada postoji vec takvo korisnicko ime zaposlenog
     * u bazi i prikazuje poruku "Korisnicko ime je u upotrebi. Pokusajte ponovo."
     */
    private void checkStructuralConstraints(Worker worker) throws Exception {
         boolean exists = checkIfExists(worker);
        if (exists) {
            throw new Exception("Korisnicko ime je u upotrebi. Pokusajte ponovo.");
        }
            }

    /**
     * Metoda proverava da li postoji zaposleni sa istim podacima u bazi.
     * 
     * @param worker tipa Worker zaposleni za koga proveravamo da li postoji vec u bazi
     * @return boolean da li postoji ili ne takav isti korisnik
     * @throws java.lang.Exception kada postoji vec takav zaposleni
     * u bazi i prikazuje poruku "Greska prilikom provere postojanja radnika u bazi."
     */
    private boolean checkIfExists(Worker worker) throws Exception {
         try {
            return repositoryWorker.checkIfExists(worker);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja radnika u bazi.", e);
        }
           }
    
}
