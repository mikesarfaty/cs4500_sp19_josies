package com.example.cs4500_sp19_josies.models;

public class SearchPredicate {

    private Integer id;
    private ServiceQuestion question;
    private ServiceAnswer answer;
    private SearchCriteria searchCriteria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceQuestion getQuestion() {
        return question;
    }

    public void setQuestion(ServiceQuestion question) {
        this.question = question;
    }

    public ServiceAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(ServiceAnswer answer) {
        this.answer = answer;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
