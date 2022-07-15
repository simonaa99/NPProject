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
	 */
    public void setGameid(Long gameid) {
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
	 */
    public void setGameName(String gameName) {
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
	 */
    public void setNumPlayers(Integer numPlayers) {
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
	 */
    public void setGameCategory(GameCategory gameCategory) {
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
	 */
    public void setPublisher(Publisher publisher) {
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
	 */
    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
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
