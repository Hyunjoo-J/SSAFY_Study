package Implematation.프로그래머스LV2_258711_도넛과막대그래프;

import java.util.*;

class Solution_HC {

    private static final int MAX_NODES = 1_000_010;

    private static final int SHAPE_DONUT = 1;
    private static final int SHAPE_STICK = 2;
    private static final int SHAPE_EIGHT = 3;

    private List<List<Integer>> graph;
    private int[] inDegree;
    private int[] outDegree;
    private boolean[] visited;

    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};

        graph = new ArrayList<>(MAX_NODES);
        inDegree = new int[MAX_NODES];
        outDegree = new int[MAX_NODES];
        visited = new boolean[MAX_NODES];
        for (int i = 0; i < MAX_NODES; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            ++inDegree[b];
            ++outDegree[a];
        }

        // find root
        int root = -1;
        for (int i = 0; i < MAX_NODES; ++i) {
            if (inDegree[i] == 0 && outDegree[i] > 1) {
                answer[0] = root = i;
                break;
            }
        }

        for (int x: graph.get(root)) {
            ++answer[find(x)];
        }
        return answer;
    }

    private int find(int x) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        visited[x] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (outDegree[now] > 1) {
                return SHAPE_EIGHT;
            }

            for (int next: graph.get(now)) {
                if (visited[next])
                    return SHAPE_DONUT;
                queue.add(next);
                visited[next] = true;
            }
        }
        return SHAPE_STICK;
    }
}