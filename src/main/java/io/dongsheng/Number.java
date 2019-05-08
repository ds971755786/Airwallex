package io.dongsheng;

public class Number extends NumberOrOperator {
    private Double value;

    public Number(Double value) {
        if (value == null) {
            throw new RuntimeException("number could not be null");
        }
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public void calculate(RPN rpn) {
        throw new UnsupportedOperationException();
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
