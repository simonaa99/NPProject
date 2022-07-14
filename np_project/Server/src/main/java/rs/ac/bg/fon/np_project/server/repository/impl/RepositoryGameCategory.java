/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository.impl;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.db.DbRepository;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;


/**
 *
 * @author Simona
 */
public class RepositoryGameCategory implements DbRepository<GameCategory, Long> {

    private Statement statement;

    public Long getGameCategoryId(String name) throws Exception {
        String upit = "SELECT id FROM kategorijaigara WHERE naziv='" + name + "'";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(upit);
        Long id = null;
        if (rs.next()) {
            id = rs.getLong("id");
        }
        statement.close();
        rs.close();
        return id;

    }

    String getCategoryName(Long categoryId) throws Exception {
        String query = "SELECT naziv FROM kategorijaigara WHERE id=" + categoryId;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        String name = null;
        if (rs.next()) {
            name = rs.getString("naziv");
        }
        statement.close();
        rs.close();
        return name;
    }

    @Override
    public List<GameCategory> getAll() throws Exception {
        List<GameCategory>  returnValue=new ArrayList<>();
      GameCategory[] categories=GameCategory.values();
        for (GameCategory category : categories) {
            returnValue.add(category);
            
        }
        return returnValue;
    }

    @Override
    public void add(GameCategory t) throws Exception {
         //TODO: Implement later
        }

    @Override
    public void edit(GameCategory odlOne, GameCategory newOne) throws Exception {
         //TODO: Implement later
            }

    @Override
    public void delete(GameCategory t) throws Exception {
         //TODO: Implement later
          }

    @Override
    public List<GameCategory> getByQuery(String query) throws Exception {
         //TODO: Implement later
         return null;
          }

}
