package com.uPanamarou.Conection;

import lombok.Getter;
import lombok.Setter;

public class JDBCConnectionData {


    public String getMyDBUsername() {
        return "root";
    }

    public String getMyDBPassword() {
        return "TYTbvb766@";
    }

    public String getMyDBConnectionUrl() {
        return "jdbc:mysql://localhost:3306/my_db";
    }
}
