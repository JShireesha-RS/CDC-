import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];  
        
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        
        List<Integer>[] buckets = new List[nums.length + 1];
        
        
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }
        
        int count = 0;
        
        for (int i = buckets.length - 1; i >= 0 && count < k; i--) {
            if (buckets[i] != null) {
                for (int val : buckets[i]) {
                    if (count == k) break;
                    result[count++] = val;
                }
            }
        }
        
        return result;
    }
}
