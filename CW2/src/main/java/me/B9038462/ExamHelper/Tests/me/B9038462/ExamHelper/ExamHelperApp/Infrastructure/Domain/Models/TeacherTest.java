package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeacherTest {
    @Test
    public void testConstructor() {
        Teacher actualTeacher = new Teacher(1, "Name", 1, 1);
        actualTeacher.setID(1);
        actualTeacher.setName("Name");
        actualTeacher.setSchoolID(1);
        actualTeacher.setUserID(1);
        assertEquals(1, actualTeacher.getID());
        assertEquals("Name", actualTeacher.getName());
        assertEquals(1, actualTeacher.getSchoolID());
        assertEquals(1, actualTeacher.getUserID());
        assertEquals("Teacher{ID=1, name='Name', schoolID=1, userID=1}", actualTeacher.toString());
    }
}

