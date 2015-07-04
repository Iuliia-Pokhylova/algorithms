package com.epam.algorithms.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/*
    https://leetcode.com/submissions/detail/30919924/
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] height = new int[] {2,1,10,12,2,3,5,6,5};
        System.out.println(largestRectangleArea(height));
    }

    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int max = 0;
        int i = 0;

        while (i < height.length || !stack.isEmpty()) {
            //push index to stack when the current height is larger than the previous one
            if (i < height.length && (stack.isEmpty() || height[i] >= height[stack.peek()])) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = height[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }

        }

        return max;
    }
}
