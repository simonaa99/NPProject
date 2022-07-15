/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 * Predstavlja domensku klasu zaposlenog.
 * Sadrzi atribute: id, username, password, firstName, lastName i loggedIn.
 * Ovi atributi su vezani za identifikaciju zaposlenog.
 * Takodje, nalaze se i get i set metode zajedno sa metodama iz klase AbstractDO.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class Worker extends AbstractDO implements Serializable{
    
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka.
	 * Jedinstven je za svakog zaposlenog.
	 * Vrednost atributa je Long.
	 */
    private Long id;
    
    /**
	 * Atribut koji predstavlja username zaposlenog.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String username;
    
    /**
	 * Atribut koji predstavlja sifru zaposlenog.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String password;
    
    /**
	 * Atribut koji predstavlja ime zaposlenog.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String firstName;
    
    /**
	 * Atribut koji predstavlja prezime zaposlenog.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String lastName;
    
    /**
	 * Atribut koji predstavlja da li je zaposleni ulogovan na aplikaciju.
	 * Vrednost atributa je boolean.
	 * Podrazumevana vrednost je false.
	 */
    private boolean loggedIn;
    
    
    /**
     * Vraca da li je zaposleni ulogovan na aplikaciju.
     * @return da li je zaposleni ulogovan kao boolean
	 */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Postavlja novu vrednost da li je zaposleni ulogovan ili ne.
     * @param loggedIn nova vrednost atributa loggedIn
	 */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    /**
     * Vraca ime zaposlenog.
     * @return ime zaposlenog kao String
	 */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Postavlja novo ime zaposlenom.
     * @param firstName nova vrednost atributa firstName
	 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Vraca prezime zaposlenog.
     * @return prezime zaposlenog kao String
	 */
    public String getLastName() {
        return lastName;
    }

    /**
     * Postavlja novo prezime zaposlenom.
     * @param lastName nova vrednost atributa lastName
	 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

    /**
     * Konstruktor koji postavlja atribute id, username i password na unete vrednosti.
     *
     * @param id       	   nova vrednost atributa id
     * @param username     nova vrednost atributa username
     * @param password     nova vrednost atributa password
     */
    public Worker(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Konstruktor koji postavlja atribute na njihove podrazumevane vrednosti.
     */
    public Worker() {
    }

    /**
     * Vraca id zaposlenog.
     * @return id zaposlenog kao Long
	 */
    public Long getId() {
        return id;
    }

    /**
     * Postavlja novu vrednost id zaposlenog.
     * @param id nova vrednost atributa id
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Vraca username zaposlenog.
     * @return username zaposlenog kao String
	 */
    public String getUsername() {
        return username;
    }

    /**
     * Postavlja novu vrednost username-a zaposlenog.
     * @param username nova vrednost atributa username
	 */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Vraca sifru zaposlenog.
     * @return sifru zaposlenog kao String
	 */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja novu sifru zaposlenog.
     * @param password nova vrednost atributa password
	 */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getAttributeList() {
        return "username, password, ime, prezime";
          }

    @Override
    public String getClassName() {
        return "radnik";
    }

    @Override
    public String getAttributeValues() {
        return "'"+username+"', '"+password+"', '"+firstName+"', '"+lastName+"'";
         }

    @Override
    public String getQueryCondition() {
        return "radnikID="+id;
    }
            
}
