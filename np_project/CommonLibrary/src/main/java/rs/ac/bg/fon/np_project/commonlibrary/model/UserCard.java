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
	 */
    public void setId(Long id) {
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
	 */
    public void setCardNumber(String CardNumber) {
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
	 */
    public void setIssueDate(LocalDate issueDate) {
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
	 */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
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
