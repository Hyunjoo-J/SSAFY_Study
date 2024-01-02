package TopologySort.p14567_선수과목;

import java.io.*;
import java.util.*;

public class p14567_YK {

    static class Subject {
        int name, cnt;

        public Subject(int name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int[] indegree, result;
    static List<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; ++i) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            ++indegree[b];
        }

        result = new int[N + 1];
        topologySort();
        for (int i = 1; i <= N; ++i) {
            sb.append(result[i] + 1).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    private static void topologySort() {
        Queue<Subject> q = new ArrayDeque<>();

        for (int i = 1; i <= N; ++i) {
            result[i] = indegree[i];
            if (indegree[i] == 0) {
                q.offer(new Subject(i, 1));
            }
        }

        while (!q.isEmpty()) {
            Subject now = q.poll();

            for (int next : list[now.name]) {
                if (--indegree[next] == 0) {
                    result[next] = now.cnt;
                    q.offer(new Subject(next, now.cnt + 1));
                }
            }
        }
    }
}
