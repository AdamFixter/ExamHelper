package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamResultRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.ExamResult;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.BaseRepository;

public class ExamResultRepository extends BaseRepository<ExamResult> implements IExamResultRepository<ExamResult> {
    public ExamResultRepository() {
        super(ExamResult.class, DatabaseManager.getManager().getExamHelperDB(), "ExamResult");
    }
}
