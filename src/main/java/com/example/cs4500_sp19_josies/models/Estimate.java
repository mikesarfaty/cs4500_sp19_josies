package com.example.cs4500_sp19_josies.models;

import java.util.List;

public class Estimate {
  private float estimate;
  private float basePrice;
  private Frequency baseFrequency;
  private boolean subscription;
  private Frequency subscriptionFrequency;
  private Frequency deliveryFrequency;
  private List<DeliveryFee> deliveryFees;

  public Estimate(float basePrice, Frequency baseFrequency, boolean subscription,
                  Frequency subscriptionFrequency, Frequency deliveryFrequency) {
    //this.estimate = 0;
    this.basePrice = basePrice;
    this.baseFrequency = baseFrequency;
    this.subscription = subscription;
    this.subscriptionFrequency = subscriptionFrequency;
    this.deliveryFrequency = deliveryFrequency;
  }

  public void setDeliveryFees(List<DeliveryFee> fees) {
    this.deliveryFees = fees;
  }

  public float getEstimate() {
    this.estimate = this.basePrice + this.getFees();
    return this.estimate;
  }

  public float getFees() {
    return 0;
  }
}
