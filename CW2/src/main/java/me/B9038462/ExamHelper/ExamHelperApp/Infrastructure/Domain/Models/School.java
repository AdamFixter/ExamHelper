package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

public class School {
    private int ID;
    private SchoolType schoolType;
    private String name;
    private String description;
    private int phoneNumber;
    private String email;

    public School(int ID, SchoolType schoolType, String name, String description, int phoneNumber, String email) {
        this.ID = ID;
        this.schoolType = schoolType;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "School{" +
                "ID=" + ID +
                ", schoolType=" + schoolType +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
