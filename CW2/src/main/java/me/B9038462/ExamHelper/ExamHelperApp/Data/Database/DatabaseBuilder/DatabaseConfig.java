package me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseBuilder;

import java.util.Arrays;
import java.util.List;

public enum DatabaseConfig {
    EXAM_HELPER_DB("ExamHelperDB", Arrays.asList(
            new Table("QuestionStore")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("Description", "VARCHAR(255)")
                    .addColumn("Answer", "VARCHAR(255)")
                    .addColumn("Type", "INT(1000)")
                    .addColumn("MarkType", "INT(1000)")
                    .addColumn("Points", "INT(1000)"),
            new Table("ExamStore")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("Name", "VARCHAR(255)"),
            new Table("ExamResult")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("ExamID", "INT(1000)")
                    .addColumn("TotalPoints", "INT(10000)")
                    .addForeignKey("ExamStore", "ID"),
            new Table("ExamQuestion")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("ExamID", "INT(1000)")
                    .addColumn("QuestionID", "INT(1000)")
                    .addForeignKey("ExamStore", "ID")
                    .addForeignKey("QuestionStore", "ID"),
            new Table("Student")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("Name", "VARCHAR(255)")
                    .addColumn("DOB", "TEXT")
                    .addColumn("ClassID", "INT(100000)")
                    .addColumn("SchoolID", "INT(100000)")
                    .addColumn("UserID", "INT(100000)")
                    .addForeignKey("Class", "ID")
                    .addForeignKey("School", "ID")
                    .addForeignKey("User", "ID"),
            new Table("Teacher")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("Name", "VARCHAR(255)")
                    .addColumn("UserID", "INT(100000)")
                    .addForeignKey("User", "ID"),
            new Table("School")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("SchoolType", "INT(1000)")
                    .addColumn("Name", "VARCHAR(255)")
                    .addColumn("Description", "VARCHAR(255)")
                    .addColumn("PhoneNumber", "INT(100000)")
                    .addColumn("Email", "VARCHAR(255)"),
            new Table("User")
                    .setPrimaryKey("ID").addColumn("ID", "INT(100000)")
                    .addColumn("Password", "VARCHAR(255)")


    ));

    private String name;
    private List<Table> tables;

    DatabaseConfig(String name, List<Table> tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public List<Table> getTables() {
        return tables;
    }
}
