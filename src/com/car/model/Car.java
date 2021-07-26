package com.car.model;

import com.car.service.Alarm;
import com.car.service.Vehicle;

public class Car implements Vehicle, Alarm {

    protected String brand;

    public Car() {
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String speedUp() {
        return "The car is speeding up.";
    }

    @Override
    public String slowDown() {
        return "The car is slowing down.";
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }

}
