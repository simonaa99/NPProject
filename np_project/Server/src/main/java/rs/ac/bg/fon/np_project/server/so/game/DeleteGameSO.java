/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.game;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;


/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za brisanje drustvene igre iz baze.
 * Sadrzi implementaciju metoda iz nadklase AbstractSO, zajedno i sa svojim metodama koje proveravaju da li
 * su svi uslovi zadovoljeni. Sadrzi i atribute repositoryGame, repositoryPublisher i repositoryRent
 * koje su tipa klase koje se nalaze na serverskoj strani. 
 * 
 * @author Simona
 * @version 1.0.0
 */
public class DeleteGameSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryGame koja uzima, dodaje, azurira i
	 * i brise drustvene igre iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame
	 */
    private RepositoryGame repositoryGame;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryGame koja uzima, dodaje, azurira i
	 * i brise izdavace iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher
	 */
    private RepositoryPublisher repositoryPublisher;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryGame koja uzima, dodaje, azurira i
	 * i brise iznajmljivanja iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent
	 */
    private RepositoryRent repositoryRent;

    /**
     * Konstruktor koji inicijalizuje atribute repositoryGame, repositoryPublisher i repositoryRent.
     */
    public DeleteGameSO() {
        repositoryGame = new RepositoryGame();
        repositoryPublisher = new RepositoryPublisher();
        repositoryRent = new RepositoryRent();
    }

    /**
     * Metoda proverava da li je uneti parametar null ili nije instanca klase Game.
     * Ako je neki od ovih uslova ispunjen baca Exception i prikazuje poruku
     * "Poslati objekat je neodgovarajuceg tipa!" Ako ne ispunjavaju kreira promenljivu 
     * game klase Game i prosledjuje je drugim metodama za proveru.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Game)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
            
        } else {
            Game game = (Game) param;
            try {
                checkStructuralConstraints(game);
              
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    /**
     * Metoda kreira promenljivu g tipa klase Game i salje da se ta igra treba obrisati iz baze.
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        Game g = (Game) param;
        repositoryRent.deleteGameRents(g);
        repositoryGame.delete(g);
//        try {
//            removePublisher(b.getPublisher());
//        } catch (Exception e) {
//            System.out.println("Publisher nije obrisan.");
//        }
        return null;
    }

    /**
     * Metoda koja prosledjuje odredjenu igru u metodu koja proverava da li je
     * drustvena igra iznajmljena pa samim tim ne moze da se izbrise iz baze.
     * 
     * @param param tipa Game koji predstavlja drustvenu igru
     * @throws java.lang.Exception ako se dogodi neka greska
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     * */
    private void checkStructuralConstraints(Game game) throws Exception {
        checkIfRentsExist(game);
    }

    /**
     * Metoda koja proverava da li drustvena igra iznajmljena i samim tim ne sme da se 
     * izbrise iz baze.
     * 
     * @param param tipa Game koji predstavlja drustvenu igru
     * @throws java.lang.Exception.Exception ako se dogodi da jeste iznajmljena
     * i izbacuje poruku "Primerci igre su zaduzeni. Nije moguce dovrsiti operaciju brisanja."
     * @throws java.lang.Exception ako se dogodi neka greska 
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     * */
    private void checkIfRentsExist(Game game) throws Exception {
        try {
            String query = "SELECT * FROM iznajmljivanje WHERE igraId=" + game.getGameid() + " AND datumVracanja IS NULL";
            List<Rent> rents = repositoryRent.getByQuery(query);
            if (rents!=null && rents.size() > 0) {
                throw new Exception("Primerci igre su zaduzeni. Nije moguce dovrsiti operaciju brisanja.");
            }
        } catch (Exception ex) {
            throw ex;
        }

    }

}
