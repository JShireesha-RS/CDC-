import java.util.HashMap;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Determine the sign
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // Convert to long to handle overflow cases like Integer.MIN_VALUE
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Append the integer part
        long integerPart = num / den;
        result.append(integerPart);

        long remainder = num % den;
        if (remainder == 0) {
            return result.toString(); // No fractional part
        }

        result.append(".");

        // Map to store remainder -> index in the fractional part
        HashMap<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            // If we have seen this remainder before, a repeating cycle is found
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                result.insert(index, "(");
                result.append(")");
                break;
            }
            map.put(remainder, result.length());

            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }

        return result.toString();
    }
}
