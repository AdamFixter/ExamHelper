package me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Database.QueryBuilder.Query;
import me.B9038462.ExamHelper.ExamHelperApp.Models.Exam;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.BaseRepository;

public class ExamRepository extends BaseRepository<Exam> implements IExamRepository<Exam> {
    public ExamRepository() {
        super(Exam.class, DatabaseManager.getManager().getExamHelperDB(), "ExamStore");
    }

    @Override
    public void create(Exam entity) {
        new Query(DatabaseManager.getManager().getExamHelperDB().getSQLConnection()).insert("ExamStore")
                .insert("ID, Name")
                .value(entity.getID())
                .value(entity.getName())
                .execute();
    }
}
