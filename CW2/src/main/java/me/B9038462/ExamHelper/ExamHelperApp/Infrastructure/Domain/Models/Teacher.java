package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

public class Teacher {
    private int ID;
    private String name;
    private int schoolID;
    private int userID;

    public Teacher(int ID, String name, int schoolID, int userID) {
        this.ID = ID;
        this.name = name;
        this.schoolID = schoolID;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", schoolID=" + schoolID +
                ", userID=" + userID +
                '}';
    }
}