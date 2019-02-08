package com.example.cs4500_sp19_josies.services;

import com.example.cs4500_sp19_josies.models.FrequentlyAskedQuestion;
import com.example.cs4500_sp19_josies.repositories.FAQRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins="*")
public class FAQService {
  @Autowired
  FAQRepository repository;
  @GetMapping("/api/faqs")
  public List<FrequentlyAskedQuestion> findAllFrequentlyAskedQuestions() {
    return repository.findAllFrequentlyAskedQuestions();
  }
  @GetMapping("/api/faqs/{id}")
  public FrequentlyAskedQuestion findFrequentlyAskedQuestionById(
          @PathVariable("id") Integer id) {
    return repository.findFrequentlyAskedQuestionById(id);
  }
}
