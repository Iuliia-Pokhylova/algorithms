package com.epam.algorithms.mapreduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by driabchenko on 7/19/15.
 */
public class SortMostPopular {
    public static void main(String[] args) {
        (new SortMostPopular()).execute();
    }

    public void execute() {
        List<Tuple<String, Long>> tuples = new ArrayList<>();
        String[] words = {"foo", "baz", "bar", "dar", "foo", "bar", "baz", "bar"};
        for (String word : words) {
            tuples.add(new Tuple<String, Long>(word, 1L) {
                @Override
                public void reduce() {
                    if (getValues().size() > 1) {
                        Long sum = 0L;
                        for (Long v : getValues()) {
                            sum += v;
                        }
                        getValues().clear();
                        getValues().add(sum);
                    }
                }
            });

        }
        MapReduce mr = new MapReduce();
        List<Tuple<String, Long>> reduced = mr.mapReduce(tuples);
        System.out.println(Arrays.toString(reduced.toArray()));

        List<Tuple<Long, String>> tuples2 = new ArrayList<>(tuples.size());
        for (Tuple<String, Long> t : reduced) {
            tuples2.add(new Tuple<>(-t.getValues().get(0), t.getKey()));
        }

        System.out.println(Arrays.toString(tuples2.toArray()));

        List<Tuple<Long, String>> reduced2 = mr.mapReduce(tuples2);
        System.out.println(Arrays.toString(reduced2.toArray()));
     }
}
