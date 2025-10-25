class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build adjacency list: graph[from] = list of {to, price}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // Queue will store {currentNode, currentCost}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0});

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        int stops = 0;

        // BFS layer-by-layer (each layer = one stop)
        while (!q.isEmpty() && stops <= k) {
            int size = q.size();
            int[] tempCost = Arrays.copyOf(minCost, n); // avoid cross-level interference

            while (size-- > 0) {
                int[] curr = q.poll();
                int node = curr[0];
                int cost = curr[1];

                for (int[] neighbour : adj.get(node)) {
                    int nextNode = neighbour[0];
                    int price = neighbour[1];
                    if (cost + price < tempCost[nextNode]) {
                        tempCost[nextNode] = cost + price;
                        q.offer(new int[]{nextNode, tempCost[nextNode]});
                    }
                }
            }
            minCost = tempCost; // update after finishing this level
            stops++;
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}
