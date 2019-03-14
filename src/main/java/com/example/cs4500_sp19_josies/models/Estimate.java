package com.example.cs4500_sp19_josies.models;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.round;

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
                  Frequency subscriptionFrequency, Frequency deliveryFrequency) {
    //this.estimate = 0;
    if (basePrice < 0) {
      throw new IllegalArgumentException("base price must be positive");
    }
    this.basePrice = basePrice;
    this.baseFrequency = baseFrequency;
    this.subscription = subscription;
    this.subscriptionFrequency = subscriptionFrequency;
    if (!(deliveryFrequency==Frequency.HOLIDAY) && !(deliveryFrequency==Frequency.EMERGENCY)
            && !(deliveryFrequency==Frequency.WEEKDAY) && !(deliveryFrequency==Frequency.WEEKEND)) {
      throw new IllegalArgumentException("delivery frequency invalid");
    }
    this.deliveryFrequency = deliveryFrequency;
    this.deliveryFees = new ArrayList<DeliveryFee>();
    this.subscriptionDiscounts = new ArrayList<SubscriptionDiscount>();
  }

  public void setDeliveryFees(List<DeliveryFee> fees) {
    this.deliveryFees = fees;
  }

  public void setDeliveryFrequency(Frequency deliveryFrequency) {
    this.deliveryFrequency = deliveryFrequency;
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

            float answer = this.basePrice * fee.getFee();
            return ((int) ((answer + 0.005f) * 100)) / 100f;
          }
        }
      }
      return 0;
    }
  }

  public void setSubscriptionDiscounts(List<SubscriptionDiscount> discounts) {
    this.subscriptionDiscounts = discounts;
  }

  public void setSubscriptionFrequency(Frequency subscriptionFrequency) {
    this.subscriptionFrequency = subscriptionFrequency;
  }

  public float getDiscount() {
    for (SubscriptionDiscount discount : this.subscriptionDiscounts) {
      if (discount.getFrequency() == this.subscriptionFrequency) {
        if (discount.isFlat()) {
          return discount.getDiscount();
        }
        else {
          float answer = this.basePrice * discount.getDiscount();
          return ((int) ((answer + 0.005f) * 100)) / 100f;
        }
      }
    }
    return 0;
  }
}
