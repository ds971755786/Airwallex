package io.dongsheng;

import java.util.*;


public class RPN {
    private List<NumberOrOperator> inputs = new ArrayList<>();
    private Deque<Double> stack = new ArrayDeque<>();
    private boolean insufficientParamters = false;
    private Add add = new Add();
    private Subtract subtract = new Subtract();
    private Multiply multiply = new Multiply();
    private Divide divide = new Divide();
    private Clear clear = new Clear();
    private Sqrt sqrt = new Sqrt();
    private Undo undo = new Undo();

    public void calUnary(Operator operator) {
        if (this.stack.size() < 1) {
            this.insufficientParamters = true;
            throw new InsufficientParametersExecption("insufficient parameters");
        }
        Double n = this.stack.removeLast();
        Double result;
        switch (operator) {
            case sqrt:
                result = Math.sqrt(n);
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
        this.stack.addLast(result);
    }

    public void calBinary(Operator operator) {
        if (this.stack.size() < 2) {
            this.insufficientParamters = true;
            throw new InsufficientParametersExecption("insufficient parameters");
        }
        Double n2 = stack.removeLast();
        Double n1 = stack.removeLast();
        Double result;
        switch (operator) {
            case add:
                result = n1 + n2;
                break;
            case subtract:
                result = n1 - n2;
                break;
            case multiply:
                result = n1 * n2;
                break;
            case divide:
                result = n1 / n2;
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
        stack.addLast(result);
    }


    public void add(NumberOrOperator numberOrOperator) {
        if (!numberOrOperator.isUndo() && this.insufficientParamters) {
            throw new InsufficientParametersExecption("not take new inputs before the last operator is removed");
        }
        this.inputs.add(numberOrOperator);
        updateStack(numberOrOperator);

    }

    public void updateStack(NumberOrOperator numberOrOperator) {
        if (numberOrOperator.isNumber()) {
            this.stack.addLast(numberOrOperator.getValue());
        } else {
            numberOrOperator.calculate(this);
        }
    }

    public void replay() {
        this.stack = new ArrayDeque<>();
        List<NumberOrOperator> toBeReplayed = inputs;
        inputs = new ArrayList<>();
        for (NumberOrOperator i : toBeReplayed) {
            add(i);
        }
    }

    public Deque<Double> getStack() {
        return this.stack;
    }

    public NumberOrOperator fromString(String s) {
        if (s == null) {
            throw new RuntimeException("null not allowed");
        }
        switch (s) {
            case "+":
                return this.add;
            case "-":
                return this.subtract;
            case "*":
                return this.multiply;
            case "/":
                return this.divide;
            case "sqrt":
                return this.sqrt;
            case "clear":
                return this.clear;
            case "undo":
                return this.undo;
            default:
                Double n = Double.valueOf(s);
                return new Number(n);
        }
    }

    public void addString(String s) {
        add(fromString(s));
    }

    public List<NumberOrOperator> getInputs() {
        return this.inputs;
    }

    public void setInputs(List<NumberOrOperator> inputs) {
        this.inputs = inputs;
    }

    public void setStack(Deque<Double> stack) {
        this.stack = stack;
    }

    public boolean isInsufficientParamters() {
        return this.insufficientParamters;
    }

    public void setInsufficientParamters(boolean insufficientParamters) {
        this.insufficientParamters = insufficientParamters;
    }
}
