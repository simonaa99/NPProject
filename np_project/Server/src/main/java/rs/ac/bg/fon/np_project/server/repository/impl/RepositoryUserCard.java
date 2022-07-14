/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;


import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.db.DbRepository;
import rs.ac.bg.fon.np_project.server.exception.NoUserCartFoundException;
import java.io.IOException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;


/**
 *
 * @author Simona
 */
public class RepositoryUserCard implements DbRepository<UserCard, Long> {

    private Statement statement;
    private PreparedStatement pstatement;
    private DatabaseBroker dbBroker;

    public RepositoryUserCard() {
        dbBroker=new DatabaseBroker();
    }
    

    @Override
    public List<UserCard> getAll() throws Exception {
        //TODO: Implement later
        return null;
    }

    @Override
    public void add(UserCard t) throws Exception {
      /*  String query = "INSERT INTO clanskaKarta (brojClanskeKarte, datumIzdavanja, datumIsteka) VALUES (?,?,?)";
        pstatement = DbConnectionFactory.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstatement.setString(1, t.getCardNumber());
        pstatement.setDate(2, Date.valueOf(t.getIssueDate()));
        pstatement.setDate(3, Date.valueOf(t.getExpiryDate()));
        pstatement.executeUpdate();
        pstatement.close();
        /*        ResultSet ckKey = statement.getGeneratedKeys();
        Long cardId = null;
        if (ckKey.next()) {
            cardId = ckKey.getLong(1);
            t.setId(cardId);
        }*/
      dbBroker.add(t);
    }

    @Override
    public void delete(UserCard t) throws Exception {
       /* String query = "DELETE FROM clanskakarta WHERE id=" + t.getId();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();*/
       dbBroker.delete(t);
    }

    @Override
    public void edit(UserCard oldOne, UserCard newOne) throws Exception {
        //Implement later
    }

    @Override
    public List<UserCard> getByQuery(String query) throws Exception {
        List<UserCard> cards = new ArrayList<>();
        UserCard card = new UserCard();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            card.setId(rs.getLong(1));
            card.setCardNumber(rs.getString(2));
            card.setIssueDate(rs.getDate(3).toLocalDate());
            card.setExpiryDate(rs.getDate(4).toLocalDate());
            cards.add(card);

        }
        statement.close();
        if (cards.size() > 0) {
            return cards;
        }
        throw new NoUserCartFoundException("Clanska karta ne postoji!");
    }

    public void updateCardNumber(UserCard oldCard, UserCard newCard) throws SQLException, IOException {
        try {

            String query = "UPDATE clanskakarta SET brojClanskeKarte= " + newCard.getCardNumber() + " WHERE id=" + oldCard.getId();
            Statement s = DbConnectionFactory.getInstance().getConnection().createStatement();
            s.executeUpdate(query);
            query = "UPDATE clanskakarta SET datumIzdavanja='" + Date.valueOf(newCard.getIssueDate()) + "' WHERE id=" + oldCard.getId();
            s.executeUpdate(query);
            query = "UPDATE clanskakarta SET datumIsteka='" + Date.valueOf(newCard.getExpiryDate()) + "' WHERE id=" + oldCard.getId();
            s.executeUpdate(query);
            this.commit();
            s.close();

        } catch (IOException ex) {
            Logger.getLogger(RepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
