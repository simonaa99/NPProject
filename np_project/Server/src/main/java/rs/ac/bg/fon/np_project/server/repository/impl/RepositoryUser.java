/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;


import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.server.controller.Controller;
import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.db.DbRepository;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;


/**
 *
 * @author Simona
 */
public class RepositoryUser implements DbRepository<User, Long> {

    private Statement statement;
    private PreparedStatement ps;
    private DatabaseBroker dbBroker;

    public RepositoryUser() {
        dbBroker=new DatabaseBroker();
    }
    

    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        String upit = "SELECT * FROM clan ORDER BY ime ASC";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setPhoneNumber(rs.getString(4));
            user.setAddress(rs.getString(5));
            List<UserCategory> categories = Controller.getInstance().getUserCategories();

            for (UserCategory uc : categories) {
                if (uc.getUserCategoryId().equals(rs.getLong(6))) {
                    user.setUserCategory(uc);
                    break;
                }

            }
            UserCard userCard = Controller.getInstance().getUserCardById(rs.getLong(7));
            user.setUsercard(userCard);
            users.add(user);

        }
        statement.close();
        rs.close();
        return users;
    }

    @Override
    public void add(User t) throws Exception {
       /* String query = "INSERT INTO clan (ime, prezime, brojTelefona, adresa,kategorijaId, clanskaKartaId) VALUES (?,?,?,?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setString(1, t.getName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getPhoneNumber());
        ps.setString(4, t.getAddress());
        ps.setLong(5, t.getUserCategory().getUserCategoryId());
        ps.setLong(6, t.getUsercard().getId());
        ps.executeUpdate();
        ps.close();
        statement.close();*/
       dbBroker.add(t);

    }

    @Override
    public void edit(User oldU, User newU) throws Exception {
        String queryUser = "UPDATE clan SET ime=?, prezime=?, brojTelefona=?, adresa=?, kategorijaid=? WHERE id=?";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(queryUser);
        ps.setString(1, newU.getName());
        ps.setString(2, newU.getLastName());
        ps.setString(3, newU.getPhoneNumber());
        ps.setString(4, newU.getAddress());
        ps.setLong(5, newU.getUserCategory().getUserCategoryId());
        ps.setLong(6, oldU.getUserId());
        ps.executeUpdate();
        ps.close();

    }

    @Override
    public void delete(User t) throws Exception {
        /*String upit = "DELETE FROM clan WHERE id=" + t.getUserId();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(upit);
        statement.close();*/
        dbBroker.delete(t);

    }

    public List<User> getusersByUserCard(String cardNumber) throws Exception {
        List<User> users = new ArrayList<>();
        String query = "SELECT c.id as id, c.ime as ime, c.prezime as prezime, c.brojTelefona as bt, c.adresa as adresa, c.kategorijaId as kategorija, c.clanskaKartaId as ck FROM clan c INNER JOIN clanskakarta ck ON (c.clanskaKartaId=ck.id) WHERE ck.brojClanskeKarte='" + cardNumber + "'";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setPhoneNumber(rs.getString(4));
            user.setAddress(rs.getString(5));
            List<UserCategory> categories = Controller.getInstance().getUserCategories();
            for (UserCategory kc : categories) {
                if (kc.getUserCategoryId().equals(rs.getLong(6))) {
                    user.setUserCategory(kc);
                    break;
                }

            }
            UserCard card = Controller.getInstance().getUserCardById(rs.getLong(7));
            user.setUsercard(card);
            users.add(user);

        }
        statement.close();
        rs.close();
        return users;
    }

    @Override
    public List<User> getByQuery(String query) throws Exception {
        List<User> users = new ArrayList<>();
        User c;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            c = new User();
            c.setUserId(rs.getLong(1));
            c.setName(rs.getString(2));
            c.setLastName(rs.getString(3));
            c.setPhoneNumber(rs.getString(4));
            c.setAddress(rs.getString(5));
            List<UserCategory> categories = Controller.getInstance().getUserCategories();

            for (UserCategory kc : categories) {
                if (kc.getUserCategoryId().equals(rs.getLong(6))) {
                    c.setUserCategory(kc);
                    break;
                }

            }
            UserCard card = Controller.getInstance().getUserCardById(rs.getLong(7));
            c.setUsercard(card);
            users.add(c);

        }
        statement.close();
        rs.close();

        return users;
    }

   /* public List<Rent> getUserRents(User u) throws Exception {
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

    }*/

   /* public void rentGame(User u, Game g) throws Exception {
        String query = "INSERT INTO iznajmljivanje (clanId, igraId,datumIznajmljivanja) VALUES (?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setLong(1, u.getUserId());
        ps.setLong(2, g.getGameid());
        ps.setDate(3, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();
        updateGameCount(b, -1);

    }*/

    private void updateGameCount(Game g, int value) throws Exception {
        int num = g.getNumberInStock() + value;
        String updateGameCount = "UPDATE igra SET kolicina= " + num + " WHERE id=" + g.getGameid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(updateGameCount);
    }

   /* public void restoreGame(Rent rental) throws Exception {
        String query = "UPDATE iznajmljivanje SET datumVracanja=? WHERE id=" + rental.getId();
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();
        updateGameCount(rental.getGame(), +1);
        //commit();
    }*/

    /*public List<Rent> getRents() throws Exception {
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
            Game g = Controller.getInstance().getGamesByQuery(gameQuery).get(0);
            r.setGame(g);
            rents.add(r);

        }
        return rents;
    }*/

    public boolean checkIfExists(User user) throws Exception {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()) && u.getAddress().equals(user.getAddress()) && u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getUserCategory().equals(user.getUserCategory()) && u.getUsercard().getCardNumber().equals(user.getUsercard().getCardNumber())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfRentsExist(User user) throws Exception {
        List<Rent> rents = Controller.getInstance().getUserRents(user);
        if (rents.size() > 0) {
            return true;
        }
        return false;

    }

   /* private void updateCardNumber(UserCard oldCard, UserCard newCard) throws SQLException {
        String query = "UPDATE clanskakarta SET brojClanskeKarte=? AND datumIzdavanja=? and datumIsteka=? WHERE brojClanskeKarte=?";
        try {
            ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        } catch (IOException ex) {
            Logger.getLogger(RepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.setString(1, newCard.getCardNumber());
        ps.setDate(2, Date.valueOf(newCard.getIssueDate()));
        ps.setDate(3, Date.valueOf(newCard.getExpiryDate()));
        ps.setString(4, oldCard.getCardNumber());
        ps.executeUpdate();
        ps.close();

    }*/

    public boolean checkIfExists(User user, boolean includeUserCard) throws Exception {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()) && u.getAddress().equals(user.getAddress()) && u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getUserCategory().equals(user.getUserCategory())) {
                return true;
            }
        }
        return false;

    }

}
