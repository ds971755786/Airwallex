package io.dongsheng;

import java.util.Deque;

public abstract class BinaryOperator extends NumberOrOperator {

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
        if (stack.size() < 2) {
            rpn.setInsufficientParamters(true);
            throw new InsufficientParametersExecption("insufficient parameters");
        }
        Double n2 = stack.removeLast();
        Double n1 = stack.removeLast();
        Double result = binary(n1, n2);
        stack.addLast(result);
    }

    @Override
    public Double unary(Double n) {
        throw new UnsupportedOperationException();
    }
}
