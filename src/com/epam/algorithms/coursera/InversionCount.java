package com.epam.algorithms.coursera;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pokhylova on 19.07.15.
 */
public class InversionCount {

    public static void main(String[] args) {


        int[] unsorted = null;  //{32, 39, 21, 45, 23, 3};
        try {
            unsorted = loadArray("IntegerArray.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Unsorted " + Arrays.toString(unsorted));
        System.out.println("Inversions " + invCount(unsorted));

    }

    public static long invCount(int[] nums) {
        if (nums.length < 2)
            return 0;

        int m = (nums.length + 1) / 2;
        int left[] = Arrays.copyOfRange(nums, 0, m);
        int right[] = Arrays.copyOfRange(nums, m, nums.length);

        return invCount(left) + invCount(right) + merge(nums, left, right);
    }

    public static long merge(int nums[], int[] left, int[] right) {
        int i = 0, j = 0, result = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                nums[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                nums[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                nums[i + j] = left[i];
                i++;
            } else {
                nums[i + j] = right[j];
                result += left.length - i;
                j++;
            }
        }
        //System.out.println("merged  " + Arrays.toString(nums));
        return result;
    }

    public static int[] loadArray(String resource) throws IOException {
        List<Integer> lst = new ArrayList<>(1000);

        try (BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String line;
            while ((line = br.readLine()) != null) {
                lst.add(Integer.parseInt(line));
            }
        } catch (NumberFormatException ex) {
            System.out.printf(ex.getLocalizedMessage());
        }

        return ArrayUtils.toPrimitive(lst.toArray(new Integer[lst.size()]));
    }
}
