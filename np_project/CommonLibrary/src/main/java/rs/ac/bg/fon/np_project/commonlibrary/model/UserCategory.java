/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 * Predstavlja domensku klasu kategorije kojoj korisnik pripada.
 * Sadrzi atribute: userCategoryId, name i membershipFeeDiscount.
 * Ovi atributi su vezani za identifikaciju kategorije kojoj korisnik pripada.
 * Takodje, nalaze se i get i set metode zajedno sa metodama iz klase AbstractDO.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class UserCategory implements Serializable{
    
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka.
	 * Jedinstven je za svaku kategoriju.
	 * Vrednost atributa je Long.
	 */
    private Long UserCategoryId;
    
    /**
	 * Atribut koji predstavlja ime kategorije.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String name;
    
    /**
	 * Atribut koji predstavlja popust korisnika koji dobija kao pripadnik odredjene kategorije korisnika.
	 * Vrednost atributa je Double.
	 * Podrazumevana vrednost je 0.0.
	 */
    private Double membershipFeeDiscount;

    
    /**
     * Vraca id kategorije korisnika.
     * @return id kategorije korisnika kao Long
	 */
    public Long getUserCategoryId() {
        return UserCategoryId;
    }

    /**
     * Postavlja novu vrednost id kategorije.
     * @param UserCategoryId nova vrednost atributa UserCategoryId
	 */
    public void setUserCategoryId(Long UserCategoryId) {
        this.UserCategoryId = UserCategoryId;
    }

    /**
     * Vraca naziv kategorije.
     * @return naziv kategorije kao String
	 */
    public String getName() {
        return name;
    }

    /**
     * Postavlja novu vrednost naziva kategorije.
     * @param name nova vrednost atributa name
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraca popust korisnika koji ima kao clan neke kategorije.
     * @return popust korisnika kao Double
	 */
    public Double getMembershipFeeDiscount() {
        return membershipFeeDiscount;
    }

    /**
     * Postavlja novu vrednost popusta korisnika.
     * @param membershipFeeDiscount nova vrednost atributa membershipFeeDiscount
	 */
    public void setMembershipFeeDiscount(Double membershipFeeDiscount) {
        this.membershipFeeDiscount = membershipFeeDiscount;
    }

    /**
     * Vraca naziv kategorije.
     *
     * @return String koji predstavlja naziv kategorije .
     */
    @Override
    public String toString() {
       return name;
    }
    
}
