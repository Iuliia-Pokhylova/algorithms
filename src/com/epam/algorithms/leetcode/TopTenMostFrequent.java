package com.epam.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopTenMostFrequent {
    // return top ten most frequent queries from list of queries
    // queries fits into the memory
    public List<String> getTopTen(List<String> queries) {
        final Map<String, Integer> fr = new HashMap<>();

        PriorityQueue<String> que = new PriorityQueue<>(10, new Comparator<String>() {
            public int compare(String s1, String s2) {
                Integer fr1 = fr.get(s1);
                Integer fr2 = fr.get(s2);

                if (fr1 == null) {
                    fr1 = 0;
                }

                if (fr2 == null) {
                    fr2 = 0;
                }

                return fr1.compareTo(fr2);
            }
        }
        );


        for (String q : queries) {
            Integer freq = fr.get(q);
            if (freq == null) {
                freq = 1;
            } else {
                freq ++;
            }
            fr.put(q, freq);

            if (freq >= fr.get(que.peek())) {
                if (que.size() > 10) {
                    que.poll();
                }
                que.add(q);
            }
        }

        return new ArrayList<>(que);
    }
}
