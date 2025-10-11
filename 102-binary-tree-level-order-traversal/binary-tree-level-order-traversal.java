/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> row = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                TreeNode front = q.poll();   // ✅ fixed “Tree Node” typo → TreeNode
                row.add(front.val);

                if (front.left != null) q.offer(front.left);
                if (front.right != null) q.offer(front.right);
            }

            ans.add(row);
        }

        return ans;  // ✅ added return statement at the end
    }
}
