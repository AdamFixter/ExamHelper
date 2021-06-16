package me.B9038462.ExamHelper.ExamHelperApp.Repositories.ExamResultRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Database.QueryBuilder.Query;
import me.B9038462.ExamHelper.ExamHelperApp.Models.ExamResult;
import me.B9038462.ExamHelper.ExamHelperApp.Repositories.BaseRepository;

public class ExamResultRepository extends BaseRepository<ExamResult> implements IExamResultRepository<ExamResult> {
    public ExamResultRepository() {
        super(ExamResult.class, DatabaseManager.getManager().getExamHelperDB(), "ExamResult");
    }

    @Override
    public void create(ExamResult entity) {
        new Query(DatabaseManager.getManager().getExamHelperDB().getSQLConnection()).insert("ExamResult")
                .insert("ID, ExamID, TotalPoints")
                .value(entity.getID())
                .value(entity.getExamID())
                .value(entity.getTotalPoints())
                .execute();
    }
}
