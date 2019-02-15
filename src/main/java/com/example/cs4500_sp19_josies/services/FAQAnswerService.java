package com.example.cs4500_sp19_josies.services;

import java.util.List;

import com.example.cs4500_sp19_josies.models.FrequentlyAskedAnswer;
import com.example.cs4500_sp19_josies.repositories.FAQAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class FAQAnswerService {
    @Autowired
    FAQAnswerRepository repository;
    @GetMapping("/api/faq-answers")
    public List<FrequentlyAskedAnswer> findAllFrequentlyAskedAnswers() {
        return repository.findAllFrequentlyAskedAnswers();
    }
    @GetMapping("/api/faq-answers/{id}")
    public FrequentlyAskedAnswer findFrequentlyAskedAnswerById(
            @PathVariable("id") Integer id) {
        return repository.findFrequentlyAskedAnswerById(id);
    }

}