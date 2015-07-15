package com.epam.algorithms.leetcode;

/**
 * Created by driabchenko on 6/27/15.
 */
public class RectangleArea {
    public static void main(String[] args) {
//        System.out.println(computeArea2(-3, 0, 3, 4, 0, -1, 9, 2));
//        System.out.println(computeArea2(0, 0, 6, 2, 0, -1, 9, 2));
//        System.out.println(computeArea2(-6, 0, 0, 4, 0, -1, 9, 2));
//        System.out.println(computeArea2(-7, 0, -1, 4, 0, -1, 9, 2));
        System.out.println(computeArea2(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1));
    }

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int sABCD = (C - A) * (D - B);
        int sEFGH = (G - E) * (H - F);
        int sIntersection = 0;
        if (((A >= E && A <= G) || (C >= E && C <= G)) &&
                ((B >= F && B <= H)) || ((D >= F && D <= H))) {
            sIntersection = (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F));
        }
        return sABCD + sEFGH - sIntersection;
    }

    public static int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        if (left < right) {
            int top = Math.min(D, H);
            int bottom = Math.max(B, F);
            if (top > bottom) {
                return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
            }
        }
        return (C - A) * (D - B) + (G - E) * (H - F);
    }
}
