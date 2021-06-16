package me.B9038462.ExamHelper.ExamHelperApp.Database;

import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseBuilder.Database;
import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseBuilder.DatabaseConfig;

import java.sql.*;

public class ExamHelperDB extends Database {
    private Connection connection;

    public ExamHelperDB(DatabaseConfig config) {
        super(config);
        this.connection = this.getSQLConnection();
    }

    //Methods to query the db
    public void execute(String sql) {
        try {
            if (this.connection == null) {
                System.out.println("No Connection !");
            }
            PreparedStatement prest = this.connection.prepareStatement(sql);
            prest.executeUpdate();
            prest.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
