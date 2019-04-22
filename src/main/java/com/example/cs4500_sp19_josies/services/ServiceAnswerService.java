package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_josies.models.ServiceAnswer;
import com.example.cs4500_sp19_josies.repositories.ServiceAnswerRepository;

@RestController
@CrossOrigin(origins="*")
public class ServiceAnswerService {
    @Autowired
    ServiceAnswerRepository repository;
    @GetMapping("/api/service-answers")
    public List<ServiceAnswer> findAllServiceAnswers() {
        return repository.findAllServiceAnswers();
    }
    @GetMapping("/api/service-answers/{service-answerId}")
    public ServiceAnswer findServiceAnswerById(
            @PathVariable("service-answerId") Integer id) {
        return repository.findServiceAnswerById(id);
    }

    @PostMapping("/api/service-answers")
    public ServiceAnswer createAnswer(@RequestBody ServiceAnswer answer) {
        return repository.save(answer);
    }

        @PutMapping("/api/service-answers/{service-answerId}")
    public ServiceAnswer updateAnswer(
            @PathVariable("service-answerId") Integer id,
            @RequestBody ServiceAnswer answerUpdates) {
        ServiceAnswer answer = repository.findServiceAnswerById(id);
        answer.setAnswer(answerUpdates.getAnswer());
        return repository.save(answer);
    }
    @DeleteMapping("/api/service-answers/{service-answerId}")
    public void deleteAnswer(
            @PathVariable("service-answerId") Integer id) {
        repository.deleteById(id);
    }
}