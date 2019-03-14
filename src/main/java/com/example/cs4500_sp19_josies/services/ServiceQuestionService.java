package com.example.cs4500_sp19_josies.services;

import com.example.cs4500_sp19_josies.models.ServiceQuestion;
import com.example.cs4500_sp19_josies.repositories.ServiceQuestionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class ServiceQuestionService {


    @Autowired
    ServiceQuestionRepository repository;


    @GetMapping("/api/service-questions")
    public List<ServiceQuestion> findAllQuestions() {
        return repository.findAllServiceQuestions();
    }

    @GetMapping("/api/services/{service-questionId}")
    public ServiceQuestion findQuestionById(
            @PathVariable("service-questionId") Integer id) {
        return repository.findServiceQuestionById(id);
    }

    @PostMapping("/api/service-questions")
    public ServiceQuestion createQuestion(@RequestBody ServiceQuestion question) {
        return repository.save(question);
    }

    @PutMapping("/api/service-questions/{service-questionId}")
    public ServiceQuestion updateQuestion(
            @PathVariable("service-questionId") Integer id,
            @RequestBody ServiceQuestion serviceUpdates) {
        ServiceQuestion question = repository.findServiceQuestionById(id);
        question.setQuestion(serviceUpdates.getQuestion());
        return repository.save(question);
    }
    @DeleteMapping("/api/service-questions/{service-questionId}")
    public void deleteQuestion(
            @PathVariable("service-questionId") Integer id) {
        repository.deleteById(id);
    }
}
