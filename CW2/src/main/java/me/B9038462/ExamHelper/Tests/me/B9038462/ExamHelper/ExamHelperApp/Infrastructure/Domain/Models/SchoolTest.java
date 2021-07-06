package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SchoolTest {
    @Test
    public void testConstructor() {
        School actualSchool = new School(1, SchoolType.PRIMARY, "Name", "The characteristics of someone or something", 10,
                "jane.doe@example.org");
        actualSchool.setDescription("The characteristics of someone or something");
        actualSchool.setEmail("jane.doe@example.org");
        actualSchool.setID(1);
        actualSchool.setName("Name");
        actualSchool.setPhoneNumber(10);
        actualSchool.setSchoolType(SchoolType.PRIMARY);
        assertEquals("The characteristics of someone or something", actualSchool.getDescription());
        assertEquals("jane.doe@example.org", actualSchool.getEmail());
        assertEquals(1, actualSchool.getID());
        assertEquals("Name", actualSchool.getName());
        assertEquals(10, actualSchool.getPhoneNumber());
        assertEquals(SchoolType.PRIMARY, actualSchool.getSchoolType());
        assertEquals(
                "School{ID=1, schoolType=PRIMARY, name='Name', description='The characteristics of someone or something',"
                        + " phoneNumber=10, email='jane.doe@example.org'}",
                actualSchool.toString());
    }
}

