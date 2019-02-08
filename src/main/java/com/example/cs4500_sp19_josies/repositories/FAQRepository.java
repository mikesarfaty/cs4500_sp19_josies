package com.example.cs4500_sp19_josies.repositories;

import com.example.cs4500_sp19_josies.models.FrequentlyAskedQuestion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FAQRepository
        extends CrudRepository<FrequentlyAskedQuestion, Integer> {
  @Query(value="SELECT entity FROM FrequentlyAskedQuestion entity")
  public List<FrequentlyAskedQuestion> findAllFrequentlyAskedQuestions();
  @Query(value="SELECT entity FROM FrequentlyAskedQuestion entity WHERE id=:id")
  public FrequentlyAskedQuestion findFrequentlyAskedQuestionById(
          @Param("id") Integer id);
}
