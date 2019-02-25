package com.example.cs4500_sp19_josies.repositories;


import java.util.List;

import com.example.cs4500_sp19_josies.models.FrequentlyAskedAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FAQAnswerRepository
        extends CrudRepository<FrequentlyAskedAnswer, Integer> {
    @Query(value="SELECT entity FROM FrequentlyAskedAnswer entity")
    public List<FrequentlyAskedAnswer> findAllFrequentlyAskedAnswers();
    @Query(value="SELECT entity FROM FrequentlyAskedAnswer entity WHERE id=:id")
    public FrequentlyAskedAnswer findFrequentlyAskedAnswerById(
            @Param("id") Integer id);
}