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
 * Predstavlja domesnku klasu za iznajmljivanje drustvenih igara.
 * Sadrzi atribute id, game, user, rentalDate, returnDate.
 * Ovi atributi su neophodni za vodjenje evidencije o izdavanju igara.
 * Takodje, nalaze se i get i set metode zajedno sa metodama iz klase AbstractDO.
 *
 * @author Simona
 * @version 1.0.0
 */
public class Rent extends AbstractDO implements Serializable {
    
	/**
	 * Predstavlja atribut koji je primarni kljuc u okviru baze podataka.
	 * Jedinstven je za svako izdavanje.
	 * Vrednost atributa je Long.
	 */
    private Long id;
    
    /**
	 * Atribut koji predstavlja naziv igre koja se iznajmljuje.
	 * Vrednost atributa je Game.
	 * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
	 */
    private Game game;
    
    /**
	 * Atribut koji predstavlja korisnika koji iznajmljuje igru.
	 * Vrednost atributa je User.
	 * @see rs.ac.bg.fon.np_project.commonlibrary.model.User
	 */
    private User user;
    
    /**
	 * Atribut koji predstavlja datum kad se igra iznajmila.
	 * Vrednost atributa je LocalDate.
	 */
    private LocalDate rentalDate;
    
    /**
   	 * Atribut koji predstavlja datum kad se igra vratila.
   	 * Vrednost atributa je LocalDate.
   	 */
    private LocalDate returnDate;
    
    
    /**
     * Vraca id iznajmljivanja.
     * @return id iznajmljivanja kao Long
	 */
    public Long getId() {
        return id;
    }

    /**
     * Postavlja novu vrednost id iznajmljivanja.
     * @param id nova vrednost atributa id
	 */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Vraca igru koja se iznajmljuje.
     * @return igru koja se iznajmljuje kao Game
	 */
    public Game getGame() {
        return game;
    }

    /**
     * Postavlja novu vrednost igre koja se iznajmljuje.
     * @param game nova vrednost atributa game
	 */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Vraca korisnika koji zeli da iznajmi igru.
     * @return korisnik kao User
	 */
    public User getUser() {
        return user;
    }

    /**
     * Postavlja novu vrednost korisnika koji zeli da iznajmi igru.
     * @param user nova vrednost atributa user
	 */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Vraca datum iznajmljivanja igre.
     * @return datum iznajmljivanja izdavaca kao LocalDate
	 */
    public LocalDate getRentalDate() {
        return rentalDate;
    }

    /**
     * Postavlja datum iznajmljivanja igre.
     * @param rentalDate nova vrednost atributa rentalDate
	 */
    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * Vraca datum vracanja igre.
     * @return datum vracanja igre kao LocalDate
	 */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Postavlja novu vrednost datuma vracanja igre.
     * @param returnDate nova vrednost atributa returnDate
	 */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String getAttributeList() {
        return "clanId, igraId, datumIznajmljivanja";
    }

    @Override
    public String getClassName() {
        return "iznajmljivanje";
    }

    @Override
    public String getAttributeValues() {
        return ""+user.getUserId()+", "+game.getGameid()+", "+"'"+Date.valueOf(rentalDate)+"'";
    }

    @Override
    public String getQueryCondition() {
        return null;
    }
    
}
