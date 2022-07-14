/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;


import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.db.DbRepository;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;

/**
 *
 * @author Simona
 */
public class RepositoryUserCategory implements DbRepository<UserCategory, Long> {

    private Statement statement;

    List<UserCategory> workers = new ArrayList<>();

    public List<UserCategory> getAll() throws Exception {
        List<UserCategory> categories = new ArrayList<>();
        String query = "SELECT * FROM kategorijaclanova";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            UserCategory kc = new UserCategory();
            kc.setUserCategoryId(rs.getLong(1));
            kc.setName(rs.getString(2));
            kc.setMembershipFeeDiscount(rs.getDouble(3));
            categories.add(kc);
        }
        return categories;

    }

    @Override
    public void add(UserCategory t) throws Exception {
        //TODO implement later
    }

    @Override
    public void edit(UserCategory oldOne, UserCategory newOne) throws Exception {
        //TODO implement later
    }

    @Override
    public void delete(UserCategory t) throws Exception {
        //TODO implement later
    }

    @Override
    public List<UserCategory> getByQuery(String query) throws Exception {
        List<UserCategory> userCategories = new ArrayList<>();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            UserCategory kc = new UserCategory();
            kc.setUserCategoryId(rs.getLong("id"));
            kc.setName(rs.getString("naziv"));
            kc.setMembershipFeeDiscount(rs.getDouble(3));
            userCategories.add(kc);
        }
        return userCategories;
    }

}
