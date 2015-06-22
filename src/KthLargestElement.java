import java.util.Arrays;

/*
    https://leetcode.com/submissions/detail/30828663/
 */
public class KthLargestElement {
    public static void main(String[] args) throws Exception {
        System.out.println(solution_1(new int[]{3,2,1,5,6,4}, 1));
        System.out.println(solution_1(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(solution_1(new int[]{3,2,1,5,6,4}, 3));
        System.out.println(solution_1(new int[]{3,2,1,5,6,4}, 4));
        System.out.println(solution_1(new int[]{3,2,1,5,6,4}, 5));
        System.out.println(solution_1(new int[]{3,2,1,5,6,4}, 6));
        System.out.println(solution_1(new int[]{4,6,5,1,2,3}, 1));
        System.out.println(solution_1(new int[]{4,6,5,1,2,3}, 2));
        System.out.println(solution_1(new int[]{4,6,5,1,2,3}, 3));
        System.out.println(solution_1(new int[]{4,6,5,1,2,3}, 4));
        System.out.println(solution_1(new int[]{4,6,5,1,2,3}, 5));
        System.out.println(solution_1(new int[]{4,6,5,1,2,3}, 6));
    }

    public static int solution_1(int[] arr, int k) throws Exception {
        Arrays.sort(arr);
        return arr[k - 1];
    }
}
