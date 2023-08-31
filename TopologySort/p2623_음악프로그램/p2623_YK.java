package TopologySort.p2623_음악프로그램;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class p2623_YK {

    static class Node {
        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Node[] input = new Node[N + 1];
        int[] inDegree = new int[N + 1];
        ArrayList<Integer> result = new ArrayList<>();

        int k, from, to;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            from = Integer.parseInt(st.nextToken());
            for (int j = 1; j < k; j += 1) {
                to = Integer.parseInt(st.nextToken());
                input[from] = new Node(to, input[from]);
                ++inDegree[to];
                from = to;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; ++i) {
            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);

            for (Node tmp = input[cur]; tmp != null; tmp = tmp.next) {
                if (--inDegree[tmp.vertex] == 0) q.offer(tmp.vertex);
            }
        }

        if (result.size() == N) {
           for (int r : result) bw.write(r + "\n");
        }
        else {
            bw.write("" + 0);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
