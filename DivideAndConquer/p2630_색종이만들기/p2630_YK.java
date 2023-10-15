package DivideAndConquer.p2630_색종이만들기;

import java.io.*;
import java.util.*;
public class p2630_YK {

    static int N, white, blue;
    static int[][] paper;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        devideNconquer(0, 0, N, N, N);

        bw.write(white + "\n" + blue);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void devideNconquer(int sx, int sy, int ex, int ey, int len) {
        int sum = 0;
        for (int i = sx; i < ex; ++i) {
            for (int j = sy; j < ey; ++j) {
                sum += paper[i][j];
            }
        }

        if (sum == 0) {
            ++white;
        } else if (sum == len * len){
            ++blue;
        } else {
            len /= 2;
            devideNconquer(sx, sy, sx + len, sy + len, len);
            devideNconquer(sx, sy + len, sx + len, ey, len);
            devideNconquer(sx + len, sy, ex, sy + len, len);
            devideNconquer(sx + len, sy + len, ex, ey, len);
        }
    }
}
