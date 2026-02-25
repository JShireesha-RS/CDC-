class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int totalGas = 0, totalCost = 0;

        // Check if total gas is enough
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas < totalCost) {
            return -1;
        }

        int tank = 0;
        int start = 0;

        // Find starting index
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];

            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        return start;
    }
}