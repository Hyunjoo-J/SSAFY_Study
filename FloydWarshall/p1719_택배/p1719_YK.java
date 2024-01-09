package FloydWarshall.p1719_택배;

import java.io.*;
import java.util.*;

public class p1719_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        final int INF = Integer.MAX_VALUE >> 2;
        int[][] dist = new int[N][N];
        int[][] result = new int[N][N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0, a, b, c; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            dist[a][b] = c;
            dist[b][a] = c;
            result[a][b] = b;
            result[b][a] = a;
        }


        // 경출도
        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        result[i][j] = result[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == j) sb.append("-");
                else sb.append(result[i][j] + 1);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
