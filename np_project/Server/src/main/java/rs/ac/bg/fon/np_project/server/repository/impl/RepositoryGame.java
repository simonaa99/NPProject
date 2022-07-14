/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;

import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.db.DbRepository;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.server.so.gameCategory.GetGameCategoryIdSO;

/**
 *
 * @author Simona
 */
public class RepositoryGame implements DbRepository<Game, Long>{
    
    RepositoryPublisher repositoryPublisher = new RepositoryPublisher();
    RepositoryGameCategory repositoryGameCategory = new RepositoryGameCategory();
    Statement statement;
    PreparedStatement ps;
    private DatabaseBroker databaseBroker;

    public RepositoryGame() {
        repositoryPublisher = new RepositoryPublisher();
        repositoryGameCategory = new RepositoryGameCategory();
        databaseBroker=new DatabaseBroker();

    }

    @Override
    public List<Game> getAll() throws Exception {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM igra ORDER BY naziv ASC";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Game g = new Game();
            g.setGameid(rs.getLong("id"));
            g.setGameName(rs.getString("naziv"));
            g.setNumPlayers(rs.getInt("brojIgraca"));
            g.setNumberInStock(rs.getInt("kolicina"));
            Long izdavacId = rs.getLong("izdavacId");
            Long kategorijaId = rs.getLong("kategorijaId");
            query = "SELECT * FROM izdavac WHERE id=" + izdavacId;
            List<Publisher> publishers = repositoryPublisher.getByQuery(query);
            if (publishers.size() == 0) {
                throw new Exception("Nastala je greska kod prikljucivanja izdavaca igri.");
            }
            Publisher publisher = publishers.get(0);
            GameCategory category = GameCategory.valueOf(repositoryGameCategory.getCategoryName(kategorijaId));
            g.setPublisher(publisher);
            g.setGameCategory(category);
            games.add(g);

        }
        statement.close();
        return games;

    }

    @Override
    public void add(Game t) throws Exception {
       
        boolean status = updateIfExists(t);
        if (status == false) {
            databaseBroker.add(t);
            
        }

    }

    @Override
    public void delete(Game t) throws Exception {
                databaseBroker.delete(t);
       // deleteRents(t);
        /*String query = "DELETE FROM igra WHERE id=" + t.getGameid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);

        statement.close();
       try {
            removePublisher(t.getPublisher());
        } catch (Exception e) {
            System.out.println("Izdavac nije obrisan.");
        }
*/

    }

    @Override
    public void edit(Game oldGame, Game newGame) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getByQuery(String query) throws Exception {
        List<Game> games = new ArrayList<>();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Game g = new Game();
            g.setGameid(rs.getLong("id"));
            g.setGameName(rs.getString("naziv"));
            g.setNumPlayers(rs.getInt("brojIgraca"));
            g.setNumberInStock(rs.getInt("kolicina"));
            Long izdavacId = rs.getLong("izdavacId");
            Long categoryId = rs.getLong("kategorijaId");
            String queryPublisher = "SELECT * FROM izdavac WHERE id=" + izdavacId;
            List<Publisher> publishers = repositoryPublisher.getByQuery(queryPublisher);
            if (publishers.size() == 0) {
                throw new Exception();
            }
            Publisher publisher = publishers.get(0);

            GameCategory category = GameCategory.valueOf(repositoryGameCategory.getCategoryName(categoryId));
            g.setPublisher(publisher);
            g.setGameCategory(category);

            games.add(g);

        }
        return games;

    }

    /*public void checkIfRentsExist(Game game) throws SQLException, Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE igraId=" + game.getGameid() + " AND datumVracanja IS NULL";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        if (rs.next()) {
            throw new Exception("Primerci igre su zaduzeni. Nije moguce dovrsiti operaciju brisanja.");
        }

    }*/

    /*private void removePublisher(Publisher publisher) throws SQLException, Exception {
        boolean exist = checkIfGamesExist(author);
        if (exist) {
            throw new Exception("Igre ovog izdavaca postoje na stanju. Nije moguce dovrsiti operaciju brisanja.");
        } else {
            String query = "DELETE FROM izdavac WHERE id=" + publisher.getPublisherId();
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
        }

    }*/

    public boolean checkIfGamesExist(Publisher publisher) throws SQLException {
        boolean exist = false;
        String query = "SELECT * from igra WHERE izdavacId=" + publisher.getPublisherId();
        try {
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            exist = true;
        }
        rs.close();
        statement.close();
        return exist;

    }

   /* private void deleteRents(Game t) throws SQLException {
        RepositoryRent r = new RepositoryRent();
        r.deleteGameRents(t);
    }*/

    private boolean updateIfExists(Game t) throws Exception {
        Long categoryId = (Long) new GetGameCategoryIdSO().execute(t.getGameCategory().toString());
        String query = "SELECT * FROM igra WHERE naziv='" + t.getGameName() + "' AND brojIgraca=" + t.getNumPlayers() + " AND kategorijaId=" + categoryId + " AND izdavacId=" + t.getPublisher().getPublisherId();
        List<Game> dbGames = getByQuery(query);
        if (dbGames.size() > 0) {
            Game dbGame = dbGames.get(0);
            int newAmount=dbGame.getNumberInStock()+t.getNumberInStock();
            query = "UPDATE igra SET kolicina= " +newAmount + " WHERE id=" + dbGame.getGameid();
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.execute(query);
            statement.close();
            return true;
        }
        return false;
    }

    public void updateGameCount(Game b, int i) throws SQLException, IOException {
        int num = b.getNumberInStock() + i;
        String updateGameCount = "UPDATE igra SET kolicina= " + num + " WHERE id=" + b.getGameid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(updateGameCount);
    }
    
}
