// import java.util.ArrayList;
// import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] list1 = {
            {1, 2},
            {5, 7},
            {9, 12}
        };
        int[][] list2 = {
            {2, 3},
            {6, 8},
            {10, 13}
        };

        List<int[]> intersections = getIntervals(list1, list2);
        System.out.println("Intersections: " + intersections.size());
        for (int[] interval : intersections) {
            System.out.printf("[%d, %d]%n", interval[0], interval[1]);
        }
    }

    // Given two sorted lists of intervals, return all intervals where the lists overlap.
    // Intervals that meet at a single endpoint are considered overlapping.
    static List<int[]> getIntervals(int[][] list1, int[][] list2) {
        List<int[]> intersections = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < list1.length && j < list2.length) {
            int start = Math.max(list1[i][0], list2[j][0]);
            int end = Math.min(list1[i][1], list2[j][1]);
            if (start <= end) {
                intersections.add(new int[]{start, end});
            }

            if (list1[i][1] < list2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return intersections;
    }
}
