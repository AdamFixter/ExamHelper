package me.B9038462.ExamHelper.ExamHelperApp.Data.Database.QueryBuilder;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class DeleteQueryTest {
    @Test
    public void testConstructor() {
        DeleteQuery actualDeleteQuery = new DeleteQuery(mock(Connection.class), "Sql");

        Connection expectedConnection = actualDeleteQuery.connection;
        assertSame(expectedConnection, actualDeleteQuery.getConnection());
        assertNull(actualDeleteQuery.getQuery());
    }

    @Test
    public void testWhere() {
        DeleteQuery deleteQuery = new DeleteQuery(mock(Connection.class), "Sql");
        DeleteQuery actualWhereResult = deleteQuery.where("Key", "Value");
        assertSame(deleteQuery, actualWhereResult);
        assertNull(actualWhereResult.getQuery());
    }

    @Test
    public void testExecute() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).close();
        when(preparedStatement.executeUpdate()).thenReturn(1);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        (new DeleteQuery(connection, "Sql")).execute();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).close();
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testExecute2() throws SQLException {
        SQLException sqlException = mock(SQLException.class);
        doNothing().when(sqlException).printStackTrace();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(sqlException).when(preparedStatement).close();
        when(preparedStatement.executeUpdate()).thenReturn(1);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        (new DeleteQuery(connection, "Sql")).execute();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).close();
        verify(preparedStatement).executeUpdate();
        verify(doThrow(sqlException)).printStackTrace();
    }

    @Test
    public void testExecute3() throws SQLException {
        SQLException sqlException = mock(SQLException.class);
        doNothing().when(sqlException).printStackTrace();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(sqlException);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        (new DeleteQuery(connection, "Sql")).execute();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeUpdate();
        verify(sqlException).printStackTrace();
    }

    @Test
    public void testExecute4() throws SQLException {
        SQLException sqlException = mock(SQLException.class);
        doNothing().when(sqlException).printStackTrace();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString())).thenThrow(sqlException);
        (new DeleteQuery(connection, "Sql")).execute();
        verify(connection).prepareStatement(anyString());
        verify(sqlException).printStackTrace();
    }
}

