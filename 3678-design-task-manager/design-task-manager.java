import java.util.*;

class TaskManager {
    // Map to store taskId -> [priority, userId]
    private Map<Integer, int[]> taskMap;
    
    // Max heap based on priority and taskId
    private PriorityQueue<int[]> maxHeap;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];  // higher priority first
            return b[1] - a[1];  // if equal priority, higher taskId first
        });

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            taskMap.put(taskId, new int[]{priority, userId});
            maxHeap.offer(new int[]{priority, taskId});
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[]{priority, userId});
        maxHeap.offer(new int[]{priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        if (taskMap.containsKey(taskId)) {
            int userId = taskMap.get(taskId)[1];
            taskMap.put(taskId, new int[]{newPriority, userId});
            maxHeap.offer(new int[]{newPriority, taskId});
        }
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
        // No need to remove from heap directly (lazy deletion)
    }

    public int execTop() {
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int priority = top[0];
            int taskId = top[1];

            if (!taskMap.containsKey(taskId)) continue;

            int[] entry = taskMap.get(taskId);
            if (entry[0] != priority) continue; // stale entry

            taskMap.remove(taskId);
            return entry[1]; // return userId
        }

        return -1; // No tasks available
    }
}
