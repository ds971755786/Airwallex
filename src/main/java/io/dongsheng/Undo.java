package io.dongsheng;

import java.util.Deque;
import java.util.List;

public class Undo extends NumberOrOperator {
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
        List<NumberOrOperator> inputs = rpn.getInputs();
        Deque<Double> stack = rpn.getStack();
        if (rpn.isInsufficientParamters()) {
            rpn.setInsufficientParamters(false);
            int last = inputs.size() - 1;
            inputs.remove(last);
            inputs.remove(last - 1);
        } else {
            int undoIndex = inputs.size() - 1;
            int beforeUndo = undoIndex - 1;
            if (beforeUndo < 0) {
                //nothing in the stack
                return;
            } else {
                NumberOrOperator numberOrOperatorBeforeUndo = inputs.get(beforeUndo);
                if (numberOrOperatorBeforeUndo.isNumber()) {
                    if (stack.removeLast() != numberOrOperatorBeforeUndo.getValue()) {
                        throw new RuntimeException("inconsistent data");
                    }
                    inputs.remove(undoIndex);
                    inputs.remove(beforeUndo);
                } else {
                    inputs.remove(undoIndex);
                    inputs.remove(beforeUndo);
                    rpn.replay();
                }
            }
        }
    }

    @Override
    public Double binary(Double n1, Double n2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double unary(Double n) {
        throw new UnsupportedOperationException();
    }

    public boolean isUndo() {
        return true;
    }
}
