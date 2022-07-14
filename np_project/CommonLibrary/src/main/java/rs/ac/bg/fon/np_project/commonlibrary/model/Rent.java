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
 *
 * @author Simona
 */
public class Rent extends AbstractDO implements Serializable {
    
    private Long id;
    private Game game;
    private User user;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

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
