/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 * Predstavlja domensku klasu za izdavaca(proizvodjaca) drustvenih igara.
 * Atributi su: publisherId i publisherName.
 * Ovi atributi su vezani za identifikaciju izdavaca drustvenih igara.
 * Takodje, nalaze se i get i set metode zajedno sa metodama iz klase AbstractDO.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class Publisher extends AbstractDO implements Serializable{
    
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka.
	 * Jedinstven je za svakog izdavaca.
	 * Vrednost atributa je Long.
	 */
    private Long publisherId;
    
    /**
	 * Atribut koji predstavlja naziv izdavaca.
	 * Vrednost atributa je String.
	 * Podrazumevana vrednost je null.
	 */
    private String publisherName;

    /**
     * Konstruktor koji postavlja atribute publisherId i publisherName na unete vrednosti.
     *
     * @param publisherID       nova vrednost atributa publisherId
     * @param imePrezime     nova vrednost atributa publisherName
     */
    public Publisher(Long publisherID, String imePrezime) {
        this.publisherId = publisherID;
        this.publisherName = imePrezime;
    }
    
    /**
     * Konstruktor koji postavlja atribute na njihove podrazumevane vrednosti.
     */
    public Publisher() {
         }

    /**
     * Vraca id izdavaca.
     * @return id izdavaca kao Long
	 */
    public Long getPublisherId() {
        return publisherId;
    }

    /**
     * Postavlja novu vrednost id izdavaca.
     * @param publisherId nova vrednost atributa publisherId
	 */
    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * Vraca naziv izdavaca.
     * @return naziv izdavaca kao String
	 */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * Postavlja novu vrednost naziva izdavaca.
     * @param publisherName nova vrednost atributa publisherName
	 */
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    /**
     * Vraca ime izdavaca.
     *
     * @return String koji predstavlja ime izdavaca.
     */
    @Override
    public String toString() {
        return publisherName;   }

    @Override
    public String getAttributeList() {
        return "imePrezime";
    }

    @Override
    public String getClassName() {
        return "izdavac";
    }

    @Override
    public String getAttributeValues() {
        return "'"+getPublisherName()+"'";
    }

    @Override
    public String getQueryCondition() {
        return "id= "+getPublisherId();
    }
    
}
