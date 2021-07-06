package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

public class Exam {
    private int ID;
    private String name;

    public Exam(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
