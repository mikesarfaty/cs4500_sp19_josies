package com.example.cs4500_sp19_josies.algorithms;

import com.example.cs4500_sp19_josies.models.*;

import java.util.*;

public class ServiceSearch {

    public List<User> searchForProviders(Service service, SearchCriteria criteria) {
        // A mapping of users to their score.
        Map<User, Integer> providerScores = new HashMap<>();

        // The list of providers for the given service.
        List<User> providers = service.getProviders();

        /* Iterate through each predicate, scoring each provider based on their answers.
         * A score of at least 1 means the provider will end up in the return list; a score
         * of 0 means the provider will be ignored.
         */
        for (SearchPredicate predicate : criteria.getSearchPredicates()) {
            /* For each predicate in the criteria, get the question,
             * the answer, and the question type.
             */
            ServiceQuestion searchQuestion = predicate.getQuestion();
            ServiceAnswer searchAnswer = predicate.getAnswer();
            QuestionType questionType = searchQuestion.getType();

            // Iterate through each provider, scoring each.
            for (User p : providers) {
                // Initialize the provider in the scoring map.
                if (!providerScores.containsKey(p)) {
                    providerScores.put(p, 0);
                }

                // Get the list of answers for the given provider.
                List<ServiceAnswer> providerAnswers = p.getServiceAnswers();

                // Get the provider's answer to the question in the predicate we are considering.
                ServiceAnswer providerAnswer = null;
                for (ServiceAnswer serviceAnswer : providerAnswers) {
                    if (serviceAnswer.getServiceQuestion().getId().equals(searchQuestion.getId())) {
                        providerAnswer = serviceAnswer;
                        break;
                    }
                }

                // Score it.
                if (providerAnswer.getServiceQuestion().getType() == QuestionType.Range) {
                    String[] searchRange = searchAnswer.getAnswer().split(",");
                    String[] providerRange = providerAnswer.getAnswer().split(",");
                    int[] searchRangeInts = new int[searchRange.length];
                    int[] providerRangeInts = new int[providerRange.length];
                    
                    for (int i = 0; i < searchRange.length; i++) {
                        searchRangeInts[i] = Integer.parseInt(searchRange[i]);
                        providerRangeInts[i] = Integer.parseInt(providerRange[i]);
                    }

                    int searchLowerBound = searchRangeInts[0];
                    int searchHigherBound = searchRangeInts[1];
                    int providerLowerBound = providerRangeInts[0];
                    int providerHigherBound = providerRangeInts[1];

                    if ((searchLowerBound >= providerLowerBound && searchLowerBound <= providerHigherBound)
                            || (searchHigherBound >= providerLowerBound && searchHigherBound <= providerHigherBound)) {
                        providerScores.put(p, providerScores.get(p) + 1);
                    }
                }
                else if (providerAnswer.getAnswer().equals(searchAnswer.getAnswer())) {
                    providerScores.put(p, providerScores.get(p) + 1);
                }
            }
        }
        // Add all providers to a return list.
        List<User> providersByScore = new ArrayList<>(providerScores.keySet());

        List<User> found = new ArrayList<>();

        for (User p : providersByScore) {
            if (providerScores.get(p) == 0) {
                found.add(p);
            }
        }

        for (User p : found) {
            providersByScore.remove(p);
        }


        // Sort the leftovers based on their rank in the score map.
        Collections.sort(providersByScore, new ServiceSearchSortByScore(providerScores));

        return providersByScore;
    }
}