package com.ikmo_app.objective_functions;

import java.util.List;

public class OF7 extends OF {

    public OF7() {
        super(-600.0, 600.0);
    }

    @Override
    public double compute(List<Double> values) {
        double sum = 0.0;
        double product = 1.0;

        for(int i = 0; i < values.size(); i++) {
            sum = sum + Math.pow(values.get(i), 2);
            product = product * Math.cos(values.get(i) / Math.sqrt(1.0 * (i+1)));
        }

        double result = 1.0 / 4000 * sum - product + 1;

        return result;
    }
}
