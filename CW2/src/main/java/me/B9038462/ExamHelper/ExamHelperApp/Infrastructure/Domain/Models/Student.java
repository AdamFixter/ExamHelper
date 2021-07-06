package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import java.util.Date;

public class Student {
    private int ID;
    private String name;
    private Date DOB;
    private int schoolID;
    private int classID;
    private int userID;

    public Student(int ID, String name, Date DOB, int schoolID, int classID, int userID) {
        this.ID = ID;
        this.name = name;
        this.DOB = DOB;
        this.schoolID = schoolID;
        this.classID = classID;
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

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", DOB=" + DOB +
                ", schoolID=" + schoolID +
                ", classID=" + classID +
                ", userID=" + userID +
                '}';
    }
}
