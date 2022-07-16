/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.user;

import java.util.List;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih korisnika iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryUser koji je
 * tipa klase koje se nalaze na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetAllUsersSO extends AbstractSO{
	/**
	 * Predstavlja atribut koji je tipa klase RepositoryUser koja uzima, dodaje, azurira i
	 * i brise korisnike iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser
	 */
    RepositoryUser repositoryUser;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryUser
     */
    public GetAllUsersSO() {
        repositoryUser=new RepositoryUser();
    }
    

    /**
     * Metoda ne proverava nikakve uslove.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        //no preconditions
         }

    /**
     * Metoda koja vraca sve korisnike iz baze.
     * @return sve okorisnike iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        try { 
            String query = "SELECT * FROM clan ORDER BY ime ASC";
            return repositoryUser.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja korisnika.", e);
        }
    }
    
}
