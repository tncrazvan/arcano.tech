package com.github.tncrazvan.arcanotech.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Administrator
 */
public class MySQL extends MysqlDataSource{

    private static final long serialVersionUID = -4729237726418164448L;

    public MySQL(String hostname, String username, String password) {
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
