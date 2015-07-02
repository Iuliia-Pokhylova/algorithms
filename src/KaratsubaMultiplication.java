import org.junit.Assert;

/**
 * Created by driabchenko on 7/2/15.
 */
public class KaratsubaMultiplication {
    public static void main(String[] args) {
        long[][] input = {
                {198, 110},
                //{198, 110},
                //{99999999, 55555555},
                //{5678, 1234}
        };
        for (long[] mp : input) {
            counter = counter2 = 0;
            long res = multiply(mp[0], mp[1]);
            //Assert.assertEquals(mp[0] * mp[1], res);
            res = multiply2(mp[0], mp[1]);
            Assert.assertEquals(mp[0] * mp[1], res);
            System.out.printf("%d, counted by %d recursions in first case and %d recursions in second\n", res, counter, counter2);
        }
    }

    public static long counter, counter2;

    public static long multiply(long x, long y) {
        counter ++;
        if (x == 0 || y == 0) {
            return 0;
        }

        long n = Math.min(ilen(x), ilen(y));
        if (n == 1) {
            return x * y;
        }

        n = Math.max(ilen(x), ilen(y));

        long[] a_b = split(x, n);
        long[] c_d = split(y, n);

        long ac = a_b[0] * c_d[0];//multiply(a_b[0], c_d[0]);
        long bd = a_b[1] * c_d[1]; //multiply(a_b[1], c_d[1]);
        long adbc = (a_b[0] + a_b[1]) * (c_d[0] + c_d[1]) - ac - bd;//multiply(a_b[0] + a_b[1], c_d[0] + c_d[1]) - ac - bd;

        return ac * (long) Math.pow(10, n) + adbc * (long) Math.pow(10, n / 2) + bd;
    }

    public static long multiply2(long x, long y) {
        counter2 ++;
        if (x == 0 || y == 0) {
            return 0;
        }

        long n = Math.min(ilen(x), ilen(y));
        if (n == 1) {
            return x * y;
        }

        n = Math.max(ilen(x), ilen(y));

        long[] a_b = split(x, n);
        long[] c_d = split(y, n);


        long ac = multiply2(a_b[0], c_d[0]);
        long bd = multiply2(a_b[1], c_d[1]);
        long ad = multiply2(a_b[0], c_d[1]);
        long bc = multiply2(a_b[1], c_d[0]);

        return ac * (long) Math.pow(10, n) + (ad + bc) * (long) Math.pow(10, n / 2) + bd;
    }

    private static long[] split(long num, long len) {
        long pow = (long) Math.pow(10, len / 2);

        return new long[] {num / pow, num % pow};
    }

    private static long ilen(long num) {
        return (num == 0) ? 1 : (long) Math.log10(num) + 1;
    }
}
