package com.example.cs4500_sp19_josies.services;

import com.example.cs4500_sp19_josies.models.FrequentlyAskedAnswer;
import com.example.cs4500_sp19_josies.models.FrequentlyAskedQuestion;
import com.example.cs4500_sp19_josies.repositories.FAQAnswerRepository;
import com.example.cs4500_sp19_josies.repositories.FAQRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins="*")
public class FAQService {
  @Autowired
  FAQRepository repository;
  FAQAnswerRepository answerRepo;
  @GetMapping("/api/faqs")
  public List<FrequentlyAskedQuestion> findAllFrequentlyAskedQuestions() {
    return repository.findAllFrequentlyAskedQuestions();
  }
  @GetMapping("/api/faqs/{id}")
  public FrequentlyAskedQuestion findFrequentlyAskedQuestionById(
          @PathVariable("id") Integer id) {
    return repository.findFrequentlyAskedQuestionById(id);
  }

  @PostMapping("/api/faqs")
  public FrequentlyAskedQuestion createFrequentlyAskedQuestion(@RequestBody FrequentlyAskedQuestion frequentlyAskedQuestion) {
    return repository.save(frequentlyAskedQuestion);
  }

  @PutMapping("/api/faqs/{faqId}")
  public FrequentlyAskedQuestion updateFrequentlyAskedQuestion(
          @PathVariable("faqId") Integer id,
          @RequestBody FrequentlyAskedQuestion faqUpdates) {
    FrequentlyAskedQuestion faq = repository.findFrequentlyAskedQuestionById(id);
    faq.setTitle(faqUpdates.getTitle());
    faq.setQuestion(faqUpdates.getQuestion());
    return repository.save(faq);
  }

  @DeleteMapping("/api/faqs/{faqId}")
  public void deleteFrequentlyAskedQuestion(
          @PathVariable("faqId") Integer id,
          @RequestBody FrequentlyAskedQuestion faqUpdates) {
    FrequentlyAskedQuestion faq = repository.findFrequentlyAskedQuestionById(id);
    for (FrequentlyAskedAnswer faa : faq.getAnswers()) {
      answerRepo.deleteById(faa.getId());
    }
    repository.deleteById(id);
  }
}
