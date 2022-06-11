package com.ikmo_app.objective_functions;

import java.util.List;
import java.util.Random;

public class OF2 extends OF {

    public OF2() {
        super(-1.28, 1.28);
    }

    @Override
    public double compute(List<Double> values) {
        double result = 0.0;

        for(int i = 0; i < values.size(); i++) {
            result = result + (i + 1) * Math.pow(values.get(i), 4);
        }

        Random random = new Random(System.currentTimeMillis());
        result = result + random.nextDouble();

        return result;
    }
}
