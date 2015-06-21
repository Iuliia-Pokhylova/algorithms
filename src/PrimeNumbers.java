import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by driabchenko on 21/06/15.
 */
public class PrimeNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(primeNumbersSieve(100).toArray()));
    }

    // Erathosphen sieve, O(log log N) - very low
    public static Set<Integer> primeNumbersSieve(int n) {
        Set<Integer> primes = new LinkedHashSet<>();
        if (n > 1) {
            boolean[] nonPrime = new boolean[n + 1];
            for (int i = 2; i <= n; i ++) {
                if (!nonPrime[i]) {
                    primes.add(i);
                    if (i * i <= n) {
                        for (int j = i * i; j <= n; j += i) {
                            nonPrime[j] = true;
                        }
                    }
                }
            }
        }
        return primes;
    }
}
