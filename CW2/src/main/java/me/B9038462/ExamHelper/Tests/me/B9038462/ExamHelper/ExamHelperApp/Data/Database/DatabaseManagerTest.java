package me.B9038462.ExamHelper.ExamHelperApp.Data.Database;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class DatabaseManagerTest {
    @Test
    public void testConstructor() {
        assertNull((new DatabaseManager()).getExamHelperDB());
    }
}

