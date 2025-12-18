import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {

            if (token.equals("+") || token.equals("-")
                    || token.equals("*") || token.equals("/")) {

                int b = stack.pop(); // second operand
                int a = stack.pop(); // first operand
                int res = 0;

                switch (token) {
                    case "+":
                        res = a + b;
                        break;
                    case "-":
                        res = a - b;
                        break;
                    case "*":
                        res = a * b;
                        break;
                    case "/":
                        res = a / b; // integer division
                        break;
                }

                stack.push(res);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
