package com.example.cs4500_sp19_josies.models;

import java.util.List;

public class SearchCriteria {

    List<SearchPredicate> searchPredicate;

    public List<SearchPredicate> getSearchPredicates() {
        return searchPredicate;
    }

    public void setSearchPredicate(List<SearchPredicate> searchPredicate) {
        this.searchPredicate = searchPredicate;
    }
}
