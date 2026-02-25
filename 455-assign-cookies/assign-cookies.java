import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {

        // Sort both arrays
        Arrays.sort(g);
        Arrays.sort(s);

        int contentChildren = 0;
        int cookieIndex = 0;

        // Greedy approach
        while (cookieIndex < s.length && contentChildren < g.length) {
            if (s[cookieIndex] >= g[contentChildren]) {
                contentChildren++;
            }
            cookieIndex++;
        }

        return contentChildren;
    }
}