/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.db.DbRepository;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.commonlibrary.model.AbstractDO;

/**
 *
 * @author Simona
 */
public class RepositoryPublisher implements DbRepository<Publisher, Long> {

    private Statement statement;
    private DatabaseBroker dbBroker;

    public RepositoryPublisher() {
        dbBroker=new DatabaseBroker();
    }
    

    @Override
    public List<Publisher> getAll() throws Exception {
        List<Publisher> publishers = new ArrayList<>();
        String query = "SELECT * FROM izdavac ORDER BY imePrezime ASC";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Publisher p = new Publisher(rs.getLong(1), rs.getString(2));
            publishers.add(p);

        }
        statement.close();
        rs.close();

        return publishers;

    }

    @Override
    public void add(Publisher t) throws Exception {
        /*String upit = "INSERT INTO izdavac (imePrezime) VALUES ('" + t.getPublisherName() + "')";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(upit);
        statement.close();*/
        dbBroker.add(t);
        
    }

    @Override
    public void edit(Publisher stari, Publisher novi) throws Exception {
        //TODO: Implement later
    }

    @Override
    public void delete(Publisher publisher) throws SQLException, Exception{
            dbBroker.delete(publisher);
            /*String query = "DELETE FROM izdavac WHERE id=" + publisher.getPublisherId();
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();*/
        

    }

    @Override
    public List<Publisher> getByQuery(String query) throws Exception {
        List<Publisher> dbPublishers = new ArrayList<>();
        Publisher dbPublisher;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            dbPublisher = new Publisher();
            dbPublisher.setPublisherId(rs.getLong("id"));
            dbPublisher.setPublisherName(rs.getString("imePrezime"));
            dbPublishers.add(dbPublisher);
        }
        statement.close();
        rs.close();

        return dbPublishers;
    }

}
