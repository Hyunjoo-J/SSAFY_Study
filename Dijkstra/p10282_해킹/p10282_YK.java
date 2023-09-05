package Dijkstra.p10282_해킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10282_YK {
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
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        final int INF = Integer.MAX_VALUE;
        int N, D, C, a, b, s;
        for (int tc = 0; tc < T; ++tc) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] list = new ArrayList[N + 1];
            for (int i = 0; i <= N; ++i) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < D; ++i) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                list[b].add(new Node(a, s));
            }

            int[] dist = new int[N + 1];
            boolean[] visited = new boolean[N + 1];
            Arrays.fill(dist, INF);
            dist[C] = 0;

            int min, stopOver;
            int cnt = 0;
            for (int i = 0; i < N; ++i) {
                min = INF;
                stopOver = -1;

                for (int j = 1; j <= N; ++j) {
                    if (visited[j] || min <= dist[j]) continue;
                    min = dist[j];
                    stopOver = j;
                }

                if (stopOver == -1) break;
                visited[stopOver] = true;
                cnt++;

                for (Node tmp : list[stopOver]) {
                    if (!visited[tmp.vertex] && dist[tmp.vertex] > min + tmp.weight) {
                        dist[tmp.vertex] = min + tmp.weight;
                    }
                }
            }

            Arrays.sort(dist);
            int last = C;
            for (int i = N; i >= 0; --i) {
                if (dist[i] != INF) {
                    last = i;
                    break;
                }
            }
            bw.write(cnt + " " + dist[last] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
