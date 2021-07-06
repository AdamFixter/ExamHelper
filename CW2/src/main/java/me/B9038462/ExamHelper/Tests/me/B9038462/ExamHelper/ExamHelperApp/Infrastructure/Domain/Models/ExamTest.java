package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExamTest {
    @Test
    public void testConstructor() {
        Exam actualExam = new Exam(1, "Name");
        actualExam.setID(1);
        actualExam.setName("Name");
        assertEquals(1, actualExam.getID());
        assertEquals("Name", actualExam.getName());
        assertEquals("Exam{ID=1, name='Name'}", actualExam.toString());
    }
}

