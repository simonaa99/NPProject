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
     * @throws java.lang.IllegalArgumentException ako je uneti id izdavaca nula ili manji od nule
	 */
    public void setPublisherId(Long publisherId) {
    	if (publisherId <= 0)
			throw new IllegalArgumentException("Id izdavaca mora bii veci od nule");
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
     * @throws java.lang.NullPointerException ako je uneti naziv izdavaca null
	 * @throws java.lang.IllegalArgumentException ako je uneti naziv izdavaca prazan String
	 */
    public void setPublisherName(String publisherName) {
    	if (publisherName == null)
			throw new NullPointerException("Naziv izdavaca ne sme biti null");
		
		if (publisherName.isEmpty())
			throw new IllegalArgumentException("Naziv izdavaca ne sme biti prazan string");
        this.publisherName = publisherName;
    }

    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		return result;
	}

    /**
	 * Poredi dva izdavaca po id-ju
	 * 
	 * @return
	 * <ul>
	 * <li>true - ako je id isti kod oba izdavaca</li>
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
		Publisher other = (Publisher) obj;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		return true;
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
