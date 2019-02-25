package com.example.cs4500_sp19_josies.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="subscription_discount")
public class SubscriptionDiscount {
    private float discount;
    private Frequency frequency;
    private boolean flat;

    public SubscriptionDiscount(float discount, Frequency frequency, boolean flat) {
        this.discount = discount;
        this.frequency = frequency;
        this.flat = flat;

        if (discount < 0) {
            throw new IllegalArgumentException("discount can't be negative");
        }
        else if (!flat && discount > 1) {
            throw new IllegalArgumentException("discount can't be 100%");
        }
    }


    public float getDiscount() {
        return this.discount;
    }

    public Frequency getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setFlat(boolean flat) {
        this.flat = flat;
    }

    public boolean isFlat() {
        return this.flat;
    }
}
