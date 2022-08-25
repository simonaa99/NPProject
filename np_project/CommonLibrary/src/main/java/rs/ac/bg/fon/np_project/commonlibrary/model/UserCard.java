/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Predstavlja domensku klasu za clansku karticu korisnika.
 * Sadrzi atribute: id, CardNumber, issueDate i expiryDate.
 * Ovi atributi su vezani za identifikaciju clanske kartice korisnika.
 * Takodje, nalaze se i get i set metode zajedno sa metodama iz klase AbstractDO.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class UserCard extends AbstractDO implements Serializable{
    
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka.
	 * Jedinstven je za svaku clansku karticu.
	 * Vrednost atributa je Long.
	 */
    private Long id;
    
    /**
	 * Atribut koji predstavlja broj clanske kartice.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String CardNumber;
    
    /**
   	 * Atribut koji predstavlja datum kad se izdala clanska kartica.
   	 * Vrednost atributa je LocalDate.
   	 */
    private LocalDate issueDate;
    
    /**
   	 * Atribut koji predstavlja datum kad clanska kartica istice.
   	 * Vrednost atributa je LocalDate.
   	 */
    private LocalDate expiryDate;

    
    /**
     * Vraca id clanske kartice.
     * @return id clanske kartice kao Long
	 */
    public Long getId() {
        return id;
    }
    
    /**
     * Postavlja novu vrednost id clanske kartice.
     * @param id nova vrednost atributa id
     * @throws java.lang.IllegalArgumentException ako je uneti id clanske kartice nula ili manji od nule
	 */
    public void setId(Long id) {
    	if (id <= 0)
			throw new IllegalArgumentException("Id clanske kartice mora bii veci od nule");
        this.id = id;
    }

    /**
     * Vraca broj clanske kartice.
     * @return broj clanske kartice kao String
	 */
    public String getCardNumber() {
        return CardNumber;
    }

    /**
     * Postavlja novu vrednost broja clanske kartice.
     * @param CardNumber nova vrednost atributa CardNumber
     * @throws java.lang.NullPointerException ako je uneti broj clanske kartice null
	 * @throws java.lang.IllegalArgumentException ako je uneti broj clanske kartice prazan String
	 */
    public void setCardNumber(String CardNumber) {
    	if (CardNumber == null)
			throw new NullPointerException("Broj clanske kartice ne sme biti null");
		
		if (CardNumber.isEmpty())
			throw new IllegalArgumentException("Broj clanske kartice ne sme biti prazan string");
        this.CardNumber = CardNumber;
    }

    /**
     * Vraca datum kad je izdata clanska kartica.
     * @return datum kad je izdata clanska kartica kao LocalDate
	 */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Postavlja novu vrednost datuma izdavanja clanske kartice.
     * @param issueDate nova vrednost atributa issueDate
     * @throws java.lang.NullPointerException ako je uneti datuma izdavanja clanske kartice null
	 */
    public void setIssueDate(LocalDate issueDate) {
    	if (issueDate == null)
			throw new NullPointerException("Datuma izdavanja clanske kartice ne sme biti null");
        this.issueDate = issueDate;
    }

    /**
     * Vraca datum kad istice clanska kartica.
     * @return datum kad istice clanska kartica kao LocalDate
	 */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Postavlja novi datum isteka clanske kartice.
     * @param expiryDate nova vrednost atributa expiryDate
     * @throws java.lang.NullPointerException ako je uneti datum isteka clanske kartice null
	 */
    public void setExpiryDate(LocalDate expiryDate) {
    	if (expiryDate == null)
			throw new NullPointerException("Datum isteka clanske kartice ne sme biti null");
        this.expiryDate = expiryDate;
    }
    
    
    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CardNumber == null) ? 0 : CardNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

    /**
	 * Poredi dve clanske kartice po id-ju i broju kartice
	 * 
	 * @return
	 * <ul>
	 * <li>true - ako je id i broj kartice isti kod obe clanske kartice</li>
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
		UserCard other = (UserCard) obj;
		if (CardNumber == null) {
			if (other.CardNumber != null)
				return false;
		} else if (!CardNumber.equals(other.CardNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
     * Vraca broj clanske kartice.
     *
     * @return String koji predstavlja broj clanske kartice.
     */
    @Override
    public String toString() {
       return CardNumber; 
    }

    @Override
    public String getAttributeList() {
        return "brojClanskeKarte, datumIzdavanja, datumIsteka";
    }

    @Override
    public String getClassName() {
        return "clanskaKarta";
    }

    @Override
    public String getAttributeValues() {
        return "'"+CardNumber+"', '"+Date.valueOf(issueDate)+"', '"+Date.valueOf(expiryDate)+"'";
    }

    @Override
    public String getQueryCondition() {
        return "brojClanskeKarte= '"+CardNumber+"'";
    }
    
}
