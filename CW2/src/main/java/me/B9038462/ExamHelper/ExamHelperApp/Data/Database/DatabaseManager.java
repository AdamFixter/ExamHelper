package me.B9038462.ExamHelper.ExamHelperApp.Data.Database;

import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseBuilder.DatabaseConfig;

public class DatabaseManager {
    private ExamHelperDB examHelperDB;

    public DatabaseManager() { }
    private static DatabaseManager dm = new DatabaseManager();
    public static DatabaseManager getManager() {
        return dm;
    }

    public void init() {
        this.examHelperDB = new ExamHelperDB(DatabaseConfig.EXAM_HELPER_DB);
    }

    public ExamHelperDB getExamHelperDB() {
        return this.examHelperDB;
    }

    @Override
    public String toString() {
        return "DatabaseManager{" +
                "examHelperDB=" + examHelperDB +
                ", dm=" + dm +
                '}';
    }
}
