package eccomapp.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Connection {

    public static java.sql.Connection create() {
        java.sql.Connection connection=null ;
        try {

            forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:5432/ecomprashant", "prashant", "prashants");

        System.out.println("Connection created");
        } catch (SQLException | ClassNotFoundException E) {
            System.out.println(E);
        }
        return connection;
    }

}

