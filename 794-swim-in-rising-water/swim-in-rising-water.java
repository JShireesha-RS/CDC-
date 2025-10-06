import java.util.PriorityQueue;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        
        // Min-heap will store entries as (elevation, row, col)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        
        // Directions: up, down, left, right
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        int time = 0;
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int elevation = current[0];
            int r = current[1];
            int c = current[2];
            
            // Update max elevation encountered so far (this represents the time)
            time = Math.max(time, elevation);
            
            // If reached bottom-right, return time
            if (r == n - 1 && c == n - 1) {
                return time;
            }
            
            // Explore neighbors
            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new int[]{grid[nr][nc], nr, nc});
                }
            }
        }
        
        // theoretically unreachable
        return -1;
    }
}
