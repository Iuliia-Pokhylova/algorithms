package com.epam.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static java.lang.Integer.max;

/**
 * Created by driabchenko on 19/06/15.
 */
public class Parentheses {
    public static void main(String[] args) {
        String[] str = {
                "((()))",
                "(()(()))",
                ")(((((()))))))",
                "))((",
                "))(())(())()()()))"
        };
        for (String s : str) {
            System.out.println(s + " - " + longestValidParenthesesDP(s) + " (" + longestValidParenthesesStack(s) + ")\n");
        }
        while (true) {

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
}
