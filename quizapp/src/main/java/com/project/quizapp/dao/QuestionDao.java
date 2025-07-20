package com.project.quizapp.dao;

import com.project.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionCategory(String questionCategory);

    @Query(value = "SELECT * FROM question where question_category = :questionCategory ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Question> findByCategoryWithLimit(String questionCategory, int limit);
}