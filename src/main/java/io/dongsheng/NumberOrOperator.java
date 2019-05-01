package io.dongsheng;

public class NumberOrOperator {
    private Double value;
    private Operator operator;
    private boolean isNumber;

    public NumberOrOperator(Double value) {
        this.value = value;
        this.isNumber = true;
    }

    public NumberOrOperator(Operator operator) {
        this.operator = operator;
        this.isNumber = false;
    }

    public Double getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    public boolean isNumber() {
        return isNumber;
    }
}
