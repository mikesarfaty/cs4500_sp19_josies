package com.example.cs4500_sp19_josies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs4500_sp19_josies.models.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    @Query(value="SELECT quote FROM Quote quote")
    public List<Quote> findAllQuotes();
    @Query(value="SELECT quote FROM Quote quote WHERE id=:id")
    public Quote findQuoteById(
            @Param("id") Integer id);
}