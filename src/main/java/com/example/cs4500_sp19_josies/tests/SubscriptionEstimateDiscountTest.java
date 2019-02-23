package com.example.cs4500_sp19_josies.tests;

import com.example.cs4500_sp19_josies.models.Estimate;
import com.example.cs4500_sp19_josies.models.Frequency;
import com.example.cs4500_sp19_josies.models.SubscriptionDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionEstimateDiscountTest {
    Estimate estimate;
    List<SubscriptionDiscount> subscriptionDiscounts;

    @BeforeEach
    void setup() {
        subscriptionDiscounts = new ArrayList<>();
        float basePrice = 100;
        Frequency defaultFrequency = Frequency.WEEKDAY;
        boolean initSubscription = false;
        Frequency subFrequency = Frequency.DAILY;

        SubscriptionDiscount flat = new SubscriptionDiscount(90, Frequency.ONETIME, true);
        SubscriptionDiscount holiday50 = new SubscriptionDiscount(.1f, Frequency.HOLIDAY, false);
        subscriptionDiscounts.add(flat);
        subscriptionDiscounts.add(holiday50);

        this.estimate = new Estimate(basePrice, defaultFrequency, initSubscription, subFrequency, defaultFrequency);
        this.estimate.setSubscriptionDiscounts(subscriptionDiscounts);
    }

    // Test one-time reward
    @Test
    void testFlatReward() {
        this.estimate.setSubscriptionFrequency(Frequency.ONETIME);
        float actual = this.estimate.getDiscount();
        float expected = 90;
        assertEquals(actual, expected);
    }

    // Test recurring reward
    @Test
    void testRecurringReward() {
        this.estimate.setSubscriptionFrequency(Frequency.HOLIDAY);
        float actual = this.estimate.getDiscount();
        float expected = 10.0f;
        assertEquals(actual, expected);
    }
    
    
    // test getting no reward if you get one-time but there are no one-time rewards
    @Test
    void testNoOneTimeReward() {
    	SubscriptionDiscount weekly10flat = new SubscriptionDiscount(10, Frequency.WEEKLY, true);
    	SubscriptionDiscount biweekly50percent = new SubscriptionDiscount(.5f, Frequency.BIWEEKLY, false);
        subscriptionDiscounts.addAll(List.of(weekly10flat, biweekly50percent));
        this.estimate.setSubscriptionDiscounts(subscriptionDiscounts);
        this.estimate.setSubscriptionFrequency(Frequency.ONETIME);
        
        float actualDiscount = this.estimate.getDiscount();
        float expectedDiscount = 0f;
        assertEquals(actualDiscount, expectedDiscount);
    }
    
    // test biweekly rewards (flat)
    @Test
    void testBiweeklyReward() {
    	SubscriptionDiscount biweekly5percent = new SubscriptionDiscount(.05f, Frequency.BIWEEKLY, false);
    	this.subscriptionDiscounts.add(biweekly5percent);
    	this.estimate.setSubscriptionDiscounts(this.subscriptionDiscounts);
    	this.estimate.setSubscriptionFrequency(Frequency.BIWEEKLY);
    	
    	float actualDiscount = this.estimate.getDiscount();
    	float expectedDiscount = 5.0f;
    	assertEquals(expectedDiscount, actualDiscount);
    }
}
