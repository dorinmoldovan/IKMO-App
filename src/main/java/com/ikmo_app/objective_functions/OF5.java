package com.ikmo_app.objective_functions;

import java.util.List;

public class OF5 extends OF {

    public OF5() {
        super(-5.12, 5.12);
    }

    @Override
    public double compute(List<Double> values) {
        double result = 0.0;
        for(int i = 0; i < values.size(); i++) {
            double term = Math.pow(values.get(i), 2) - 10.0 * Math.cos(2 * Math.PI * values.get(i)) + 10;
            result = result + term;
        }
        return result;
    }
}
