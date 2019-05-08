package io.dongsheng;

import java.util.Deque;

public abstract class UnaryOperator extends NumberOrOperator {
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
        Deque<Double> stack = rpn.getStack();
        if (stack.size() < 1) {
            rpn.setInsufficientParamters(true);
            throw new InsufficientParametersExecption("insufficient parameters");
        }
        Double n = stack.removeLast();
        Double result = unary(n);
        stack.addLast(result);
    }

    @Override
    public Double binary(Double n1, Double n2) {
        throw new UnsupportedOperationException();
    }
}
