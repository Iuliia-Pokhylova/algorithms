package com.epam.algorithms.leetcode;

/*
    https://leetcode.com/submissions/detail/30922321/
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        if (height.length < 3)
            return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int max = height[0];

        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = max;
            if (height[i] > max)
                max = height[i];
        }
        max = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = max;
            if (height[i] > max)
                max = height[i];
        }
        int ret = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int trap = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (trap > 0)
                ret += trap;
        }
        return ret;
    }
}
