/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;
import rs.ac.bg.fon.np_project.server.so.game.GetGamesByQuerySO;


/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za proces iznajmljivanja igri i unosenje
 * toga u bazu. Sadrzi implementaciju metoda iz nadklase AbstractSO i atribute repositoryRent i 
 * repositoryGame koje su tipa klase koja se nalazi na serverskoj strani.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class RentGameSO extends AbstractSO {

	/**
	 * Predstavlja atribut koji je tipa klase RepositoryRent koja uzima, dodaje, azurira i
	 * i brise iznajmljivanja iz baze. 
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent
	 */
    RepositoryRent repositoryRent;
    
    /**
	 * Predstavlja atribut koji je tipa klase RepositoryRent koja uzima, dodaje, azurira i
	 * i brise drustvene igre iz baze.
	 * @see rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame
	 */
    RepositoryGame repositoryGame;

    /**
     * Konstruktor koji inicijalizuje atribut repositoryRent i repositoryGame.
     */
    public RentGameSO() {
        repositoryRent = new RepositoryRent();
        repositoryGame=new RepositoryGame();
    }

    /**
     * Metoda proverava da li je uneti objekat null ili ako je duzina liste tih objekata
     * manja od dva. Ako je neki uslov ispunjen baca Exception i prikazuje poruku
     * "Poslati objekat je neodgovarajuceg tipa!". Ako prvi element liste nije instanca User klase
     * ili  drugi element Game klase baca exception i poruku "Poslati argumenti su neodgovarajuceg tipa!"
     * Ako su svi uslovi ispunjeni kreira promenljive user i game klasa User i Game i prosledjuje ih
     * daljim metodama za proveru.
     */
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || ((List<Object>) param).size() < 2) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        }
        if (!(((List<Object>) param).get(0) instanceof User) || !(((List<Object>) param).get(1) instanceof Game)) {
            throw new Exception("Poslati argumenti su neodgovarajuceg tipa!");
        } else {
            User user = (User) ((List<Object>) param).get(0);
            Game game = (Game) ((List<Object>) param).get(1);
            checkValueConstraints(user, game);
            checkStructuralConstraints(user, game);
        }
    }

    /**
     * Metoda koja proverava da li su ispunjeni svi vrednosni uslovi pre nego sto se izvrsi
     * iznajmljivanje.
     * 
     * @param g tipa Game koja predstavlja drustvenu igru
     * @param user tipa User koji predstavlja korisnika
     * @throws java.lang.Exception koja se javlja ako se desi neka greska pri proveri
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.User
     */
    private void checkValueConstraints(User user, Game game) throws Exception {
        //TO DO -proveriti da li je clanska karta istekla
        checkUserCardIsValid(user.getUsercard());
    }

    /**
     * Metoda koja proverava da li su ispunjeni svi strukturni uslovi pre nego sto se izvrsi
     * iznajmljivanje.
     * 
     * @param g tipa Game koja predstavlja drustvenu igru
     * @param user tipa User koji predstavlja korisnika
     * @throws java.lang.Exception koja se desava kada korisnik ima vec dva iznajmljivanja 
     * i vraca poruku "Korisnik ima dve zaduzene igre!" ili kada nema igre na stanju i vraca poruku
     * "Nema igra na stanju!"
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.User
     */
    private void checkStructuralConstraints(User user, Game game) throws Exception {
        int userRents;
        int gamesOnStorage;
        userRents = checkUserRents(user);
        if (userRents == 2) {
            throw new Exception("Korisnik ima dve zaduzene igre!");
        }

        gamesOnStorage = checkGameCount(game);
        if (gamesOnStorage <= 0) {
            throw new Exception("Nema igra na stanju!");
        }

    }

    /**
     * Metoda kreira promenljive user i game tipa klasa User i Game. Prosledjuje metodama 
     * promenljive za azuriranje kolicine igara na stanju u bazi. Ako dodje do 
     * greske baca se exception i prikazuje odgovarajucu poruku.
     * 
     *  @return null
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
        User u = (User) ((List<Object>) param).get(0);
        Game g = (Game) ((List<Object>) param).get(1);
        try {
            repositoryRent.rentGame(u, g);
            updateGameCount(g,-1);
        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        }
        return null;
    }

    /**
     * Metoda koja proverava korisnikova vec prethodna iznajmljivanja.
     * 
     * @param u tipa User koja predstavlja korisnika
     * @return koliko iznajmljivanja ima korisnik
     * @throws java.lang.Exception koja se desava kada dodje do neke greske pri proveri u bazi
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.User
     */
    private int checkUserRents(User u) throws Exception {
        return repositoryRent.getUserRents(u).size();
    }

    /**
     * Metoda koja proverava da li postoji na stanju igra koju korisnik zeli da iznajmi.
     * 
     * @param g tipa Game koja predstavlja drustvenu igru
     * @return koliko ima odredjene igre na stanju
     * @throws java.lang.Exception koja se desava kada dodje do greske u bazi i vraca 
     * poruku "Greska prilikom provere stanja na skladistu."
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     */
    private int checkGameCount(Game g) throws Exception {
        GetGamesByQuerySO getGamesByQuerySO = new GetGamesByQuerySO();
        try {
            String query = "SELECT * FROM igra WHERE id=" + g.getGameid();
            Game game = ((List<Game>) getGamesByQuerySO.execute(query)).get(0);
            return game.getNumberInStock();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere stanja na skladistu.", e);
        }

    }

    /**
     * Metoda koja proverava da li je korisnikova clanska kartica vazeca.
     * 
     * @param userCard tipa UserCard koja predstavlja clansku karticu korisnika
     * @throws java.lang.Exception koja se desava kada je clanska kartica neispravna i vraca 
     * poruku "Clanska karta korisnika je istekla. Nije moguce iznajmiti igru."
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.UserCard
     */
    private void checkUserCardIsValid(UserCard usercard) throws Exception {
       if(usercard.getExpiryDate().isBefore(LocalDate.now()))
           throw new Exception("Clanska karta korisnika je istekla. Nije moguce iznajmiti igru.");
    }

    /**
     * Metoda koja azurira dostupne kolicine drustvene igre nakon iznajmljivanja.
     * 
     * @param game tipa Game koja predstavlja drustvenu igru 
     * @param  i tipa int koja predstavlja koliki je broj odredjene igre iznajmljen
     * @throws java.sql.SQLException kada se desi neka vrsta greske u radu sa bazom prilikom
     * azuriranja kolicine igre
     * @throws java.io.IOException kada se desi neka vrsta I/O exception-a
     * @see rs.ac.bg.fon.np_project.commonlibrary.model.Game
     */
    private void updateGameCount(Game g, int i) throws SQLException, IOException {
        repositoryGame.updateGameCount(g,i);
    }
    
}
