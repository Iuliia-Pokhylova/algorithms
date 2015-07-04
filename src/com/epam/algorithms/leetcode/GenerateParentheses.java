package com.epam.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    https://leetcode.com/submissions/detail/30824405/
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        String expected = "[(((()))), ((()())), ((())()), ((()))(), (()(())), (()()()), (()())(), (())(()), (())()(), ()((())), ()(()()), ()(())(), ()()(()), ()()()()]";
        String actual1 = Arrays.toString(generateParenthesis(4).toArray());
        String actual2 = Arrays.toString(generateParenthesis2(4).toArray());
        System.out.println(actual1);
        System.out.println(actual2);
        System.out.println(expected);
        assert(expected.equals(actual1));
        assert(expected.equals(actual2));
    }

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> diff = new ArrayList<>();

        result.add("");
        diff.add(0);

        for (int i = 0; i < 2 * n; i++) {
            ArrayList<String> temp1 = new ArrayList<>();
            ArrayList<Integer> temp2 = new ArrayList<>();

            for (int j = 0; j < result.size(); j++) {
                String s = result.get(j);
                int k = diff.get(j);

                if (i < 2 * n - 1) {
                    temp1.add(s + "(");
                    temp2.add(k + 1);
                }

                if (k > 0 && i < 2 * n - 1 || k == 1 && i == 2 * n - 1) {
                    temp1.add(s + ")");
                    temp2.add(k - 1);
                }
            }

            result = new ArrayList<>(temp1);
            diff = new ArrayList<>(temp2);
        }

        return result;    }

    public static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisRecurrent(result, "", n, n);
        return result;
    }

    public static void generateParenthesisRecurrent(List<String> result, String expr, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(expr);
        } else if(left <= right) {
            if (left > 0) {
                generateParenthesisRecurrent(result, expr + "(", left - 1, right);
            }
            if (right > 0) {
                generateParenthesisRecurrent(result, expr + ")", left, right - 1);
            }
        }
    }
}
