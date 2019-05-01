package io.dongsheng;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        System.out.println("please input:");
        Scanner scanner = new Scanner(System.in);

        RPN rpn = new RPN();
        while (true) {
            String oneline = scanner.nextLine();
            String[] inputs = oneline.split("\\s+");
            NumberOrOperator[] symbols = new NumberOrOperator[inputs.length];
            try {
                for (int i = 0; i < inputs.length; i++) {
                    NumberOrOperator numberOrOperator = rpn.fromString(inputs[i]);
                    symbols[i] = numberOrOperator;
                }

                for (NumberOrOperator s : symbols) {
                    try {
                        rpn.add(s);
                    } catch (InsufficientParametersExecption e) {
                        System.out.println(e.getMessage());
                    }
                }

                StringBuffer sb = new StringBuffer();
                sb.append("stack:");
                for(Object d : rpn.getStack().toArray()) {
                    sb.append(" ").append(d);
                }
                System.out.println(sb.toString());

            } catch (NumberFormatException e) {
                System.out.println("neither a supported operator nor a number");
            }
        }


    }



}
