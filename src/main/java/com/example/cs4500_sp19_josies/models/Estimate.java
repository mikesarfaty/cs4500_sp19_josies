package com.example.cs4500_sp19_josies.models;

import java.util.ArrayList;
import java.util.List;

public class Estimate {
  private float estimate;
  private float basePrice;
  private Frequency baseFrequency;
  private boolean subscription;
  private Frequency subscriptionFrequency;
  private Frequency deliveryFrequency;
  private List<DeliveryFee> deliveryFees;
  private List<SubscriptionDiscount> subscriptionDiscounts;

  public Estimate(float basePrice, Frequency baseFrequency, boolean subscription,
                  Frequency subscriptionFrequency, Frequency deliveryFrequency, SubscriptionDiscount subscriptionDiscounts) {
    //this.estimate = 0;
    this.basePrice = basePrice;
    this.baseFrequency = baseFrequency;
    this.subscription = subscription;
    this.subscriptionFrequency = subscriptionFrequency;
    this.deliveryFrequency = deliveryFrequency;
    this.deliveryFees = new ArrayList<DeliveryFee>();
    this.subscriptionDiscounts = new ArrayList<SubscriptionDiscount>();
  }

  public void setDeliveryFees(List<DeliveryFee> fees) {
    this.deliveryFees = fees;
  }

  public float getEstimate() {
    this.estimate = this.basePrice - this.getDiscount();
    this.estimate += this.getFees();
    return this.estimate;
  }

  public float getFees() {
    if (this.deliveryFees.size() == 0) { return 0; }
    else {
      for (DeliveryFee fee : deliveryFees) {
        if (fee.getFrequency() == this.deliveryFrequency) {
          if (fee.isFlat()) {
            return fee.getFee();
          }
          else {
            return this.basePrice * fee.getFee();
          }
        }
      }
      return 0;
    }
  }

  public float getDiscount() {
    for (SubscriptionDiscount discount : subscriptionDiscounts) {
      if (this.subscriptionFrequency == discount.getFrequency()) {
        if (discount.isFlat()) {
          return discount.getDiscount();
        }
        return this.basePrice * (discount.getDiscount() / 100);
      }
    }
    return 0;
  }
}
