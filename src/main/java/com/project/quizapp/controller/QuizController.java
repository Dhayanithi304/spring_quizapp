package com.project.quizapp.controller;

import com.project.quizapp.dto.AnswerRes;
import com.project.quizapp.dto.QuestionWrapper;
import com.project.quizapp.model.Question;
import com.project.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestParam String cat, @RequestParam int numQ, @RequestParam String title){
        try {
            return new ResponseEntity<>(quizService.insert(title, cat, numQ), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        try {
            return new ResponseEntity<>(quizService.getQuestionByQuizId(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
        }
    }


    @PostMapping("/submitQuiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody AnswerRes answerRes){
        try {
            return new ResponseEntity<>(quizService.calculateScore(id, answerRes), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(0, HttpStatus.BAD_GATEWAY);
        }
    }
}
