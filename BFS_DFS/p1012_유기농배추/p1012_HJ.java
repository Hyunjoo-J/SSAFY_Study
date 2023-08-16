import java.util.*;
import java.io.*;

public class p1012_HJ {
    static int dirX[] = {0, 0, -1, 1};
    static int dirY[] = {-1, 1, 0, 0};
    static int farm[][];
    static boolean visit[][];
    static int n, m, k;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; ++tc) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            farm = new int[n][m];
            visit = new boolean[n][m];

            for(int i = 0; i < k; ++i) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                farm[x][y] = 1;
            }

            ans = 0;
            for(int i = 0; i < n; ++i) {
                for(int j = 0; j < m; ++j) {

                    if(farm[i][j] == 1 && visit[i][j] == false) {
                        ans++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }

    public static void dfs(int x, int y) {
        visit[x][y] = true;
        int nx, ny;
        for(int i=0; i<4; i++) {
            nx = x + dirX[i];
            ny = y + dirY[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m  && visit[nx][ny] == false && farm[nx][ny] == 1) {
                dfs(nx, ny);
            }

        }
    }
}
