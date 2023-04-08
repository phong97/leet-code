package medium._347;

import java.util.*;

/**
 * @author phonghv
 */
public class Solution {

    public static int[] topKFrequent(int[] nums, int k) {
        final int n = nums.length;

        // find frequencies
        final Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.compute(num, (key, count) -> count == null ? 1 : count + 1);
        }

        final List<Integer>[] bucket = new ArrayList[n + 1];
        freq.forEach((key, value) -> {
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<>();
            }
            bucket[value].add(key);
        });

        // find top k most frequent elements
        final int[] result = new int[k];
        for (int i = n; i >= 0 || k > 0; i--) {
            if (bucket[i] != null) {
                for (final Integer num : bucket[i]) {
                    result[--k] = num;
                    if (k == 0) {
                        return result;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] result = topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
        System.exit(1);
    }
}
