/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.user;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za azuriranje korisnika u bazu.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryUser, repositoryUserCard
 * i repositoryUserCategory koje su tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class UpdateUserSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUser koja uzima, dodaje, azurira i
	 * i brise korisnike iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser
	 */
    RepositoryUser repositoryUser;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryUserCategory koja uzima, dodaje, azurira i
	 * i brise kategorije korisnika iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory
	 */
    RepositoryUserCategory repositoryUserCategory;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryUserCard koja uzima, dodaje, azurira i
	 * i brise clanske kartice iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard
	 */
    private RepositoryUserCard repositoryUserCard;

    /**
     * Konstruktor koji inicijalizuje atribute repositoryUser,
     * repositoryUserCard i repositoryUserCategory.
     */
    public UpdateUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCategory = new RepositoryUserCategory();
        repositoryUserCard = new RepositoryUserCard();
    }

    /**
     * Metoda proverava da li je uneti parametar null. Ako jeste baca Exception i 
     * prikazuje poruku "Nije poslat parametar!" Ako nije kreira listu korisnika i u nju 
     * ubacuje unete parametre. Proverava da li je velicina liste manja od 2 ili 
     * prvi element null ili drugi element null. Ako je neki uslov ispunjen baca se 
     * Exception i prikazuje se poruka "Nisu poslati potrebni parametri!" Onda proverava
     * da li su prva dva elementa liste instanca klase User. Ako nisu baca Exception
     * i prikazuje poruku "Poslati objekat je neodgovarajuceg tipa!" Ako je sve u redu
     * dodaje azuriranog korisnika u listu i prosledjuje daljim metodama
     * za proveru.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Nije poslat parametar!");
        }
        List<User> users = (List<User>) param;

        if (users.size() < 2 || users.get(0).equals(null) || users.get(1).equals(null)) {
            throw new Exception("Nisu poslati potrebni parametri!");
        }
        if (!(users.get(0) instanceof User) || !(users.get(1) instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User newUser = ((List<User>) param).get(1);
            checkStructuralConstraints(newUser);
        } 

    }

    /**
     * Metoda proverava stare podatke i nove podatke korisnika koje smo azurirali i azurira u bazi.
     * Ako dodje do greske baca se Exception i prikazuje se poruka
     * "Greska prilikom izmene podataka o korisniku."
     * 
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<User> usersForUpdate = (List<User>) param;
        User oldUser = usersForUpdate.get(0);
        User newUser = usersForUpdate.get(1);
        String query = "SELECT * FROM kategorijaclanova WHERE naziv='" + newUser.getUserCategory().getName() + "'";
        UserCategory uc = repositoryUserCategory.getByQuery(query).get(0);
        newUser.setUserCategory(uc);
        try {
            repositoryUser.edit(oldUser, newUser);
            if (!oldUser.getUsercard().getCardNumber().equals(newUser.getUsercard().getCardNumber())) {
                repositoryUserCard.updateCardNumber(oldUser.getUsercard(), newUser.getUsercard());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom izmene podataka o korisniku.", e);
        }

    }

    /**
     * Metoda proverava da li korisnik postoji u bazi.
     * Ako postoji baca Exception i prikazuje poruku "Korisnik postoji."
     * @param user tipa User koji predstavlja korisnika kojeg smo azurirali
     * @throws java.lang.Exception ako korisnik postoji u bazi
     */
    private void checkStructuralConstraints(User user) throws Exception {
        boolean exists = checkIfExists(user);
        if (exists) {
            throw new Exception("Korisnik postoji.");
        }

    }

    /**
     * Metoda proverava da li korisnik postoji u bazi. Ako se javi greska baca Exception
     * i prikazuje poruku "Greska prilikom provere postojanja korisnika u bazi."
     * @param user tipa User korisnik koga proveravamo u bazi
     * @return boolean da li korisnik postoji u bazi ili ne
     * @throws java.lang.Exception ako dodje do greske pri pretrazi u bazi
     */
    private boolean checkIfExists(User user) throws Exception {
        try {
            return repositoryUser.checkIfExists(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja korisnika u bazi.", e);
        }
    }

}
