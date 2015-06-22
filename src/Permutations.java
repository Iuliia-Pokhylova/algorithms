public class Permutations {

    public static void main(String[] args) {
        permutation("", "abcd");
    }

    private static void permutation(String base, String set) {
        if (set.length() > 0) {
            for (int i = 0; i < set.length(); i++) {
                String rest = "";
                if (i > 0) {
                    rest = set.substring(0, i);
                }
                if (i < set.length() - 1) {
                    rest += set.substring(i + 1);
                }
                permutation(base + set.substring(i, i + 1), rest);
            }
        } else {
            System.out.println(base + set);
        }
    }
}
