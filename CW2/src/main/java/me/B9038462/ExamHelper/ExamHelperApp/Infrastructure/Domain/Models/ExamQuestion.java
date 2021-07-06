package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

public class ExamQuestion {
    private int ID;
    private int ExamID;
    private int QuestionID;

    public ExamQuestion(int ID, int examID, int questionID) {
        this.ID = ID;
        ExamID = examID;
        QuestionID = questionID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int examID) {
        ExamID = examID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    @Override
    public String toString() {
        return "ExamQuestion{" +
                "ID=" + ID +
                ", ExamID=" + ExamID +
                ", QuestionID=" + QuestionID +
                '}';
    }
}
