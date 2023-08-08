package Bruteforce.p15683_감시;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15683_YK {

    // CCTV 모드
    static int[][] mode = {
            {}, {1, 2, 3, 4}, {1, 2}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1}
    };
    static int N, M, result = Integer.MAX_VALUE;
    static int k; // cctv 개수
    static int[][] input;
    static int[] cctvX, cctvY, cctvMode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][M];
        cctvX = new int[8];
        cctvY = new int[8];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (input[i][j] > 0 && input[i][j] < 6) {
                    cctvX[k] = i; cctvY[k++] = j;
                }
            }
        }

        cctvX = Arrays.copyOf(cctvX, k);
        cctvY = Arrays.copyOf(cctvY, k);
        cctvMode = new int[k];

        combi(0, 0);
        bw.write(String.valueOf(result));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void combi(int cnt, int start) {
        if (cnt == k) {
            // 사각 지대 찾기 로직
            int[][] arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.copyOf(input[i], M);
            }
            for (int i = 0; i < k; i++) {
                bfs(arr, i);
            }

            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 0) ++tmp;
                }
            }
            if (tmp < result) result = tmp;
            return;
        }

        for (int i = start; i < k; ++i) {
            for (int m : mode[input[cctvX[i]][cctvY[i]]]) {
                cctvMode[cnt] = m;
                combi(cnt + 1, i + 1);
            }
        }
    }

    private static void bfs(int[][] arr, int k) {
        int x = cctvX[k]; int y = cctvY[k];
        int c = input[x][y]; // cctv 종류
        int m = cctvMode[k]; // cctv mode

        // 상 (1-1  2-1  3-1,2  4-1,2,3  5)
        if ((c == 1 && m == 1) || (c == 2 && m == 1) ||
                (c == 3 && (m == 1 || m == 2)) || (c == 4 && m != 4) || c == 5) {
            int nx = x - 1;
            while (nx >= 0 && arr[nx][y] != 6) {
                arr[nx--][y] = 9;
            }
        }

        // 하 (1-2  2-1  3-3,4  4-1,2,4  5)
        if ((c == 1 && m == 2) || (c == 2 && m == 1) ||
                (c == 3 && (m == 3 || m == 4)) || (c == 4 && m != 3) || c == 5) {
            int nx = x + 1;
            while (nx < N && arr[nx][y] != 6) {
                arr[nx++][y] = 9;
            }
        }

        // 좌 (1-3  2-2  3-1,3  4-1,3,4  5)
        if ((c == 1 && m == 3) || (c == 2 && m == 2) ||
                (c == 3 && (m == 1 || m == 3)) || (c == 4 && m != 2) || c == 5) {
            int ny = y - 1;
            while (ny >= 0 && arr[x][ny] != 6) {
                arr[x][ny--] = 9;
            }
        }

        // 우 (1-4  2-2  3-2,4  4-2,3,4  5)
        if ((c == 1 && m == 4) || (c == 2 && m == 2) ||
                (c == 3 && (m == 2 || m == 4)) || (c == 4 && m != 1) || c == 5) {
            int ny = y + 1;
            while (ny < M && arr[x][ny] != 6) {
                arr[x][ny++] = 9;
            }
        }
    }
}

