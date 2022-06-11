package com.ikmo_app.objective_functions;

import java.util.List;

public class OF3 extends OF {

    public OF3() {
        super(-100.0, 100.0);
    }

    @Override
    public double compute(List<Double> values) {
        double result = Math.abs(values.get(0));

        for(int i = 1; i < values.size(); i++) {
            if(result < Math.abs(values.get(i))) {
                result = Math.abs(values.get(i));
            }
        }

        return result;
    }
}
