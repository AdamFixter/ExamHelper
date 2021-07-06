package me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TableTest {
    @Test
    public void testConstructor() {
        Table actualTable = new Table("Table Name");
        assertEquals("", actualTable.getColumns());
        assertEquals("", actualTable.getPrimaryKey());
        assertEquals("", actualTable.getTableCreation());
        assertEquals("Table Name", actualTable.getTableName());
        assertEquals("Table{, tableName='Table Name', columns='', primaryKey='', tableCreation=''}",
                actualTable.toString());
    }

    @Test
    public void testSetPrimaryKey() {
        Table table = new Table("Table Name");
        Table actualSetPrimaryKeyResult = table.setPrimaryKey("Primary Key");
        assertSame(table, actualSetPrimaryKeyResult);
        assertEquals("(`Primary Key`)", actualSetPrimaryKeyResult.getPrimaryKey());
    }

    @Test
    public void testAddForeignKey() {
        Table table = new Table("Table Name");
        Table actualAddForeignKeyResult = table.addForeignKey("Table", "Table Col");
        assertSame(table, actualAddForeignKeyResult);
        assertEquals(" FOREIGN KEY (`Table Col`) REFERENCES Table (`Table Col`),", actualAddForeignKeyResult.getColumns());
    }

    @Test
    public void testAddColumn() {
        Table table = new Table("Table Name");
        Table actualAddColumnResult = table.addColumn("Name", "Data Info");
        assertSame(table, actualAddColumnResult);
        assertEquals("`Name` Data Info,", actualAddColumnResult.getColumns());
    }

    @Test
    public void testAddColumn2() {
        Table table = new Table("Table Name");
        table.addForeignKey("", "");
        Table actualAddColumnResult = table.addColumn("Name", "Data Info");
        assertSame(table, actualAddColumnResult);
        assertEquals(" FOREIGN KEY (``) REFERENCES  (``),`Name` Data Info,", actualAddColumnResult.getColumns());
    }
}

