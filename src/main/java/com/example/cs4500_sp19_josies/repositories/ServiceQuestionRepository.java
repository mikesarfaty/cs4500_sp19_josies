package com.example.cs4500_sp19_josies.repositories;

import com.example.cs4500_sp19_josies.models.ServiceQuestion;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ServiceQuestionRepository
    extends CrudRepository<ServiceQuestion, Integer> {
  @Query(value="SELECT entity FROM ServiceQuestion entity")
  public List<ServiceQuestion> findAllServiceQuestions();
  @Query(value="SELECT entity FROM ServiceQuestion entity WHERE id=:id")
  public ServiceQuestion findServiceQuestionById(
      @Param("id") Integer id);
}
