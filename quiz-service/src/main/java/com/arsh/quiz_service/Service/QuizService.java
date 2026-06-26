package com.arsh.quiz_service.Service;

import com.arsh.quiz_service.Feign.QuizInterface;
import com.arsh.quiz_service.Model.QuestionWrapper;
import com.arsh.quiz_service.Model.Quiz;
import com.arsh.quiz_service.Model.Response;
import com.arsh.quiz_service.Repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);


        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz=quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionids();
        List<QuestionWrapper> questionsForUsers = quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getQuizResult(Integer id, List<Response> responses) {
//
        ResponseEntity<Integer> result=quizInterface.getScore(responses);

        return result;
    }
}
