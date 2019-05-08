package io.dongsheng;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Clear extends NumberOrOperator {
    @Override
    public Double getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public void calculate(RPN rpn) {
        rpn.setInputs(new ArrayList<>());
        rpn.setStack(new ArrayDeque<>());
    }

    @Override
    public Double binary(Double n1, Double n2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double unary(Double n) {
        throw new UnsupportedOperationException();
    }
}
