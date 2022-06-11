package com.ikmo_app.objective_functions;

import java.util.List;

public class OF1 extends OF {

    public OF1() {
        super(-100.0, 100.0);
    }

    public double compute(List<Double> values) {
        double result = 0.0;

        for(int i = 0; i < values.size(); i++) {
            result = result + values.get(i) * values.get(i);
        }

        return result;
    }
}
