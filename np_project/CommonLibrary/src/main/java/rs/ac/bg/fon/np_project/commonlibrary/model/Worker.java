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
     * @throws java.lang.NullPointerException ako je uneto ime zaposlenog null
	 * @throws java.lang.IllegalArgumentException ako je uneto ime zaposlenog prazan String
	 */
    public void setFirstName(String firstName) {
    	if (firstName == null)
			throw new NullPointerException("Ime zaposlenog ne sme biti null");
		
		if (firstName.isEmpty())
			throw new IllegalArgumentException("Ime zaposlenog ne sme biti prazan string");
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
     * @throws java.lang.NullPointerException ako je uneto prezime zaposlenog null
	 * @throws java.lang.IllegalArgumentException ako je uneto prezime zaposlenog prazan String
	 */
    public void setLastName(String lastName) {
    	if (lastName == null)
			throw new NullPointerException("Prezime zaposlenog ne sme biti null");
		
		if (lastName.isEmpty())
			throw new IllegalArgumentException("Prezime zaposlenog ne sme biti prazan string");
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
     * @throws java.lang.IllegalArgumentException ako je uneti id zaposlenog nula ili manji od nule
	 */
    public void setId(Long id) {
    	if (id <= 0)
			throw new IllegalArgumentException("Id zaposlenog mora bii veci od nule");
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
     * @throws java.lang.NullPointerException ako je uneti username zaposlenog null
	 * @throws java.lang.IllegalArgumentException ako je uneti username zaposlenog prazan String
	 */
    public void setUsername(String username) {
    	if (username == null)
			throw new NullPointerException("Username zaposlenog ne sme biti null");
		
		if (username.isEmpty())
			throw new IllegalArgumentException("Username zaposlenog ne sme biti prazan string");
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
     * @throws java.lang.NullPointerException ako je uneta sifra zaposlenog null
	 * @throws java.lang.IllegalArgumentException ako je uneta sifra zaposlenog prazan String
	 */
    public void setPassword(String password) {
    	if (password == null)
			throw new NullPointerException("SIfra zaposlenog ne sme biti null");
		
		if (password.isEmpty())
			throw new IllegalArgumentException("Sifra zaposlenog ne sme biti prazan string");
        this.password = password;
    }

    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

    /**
	 * Poredi dva zaposlenog po id-ju, imenu i prezimenu
	 * 
	 * @return
	 * <ul>
	 * <li>true - ako je id, ime i prezime isti kod oba zaposlenog</li>
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
		Worker other = (Worker) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
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
