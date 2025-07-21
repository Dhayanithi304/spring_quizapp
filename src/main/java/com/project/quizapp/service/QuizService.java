package com.project.quizapp.service;

import com.project.quizapp.dao.QuizDao;
import com.project.quizapp.dto.AnswerRes;
import com.project.quizapp.dto.QuestionWrapper;
import com.project.quizapp.model.Question;
import com.project.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionService questionService;

    public String insert(String title, String category, int numQ){
        List<Question> questions = questionService.getAllByCatWithLimit(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return "Success";
    }

    public List<QuestionWrapper> getQuestionByQuizId(int id) {
        Optional<Quiz> optionalQuiz = quizDao.findById(id);
        if(optionalQuiz.isEmpty()){
            return new ArrayList<>();
        }
        List<Question> questions = optionalQuiz.get().getQuestions();
        List<QuestionWrapper> questionList = new ArrayList<>();
        for(Question q: questions){
            questionList.add(new QuestionWrapper(
                    q.getID(),
                    q.getQuestion(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            ));
        }
        return questionList;
    }


    public Integer calculateScore(int id, AnswerRes answerRes) {
        int scorce = 0;
        return scorce;
    }
}
