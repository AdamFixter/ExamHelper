package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.QuestionRepository;

import me.B9038462.ExamHelper.ExamHelperApp.Data.Database.DatabaseManager;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Domain.Models.Question;
import me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Persistence.Repositories.BaseRepository;

public class QuestionRepository extends BaseRepository<Question> implements IQuestionRepository<Question> {
    public QuestionRepository() {
        super(Question.class, DatabaseManager.getManager().getExamHelperDB(), "QuestionStore");
    }
}
