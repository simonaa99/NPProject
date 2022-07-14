/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 *
 * @author Simona
 */
public class Game extends AbstractDO implements Serializable {
 
    private Long gameid;
    private String gameName;
    private Integer numPlayers;
    private GameCategory gameCategory;
    private Publisher publisher;
    private Integer numberInStock;

    public Long getGameid() {
        return gameid;
    }

    public void setGameid(Long gameid) {
        this.gameid = gameid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

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
