package io.dongsheng;

import java.util.*;

import static io.dongsheng.Operator.*;

public class RPN {
    private List<NumberOrOperator> inputs = new ArrayList<NumberOrOperator>();
    private Deque<Double> stack = new ArrayDeque<Double>();
    private int count = 0;
    private boolean insufficientParamters = false;

    public void calUnary(Operator operator) {
        if (stack.size() < 1) {
            insufficientParamters = true;
            throw new InsufficientParametersExecption("insufficient paramters");
        }
        Double n = stack.removeLast();
        Double result;
        switch (operator) {
            case sqrt:
                result = Math.sqrt(n);
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
        stack.addLast(result);
    }

    public void calBinary(Operator operator) {
        if (stack.size() < 2) {
            insufficientParamters = true;
            throw new InsufficientParametersExecption("insufficient paramters");
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
        if (!undo.equals(numberOrOperator.getOperator()) && insufficientParamters) {
            throw new InsufficientParametersExecption("not take new inputs before the last operator is removed");
        }
        inputs.add(numberOrOperator);
        count++;
        updateStack(numberOrOperator);

    }

    public void updateStack(NumberOrOperator numberOrOperator) {
        if (numberOrOperator.isNumber()) {
            stack.addLast(numberOrOperator.getValue());
        } else {
            switch (numberOrOperator.getOperator()) {
                case add:
                    calBinary(numberOrOperator.getOperator());
                    break;
                case subtract:
                    calBinary(numberOrOperator.getOperator());
                    break;
                case multiply:
                    calBinary(numberOrOperator.getOperator());
                    break;
                case divide:
                    calBinary(numberOrOperator.getOperator());
                    break;
                case sqrt:
                    calUnary(numberOrOperator.getOperator());
                    break;
                case clear:
                    stack = new ArrayDeque<>();
                    inputs = new ArrayList<>();
                    count = 0;
                    break;
                case undo:
                    //
                    if (insufficientParamters) {
                        insufficientParamters = false;
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
                                replay();
                            }
                        }
                    }

            }

        }
    }

    public void replay() {
        stack = new ArrayDeque<>();
        for (NumberOrOperator i : inputs) {
            updateStack(i);
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
                return new NumberOrOperator(add);
            case "-":
                return new NumberOrOperator(subtract);
            case "*":
                return new NumberOrOperator(multiply);
            case "/":
                return new NumberOrOperator(divide);
            case "sqrt":
                return new NumberOrOperator(sqrt);
            case "clear":
                return new NumberOrOperator(clear);
            case "undo":
                return new NumberOrOperator(undo);
            default:
                Double n = Double.valueOf(s);
                return new NumberOrOperator(n);
        }
    }

    public void addString(String s) {
        add(fromString(s));
    }
}
