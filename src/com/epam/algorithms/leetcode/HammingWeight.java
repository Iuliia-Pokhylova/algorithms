package com.epam.algorithms.leetcode;

import java.util.Arrays;

/**
 * Created by driabchenko on 6/28/15.
 */
public class HammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(0b00000000000000100000000000000001));
    }

    public static int hammingWeight(int n) {
        int bits = 0;
        while (n != 0) {
            if ((n & 0b1) != 0) {
                bits ++;
            }
            n = n >>> 1;
        }
        return bits;
    }
}
