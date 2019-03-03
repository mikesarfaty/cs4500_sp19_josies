package com.example.cs4500_sp19_josies.tests;

import com.example.cs4500_sp19_josies.algorithms.ServiceSearch;
import com.example.cs4500_sp19_josies.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceSearchTest {

    Service cleaning = new Service();
    ServiceQuestion numberOfRooms;
    ServiceQuestion havePets;

    User sam = new User();
    User jose = new User();
    User jack = new User();


    @BeforeEach
    void setUp() {
        numberOfRooms = new ServiceQuestion("How many rooms", QuestionType.Range);
        numberOfRooms.setId(1);

        havePets = new ServiceQuestion("Have any pets?", QuestionType.TrueFalse);
        havePets.setId(2);


    }


    @Test
    void testServiceSearchRange() {

        ServiceAnswer samsRooms = new ServiceAnswer();
        samsRooms.setId(1);
        samsRooms.setMinRangeAnswer(5);
        samsRooms.setMaxRangeAnswer(6);
        samsRooms.setProvider(sam);
        samsRooms.setServiceQuestion(numberOfRooms);
        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsRooms);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer josesRooms = new ServiceAnswer();
        josesRooms.setId(2);
        josesRooms.setMinRangeAnswer(1);
        josesRooms.setMaxRangeAnswer(3);
        josesRooms.setProvider(jose);
        josesRooms.setServiceQuestion(numberOfRooms);
        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesRooms);
        jose.setServiceAnswers(josesAnswers);


        ServiceAnswer jacksRooms = new ServiceAnswer();
        jacksRooms.setId(2);
        jacksRooms.setMinRangeAnswer(3);
        jacksRooms.setMaxRangeAnswer(5);
        jacksRooms.setProvider(jack);
        jacksRooms.setServiceQuestion(numberOfRooms);
        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksRooms);
        jack.setServiceAnswers(jacksAnswers);

        List<User> cleaners = new ArrayList<>();
        cleaners.add(sam);
        cleaners.add(jose);
        cleaners.add(jack);
        cleaning.setProviders(cleaners);

        SearchPredicate predicate = new SearchPredicate();
        predicate.setQuestion(numberOfRooms);
        ServiceAnswer predAnswer = new ServiceAnswer();
        predAnswer.setServiceQuestion(numberOfRooms);
        predAnswer.setMaxRangeAnswer(1);
        predAnswer.setMinRangeAnswer(2);
        predicate.setAnswer(predAnswer);

        SearchCriteria criteria = new SearchCriteria();

        List<SearchPredicate> somePredicates = new ArrayList<>();
        somePredicates.add(predicate);
        criteria.setSearchPredicate(somePredicates);
        ServiceSearch search = new ServiceSearch();
        List<User> mySearch = search.searchForProviders(cleaning, criteria);

        List<User> expectedResults = new ArrayList<>();
        expectedResults.add(jose);
        assertEquals(expectedResults, mySearch);
    }

    @Test
    void testServiceSearchTrueFalse() {

        ServiceAnswer samsPets = new ServiceAnswer();
        samsPets.setId(1);
        samsPets.setTrueFalseAnswer(Boolean.TRUE);
        samsPets.setServiceQuestion(havePets);
        samsPets.setProvider(sam);
        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsPets);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer jacksPets = new ServiceAnswer();
        jacksPets.setId(2);
        jacksPets.setTrueFalseAnswer(Boolean.FALSE);
        jacksPets.setServiceQuestion(havePets);
        jacksPets.setProvider(jack);
        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksPets);
        jack.setServiceAnswers(jacksAnswers);

        ServiceAnswer josesPets = new ServiceAnswer();
        josesPets.setId(3);
        josesPets.setTrueFalseAnswer(Boolean.FALSE);
        josesPets.setServiceQuestion(havePets);
        josesPets.setProvider(jose);
        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesPets);
        jose.setServiceAnswers(josesAnswers);

        List<User> cleaners = new ArrayList<>();
        cleaners.add(sam);
        cleaners.add(jose);
        cleaners.add(jack);
        cleaning.setProviders(cleaners);

        SearchPredicate predicate = new SearchPredicate();
        predicate.setQuestion(havePets);
        ServiceAnswer predAnswer = new ServiceAnswer();
        predAnswer.setServiceQuestion(numberOfRooms);
        predAnswer.setTrueFalseAnswer(Boolean.TRUE);
        predicate.setAnswer(predAnswer);

        SearchCriteria criteria = new SearchCriteria();

        List<SearchPredicate> somePredicates = new ArrayList<>();
        somePredicates.add(predicate);
        criteria.setSearchPredicate(somePredicates);
        ServiceSearch search = new ServiceSearch();
        List<User> mySearch = search.searchForProviders(cleaning, criteria);

        List<User> expectedResults = new ArrayList<>();
        expectedResults.add(sam);


        assertEquals(expectedResults, mySearch);
    }


}
