package com.uPanamarou.Conection;

import lombok.Getter;
import lombok.Setter;

public class JDBCConnectionData {


    public String getMyDBUsername() {
        return "bestuser";
    }

    public String getMyDBPassword() {
        return "bestuser";
    }

    public String getMyDBConnectionUrl() {
        return "jdbc:mysql://localhost:3306/my_db";
    }
}
