package com.example.cs4500_sp19_josies.tests;

import com.example.cs4500_sp19_josies.models.DeliveryFee;
import com.example.cs4500_sp19_josies.models.Estimate;
import com.example.cs4500_sp19_josies.models.Frequency;


import com.example.cs4500_sp19_josies.models.SubscriptionDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeliveryFeeEstimateTest {

    Estimate initEst;
    List<DeliveryFee> deliveryFees2;

    @BeforeEach
    void setUp() {

        List<DeliveryFee> deliveryFees = new ArrayList<DeliveryFee>();
        deliveryFees2 = new ArrayList<DeliveryFee>();
        DeliveryFee weekday = new DeliveryFee(0, Frequency.WEEKDAY, false);
        DeliveryFee weekend = new DeliveryFee(0.1f, Frequency.WEEKEND, false);
        DeliveryFee holiday = new DeliveryFee(0.2f, Frequency.HOLIDAY, false);
        DeliveryFee emergency = new DeliveryFee(0.3f, Frequency.EMERGENCY, false);
        DeliveryFee emergency2 = new DeliveryFee(200, Frequency.EMERGENCY, true);
        deliveryFees2.add(emergency2);
        deliveryFees.add(weekday);
        deliveryFees.add(weekend);
        deliveryFees.add(holiday);
        deliveryFees.add(emergency);


        float basePrice = 100;
        Frequency initFrequency= Frequency.HOLIDAY;
        boolean initSubscription = false;
        Frequency initSubscriptionFrequency = Frequency.ONETIME;
        Frequency initDeliveryFrequency = Frequency.WEEKDAY;

        initEst = new Estimate(basePrice, initFrequency, initSubscription, initSubscriptionFrequency,
                initDeliveryFrequency);
        initEst.setDeliveryFees(deliveryFees);
    }

    // Test on Holiday
    @Test
    void testHolidayFrequency() {
        this.initEst.setDeliveryFrequency(Frequency.HOLIDAY);
        float actual = this.initEst.getFees();
        float expected = 20.00f;
        assertEquals(expected, actual);
    }

    // Test fee on Weekend
    @Test
    void testWeekendFrequency() {
        this.initEst.setDeliveryFrequency(Frequency.WEEKEND);
        float actual = initEst.getFees();
        float expected = 10.00f;
        assertEquals(expected, actual);
    }

    // Test fee on Weekday
    @Test
    void testWeekdayFrequency() {
        this.initEst.setDeliveryFrequency(Frequency.WEEKDAY);
        float actual = initEst.getFees();
        float expected = 0.0f;
        assertEquals(expected, actual);
    }

    // Test fee on Emergency
    @Test
    void testEmergencyFrequency() {
        this.initEst.setDeliveryFrequency(Frequency.EMERGENCY);
        float actual = initEst.getFees();
        float expected = 30.00f;
        assertEquals(expected, actual);
    }

    // Test flat fee
    @Test
    void testFlatFee() {
        this.initEst.setDeliveryFrequency(Frequency.EMERGENCY);
        initEst.setDeliveryFees(deliveryFees2);
        float actual = initEst.getFees();
        float expected = 200.00f;
        assertEquals(expected, actual);
    }

    // Test total fee no discount.





}
