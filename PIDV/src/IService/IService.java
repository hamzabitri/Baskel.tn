/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azizs
 * @param <T>
 */

public interface IService<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean update(T t,String b) throws SQLException;
    List<T> readAll() throws SQLException;
    List<T> getTrier()throws SQLException;
    T getById(T f);
    T getByName(T f);
    
}

