/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih iznajmljivanja drustvenih
 * igara za odredjenog korisnike iz baze. Sadrzi implementaciju metoda iz nadklase AbstractSO i atribut repositoryRent 
 * koji je tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetAllUserRentsSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryRent koja uzima, dodaje, azurira i
	 * i brise iznajmljivanja iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent
	 */
    RepositoryRent repositoryRent;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryRent.
     */
    public GetAllUserRentsSO() {
        repositoryRent = new RepositoryRent();
    }

    /**
     * Metoda proverava da li je uneti objekat null ili nije instanca klase User. 
     * Ako je neki od ovih uslova ispunjen baca Exception i poruku
     * "Poslat je parametar neodgovarajuceg tipa!"
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslat je parametar neodgovarajuceg tipa!");
        }
    }

    /**
     * Metoda pravi listu svih iznajmljivanja drustvenih igara za odredjenog korisnika
     * iz baze i vraca tu listu. Ako dodje do greske baca Exception.
     * 
     * @return listu svih iznajmljivanja svih iznajmljivanja drustvenih igara za odredjenog korisnika
     * iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Rent> rents = repositoryRent.getAllUserRents((User) param);
        return rents;
    }

}
