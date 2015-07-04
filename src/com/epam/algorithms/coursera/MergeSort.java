package com.epam.algorithms.coursera;

import java.util.Arrays;

/**
 * Created by pokhylova on 04.07.15.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] unsorted = {32, 39, 21, 45, 23, 3};
        System.out.println("Unsorted " + Arrays.toString(unsorted));
        unsorted = sort(unsorted);

        System.out.println("Sorted " + Arrays.toString(unsorted));

    }

    public static int[] sort(int[] nums) {
        if (nums.length <= 1) return nums;

        int[] left, right;

        int middle = nums.length / 2;
        left = Arrays.copyOfRange(nums, 0, middle);
        right = Arrays.copyOfRange(nums, middle, nums.length);

        //System.out.println("Left " + Arrays.toString(left));
        //System.out.println("Right " + Arrays.toString(right));


        return merge(sort(left), sort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (notEmpty(left) && notEmpty(right)) {

            if (left[0] <= right[0]) {
                result[i] = left[0];
                left = rest(left);

            } else {
                result[i] = right[0];
                right = rest(right);
            }
            i++;
        }
        if (notEmpty(left)) result[i] = left[0];
        if (notEmpty(right)) result[i] = right[0];
        return result;
    }

    public static boolean notEmpty(int[] nums) {
        return (nums != null) && (nums.length > 0);
    }

    public static int[] rest(int[] nums) {
        if (nums.length > 1) {
            int tmp[] = new int[nums.length - 1];
            System.arraycopy(nums, 1, tmp, 0, nums.length - 1);
            return tmp;
        } else return null;
    }
}
