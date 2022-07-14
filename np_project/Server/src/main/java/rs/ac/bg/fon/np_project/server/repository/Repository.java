/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.repository;

import java.util.List;

/**
 *
 * @author Simona
 */
public interface Repository<T, K> {
    
    List<T> getAll() throws Exception;
    void add(T t) throws Exception;
    void edit(T stari, T novi) throws Exception;
    void delete(T t) throws Exception;
    List<T> getByQuery(String query) throws Exception;
   
}
