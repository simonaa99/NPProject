/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.broker;

import java.io.IOException;
import rs.ac.bg.fon.np_project.commonlibrary.model.AbstractDO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import  rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;

/**
 *
 * @author Simona
 */
public class DatabaseBroker {
    
    public void add(AbstractDO abstractDO) throws SQLException, IOException{
        String query="INSERT INTO "+abstractDO.getClassName()+" ("+abstractDO.getAttributeList()+") VALUES ("+abstractDO.getAttributeValues()+")";
        Statement s= DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        s.close();
       
    }
    public void delete(AbstractDO abstractDO) throws SQLException, IOException{
         String query="DELETE FROM "+abstractDO.getClassName()+" WHERE "+abstractDO.getQueryCondition();
        Statement s= DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        s.close();
       
    }
    public void getAll(AbstractDO abstractDO) throws SQLException, IOException{
         String query="SELECT * FROM "+abstractDO.getClassName();
        Statement s= DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        s.close();
        
    }
    
}
