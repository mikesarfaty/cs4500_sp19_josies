package com.example.cs4500_sp19_josies.repositories;

import com.example.cs4500_sp19_josies.models.ServiceAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceAnswerRepository extends CrudRepository<ServiceAnswer, Integer> {
    @Query("SELECT serviceAnswer FROM ServiceAnswer serviceAnswer")
    List<ServiceAnswer> findAllServiceAnswers();
    @Query("SELECT serviceAnswer FROM ServiceAnswer serviceAnswer WHERE serviceAnswer.id=:id")
    ServiceAnswer findServiceAnswerById(
            @Param("id") Integer id);

}
