package com.ikmo_app.objective_functions;

import java.util.List;

public class OF4 extends OF {

    public OF4() {
        super(-30.0, 30.0);
    }

    @Override
    public double compute(List<Double> values) {
        double result = 0;

        for(int i = 0; i < values.size() - 1; i++) {
            double term = 100 * Math.pow(values.get(i+1) - Math.pow(values.get(i), 2), 2);
            term = term + Math.pow(values.get(i) - 1, 2);
            result = result + term;
        }

        return result;
    }
}
