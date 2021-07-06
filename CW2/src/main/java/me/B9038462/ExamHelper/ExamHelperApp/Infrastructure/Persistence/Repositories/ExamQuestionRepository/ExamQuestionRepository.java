package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.ExamQuestionRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.ExamQuestion;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.BaseRepository;

public class ExamQuestionRepository extends BaseRepository<ExamQuestion> implements IExamQuestionRepository<ExamQuestion> {
    public ExamQuestionRepository() {
        super(ExamQuestion.class, DatabaseManager.getManager().getExamHelperDB(), "ExamQuestion");
    }
}
