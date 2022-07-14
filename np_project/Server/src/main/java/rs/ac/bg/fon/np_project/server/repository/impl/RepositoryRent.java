/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;

import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.server.controller.Controller;
import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.server.repository.Repository;


/**
 *
 * @author Simona
 */
public class RepositoryRent implements Repository {

    private Statement statement;
    private PreparedStatement ps;
    DatabaseBroker dbBroker;

    public RepositoryRent() {
        dbBroker=new DatabaseBroker();
    }
    

    @Override
    public List<Rent> getAll() throws Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE datumVracanja IS NULL";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));

            r.setRentalDate(rs.getDate(3).toLocalDate());
            Long gameid = rs.getLong(2);
            String gameQuery = "SELECT * FROM igra WHERE id=" + gameid;
            Game b = Controller.getInstance().getGamesByQuery(gameQuery).get(0);
            r.setGame(b);
            rents.add(r);

        }
        return rents;
    }

    @Override
    public void add(Object t) throws Exception {
       //TODO: implement later
    }

    @Override
    public void edit(Object stari, Object novi) throws Exception {
        //TODO: implement later
    }

    @Override
    public void delete(Object t) throws Exception {
        //TODO: implement later
    }

    @Override
    public List getByQuery(String query) throws Exception {
         statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));

            r.setRentalDate(rs.getDate(3).toLocalDate());
            Long gameid = rs.getLong(2);
            String gameQuery = "SELECT * FROM igra WHERE id=" + gameid;
            Game g = Controller.getInstance().getGamesByQuery(gameQuery).get(0);
            r.setGame(g);
            rents.add(r);

        }
        return rents;
        

    }

    public List<Rent> getUserRents(User u) throws Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE clanId=" + u.getUserId() + " AND datumVracanja IS NULL";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));
            r.setUser(u);
            r.setRentalDate(rs.getDate(3).toLocalDate());
            Long gameid = rs.getLong(2);
            String gameQuery = "SELECT * FROM igra WHERE id=" + gameid;
            Game g = Controller.getInstance().getGamesByQuery(gameQuery).get(0);
            r.setGame(g);
            rents.add(r);

        }

        return rents;

    }

    public void rentGame(User u, Game g) throws Exception {
       /* String query = "INSERT INTO iznajmljivanje (clanId, igraaId,datumIznajmljivanja) VALUES (?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setLong(1, u.getUserId());
        ps.setLong(2, b.getGameid());
        ps.setDate(3, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();*/
       Rent r=new Rent();
       r.setGame(g);
       r.setUser(u);
       r.setRentalDate(LocalDate.now());
       dbBroker.add(r);


    }


    public void restoreGame(Rent rental) throws Exception {
        String query = "UPDATE iznajmljivanje SET datumVracanja=? WHERE id=" + rental.getId();
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();

    }

    public void deleteGameRents(Game t) throws SQLException {
        String query = "DELETE FROM iznajmljivanje WHERE igraId=" + t.getGameid();
        try {
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryRent.class.getName()).log(Level.SEVERE, null, ex);
        }
        statement.execute(query);

    }

    public List<Rent> getAllUserRents(User u) throws SQLException, IOException, Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE clanId=" + u.getUserId();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));
            r.setUser(u);
            r.setRentalDate(rs.getDate(3).toLocalDate());
            try {
                 if(rs.getDate(4) !=null) r.setReturnDate(rs.getDate(4).toLocalDate());
                 Long gameid = rs.getLong(2);
            String gameQuery = "SELECT * FROM igra WHERE id=" + gameid;
            Game g = Controller.getInstance().getGamesByQuery(gameQuery).get(0);
            r.setGame(g);
            rents.add(r);

            } catch (Exception e) {
                e.printStackTrace();
            }
           
            
            
        }

        return rents;
           }
    

}
