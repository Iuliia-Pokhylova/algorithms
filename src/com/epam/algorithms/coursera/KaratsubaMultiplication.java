package com.epam.algorithms.coursera;

import org.junit.Assert;

/**
 * Created by driabchenko on 7/2/15.
 */
public class KaratsubaMultiplication {
    public static void main(String[] args) {
        long[][] input = {
                {198, 110},
                {99999999, 55555555},
                {5678, 1234}
        };
        for (long[] mp : input) {
            long res = multiply(mp[0], mp[1]);
            Assert.assertEquals(mp[0] * mp[1], res);
            System.out.println(res);
        }
    }

    public static long multiply(long x, long y) {
        if (x == 0 || y == 0) {
            return 0;
        }

        long n = Math.max(ilen(x), ilen(y));
        if (n == 1) {
            return x * y;
        }

        long[] a_b = split(x, n);
        long[] c_d = split(y, n);

        long ac = multiply(a_b[0], c_d[0]);
        long bd = multiply(a_b[1], c_d[1]);
        long adbc = multiply(a_b[0] + a_b[1], c_d[0] + c_d[1]) - ac - bd;

        return ac * (long) Math.pow(10, n - n % 2) + (long)(adbc *  Math.pow(10, (n - n % 2) / 2)) + bd;
    }

    private static long[] split(long num, long len) {
        long pow = (long) Math.pow(10, len / 2);

        return new long[] {num / pow, num % pow};
    }

    private static long ilen(long num) {
        return (num == 0) ? 1 : (long) Math.log10(num) + 1;
    }
}
