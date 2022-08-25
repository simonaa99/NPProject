/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 * Predstavlja domensku klasu za drustvenu igru
 * Sadrzi atribute: gameid, gameName, numPlayers, gameCategory,publisher i numberInStock
 * Ovi atributi su vazni za identifikaciju same drustvene igre
 * 
 * @author Simona
 * @version 1.0.0
 */
public class Game extends AbstractDO implements Serializable {
 
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka
	 * Jedinstven je za svaku igru
	 * Vrednost atributa je Long
	 */
    private Long gameid;
    
	/**
	 * Atribut koji predstavlja naziv drustvene igre
	 * Vrednost atributa je String
	 * Podrazumevana vrednost je null
	 */
    private String gameName;
    
	/**
	 * Atribut koji predstavlja broj igraca koji mogu da igraju odredjenu igru
	 * Vrednost atributa je Integer
	 * Podrazumevana vrednost je 0
	 */
    private Integer numPlayers;
    
	/**
	 * Atribut koji predstavlja kategoriju kojoj drustvena igra pripada
	 * Vrednost atributa se uzima iz enuma GameCategory
	 * Podrazumevana vrednost je Porodicne_igre
	 * @see rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory
	 */
    private GameCategory gameCategory;
    
	/**
	 * Atribut koji predstavlja kreatora igre
	 * Vrednost atributa je tipa Publisher gde ima svoj id i ime
	 * @see rs.ac.bg.fon.np_project.commonlibrary.model.Publisher
	 */
    private Publisher publisher;
    
    /**
	 * Atribut koji predstavlja dostupne kolicine igre
	 * Vrednost atributa je Integer
	 * Podrazumevana vrednost je 0
	 */
    private Integer numberInStock;

    
    /**
     * Vraca id igre
     * @return id igre kao Long
	 */
    public Long getGameid() {
        return gameid;
    }

    /**
     * Postavlja novu vrednost id igre
     * @param gameid nova vrednost atributa gameid
     * @throws java.lang.IllegalArgumentException ako je uneti id nula ili manji od nule
	 */
    public void setGameid(Long gameid) {
    	if (gameid <= 0)
			throw new IllegalArgumentException("Id mora bii veci od nule");
        this.gameid = gameid;
    }
    
    /**
     * Vraca naziv igre
     * @return naziv igre kao String
	 */
    public String getGameName() {
        return gameName;
    }

    /**
     * Postavlja novu vrednost za naziv igre
     * @param gameName nova vrednost atributa gameName
     * @throws java.lang.NullPointerException ako je uneti naziv igre null
	 * @throws java.lang.IllegalArgumentException ako je uneti naziv igre prazan String
	 */
    public void setGameName(String gameName) {
    	if (gameName == null)
			throw new NullPointerException("Naziv igre ne sme biti null");
		
		if (gameName.isEmpty())
			throw new IllegalArgumentException("Naziv igre ne sme biti prazan string");
        this.gameName = gameName;
    }

    /**
     * Vraca broj igraca koji bi trebalo da igraju igru
     * @return broj igraca igre kao Integer
	 */
    public Integer getNumPlayers() {
        return numPlayers;
    }

    /**
     * Postavlja novu vrednost za broj igraca koji bi trebalo da igraju igru
     * @param numPlayers nova vrednost atributa numPlayers
     * @throws java.lang.IllegalArgumentException ako je uneti broj igraca nula ili manji od nule
	 */
    public void setNumPlayers(Integer numPlayers) {
    	if (numPlayers <= 0)
			throw new IllegalArgumentException("Broj igraca mora bii veci od nule");
        this.numPlayers = numPlayers;
    }

    /**
     * Vraca kategoriju igre
     * @return kategoriju igre kao enum GameCategory
	 */
    public GameCategory getGameCategory() {
        return gameCategory;
    }

    /**
     * Postavlja novu vrednost za kategoriju igre
     * @param gameCategory nova vrednost atributa gameCategory
     * @throws java.lang.NullPointerException ako je uneta kategorija igre null
	 */
    public void setGameCategory(GameCategory gameCategory) {
    	if (gameCategory == null)
			throw new NullPointerException("Kategorija igre ne sme biti null");
        this.gameCategory = gameCategory;
    }

    /**
     * Vraca izdavaca igre
     * @return izdavaca igre kao Publisher
	 */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Postavlja novu vrednost za izdavaca igre
     * @param publisher nova vrednost atributa publisher
     * @throws java.lang.NullPointerException ako je uneti izdavac igre null
	 */
    public void setPublisher(Publisher publisher) {
    	if (publisher == null)
			throw new NullPointerException("Izdavac igre ne sme biti null");
        this.publisher = publisher;
    }

    /**
     * Vraca kolicinu dostupnih igara za svaku igru
     * @return broj dostupnih igara kao Integer
	 */
    public Integer getNumberInStock() {
        return numberInStock;
    }

    /**
     * Postavlja novu vrednost za kolicinu dostupnih igara
     * @param numberInStock nova vrednost atributa numberInStock
     * @throws java.lang.IllegalArgumentException ako je uneti broj igri nula ili manji od nule
	 */
    public void setNumberInStock(Integer numberInStock) {
    	if (numberInStock <= 0)
			throw new IllegalArgumentException("Broj igri mora bii veci od nule");
        this.numberInStock = numberInStock;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
		result = prime * result + ((gameid == null) ? 0 : gameid.hashCode());
		return result;
	}

	/**
	 * Poredi dve igre po id-ju i nazivu
	 * 
	 * @return
	 * <ul>
	 * <li>true - ako je id i naziv isti kod obe igre</li>
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
		Game other = (Game) obj;
		if (gameName == null) {
			if (other.gameName != null)
				return false;
		} else if (!gameName.equals(other.gameName))
			return false;
		if (gameid == null) {
			if (other.gameid != null)
				return false;
		} else if (!gameid.equals(other.gameid))
			return false;
		return true;
	}

	@Override
    public String getAttributeList() {
        return "naziv, brojIgraca, kategorijaId, izdavacId, kolicina";
       }

    @Override
    public String getClassName() {
        return "igra";
        }

    @Override
    public String getAttributeValues() {
        int catId = getGameCategory().ordinal()+1;
        return "'"+getGameName()+"', "+getNumPlayers()+", "+catId+", "+getPublisher().getPublisherId()+", "+getNumberInStock();
        
          }

    @Override
    public String getQueryCondition() {
        return "id="+getGameid();
            }
    
    
}
