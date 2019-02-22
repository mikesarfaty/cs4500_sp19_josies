package com.example.cs4500_sp19_josies.models;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="delivery_fee")
public class DeliveryFee {
  private float fee;
  private Frequency frequency;
  private boolean flat;

  public float getFee() {
    return fee;
  }

  public void setFee(float fee) {
    this.fee = fee;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
  }

  public boolean isFlat() {
    return flat;
  }

  public void setFlat(boolean flat) {
    this.flat = flat;
  }

  public DeliveryFee(float fee, Frequency frequency, boolean flat) {
    this.frequency = frequency;
    this.flat = flat;
    if (fee < 0) {
      throw new IllegalArgumentException("fee must be greater than 0");
    }
    if (!flat && fee > 1) {
      throw new IllegalArgumentException("pct fees must < 1");
    }
    this.fee = fee;
  }


}
