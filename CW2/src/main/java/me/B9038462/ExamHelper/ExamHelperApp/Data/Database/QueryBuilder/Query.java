package me.B9038462.ExamHelper.ExamHelperApp.Data.Database.QueryBuilder;

import java.sql.Connection;

public class Query {
    protected Connection connection;
    protected String sql;

    public Query(Connection connection) {
        this.connection = connection;
    }

    public SelectQuery select(String tableName) {
        return new SelectQuery(this.connection, "SELECT * FROM " + tableName);
    }
    public InsertQuery insert(String tableName) {
        return new InsertQuery(this.connection, "INSERT INTO " + tableName + "(");
    }
    public UpdateQuery update(String tableName) {
        return new UpdateQuery(this.connection, "UPDATE " + tableName + " SET");
    }
    public DeleteQuery delete(String tableName) {
        return new DeleteQuery(this.connection, "DELETE FROM " + tableName);
    }


    public Connection getConnection() {
        return this.connection;
    }
    public String getQuery() {
        return this.sql;
    }


    @Override
    public String toString() {
        return "Query{" +
                "connection=" + connection +
                '}';
    }
}
