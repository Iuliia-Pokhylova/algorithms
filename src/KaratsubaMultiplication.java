/**
 * Created by driabchenko on 7/2/15.
 */
public class KaratsubaMultiplication {
    public static void main(String[] args) {
        int[][] input = {
                {1340, 46},
                {5678, 1234}
        };
        for (int[] mp : input) {
            int res = multiply(mp[0], mp[1]);
            System.out.println(res);
            assert res == mp[0] * mp[1];
        }
    }

    public static int multiply(int x, int y) {
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

    private static int[] split(int num, int len) {
        int pow = (int) Math.pow(10, len - len / 2);

        return new int[] {num / pow, num % pow};
    }

    private static int ilen(int num) {
        return (num == 0) ? 1 : (int) Math.log10(num) + 1;
    }
}
