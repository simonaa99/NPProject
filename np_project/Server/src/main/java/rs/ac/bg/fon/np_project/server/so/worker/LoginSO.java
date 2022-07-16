/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za logovanje zaposlenog u aplikaciju.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO, konstruktor i atribut repositoryWorker
 * koji je tipa klase koje se nalaze na serverskoj strani.
 *
 * @author Simona
 * @version 1.0.0
 */
public class LoginSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryWorker koja uzima, dodaje, azurira i
	 * i brise zaposlene iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker
	 */
    private RepositoryWorker repositoryWorker;

    /**
     * Kontruktor koji inicijalizuje atribut repositoryWorker.
     */
    public LoginSO() {
        repositoryWorker = new RepositoryWorker();
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
     * Metoda kreira listu zaposlenih, promenljive ulogovanog zaposlenog i zaposlenog. 
     * Iz baze vraca listu svih zaposlenih i proverava koji zaposlen se trenutno loguje.
     * Ako je vec ulogovan baca Exception i poruku "Korisnik je vec prijavljen." Ako ne
     * postavlja da je ulogovan. Ako dodje do greske baca Exception ili ako ne nadje korisnika baca Exception sa 
     * porukom "Nepoznat korisnik!"
     * 
     * @return ulogovanog zaposlenog
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Worker> workers;
        Worker currentUser;
        Worker user = (Worker) param;
        try {
            workers = (List<Worker>) repositoryWorker.getAll();
            for (Worker r : workers) {
                if (r.getUsername().equals(user.getUsername()) && r.getPassword().equals(user.getPassword())) {
                    currentUser = r;
                    if(currentUser.isLoggedIn()==true){
                    throw  new Exception("Korisnik je vec prijavljen.");
                    }
                    repositoryWorker.setUserIsLoggedIn(currentUser);
                    return currentUser;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
       
        }
        throw new Exception("Nepoznat korisnik!");

    }

}
