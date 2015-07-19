package com.epam.algorithms.coursera.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountInversions {
    public static void main(String... args) throws IOException {
        CountInversions inst = new CountInversions();
        Integer[] nums = {5,3,1,2,4,6};//inst.loadArray("com/epam/algorithms/coursera/test1/integers.lst");
        System.out.println(inst.countInversions(nums));
    }

    private long countInversions(Integer[] arr) {
        if (arr != null && arr.length > 0) {
            Integer[] tmp = new Integer[arr.length];
            return mergeSort(arr, 0, arr.length, tmp);
        }

        return 0;
    }

    private long mergeSort(Integer[] arr, int start, int length, Integer[] tmp) {
        long cnt = 0;
        int half = length / 2;
        if (half > 0) {
            // Sort left part
            cnt += mergeSort(arr, start, half, tmp);
            // Sort right part
            cnt += mergeSort(arr, start + half, length - half, tmp);

            // Merge both parts into resulting array
            for (int k = start, l = start, r = start + half; k < start + length; k ++) {
                if (l < start + half && (r >= start + length || arr[l] < arr[r])) {
                    tmp[k] = arr[l ++];
                } else if (r < start + length && (l >= start + half || arr[l] >= arr[r])) {
                    if (l < start + half) { //inversion detected
                        cnt += start + half - l;
                    }
                    tmp[k] = arr[r ++];
                }
            }
            System.arraycopy(tmp, start, arr, start, length);
        }
        return cnt;
    }

    private Integer[] loadArray(String resource) throws IOException {
        List<Integer> lst = new ArrayList<>(1000);

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resource);
                InputStreamReader sr = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(sr)) {
            String line;
            while((line = br.readLine()) != null){
                lst.add(Integer.parseInt(line));
            }
        } catch (NumberFormatException ex) {
            System.out.printf(ex.getLocalizedMessage());
        }

        return lst.toArray(new Integer[lst.size()]);
    }
}
