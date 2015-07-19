package com.epam.algorithms.mapreduce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by driabchenko on 7/8/15.
 */
public class MapReduce {
    public <K extends Comparable<K>, V> List<Tuple<K, V>> mapReduce(List<Tuple<K, V>> tuples) {
        List<LinkedList<Tuple<K, V>>> files = new ArrayList<>();
        for (int i = 0; i < tuples.size(); i += 3) {
            int r = i <= tuples.size() - 3 ? i + 3 : tuples.size();
            files.add(map(tuples.subList(i, r)));
        }

        return reduce(files);
    }

    private <K extends Comparable<K>, V> LinkedList<Tuple<K, V>> map(List<Tuple<K, V>> tuples) {
        Collections.sort(tuples);
        LinkedList<Tuple<K, V>> result = new LinkedList<>();
        for (Tuple<K, V> tuple : tuples) {
            Tuple<K, V> prev = result.peekLast();
            if (!tuple.mergeTo(prev)) {
                result.addLast(tuple);
            }
        }

        return result;
    }

    private <K extends Comparable<K>, V> List<Tuple<K, V>> reduce(List<LinkedList<Tuple<K, V>>> files) {
        LinkedList<Tuple<K, V>> tuples = new LinkedList<>();

        while (true) {
            List<LinkedList<Tuple<K, V>>> min = null;
            for (LinkedList<Tuple<K, V>> file : files) {
                if (!file.isEmpty()) {
                    if (min == null || min.get(0).peekFirst().compareTo(file.peekFirst()) > 0) {
                        min = new ArrayList<>();
                        min.add(file);
                    } else if (min.get(0).peekFirst().equals(file.peekFirst())) {
                        min.add(file);
                    }
                }
            }
            if (min == null) {
                break;
            }
            Tuple<K, V> t = null;
            for (LinkedList<Tuple<K, V>> file : min) {
                if (t == null) {
                    t = file.pollFirst();
                } else {
                    file.pollFirst().mergeTo(t);
                }
            }
            tuples.add(t);
        }

        return tuples;
    }
}
