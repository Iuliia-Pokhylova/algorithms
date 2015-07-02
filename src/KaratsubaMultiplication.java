import org.junit.Assert;

/**
 * Created by driabchenko on 7/2/15.
 */
public class KaratsubaMultiplication {
    public static void main(String[] args) {
        int[][] input = {
                {1341, 4631},
                {5678, 1234}
        };
        for (int[] mp : input) {
            counter = counter2 = 0;
            int res = multiply(mp[0], mp[1]);
            Assert.assertEquals(res, multiply2(mp[0], mp[1]));
            Assert.assertEquals(res, mp[0] * mp[1]);
            System.out.printf("%d, counted by %d recursions in first case and %d recursions in second\n", res, counter, counter2);
        }
    }

    public static int counter, counter2;

    public static int multiply(int x, int y) {
        counter ++;
        if (x == 0 || y == 0) {
            return 0;
        }

        int n = Math.min(ilen(x), ilen(y));
        if (n == 1) {
            return x * y;
        }

        int[] a_b = split(x, n);
        int[] c_d = split(y, n);

        int ac = multiply(a_b[0], c_d[0]);
        int bd = multiply(a_b[1], c_d[1]);
        int adbc = multiply(a_b[0] + a_b[1], c_d[0] + c_d[1]) - ac - bd;

        return ac * (int) Math.pow(10, n) + adbc * (int) Math.pow(10, n / 2) + bd;
    }

    public static int multiply2(int x, int y) {
        counter2 ++;
        if (x == 0 || y == 0) {
            return 0;
        }

        int n = Math.min(ilen(x), ilen(y));
        if (n == 1) {
            return x * y;
        }

        int[] a_b = split(x, n);
        int[] c_d = split(y, n);

        int ac = multiply2(a_b[0], c_d[0]);
        int bd = multiply2(a_b[1], c_d[1]);
        int ad = multiply2(a_b[0], c_d[1]);
        int bc = multiply2(a_b[1], c_d[0]);

        return ac * (int) Math.pow(10, n) + (ad + bc) * (int) Math.pow(10, n / 2) + bd;
    }

    private static int[] split(int num, int len) {
        int pow = (int) Math.pow(10, len - len / 2);

        return new int[] {num / pow, num % pow};
    }

    private static int ilen(int num) {
        return (num == 0) ? 1 : (int) Math.log10(num) + 1;
    }
}
