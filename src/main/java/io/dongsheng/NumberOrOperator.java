package io.dongsheng;

import java.util.Deque;
import java.util.List;

public abstract class NumberOrOperator {

    public abstract Double getValue();

    public abstract boolean isNumber();

    public abstract void calculate(RPN rpn);

    public abstract Double binary(Double n1, Double n2);

    public abstract Double unary(Double n);

    public boolean isUndo() {
        return false;
    }
}
