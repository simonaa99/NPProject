/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.np_project.server.so.user;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih korisnika iz baze pomocu upita.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryUser koji je
 * tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetUsersByQuerySO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUser koja uzima, dodaje, azurira i
	 * i brise korisnike iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser
	 */
    RepositoryUser repositoryUser;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryUser
     */
    public GetUsersByQuerySO() {
        repositoryUser=new RepositoryUser();
    }
    

    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase String.
     * Ako je neki od ovih uslova ispunjen baca Exception i prikazuje poruku
     * "Poslati objekat je neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
      
    }

    /**
     * Metoda kreira listu svih korisnika. Pomocu upita iz baze ih ubacuje u listu.
     * Ako dodje do greske pri radu sa bazom baca se Exception i prikazuje poruka
     * "Greska prilikom ucitavanja igara."
     * 
     * @return lista svih korisnika
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
         List<User> users = new ArrayList<>();
        String query=(String) param;
        try {
            users = repositoryUser.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja igara.", e);
        }
        return users;
        }
    
}
