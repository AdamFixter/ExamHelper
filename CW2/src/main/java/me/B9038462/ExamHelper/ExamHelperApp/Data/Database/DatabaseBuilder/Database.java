package me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {
    private DatabaseConfig config;
    private String fileName;
    private Connection connection;

    public Database(DatabaseConfig config) {
        this.config = config;
        this.fileName = config.getName();

        //Create tables
        this.config.getTables().forEach(table -> table.build(this));
    }


    // Initialize Connection with table
    public void initialize(String tableName) {
        connection = getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName);
            ResultSet rs = ps.executeQuery();
            close(ps, rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Unable to retreive connection");
        }
    }

    public Connection getSQLConnection() {
        File dataFolder = new File(this.fileName + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                System.out.println("File write error: " + this.fileName + ".db");
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            // Make sure the library is installed
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            System.out.println("SQLite exception on initialize:" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("You need the SQLite JBDC library.");
        }
        return null;
    }
    public void loadTable(Table table) {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(table.getTableCreation());
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize(table.getTableName());
    }

    // Close Database
    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            System.out.println("Error occured when closing error");
        }
    }

    public DatabaseConfig getConfig() {
        return config;
    }

}
