package com.epam.algorithms.mapreduce;

import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by driabchenko on 7/19/15.
 */
public class Tuple<K extends Comparable<K>, V> implements Comparable<Tuple<K, V>> {
    private K key;
    private List<V> values = new ArrayList<>();

    public Tuple(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        this.key = key;
        this.values.add(value);
    }

    public K getKey() {
        return key;
    }

    public List<V> getValues() {
        return values;
    }

    public boolean mergeTo(Tuple<K, V> target) {
        boolean merged = false;
        if (equals(target)) {
            target.getValues().addAll(getValues());
            target.reduce();
            merged = true;
        }
        return merged;
    }

    public void reduce() {

    }

    @Override
    public int compareTo(@NotNull Tuple<K, V> o) {
        return getKey().compareTo(o.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<? extends Comparable, ?> tuple = (Tuple<? extends Comparable, ?>) o;

        return key.equals(tuple.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s : %s", key.toString(), Arrays.toString(getValues().toArray()));
    }
}
