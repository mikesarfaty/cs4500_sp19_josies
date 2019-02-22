package com.example.cs4500_sp19_josies.models;

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
    this.fee = fee;
    this.frequency = frequency;
    this.flat = flat;
  }


}
