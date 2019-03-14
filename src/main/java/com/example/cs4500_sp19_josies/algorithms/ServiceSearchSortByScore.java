package com.example.cs4500_sp19_josies.algorithms;

import com.example.cs4500_sp19_josies.models.User;

import java.util.Comparator;
import java.util.Map;

public class ServiceSearchSortByScore implements Comparator<User> {

    // The score map to initialize the comparator with.
    private Map<User, Integer> scoreMap;

    public ServiceSearchSortByScore(Map<User, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }

    // Sort users in descending order.
    @Override
    public int compare(User u1, User u2) {
        return this.scoreMap.get(u2) - this.scoreMap.get(u1);
    }
}
