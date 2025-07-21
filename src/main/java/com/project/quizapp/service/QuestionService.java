package com.project.quizapp.service;

import com.project.quizapp.dao.QuestionDao;
import com.project.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAll() {
        return questionDao.findAll();
    }

    public List<Question> getAllByCat(String questionCategory) {
        return questionDao.findByQuestionCategory(questionCategory);
    }

    public Question insert(Question question){
        return questionDao.save(question);
    }

    public String deleteById(Integer id) {
        questionDao.deleteById(id);
        return "success";
    }

    public List<Question> getAllByCatWithLimit(String questionCategory, int limit) {
        return questionDao.findByCategoryWithLimit(questionCategory, limit);
    }

}
