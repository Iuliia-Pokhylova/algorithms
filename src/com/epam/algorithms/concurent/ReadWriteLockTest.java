package com.epam.algorithms.concurent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by driabchenko on 7/12/15.
 */
public class ReadWriteLockTest {

    public interface I {
        int i = 0;

//        void foo(String foo);
        void foo(Integer foo);
        void foo(Object foo);
    }

    public static void main(String... args) {
        I i = new I() {
//            @Override
//            public void foo(String foo) {
//                System.out.println("String " + foo);
//            }

            @Override
            public void foo(Integer foo) {
                System.out.println("Integer " + foo);
            }

            @Override
            public void foo(Object foo) {
                System.out.println("Object " + foo);
            }
        };

        Object s = null;
        i.foo(s);
        i.foo("s");
        i.foo(null);
    }

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Object data;

    public synchronized void write(Object data) {
        lock.writeLock().lock();
        try {
            this.data = data;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object read() {
        lock.readLock().lock();
        try {
            return data;
        } finally {
            lock.readLock().unlock();
        }
    }
}
