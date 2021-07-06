package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Exam;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.BaseRepository;

public class ExamRepository extends BaseRepository<Exam> implements IExamRepository<Exam> {
    public ExamRepository() {
        super(Exam.class, DatabaseManager.getManager().getExamHelperDB(), "ExamStore");
    }

}
