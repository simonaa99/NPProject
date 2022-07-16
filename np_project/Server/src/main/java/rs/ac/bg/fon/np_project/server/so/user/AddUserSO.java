/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.user;

import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za dodavanje korisnika u bazu.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryUser, repositoryUserCard
 * i repositoryUserCategory koje su tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class AddUserSO extends AbstractSO {

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
	 * Predstavlja atribut koji je tipa klase RepositoryUserCategory koja uzima, dodaje, azurira i
	 * i brise kategorije korisnika iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory
	 */
    RepositoryUserCategory repositoryUserCategory;

    /**
     * Konstruktor koji inicijalizuje atribute repositoryUser,
     * repositoryUserCard i repositoryUserCategory.
     */
    public AddUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCard=new RepositoryUserCard();
        repositoryUserCategory=new RepositoryUserCategory();
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
            //   checkValueConstraints(user);
            checkStructuralConstraints(user);
        }
    }

    /**
     * Metoda kreira promenljivu u tipa User. Kreira clansku karticu i kategoriju
     * korisnika. Na kraju dodaje korisnika u bazu.
     * @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        User u=(User) param;
        repositoryUserCard.add(u.getUsercard());
        String query="SELECT * FROM clanskakarta WHERE brojClanskeKarte= '"+u.getUsercard().getCardNumber()+"'";
        UserCard card=repositoryUserCard.getByQuery(query).get(0);
        u.setUsercard(card);
        query = "SELECT * FROM kategorijaclanova WHERE naziv='" + u.getUserCategory().getName() + "'";
        UserCategory category=repositoryUserCategory.getByQuery(query).get(0);
        u.setUserCategory(category);
        repositoryUser.add(u);
        
        return null;
    }

    /**
     * Metoda proverava da li korisnik postoji vec u bazi.
     * 
     * @param user tipa User koja predstavlja korisnika kojeg zelimo da dodamo u bazu
     * @throws java.lang.Exception kada korisnik postoji vec u bazi i prikazuje poruku
     * "Korisnik postoji."
     */
    private void checkStructuralConstraints(User user) throws Exception {
        boolean exists = checkIfExists(user,false);
        if (exists) {
            throw new Exception("Korisnik postoji.");
        }

    }

    /**
     * Metoda proverava da li korisnik sa odredjenom clanskom karticom postoji u bazi.
     * 
     * @param user tipa User korisnik koga zelimo da dodamo u bazu
     * @param includeUserCard tipa boolean da li korisnik ima clansku karticu ili ne
     * @return boolean da li postoji korisnik sa tom clanskom karticom 
     * @throws java.lang.Exception kada dodje do greske prilikom provere i prikazuje
     * poruku "Greska prilikom provere postojanja korisnika u bazi."
     */
    private boolean checkIfExists(User user, boolean includeUserCard) throws Exception {
        try {
            return repositoryUser.checkIfExists(user, includeUserCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja korisnika u bazi.", e);
        }
    }

}
