import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.max;

/**
 * Created by driabchenko on 19/06/15.
 */
public class Test2 {
    public static void main(String[] args) {
//        String[] str = {
//                "((()))",
//                "(()(()))",
//                ")(((((()))))))",
//                "))((",
//                "))(())(())()()()))"
//        };
//        for (String s : str) {
//            System.out.println(s + " - " + longestValidParenthesesDP(s) + " (" + longestValidParenthesesStack(s) + ")\n");
//        }

//        printPrimeNumbers(100);

//        int[] numbers = {100, 110011, 12345, 123321};
//        for (int n : numbers) {
//            System.out.println(n + " : " + reverse(n));
//        }

        String[] str = {
                "192.168.1.0",
                "192.168.124.45",
                "127.0.0.1",
        };
        for (String s : str) {
            System.out.println(s + " - " + ipEncode(s) + ", " + ipDecode(ipEncode(s)));
        }
    }

    public static int longestValidParenthesesDP(String s) {
        int n = s.length();
        int len = 0;
        int[] dp = new int[n];

        for (int i = 2; i < n; i ++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2;
                    if (i >= 2) {
                        dp[i] += dp[i - 2];
                    }
                } else {
                    int idx = i - dp[i - 1] - 1;
                    if (idx >= 0 && s.charAt(idx) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if (idx > 0) {
                            dp[i] += dp[idx - 1];
                        }
                    }
                }
            }
            len = max(len, dp[i]);
            System.out.println(Arrays.toString(dp) + " ");
        }
        return len;
    }


    public static int longestValidParenthesesStack(String s) {
        int len = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                if (stack.isEmpty()) {
                    len = max(len, i + 1);
                } else {
                    len = max(len, i - stack.peek());
                }
            } else {
                stack.push(i);
            }
        }

        return len;
    }

    public static void printPrimeNumbers(int n) {
        if (n > 1) {
            boolean[] nonPrime = new boolean[n + 1];
            for (int i = 2; i <= n; i ++) {
                if (!nonPrime[i]) {
                    System.out.print(i + ", ");
                    if (i * i <= n) {
                        for (int j = i * i; j <= n; j += i) {
                            nonPrime[j] = true;
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    public static int reverse(int n) {
//        int reversed = 0;
//
//        while (n != 0) {
//            reversed = reversed * 10 + n % 10;
//            n /= 10;
//        }
//
//        return reversed;
        return (n & 0xf0) >> 4 | (n & 0x0f) << 4;
    }

    private static Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
    public static long ipEncode(String ip) {
        long result = -1;
        if (ip != null) {
            Matcher matcher = pattern.matcher(ip);
            if (matcher.find()) {
                result = 0;
                for (int i = 1; i <= 4; i ++) {
                    long octet = Long.parseLong(matcher.group(i));
                    result |= octet << (4 - i) * 0x08;
                }
            }

        }
        return result;
    }

    public static String ipDecode(long ip) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i ++) {
            long octet = (ip & 0xFF << (3 - i) * 0x08) >> 0x08 * (3 - i);
            result.append(octet);
            if (i < 3) {
                result.append('.');
            }
        }
        return result.toString();
    }
}
