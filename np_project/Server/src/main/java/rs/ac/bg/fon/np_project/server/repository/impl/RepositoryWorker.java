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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;


/**
 *
 * @author Simona
 */
public class RepositoryWorker implements DbRepository<Worker, Long> {

    private DatabaseBroker databaseBroker;

    public RepositoryWorker() {
        databaseBroker = new DatabaseBroker();
    }

    public List<Worker> getAll() throws Exception {
        List<Worker> workers = new ArrayList<>();
        String query = "SELECT * FROM radnik";
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Worker b = new Worker(rs.getLong(1), rs.getString(2), rs.getString(3)
            );
            b.setFirstName(rs.getString("ime"));
            b.setLastName(rs.getString("prezime"));
            int prijavljen=rs.getInt("prijavljen");
            if(prijavljen==0) b.setLoggedIn(false);
            else b.setLoggedIn(true);
            workers.add(b);
        }
        return workers;

    }

    @Override
    public void add(Worker t) throws Exception {
        databaseBroker.add(t);
    }

    @Override
    public void edit(Worker oldOne, Worker newOne) throws Exception {
        //TODO: Implement later
    }

    @Override
    public void delete(Worker t) throws Exception {
        databaseBroker.delete(t);
    }

    @Override
    public List<Worker> getByQuery(String query) throws Exception {
        List<Worker> workers = new ArrayList<>();

        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Worker b = new Worker(rs.getLong(1), rs.getString(2), rs.getString(3)
            );
            workers.add(b);
        }
        return workers;
    }

    public boolean checkIfExists(Worker worker) throws Exception {
        List<Worker> workers = getAll();
        for (Worker u : workers) {
            if (u.getUsername().equals(worker.getUsername())) {
                return true;
            }
        }
        return false;
          }

    public void setUserIsLoggedIn(Worker currentUser) throws SQLException, IOException {
        String query = "UPDATE radnik SET prijavljen=1 WHERE radnikID="+currentUser.getId();
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();
          }
        public void setUserIsLoggedOut(Worker currentUser) throws SQLException, IOException {
        String query = "UPDATE radnik SET prijavljen=0 WHERE radnikID="+currentUser.getId();
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        this.commit();
            System.out.println("Status: izlogovan");
        statement.close();
          }

}
