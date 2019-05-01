package io.dongsheng;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class TestRPN {
    public static final double DELTA = 0.0000000001;

    @Test
    public void test1() {
        RPN rpn = new RPN();

        rpn.addString("5");

        Assert.assertArrayEquals(new Double[]{5.0}, rpn.getStack().toArray(new Double[0]));

        rpn.addString("2");

        Assert.assertArrayEquals(new Double[]{5.0, 2.0}, rpn.getStack().toArray(new Double[0]));

    }

    @Test
    public void test2() {
        RPN rpn = new RPN();

        rpn.addString("2");
        rpn.addString("sqrt");

        Double[] actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected = {1.4142135623};

        compare(expected, actual);

        rpn.addString("clear");
        rpn.addString("9");
        rpn.addString("sqrt");

        actual = rpn.getStack().toArray(new Double[0]);
        expected[0] = 3.0;

        compare(expected, actual);
    }

    @Test
    public void test3() {
        RPN rpn = new RPN();
        rpn.addString("5");
        rpn.addString("2");
        rpn.addString("-");

        Double[] actual = rpn.getStack().toArray(new Double[0]);
        Assert.assertEquals(1, actual.length);
        Assert.assertEquals(3.0, actual[0], DELTA);

        rpn.addString("3");
        rpn.addString("-");
        actual = rpn.getStack().toArray(new Double[0]);
        Assert.assertEquals(1, actual.length);
        Assert.assertEquals(0.0, actual[0], DELTA);

        rpn.addString("clear");
        actual = rpn.getStack().toArray(new Double[0]);
        Assert.assertEquals(0, actual.length);
    }

    public void compare(Double[] expected, Double[] actual) {
        Assert.assertEquals("different length", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(expected[i], actual[i], DELTA);
        }
    }

    @Test
    public void test4() {
        RPN rpn = new RPN();
        rpn.addString("5");
        rpn.addString("4");
        rpn.addString("3");
        rpn.addString("2");

        Double[] actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected = {5d,4d,3d,2d};

        compare(expected, actual);

        rpn.addString("undo");

        actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected1 = {5d,4d,3d};

        compare(expected1, actual);

        rpn.addString("undo");

        actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected2 = {5d,4d};

        compare(expected2, actual);

        rpn.addString("*");

        actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected3 = {20d};

        compare(expected3, actual);

        rpn.addString("5");
        rpn.addString("*");
        actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected4 = {100d};

        compare(expected4, actual);

        rpn.addString("undo");

        actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected5 = {20d, 5d};

        compare(expected5, actual);

    }

    @Test(expected = InsufficientParametersExecption.class)
    public void test5() {
        //12 3* 5+ * * 6 5
        RPN rpn = new RPN();
        rpn.addString("1");
        rpn.addString("2");
        rpn.addString("3");
        rpn.addString("*");
        rpn.addString("5");
        rpn.addString("+");
        rpn.addString("*");

        Double[] actual = rpn.getStack().toArray(new Double[0]);
        Double[] expected = {11d};

        compare(expected, actual);
        rpn.addString("*");
        rpn.addString("6");
        rpn.addString("5");
    }
}
