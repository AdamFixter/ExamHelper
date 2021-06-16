package me.B9038462.ExamHelper.ExamHelperApp.Models;

public class Question {
    private int ID;
    private String description;
    private String answer;
    private QuestionType type;
    private QuestionMarkType markType;
    private int points;

    public Question(int ID, String description, String answer, QuestionType type, QuestionMarkType markType, int points) {
        this.ID = ID;
        this.description = description;
        this.answer = answer;
        this.type = type;
        this.markType = markType;
        this.points = points;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public QuestionMarkType getMarkType() {
        return markType;
    }

    public void setMarkType(QuestionMarkType markType) {
        this.markType = markType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Question{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", answer='" + answer + '\'' +
                ", type=" + type +
                ", markType=" + markType +
                ", points=" + points +
                '}';
    }
}
