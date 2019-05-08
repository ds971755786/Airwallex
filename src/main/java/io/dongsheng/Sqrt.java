package io.dongsheng;

public class Sqrt extends UnaryOperator {
    @Override
    public Double getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public Double unary(Double n) {
        return Math.sqrt(n);
    }
}
