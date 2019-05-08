package io.dongsheng;

public class Divide extends BinaryOperator {

    @Override
    public Double getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public Double binary(Double n1, Double n2) {
        return n1 / n2;
    }
}
