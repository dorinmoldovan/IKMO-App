package com.ikmo_app.objective_functions;

import java.util.List;

public class OF6 extends OF {

    public OF6() {
        super(-10.0, 10.0);
    }

    @Override
    public double compute(List<Double> values) {
        double product = 1.0;
        double sum = 0.0;

        for(int i = 0; i < values.size(); i++) {
            product = product * Math.abs(values.get(i));
            sum = sum + Math.abs(values.get(i));
        }

        double result = product + sum;

        return result;
    }
}
