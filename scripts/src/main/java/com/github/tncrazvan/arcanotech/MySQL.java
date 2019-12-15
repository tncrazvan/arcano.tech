/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.tncrazvan.arcanotech;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class MySQL extends MysqlDataSource{

    public MySQL(String hostname,String username, String password) {
        setUser(username);
        setPassword(password);
        setServerName(hostname);
    }
    
    public MySQL(String hostname,String username, String password, String database) {
        setUser(username);
        setPassword(password);
        setServerName(hostname);
        setDatabaseName(database);
    }
    
    public Connection connect() throws SQLException{
        return getConnection();
    }
}
