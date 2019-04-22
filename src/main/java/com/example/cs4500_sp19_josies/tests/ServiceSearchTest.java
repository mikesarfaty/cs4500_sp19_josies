package com.example.cs4500_sp19_josies.tests;

import com.example.cs4500_sp19_josies.algorithms.ServiceSearch;
import com.example.cs4500_sp19_josies.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ServiceSearchTest {

    Service cleaning = new Service();
    ServiceQuestion numberOfRooms;
    ServiceQuestion havePets;
    ServiceQuestion typeOfCleaning;

    User sam = new User();
    User jose = new User();
    User jack = new User();


    @BeforeEach
    void setUp() {

        numberOfRooms = new ServiceQuestion("How many rooms", QuestionType.Range);
        numberOfRooms.setId(1);

        havePets = new ServiceQuestion("Have any pets?", QuestionType.TrueFalse);
        havePets.setId(2);

        typeOfCleaning = new ServiceQuestion("Type of cleaning", QuestionType.MultipleChoice);
        typeOfCleaning.setId(3);
    }

    @Test
    void testServiceSearchRange() {

        ServiceAnswer samsRooms = new ServiceAnswer();
        samsRooms.setId(1);
        samsRooms.setAnswer("5,6");
        samsRooms.setProvider(sam);
        samsRooms.setServiceQuestion(numberOfRooms);
        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsRooms);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer josesRooms = new ServiceAnswer();
        josesRooms.setId(2);
        josesRooms.setAnswer("1,3");
        josesRooms.setProvider(jose);
        josesRooms.setServiceQuestion(numberOfRooms);
        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesRooms);
        jose.setServiceAnswers(josesAnswers);


        ServiceAnswer jacksRooms = new ServiceAnswer();
        jacksRooms.setId(2);
        jacksRooms.setAnswer("3,5");
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
        predAnswer.setAnswer("1,2");
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
        samsPets.setAnswer("True");
        samsPets.setServiceQuestion(havePets);
        samsPets.setProvider(sam);
        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsPets);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer jacksPets = new ServiceAnswer();
        jacksPets.setId(2);
        jacksPets.setAnswer("False");
        jacksPets.setServiceQuestion(havePets);
        jacksPets.setProvider(jack);
        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksPets);
        jack.setServiceAnswers(jacksAnswers);

        ServiceAnswer josesPets = new ServiceAnswer();
        josesPets.setId(3);
        josesPets.setAnswer("False");
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
        predAnswer.setAnswer("True");
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

    @Test
    void testServiceSearchMCQs() {

        /* Choices for specific example (types of cleaning) with corresponding id:
         * 1 - Deep Cleaning
         * 2 - Window Cleaning
         * 3 - Floor Cleaning
         * 4 - Spring Cleaning
         * 5 - Winter Cleaning
         */

        ServiceAnswer samsType = new ServiceAnswer();
        samsType.setId(1);
        samsType.setAnswer("1");
        samsType.setServiceQuestion(typeOfCleaning);
        samsType.setProvider(sam);
        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsType);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer josesType = new ServiceAnswer();
        josesType.setId(2);
        josesType.setAnswer("4");
        josesType.setServiceQuestion(typeOfCleaning);
        josesType.setProvider(jose);
        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesType);
        jose.setServiceAnswers(josesAnswers);

        ServiceAnswer jacksType = new ServiceAnswer();
        jacksType.setId(3);
        jacksType.setAnswer("5");
        jacksType.setServiceQuestion(typeOfCleaning);
        jacksType.setProvider(jack);
        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksType);
        jack.setServiceAnswers(jacksAnswers);

        List<User> cleaners = new ArrayList<>();
        cleaners.add(sam);
        cleaners.add(jose);
        cleaners.add(jack);
        cleaning.setProviders(cleaners);

        // Search for Deep Cleaning
        SearchPredicate predicate1 = new SearchPredicate();
        predicate1.setQuestion(typeOfCleaning);
        ServiceAnswer predAnswer1 = new ServiceAnswer();
        predAnswer1.setServiceQuestion(typeOfCleaning);
        predAnswer1.setAnswer("1");
        predicate1.setAnswer(predAnswer1);

        SearchCriteria criteria1 = new SearchCriteria();

        List<SearchPredicate> somePredicates1 = new ArrayList<>();
        somePredicates1.add(predicate1);
        criteria1.setSearchPredicate(somePredicates1);
        ServiceSearch search = new ServiceSearch();
        List<User> mySearch = search.searchForProviders(cleaning, criteria1);

        List<User> expectedResults1 = new ArrayList<>();
        expectedResults1.add(sam);

        assertEquals(expectedResults1, mySearch);

        // Search for Spring Cleaning
        SearchPredicate predicate2 = new SearchPredicate();
        predicate2.setQuestion(typeOfCleaning);
        ServiceAnswer predAnswer2 = new ServiceAnswer();
        predAnswer2.setServiceQuestion(typeOfCleaning);
        predAnswer2.setAnswer("4");
        predicate2.setAnswer(predAnswer2);

        SearchCriteria criteria2 = new SearchCriteria();

        List<SearchPredicate> somePredicates2 = new ArrayList<>();
        somePredicates2.add(predicate2);
        criteria2.setSearchPredicate(somePredicates2);
        mySearch = search.searchForProviders(cleaning, criteria2);

        List<User> expectedResults2 = new ArrayList<>();
        expectedResults2.add(jose);

        assertEquals(expectedResults2, mySearch);

        // Search for Winter Cleaning
        SearchPredicate predicate3 = new SearchPredicate();
        predicate3.setQuestion(typeOfCleaning);
        ServiceAnswer predAnswer3 = new ServiceAnswer();
        predAnswer3.setServiceQuestion(typeOfCleaning);
        predAnswer3.setAnswer("5");
        predicate3.setAnswer(predAnswer3);

        SearchCriteria criteria3 = new SearchCriteria();

        List<SearchPredicate> somePredicates3 = new ArrayList<>();
        somePredicates3.add(predicate3);
        criteria3.setSearchPredicate(somePredicates3);
        mySearch = search.searchForProviders(cleaning, criteria3);

        List<User> expectedResults3 = new ArrayList<>();
        expectedResults3.add(jack);

        assertEquals(expectedResults3, mySearch);

    }

    @Test
    void testSearchIsSorted() {

        ServiceAnswer samsRooms = new ServiceAnswer();
        samsRooms.setId(1);
        samsRooms.setAnswer("5,6");
        samsRooms.setProvider(sam);
        samsRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer samsPets = new ServiceAnswer();
        samsPets.setId(2);
        samsPets.setAnswer("True");
        samsPets.setServiceQuestion(havePets);
        samsPets.setProvider(sam);

        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsRooms);
        samsAnswers.add(samsPets);
        sam.setServiceAnswers(samsAnswers);


        ServiceAnswer josesRooms = new ServiceAnswer();
        josesRooms.setId(3);
        josesRooms.setAnswer("3,4");
        josesRooms.setProvider(jose);
        josesRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer josesPets = new ServiceAnswer();
        josesPets.setId(4);
        josesPets.setAnswer("False");
        josesPets.setServiceQuestion(havePets);
        josesPets.setProvider(jose);


        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesRooms);
        josesAnswers.add(josesPets);
        jose.setServiceAnswers(josesAnswers);


        ServiceAnswer jacksRooms = new ServiceAnswer();
        jacksRooms.setId(5);
        jacksRooms.setAnswer("1,2");
        jacksRooms.setProvider(jack);
        jacksRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer jacksPets = new ServiceAnswer();
        jacksPets.setId(6);
        jacksPets.setAnswer("False");
        jacksPets.setServiceQuestion(havePets);
        jacksPets.setProvider(jack);

        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksRooms);
        jacksAnswers.add(jacksPets);
        jack.setServiceAnswers(jacksAnswers);


        List<User> cleaners = new ArrayList<>();
        cleaners.add(sam);
        cleaners.add(jose);
        cleaners.add(jack);
        cleaning.setProviders(cleaners);

        SearchPredicate roomPredicate = new SearchPredicate();
        roomPredicate.setQuestion(numberOfRooms);
        ServiceAnswer roomAnswer = new ServiceAnswer();
        roomAnswer.setServiceQuestion(numberOfRooms);
        roomAnswer.setAnswer("3,5");
        roomPredicate.setAnswer(roomAnswer);

        SearchPredicate petPredicate = new SearchPredicate();
        petPredicate.setQuestion(havePets);
        ServiceAnswer petAnswer = new ServiceAnswer();
        petAnswer.setServiceQuestion(havePets);
        petAnswer.setAnswer("True");
        petPredicate.setAnswer(petAnswer);

        SearchCriteria criteria = new SearchCriteria();

        List<SearchPredicate> somePredicates = new ArrayList<>();
        somePredicates.add(roomPredicate);
        somePredicates.add(petPredicate);
        criteria.setSearchPredicate(somePredicates);
        ServiceSearch search = new ServiceSearch();
        List<User> mySearch = search.searchForProviders(cleaning, criteria);

        List<User> expectedResults = new ArrayList<>();
        expectedResults.add(sam);
        expectedResults.add(jose);

        assertEquals(expectedResults, mySearch);

    }

    @Test
    void testServiceSearchSize() {

        ServiceAnswer samsRooms = new ServiceAnswer();
        samsRooms.setId(1);
        samsRooms.setAnswer("5,6");
        samsRooms.setProvider(sam);
        samsRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer samsPets = new ServiceAnswer();
        samsPets.setId(2);
        samsPets.setAnswer("True");
        samsPets.setServiceQuestion(havePets);
        samsPets.setProvider(sam);

        ServiceAnswer samsType = new ServiceAnswer();
        samsType.setId(1);
        samsType.setAnswer("1");
        samsType.setServiceQuestion(typeOfCleaning);
        samsType.setProvider(sam);

        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsRooms);
        samsAnswers.add(samsPets);
        samsAnswers.add(samsType);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer josesRooms = new ServiceAnswer();
        josesRooms.setId(3);
        josesRooms.setAnswer("3,4");
        josesRooms.setProvider(jose);
        josesRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer josesPets = new ServiceAnswer();
        josesPets.setId(4);
        josesPets.setAnswer("False");
        josesPets.setServiceQuestion(havePets);
        josesPets.setProvider(jose);

        ServiceAnswer josesType = new ServiceAnswer();
        josesType.setId(2);
        josesType.setAnswer("4");
        josesType.setServiceQuestion(typeOfCleaning);
        josesType.setProvider(jose);

        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesRooms);
        josesAnswers.add(josesPets);
        josesAnswers.add(josesType);
        jose.setServiceAnswers(josesAnswers);

        ServiceAnswer jacksRooms = new ServiceAnswer();
        jacksRooms.setId(5);
        jacksRooms.setAnswer("1,2");
        jacksRooms.setProvider(jack);
        jacksRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer jacksPets = new ServiceAnswer();
        jacksPets.setId(6);
        jacksPets.setAnswer("False");
        jacksPets.setServiceQuestion(havePets);
        jacksPets.setProvider(jack);

        ServiceAnswer jacksType = new ServiceAnswer();
        jacksType.setId(3);
        jacksType.setAnswer("5");
        jacksType.setServiceQuestion(typeOfCleaning);
        jacksType.setProvider(jack);

        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksRooms);
        jacksAnswers.add(jacksPets);
        jacksAnswers.add(jacksType);
        jack.setServiceAnswers(jacksAnswers);

        List<User> cleaners = new ArrayList<>();
        cleaners.add(sam);
        cleaners.add(jose);
        cleaners.add(jack);
        cleaning.setProviders(cleaners);

        SearchPredicate roomPredicate = new SearchPredicate();
        roomPredicate.setQuestion(numberOfRooms);
        ServiceAnswer roomAnswer = new ServiceAnswer();
        roomAnswer.setServiceQuestion(numberOfRooms);
        roomAnswer.setAnswer("3,5");
        roomPredicate.setAnswer(roomAnswer);

        SearchPredicate petPredicate = new SearchPredicate();
        petPredicate.setQuestion(havePets);
        ServiceAnswer petAnswer = new ServiceAnswer();
        petAnswer.setServiceQuestion(havePets);
        petAnswer.setAnswer("True");
        petPredicate.setAnswer(petAnswer);

        SearchPredicate typePredicate1 = new SearchPredicate();
        typePredicate1.setQuestion(typeOfCleaning);
        ServiceAnswer typeAnswer1 = new ServiceAnswer();
        typeAnswer1.setServiceQuestion(typeOfCleaning);
        typeAnswer1.setAnswer("1");
        typePredicate1.setAnswer(typeAnswer1);

        SearchCriteria criteria = new SearchCriteria();

        List<SearchPredicate> somePredicates = new ArrayList<>();
        somePredicates.add(roomPredicate);
        somePredicates.add(petPredicate);
        somePredicates.add(typePredicate1);
        criteria.setSearchPredicate(somePredicates);
        ServiceSearch search = new ServiceSearch();
        List<User> mySearch = search.searchForProviders(cleaning, criteria);

        List<User> expectedResults = new ArrayList<>();
        expectedResults.add(sam);
        expectedResults.add(jose);

        assertEquals(expectedResults.size(), mySearch.size());

        expectedResults.remove(sam);

        assertNotEquals(expectedResults.size(), mySearch.size());

        expectedResults.add(jack);

        assertEquals(expectedResults.size(), mySearch.size());

        expectedResults.add(sam);
    }

    @Test
    void testServiceSearchNoProviders() {
        ServiceAnswer samsRooms = new ServiceAnswer();
        samsRooms.setId(1);
        samsRooms.setAnswer("5,6");
        samsRooms.setProvider(sam);
        samsRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer samsPets = new ServiceAnswer();
        samsPets.setId(2);
        samsPets.setAnswer("True");
        samsPets.setServiceQuestion(havePets);
        samsPets.setProvider(sam);

        ServiceAnswer samsType = new ServiceAnswer();
        samsType.setId(1);
        samsType.setAnswer("1");
        samsType.setServiceQuestion(typeOfCleaning);
        samsType.setProvider(sam);

        List<ServiceAnswer> samsAnswers = new ArrayList<>();
        samsAnswers.add(samsRooms);
        samsAnswers.add(samsPets);
        samsAnswers.add(samsType);
        sam.setServiceAnswers(samsAnswers);

        ServiceAnswer josesRooms = new ServiceAnswer();
        josesRooms.setId(3);
        josesRooms.setAnswer("3,4");
        josesRooms.setProvider(jose);
        josesRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer josesPets = new ServiceAnswer();
        josesPets.setId(4);
        josesPets.setAnswer("False");
        josesPets.setServiceQuestion(havePets);
        josesPets.setProvider(jose);

        ServiceAnswer josesType = new ServiceAnswer();
        josesType.setId(2);
        josesType.setAnswer("4");
        josesType.setServiceQuestion(typeOfCleaning);
        josesType.setProvider(jose);

        List<ServiceAnswer> josesAnswers = new ArrayList<>();
        josesAnswers.add(josesRooms);
        josesAnswers.add(josesPets);
        josesAnswers.add(josesType);
        jose.setServiceAnswers(josesAnswers);

        ServiceAnswer jacksRooms = new ServiceAnswer();
        jacksRooms.setId(5);
        jacksRooms.setAnswer("1,2");
        jacksRooms.setProvider(jack);
        jacksRooms.setServiceQuestion(numberOfRooms);

        ServiceAnswer jacksPets = new ServiceAnswer();
        jacksPets.setId(6);
        jacksPets.setAnswer("False");
        jacksPets.setServiceQuestion(havePets);
        jacksPets.setProvider(jack);

        ServiceAnswer jacksType = new ServiceAnswer();
        jacksType.setId(3);
        jacksType.setAnswer("5");
        jacksType.setServiceQuestion(typeOfCleaning);
        jacksType.setProvider(jack);

        List<ServiceAnswer> jacksAnswers = new ArrayList<>();
        jacksAnswers.add(jacksRooms);
        jacksAnswers.add(jacksPets);
        jacksAnswers.add(jacksType);
        jack.setServiceAnswers(jacksAnswers);

        List<User> cleaners = new ArrayList<>();
        cleaners.add(sam);
        cleaners.add(jose);
        cleaners.add(jack);
        cleaning.setProviders(cleaners);

        SearchPredicate petPredicate = new SearchPredicate();
        petPredicate.setQuestion(havePets);
        ServiceAnswer petAnswer = new ServiceAnswer();
        petAnswer.setServiceQuestion(havePets);
        petAnswer.setAnswer("True");
        petPredicate.setAnswer(petAnswer);

        SearchPredicate typePredicate2 = new SearchPredicate();
        typePredicate2.setQuestion(typeOfCleaning);
        ServiceAnswer typeAnswer2 = new ServiceAnswer();
        typeAnswer2.setServiceQuestion(typeOfCleaning);
        typeAnswer2.setAnswer("2");
        typePredicate2.setAnswer(typeAnswer2);

        SearchCriteria criteria = new SearchCriteria();

        List<SearchPredicate> somePredicates = new ArrayList<>();
        somePredicates.add(typePredicate2);
        criteria.setSearchPredicate(somePredicates);
        ServiceSearch search = new ServiceSearch();
        List<User> mySearch = search.searchForProviders(cleaning, criteria);

        List<User> expectedResults = new ArrayList<>();

        assertEquals(expectedResults.size(), mySearch.size());

        somePredicates.add(petPredicate);
        criteria.setSearchPredicate(somePredicates);
        mySearch = search.searchForProviders(cleaning,criteria);

        assertNotEquals(expectedResults.size(), mySearch.size());


    }
}