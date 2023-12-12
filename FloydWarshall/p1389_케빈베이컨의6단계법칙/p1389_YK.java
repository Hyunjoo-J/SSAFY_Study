package FloydWarshall.p1389_케빈베이컨의6단계법칙;

import java.io.*;
import java.util.*;
public class p1389_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], N - 1);
            dist[i][i] = 0;
        }

        int min = N * N;
        int result = -1;

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        // 경 출 도
        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[j][k]);
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            int tmp = 0;
            for (int j = 0; j < N; ++j) {
                if (i == j) continue;
                tmp += dist[i][j];
            }
            if (tmp < min || (tmp == min && result > i)) {
                min = tmp;
                result = i;
            }
        }

        System.out.println(result + 1);
        br.close();
    }
}
