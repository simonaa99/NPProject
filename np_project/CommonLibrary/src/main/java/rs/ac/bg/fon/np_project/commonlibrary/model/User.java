/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 * Predstavlja domensku klasu za korisnika.
 * Sadrzi atribute: userId, name, lastName, phoneNumber, adress, userCategory i usercard.
 * Ovi atributi su vezani za identifikaciju korisnika drustvenih igara.
 * Takodje, nalaze se i get i set metode zajedno sa metodama iz klase AbstractDO.
 *
 * @author Simona
 * @version 1.0.0
 */
public class User extends AbstractDO implements Serializable {
    
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka.
	 * Jedinstven je za svakog korisnika.
	 * Vrednost atributa je Long.
	 */
    private Long userId;
    
    /**
	 * Atribut koji predstavlja ime korisnika.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String name;
    
    /**
	 * Atribut koji predstavlja prezime korisnika.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String lastName;
    
    /**
	 * Atribut koji predstavlja broj telefona korisnika.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String phoneNumber;
    
    /**
	 * Atribut koji predstavlja adresu korisnika.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String address;
    
    /**
	 * Atribut koji predstavlja kategoriju korisnika.
	 * Vrednost atributa je UserCategory gde ima svoj id, ime i popust.
	 * @see rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory
	 */
    private UserCategory userCategory;
    
    /**
	 * Atribut koji predstavlja clansku karticu korisnika.
	 * Vrednost atributa je UserCard gde ima svoj id, broj kartice, datum isteka i izdavanja kartice.
	 * @see rs.ac.bg.fon.np_project.commonlibrary.model.UserCard
	 */
    private UserCard usercard;

    
    /**
     * Vraca id korisnika.
     * @return id korisnika kao Long
	 */
    public Long getUserId() {
        return userId;
    }

    /**
     * Postavlja novu vrednost id korisnika.
     * @param userId nova vrednost atributa userId
	 */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Vraca ime korisnika.
     * @return ime korisnika kao String
	 */
    public String getName() {
        return name;
    }

    /**
     * Postavlja novo ime korisnika.
     * @param name nova vrednost atributa name
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraca prezime korisnika.
     * @return prezime korisnika kao String
	 */
    public String getLastName() {
        return lastName;
    }

    /**
     * Postavlja novo prezime korisnika.
     * @param lastName nova vrednost atributa lastName
	 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Vraca broj telefona korisnika.
     * @return broj telefona korisnika kao String
	 */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Postavlja novu vrednost broja telefona korisnika.
     * @param phoneNumber nova vrednost atributa phoneNumber
	 */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Vraca adresu korisnika.
     * @return adresu korisnika kao String
	 */
    public String getAddress() {
        return address;
    }

    /**
     * Postavlja novu adresu korisnika.
     * @param address nova vrednost atributa address
	 */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Vraca kategoriju korisnika.
     * @return kategorija korisnika kao UserCategory
	 */
    public UserCategory getUserCategory() {
        return userCategory;
    }

    /**
     * Postavlja novu vrednost kategorije korisnika.
     * @param userCategory nova vrednost atributa userCategory
	 */
    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    /**
     * Vraca clansku karticu korisnika.
     * @return clanska kartica korisnika kao UserCard
	 */
    public UserCard getUsercard() {
        return usercard;
    }

    /**
     * Postavlja novu vrednost clanske kartice korisnika.
     * @param usercard nova vrednost atributa usercard
	 */
    public void setUsercard(UserCard usercard) {
        this.usercard = usercard;
    }

    @Override
    public String getAttributeList() {
        return "ime, prezime, brojTelefona, adresa, kategorijaId, clanskaKartaId";
    }

    @Override
    public String getClassName() {
        return "clan";
    }

    @Override
    public String getAttributeValues() {
         return "'"+name+"', '"+lastName+"', '"+phoneNumber+"', '"+address+"', "+userCategory.getUserCategoryId()+", "+usercard.getId();
    }

    @Override
    public String getQueryCondition() {
        return "id="+userId;
    }
    
}
