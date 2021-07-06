package me.B9038462.ExamHelper.ExamHelperApp.Data.Database.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteQuery extends Query {
    private String sql;
    private boolean and;
    private final ArrayList<Object> values;

    public DeleteQuery(final Connection connection, final String sql) {
        super(connection);
        this.sql = sql;
        this.and = false;
        this.values = new ArrayList<Object>();
    }

    public DeleteQuery where(final String key, final Object value) {
        if (this.and) {
            this.sql = String.valueOf(this.sql) + " AND";
        }
        else {
            this.sql = String.valueOf(this.sql) + " WHERE";
        }
        this.sql = String.valueOf(this.sql) + " " + key + "=";
        this.values.add(value);
        this.sql = String.valueOf(this.sql) + "?";
        this.and = true;
        return this;
    }

    public void execute() {
        try {
            final PreparedStatement prest = this.connection.prepareStatement(this.sql);
            int i = 1;
            for (final Object object : this.values) {
                prest.setObject(i, object);
                ++i;
            }
            prest.executeUpdate();
            prest.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
