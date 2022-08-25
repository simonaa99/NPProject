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
     * @throws java.lang.IllegalArgumentException ako je uneti id korisnika nula ili manji od nule
	 */
    public void setUserId(Long userId) {
    	if (userId <= 0)
			throw new IllegalArgumentException("Id korisnika mora bii veci od nule");
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
     * @throws java.lang.NullPointerException ako je uneto ime korisnika null
	 * @throws java.lang.IllegalArgumentException ako je uneto ime korisnika prazan String
	 */
    public void setName(String name) {
    	if (name == null)
			throw new NullPointerException("Ime korisnika ne sme biti null");
		
		if (name.isEmpty())
			throw new IllegalArgumentException("Ime korisnika ne sme biti prazan string");
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
     * @throws java.lang.NullPointerException ako je uneto prezime korisnika null
	 * @throws java.lang.IllegalArgumentException ako je uneto prezime korisnika prazan String
	 */
    public void setLastName(String lastName) {
    	if (lastName == null)
			throw new NullPointerException("Prezime korisnika ne sme biti null");
		
		if (lastName.isEmpty())
			throw new IllegalArgumentException("Prezime korisnika ne sme biti prazan string");
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
     * @throws java.lang.NullPointerException ako je uneti broj telefona null
	 * @throws java.lang.IllegalArgumentException ako je uneti broj telefona prazan String
	 */
    public void setPhoneNumber(String phoneNumber) {
    	if (phoneNumber == null)
			throw new NullPointerException("Broj telefona ne sme biti null");
		
		if (phoneNumber.isEmpty())
			throw new IllegalArgumentException("Broj telefona ne sme biti prazan string");
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
     * @throws java.lang.NullPointerException ako je uneta adresa null
	 * @throws java.lang.IllegalArgumentException ako je uneta adresa prazan String
	 */
    public void setAddress(String address) {
    	if (address == null)
			throw new NullPointerException("Adresa ne sme biti null");
		
		if (address.isEmpty())
			throw new IllegalArgumentException("Adresa ne sme biti prazan string");
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
     * @throws java.lang.NullPointerException ako je uneta kategorija korisnika null
	 */
    public void setUserCategory(UserCategory userCategory) {
    	if (userCategory == null)
			throw new NullPointerException("Kategorija korisnika ne sme biti null");
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
     * @throws java.lang.NullPointerException ako je uneta kartica korisnika null
	 */
    public void setUsercard(UserCard usercard) {
    	if (usercard == null)
			throw new NullPointerException("Kartica korisnika ne sme biti null");
        this.usercard = usercard;
    }
    
    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

    /**
	 * Poredi dva korsinika po id-ju, imenu i prezimenu
	 * 
	 * @return
	 * <ul>
	 * <li>true - ako je id, ime i prezime isti kod oba korisnika</li>
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
		User other = (User) obj;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
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
