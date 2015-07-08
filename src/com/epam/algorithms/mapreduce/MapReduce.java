package com.epam.algorithms.mapreduce;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by driabchenko on 7/8/15.
 */
public class MapReduce {
    public static void main(String[] args) {
        (new MapReduce(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                String w1 = o1 == null ? "" : o1.word;
                String w2 = o2 == null ? "" : o2.word;
                return w1.compareTo(w2);
            }
        })).execute();
    }

    class Tuple {
        String word;
        int count = 1;

        public Tuple(String word) {
            this.word = word;
        }
    }

    public void mapReduce() {
        List<String> words = Lists.newArrayList("foo", "baz", "bar", "dar", "foo", "bar", "baz", "bar");

        System.out.println(Arrays.toString(twoMostPopular(words).toArray()));
    }

    private Comparator<Tuple> comparator;

    public MapReduce(Comparator<Tuple> comparator) {
        this.comparator = comparator;
    }

    private List<Tuple> reduce(List<String> words) {
        LinkedList<Tuple> tuples = new LinkedList<>();

        LinkedList<Tuple> tuples1 = map(words.subList(0, words.size() / 2));
        LinkedList<Tuple> tuples2 = map(words.subList(words.size() / 2, words.size()));


        while (!tuples1.isEmpty() || !tuples2.isEmpty()) {
            Tuple t1 = tuples1.isEmpty() ? null : tuples1.peekFirst();
            Tuple t2 = tuples2.isEmpty() ? null : tuples2.peekFirst();
            int cmp = comparator.compare(t1, t2);
            Tuple toAdd;
            if (t1 != null && (cmp < 0 || t2 == null)) {
                toAdd = tuples1.pollFirst();
            } else if (t2 != null && (cmp > 0 || t1 == null)) {
                toAdd = tuples2.pollFirst();
            } else {
                toAdd = tuples1.pollFirst();
                toAdd.count += tuples2.pollFirst().count;
            }
            if (!tuples.isEmpty() && tuples.peekLast().word.equals(toAdd.word)) {
                tuples.peekLast().count += toAdd.count;
            } else {
                tuples.add(toAdd);
            }

        }

        return tuples;
    }

    private LinkedList<Tuple> map(List<String> words) {
        ArrayList<Tuple> tuples = new ArrayList<>();
        for (String word : words) {
            tuples.add(new Tuple(word));
        }
        Collections.sort(tuples, comparator);
        return new LinkedList<>(tuples);
    }
}
