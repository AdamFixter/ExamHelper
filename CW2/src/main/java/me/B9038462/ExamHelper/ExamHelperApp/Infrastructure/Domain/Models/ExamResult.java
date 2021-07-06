package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

public class ExamResult {

    private int ID;
    private int examID;
    private int totalPoints;

    public ExamResult(int ID, int examID, int totalPoints) {
        this.ID = ID;
        this.examID = examID;
        this.totalPoints = totalPoints;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "ID=" + ID +
                ", examID=" + examID +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
