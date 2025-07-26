import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>(); 
        
        for (int num : nums) {  
            numSet.add(num);
        }
        
        int lcs = 0;  
        
        for (int value : numSet) {
            
            if (!numSet.contains(value - 1)) {
                int currNum = value;
                int currSub = 1;
                
                
                while (numSet.contains(currNum + 1)) {
                    currNum++;
                    currSub++;
                }
                
                lcs = Math.max(lcs, currSub);
            }
        }
        
        return lcs;
    }
}
