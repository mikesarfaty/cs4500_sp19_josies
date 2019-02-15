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
    @PostMapping("/api/faqs")
    public FrequentlyAskedAnswer createFrequentlyAskedAnswer(@RequestBody FrequentlyAskedAnswer frequentlyAskedQuestion) {
        return repository.save(frequentlyAskedQuestion);
    }
    @PutMapping("/api/faqs/{faqAnswerId}/title")
    public FrequentlyAskedAnswer updateFrequentlyAskedAnswer(
            @PathVariable("faqAnswerId") Integer id,
            @RequestBody FrequentlyAskedAnswer faqAnswerUpdates) {
        FrequentlyAskedAnswer faq = repository.findFrequentlyAskedAnswerById(id);
        faq.setAnswer(faqAnswerUpdates.getAnswer());
        return repository.save(faq);
    }
    @DeleteMapping("/api/faqs/{faqAnswerId}")
    public void deleteFrequentlyAskedAnswer(
            @PathVariable("faqAnswerId") Integer id) {
        repository.deleteById(id);
    }

}