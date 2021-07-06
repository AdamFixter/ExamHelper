package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuestionTest {
    @Test
    public void testConstructor() {
        Question actualQuestion = new Question(1, "The characteristics of someone or something", "Answer",
                QuestionType.MULTIPLE_ANSWER, QuestionMarkType.MANUAL, 1);
        actualQuestion.setAnswer("Answer");
        actualQuestion.setDescription("The characteristics of someone or something");
        actualQuestion.setID(1);
        actualQuestion.setMarkType(QuestionMarkType.MANUAL);
        actualQuestion.setPoints(1);
        actualQuestion.setType(QuestionType.MULTIPLE_ANSWER);
        assertEquals("Answer", actualQuestion.getAnswer());
        assertEquals("The characteristics of someone or something", actualQuestion.getDescription());
        assertEquals(1, actualQuestion.getID());
        assertEquals(QuestionMarkType.MANUAL, actualQuestion.getMarkType());
        assertEquals(1, actualQuestion.getPoints());
        assertEquals(QuestionType.MULTIPLE_ANSWER, actualQuestion.getType());
        assertEquals(
                "Question{ID=1, description='The characteristics of someone or something', answer='Answer', type=MULTIPLE"
                        + "_ANSWER, markType=MANUAL, points=1}",
                actualQuestion.toString());
    }
}

