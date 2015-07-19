package com.epam.algorithms.mapreduce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by driabchenko on 7/19/15.
 */
public class SortMostPopular {
    public static void main(String[] args) throws IOException {
        (new SortMostPopular()).execute();
    }

    public void execute() throws IOException {
        List<Tuple<String, Long>> tuples = new ArrayList<>();
        List<String> words = loadWords("com/epam/algorithms/mapreduce/mapreduce.txt");//{"foo", "baz", "bar", "dar", "foo", "bar", "baz", "bar"};
        for (String word : words) {
            tuples.add(new Tuple<String, Long>(word, -1L) {
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
        printList("Reduced tuples", reduced, 10);

        // Reversing tuples
        List<Tuple<Long, String>> tuples2 = new ArrayList<>(tuples.size());
        for (Tuple<String, Long> t : reduced) {
            tuples2.add(new Tuple<>(t.getValues().get(0), t.getKey()));
        }

        List<Tuple<Long, String>> reduced2 = mr.mapReduce(tuples2);
        printList("Sorted tuples", reduced2, 10);
    }

    private List<String> loadWords(String resource) throws IOException {
        List<String> words = new ArrayList<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resource);
             InputStreamReader sr = new InputStreamReader(input);
             BufferedReader br = new BufferedReader(sr)) {
            String line;
            while((line = br.readLine()) != null) {
                String[] arr = line.split("[^a-zA-Z']+");
                for (String w : arr) {
                    if (w != null && !w.isEmpty()) {
                        words.add(w.toLowerCase());
                    }
                }
            }
        }

        return words;
    }

    private void printList(String msg, List<?> tuples, int limit) {
        System.out.println(msg);
        for (Object t : tuples) {
            if (limit -- <= 0) {
                break;
            }
            System.out.printf("\t%s\n", t.toString());
        }
    }
}
