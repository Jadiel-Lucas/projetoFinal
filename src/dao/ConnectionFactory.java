package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String url;
    private final String usr;
    private final String pwd;
    
    public ConnectionFactory() {
        
        this.url = "jdbc:mysql://localhost/sakila";
        this.usr = "root";
        this.pwd = "1234";
        
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, usr, pwd);
    }
    
}
