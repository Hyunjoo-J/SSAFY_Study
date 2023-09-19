package BFS_DFS.p1939_중량제한;

import java.io.*;
import java.util.*;

public class p1939_HC {

    private static class Node {
        int idx, weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    private static int N;
    private static List<List<Node>> graph;
    private static int origin, destination;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0, A, B, C; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());
        origin = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 1_000_000_000;
        int mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (bfs(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left - 1);
    }

    private static boolean bfs(int weight) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(origin);
        visited[origin] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == destination)
                return true;

            for (Node nextNode: graph.get(now)) {
                if (visited[nextNode.idx])
                    continue;
                if (nextNode.weight < weight)
                    continue;
                queue.add(nextNode.idx);
                visited[nextNode.idx] = true;
            }
        }
        return false;
    }
}
