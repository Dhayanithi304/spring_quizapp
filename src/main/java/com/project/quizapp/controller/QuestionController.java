package com.project.quizapp.controller;

import com.project.quizapp.model.Question;
import com.project.quizapp.response.ResponseHandler;
import com.project.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getAllQues")
    public ResponseEntity<List<Question>> getAllQues(){
        try {
//            return ResponseEntity.ok(questionService.getAll());
            return new ResponseEntity<>(questionService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/getAllQues/{questionCategory}")
    public ResponseEntity<List<Question>> getAllByCategory(@PathVariable String questionCategory){
        try {
            return new ResponseEntity<>(questionService.getAllByCat(questionCategory), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Question question){
        try {
            Question newQuestion = questionService.insert(question);
            return ResponseHandler.generate("Login successful", HttpStatus.CREATED, newQuestion);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_GATEWAY);

        }
    }

    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(questionService.deleteById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_GATEWAY);
        }
    }
}
