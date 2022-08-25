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
 * Takodje, nalaze se i get i set metode.
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
     * Postavlja novu vrednost id kategorije korisnika.
     * @param UserCategoryId nova vrednost atributa UserCategoryId
     * @throws java.lang.IllegalArgumentException ako je uneti id kategorije korisnika nula ili manji od nule
	 */
    public void setUserCategoryId(Long UserCategoryId) {
    	if (UserCategoryId <= 0)
			throw new IllegalArgumentException("Id kategorije korisnika mora bii veci od nule");
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
     * Postavlja novu vrednost naziva kategorije korisnika.
     * @param name nova vrednost atributa name
     * @throws java.lang.NullPointerException ako je uneti naziv izdavaca null
	 * @throws java.lang.IllegalArgumentException ako je uneti naziv kategorije korisnika prazan String
	 */
    public void setName(String name) {
    	if (name == null)
			throw new NullPointerException("Naziv kategorije korisnika ne sme biti null");
		
		if (name.isEmpty())
			throw new IllegalArgumentException("Naziv kategorije korisnika ne sme biti prazan string");
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
     * @throws java.lang.IllegalArgumentException ako je uneti vrednost popusta nula ili manji od nule
	 */
    public void setMembershipFeeDiscount(Double membershipFeeDiscount) {
    	if (membershipFeeDiscount <= 0)
			throw new IllegalArgumentException("Vrednost popusta mora bii veci od nule");
        this.membershipFeeDiscount = membershipFeeDiscount;
    }

    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UserCategoryId == null) ? 0 : UserCategoryId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

    /**
	 * Poredi dve kategorije korisnika po id-ju i nazivu
	 * 
	 * @return
	 * <ul>
	 * <li>true - ako je id i naziv isti kod obe kategorije korisnika</li>
	 * <li>false - ako to nije slucaj</li>
	 * </ul>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCategory other = (UserCategory) obj;
		if (UserCategoryId == null) {
			if (other.UserCategoryId != null)
				return false;
		} else if (!UserCategoryId.equals(other.UserCategoryId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
