package com.epam.algorithms.coursera;

import java.util.Arrays;

/**
 * Created by driabchenko on 7/4/15.
 *
 * Merge sort implementation using O(n) extra space
 *
 */
public class MergeSortD {
    public static void main(String[] args) {
        (new MergeSortD()).execute();
    }

    public void execute() {
        int[] arr = {5, 4, 1, 8, 7, 2, 6, 3};
        System.out.printf("%s -> ", Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr) {
        if (arr != null && arr.length > 0) {
            int[] tmp = new int[arr.length];
            mergeSort(arr, 0, arr.length, tmp);
        }
    }

    private void mergeSort(int[] arr, int start, int length, int[] tmp) {
        int half = length / 2;
        if (half > 0) {
            // Sort left part
            mergeSort(arr, start, half, tmp);
            // Sort right part
            mergeSort(arr, start + half, length - half, tmp);

            // Merge both parts into resulting array
            for (int k = start, l = start, r = start + half; k < start + length; k ++) {
                if (l < start + half && (r >= start + length || arr[l] < arr[r])) {
                    tmp[k] = arr[l ++];
                } else if (r < start + length && (l >= start + half || arr[l] >= arr[r])) {
                    tmp[k] = arr[r ++];
                }
            }
            System.arraycopy(tmp, start, arr, start, length);
        }
    }
}
