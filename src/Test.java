import javax.lang.model.util.Elements;
import java.util.*;

import static java.lang.Integer.max;

/**
 * Created by driabchenko on 19/06/15.
 */
public class Test {
    public static void main(String[] args) {
        String[] str = {
                "((()))",
                ")((())))",
                "))((",
                "))(())(())()()()))"
        };
        for (String s : str) {
            System.out.println(s + " - " + longestValidParenthesesDP(s) + " (" + longestValidParenthesesStack(s) + ")\n");
        }
    }

    public static int longestValidParenthesesDP(String s) {
        int n = s.length();
        int len = 0;
        int[] dp = new int[n];

        for (int i = 1; i < n; i ++) {
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
//            System.out.println(Arrays.toString(dp) + " ");
            len = max(len, dp[i]);
        }
        return len;
    }


    public static int longestValidParenthesesStack(String s) {
        int len = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                int idx = stack.isEmpty() ? -1 : stack.peek();
                len = max(len, i - idx);
            } else {
                stack.push(i);
            }
            System.out.println(s.substring(0, i + 1) + ": " + Arrays.toString(stack.toArray()));
        }

        return len;
    }
}
