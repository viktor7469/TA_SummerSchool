package com.car.model;

import java.util.Objects;

public class BMW  extends Car{

    private String model;
    private int maxSpeed;


    public BMW() {
        this.brand = "BMW";
    }

    public BMW(String model) {
        this.brand = "BMW";
        this.model = model;

    }

    public BMW(String model,int maxSpeed) {
        this.brand = "BMW";
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BMW)) return false;
        BMW bmw = (BMW) o;
        return maxSpeed == bmw.maxSpeed &&
                Objects.equals(model, bmw.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed);
    }

    @Override
    public String toString() {
        return "BMW{" +
                "model: '" + model + '\'' +
                ", maxSpeed: " + maxSpeed +
                '}';
    }
}
