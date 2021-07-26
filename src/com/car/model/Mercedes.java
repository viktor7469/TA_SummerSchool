package com.car.model;

import java.util.Objects;

public class Mercedes extends Car{


    private String model;
    private boolean AMG =false;


    public Mercedes() {
        this.brand = "Mercedes";
    }

    public Mercedes(String model) {
        this.brand = "Mercedes";
        this.model = model;
    }
    public Mercedes(String model,boolean AMG) {
        this.brand = "Mercedes";
        this.model = model;
        this.AMG = AMG;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAMG() {
        return AMG;
    }

    public void setAMG(boolean AMG) {
        this.AMG = AMG;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mercedes)) return false;
        Mercedes mercedes = (Mercedes) o;
        return AMG == mercedes.AMG &&
                Objects.equals(model, mercedes.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, AMG);
    }

    @Override
    public String toString() {
        return "Mercedes{" +
                "model='" + model + '\''
                + (AMG ? ", AMG" : "") +
                '}';
    }
}
