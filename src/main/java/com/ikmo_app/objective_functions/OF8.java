package com.ikmo_app.objective_functions;

import java.util.List;

public class OF8 extends OF {

    public OF8() {
        super(-100.0, 100.0);
    }

    @Override
    public double compute(List<Double> values) {
        double result = 0.0;

        for(int i = 0; i < values.size(); i++) {
            double sum = 0.0;
            for(int j = 0; j <= i; j++) {
                sum = sum + values.get(j);
            }
            result = result + Math.pow(sum, 2);
        }

        return result;
    }
}
