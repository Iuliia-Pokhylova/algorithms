import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/submissions/detail/30833902/
 */
public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Lists.newArrayList(-1));
        triangle.add(Lists.newArrayList(2, 3));
        triangle.add(Lists.newArrayList(1, -1, -3));
        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        for (int row = triangle.size() - 1; row >= 0; row --) {
            for (int i = 0; i <= row; i ++) {
                if (row == triangle.size() - 1) {
                    dp[i] = triangle.get(row).get(i);
                } else {
                    dp[i] = Math.min(dp[i], dp[i + 1]) + triangle.get(row).get(i);
                }
            }
        }
        return dp[0];

//        return minimumTotalRecurrent(triangle, triangle.get(0).get(0), 1, 0);
    }

    public static int minimumTotalRecurrent(List<List<Integer>> triangle, int total, int row, int cur) {
        if (row >= triangle.size()) {
            return total;
        }

        int left = total + triangle.get(row).get(cur);
        int right = total + triangle.get(row).get(cur + 1);

        left = minimumTotalRecurrent(triangle, left, row + 1, cur);
        right = minimumTotalRecurrent(triangle, right, row + 1, cur + 1);

        return Math.min(left, right);
    }
}
