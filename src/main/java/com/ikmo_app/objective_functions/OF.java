package com.ikmo_app.objective_functions;

import java.util.List;

public abstract class OF {
    private Double minValue;
    private Double maxValue;

    public OF(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public abstract double compute(List<Double> values);

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }
}
