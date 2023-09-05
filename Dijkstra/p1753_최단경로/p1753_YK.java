package p1753_최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1753_YK {

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        ArrayList<Node>[] list = new ArrayList[V + 1];
        for (int i = 0; i <= V; ++i) {
            list[i] = new ArrayList<>();
        }

        int weight, from, to;
        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, weight));
        }

        final int INF = Integer.MAX_VALUE;
        int[] distance = new int[V + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        boolean[] visited = new boolean[V + 1];
        int min, stopOver;
        for (int i = 0; i < V; ++i) {
            min = INF;
            stopOver = -1;

            for (int j = 1; j <= V; ++j) {
                if (!visited[j] && min > distance[j]) { 
                    min = distance[j];
                    stopOver = j;
                }
            }

            if (stopOver == -1) break;
            visited[stopOver] = true;

            for (Node tmp : list[stopOver]) {
                if (!visited[tmp.vertex] && distance[tmp.vertex] > tmp.vertex + min) {
                    distance[tmp.vertex] = tmp.weight + min;
                }
            }
        }

        for (int i = 1; i <= V; ++i) {
            bw.write((distance[i] == INF ? "INF" : distance[i]) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}