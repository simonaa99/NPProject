/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.user;

import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za brisanje korisnika iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryUser i repositoryUserCard
 * koje su tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class DeleteUserSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUser koja uzima, dodaje, azurira i
	 * i brise korisnike iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser
	 */
    RepositoryUser repositoryUser;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryUserCard koja uzima, dodaje, azurira i
	 * i brise clanske kartice iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard
	 */
    RepositoryUserCard repositoryUserCard;

    /**
     * Konstruktor koji inicijalizuje atribute repositoryUser i
     * repositoryUserCard.
     */
    public DeleteUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCard = new RepositoryUserCard();
    }

    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase User.
     * Ako je neki od ovih uslova ispunjen baca Exception i prikazuje poruku
     * "Poslati objekat je neodgovarajuceg tipa!"
     * Ako nije onda kreira user promenljivu tipa User i prosledjuje je 
     * drugim metodama za proveru.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User user = (User) param;
            checkValueConstraints(user);
            checkStructuralConstraints(user);
        }
    }

    /**
     * Metoda kreira promenljivu user tipa User i prosledjuje bazi da treba taj korisnik
     * da se obrise. Ako dodje do greske baca se Exception i prikazuje poruku
     * "Greska prilikom brisanja korisnika."
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        User user = (User) param;
        try {
            repositoryUser.delete(user);
            repositoryUserCard.delete(user.getUsercard());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom brisanja korisnika." + e.getMessage(), e);
        }
        
    }

    /**
     * Metoda proverava da li korisnik vec ima neka iznajmljivanja igara.
     * @param user tipa User korisnik kojem se proverava iznajmljivanje
     * @throws java.lang.Exception kada dodje do greske prilikom provere
     */
    private void checkStructuralConstraints(User user) throws Exception {
        checkRentsExist(user);
    }

    /**
     * Metoda proverava da li je vrednost id user-a null. Ako jeste baca Exception i prikazuje
     * poruku "Id korisnika nije poslat!"
     * @param user tipa User korisnik ciji se id proverava da li postoji 
     * @throws java.lang.Exception ako id ne postoji
     */
    private void checkValueConstraints(User user) throws Exception {
        if (user.getUserId().equals(null)) {
            throw new Exception("Id korisnika nije poslat!");
        }
    }

    /**
     * Metoda proverava da li korisnik kojeg zelimo da izbrisemo ima vec neka iznajmljivanja 
     * koje nije vratio. Ako postoje baca se Exception i prikazuje poruka 
     * "Korisnik je zaduzio igru. Nije moguce dovrsiti operaciju brisanja."
     * @param user tipa User korisnik kojeg zelimo da obrisemo
     * @throws java.lang.Exception ako korisnik i dalje ima neka zaduzenja
     */
    private void checkRentsExist(User user) throws Exception {
        if (repositoryUser.checkIfRentsExist(user)) {
            throw new Exception("Korisnik je zaduzio igru. Nije moguce dovrsiti operaciju brisanja.");
        }
    }
}
