package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExamQuestionTest {
    @Test
    public void testConstructor() {
        ExamQuestion actualExamQuestion = new ExamQuestion(1, 1, 1);
        actualExamQuestion.setExamID(1);
        actualExamQuestion.setID(1);
        actualExamQuestion.setQuestionID(1);
        assertEquals(1, actualExamQuestion.getExamID());
        assertEquals(1, actualExamQuestion.getID());
        assertEquals(1, actualExamQuestion.getQuestionID());
        assertEquals("ExamQuestion{ID=1, ExamID=1, QuestionID=1}", actualExamQuestion.toString());
    }
}

