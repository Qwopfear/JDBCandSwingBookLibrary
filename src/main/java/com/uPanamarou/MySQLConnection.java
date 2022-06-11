package com.uPanamarou;

import com.uPanamarou.Conection.JDBCConnectionData;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public Connection connectToDataBase(){
        JDBCConnectionData connectionData = new JDBCConnectionData();
        String username = connectionData.getMyDBUsername();
        String password = connectionData.getMyDBPassword();
        String url = connectionData.getMyDBConnectionUrl();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return connection;
    }
    }
