package com.arsh.question_service.Service;

import com.arsh.question_service.Model.Question;
import com.arsh.question_service.Model.QuestionWrapper;
import com.arsh.question_service.Model.Response;
import com.arsh.question_service.Repo.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return  new ResponseEntity<>(questionDao.findQuestionsByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<String> addQuestion(Question question) {
        Question save = questionDao.save(question);
        if (save != null) {
            return new ResponseEntity<>("Question added successfully",HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add question",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        if(questionDao.existsById(id)){
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Question not found",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        if (questionDao.existsById(question.getId())) {
            questionDao.save(question);
            return new ResponseEntity<>("Question updated successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>("Question not found",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numberOfQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numberOfQuestions);

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> listOfIds) {
        List<QuestionWrapper> wrappers= new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for(Integer id : listOfIds){
            Question q=questionDao.findById(id).get();
            questions.add(q);
        }
        for(Question q : questions){
            QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrappers.add(qw);
        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int result=0;
        
        for(Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                result++;
            }
        }

        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}