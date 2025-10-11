class Solution {
    private int res = 0;  // to store the maximum diameter

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;   // return the final diameter
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);

        // Update the diameter: number of edges between two farthest nodes
        res = Math.max(res, l + r);

        // Return the height of the current node
        return 1 + Math.max(l, r);
    }
}
