package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class StudentTest {
    @Test
    public void testConstructor() {
        Student actualStudent = new Student(1, "Name", new Date(1L), 1, 1, 1);
        actualStudent.setClassID(1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant());
        actualStudent.setDOB(fromResult);
        actualStudent.setID(1);
        actualStudent.setName("Name");
        actualStudent.setSchoolID(1);
        actualStudent.setUserID(1);
        assertEquals(1, actualStudent.getClassID());
        assertSame(fromResult, actualStudent.getDOB());
        assertEquals(1, actualStudent.getID());
        assertEquals("Name", actualStudent.getName());
        assertEquals(1, actualStudent.getSchoolID());
        assertEquals(1, actualStudent.getUserID());
        assertEquals("Student{ID=1, name='Name', DOB=Thu Jan 01 00:00:00 GMT 1970, schoolID=1, classID=1, userID=1}",
                actualStudent.toString());
    }
}

