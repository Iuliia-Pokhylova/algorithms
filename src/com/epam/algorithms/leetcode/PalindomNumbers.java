package com.epam.algorithms.leetcode;

/**
 * Created by driabchenko on 21/06/15.
 */
public class PalindomNumbers {
    public static void main(String[] args) {
        int[] numbers = {100, 110011, 12345, 123321};
        for (int n : numbers) {
            System.out.println(n + " : " + reverse(n));
        }
    }

    public static int reverse(int n) {
        int reversed = 0;

        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }

        return reversed;
    }
}
