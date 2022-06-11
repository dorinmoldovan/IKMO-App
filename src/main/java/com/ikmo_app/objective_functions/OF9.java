package com.ikmo_app.objective_functions;

import java.util.List;

public class OF9 extends OF {

    public OF9() {
        super(-300.0, 300.0);
    }

    @Override
    public double compute(List<Double> values) {
        double sum = 0.0;
        double product = 1.0;

        for(int i = 0; i < values.size(); i++) {
            sum = sum + Math.pow(values.get(i), 2);
            product = product * Math.pow(Math.cos(Math.pow(values.get(i), 2) / (i + 1)), 4);
        }

        double result = 1.0 / 2000 * sum - product + 1;

        return result;
    }
}
