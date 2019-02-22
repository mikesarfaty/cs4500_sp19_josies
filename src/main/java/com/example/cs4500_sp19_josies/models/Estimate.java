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
  }

  public void setDeliveryFees(List<DeliveryFee> fees) {
    this.deliveryFees = fees;
  }

  public float getEstimate() {
    this.estimate = this.basePrice + this.getFees();
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
}
