package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExamResultTest {
    @Test
    public void testConstructor() {
        ExamResult actualExamResult = new ExamResult(1, 1, 1);
        actualExamResult.setExamID(1);
        actualExamResult.setID(1);
        actualExamResult.setTotalPoints(1);
        assertEquals(1, actualExamResult.getExamID());
        assertEquals(1, actualExamResult.getID());
        assertEquals(1, actualExamResult.getTotalPoints());
        assertEquals("ExamResult{ID=1, examID=1, totalPoints=1}", actualExamResult.toString());
    }
}

