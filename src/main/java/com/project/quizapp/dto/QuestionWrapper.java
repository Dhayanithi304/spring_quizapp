package com.project.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    private Integer ID;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

//    public QuestionWrapper(Integer ID, String question, String option2, String option1, String option3, String option4) {
//        this.ID = ID;
//        this.question = question;
//        this.option2 = option2;
//        this.option1 = option1;
//        this.option3 = option3;
//        this.option4 = option4;
//    }
}
