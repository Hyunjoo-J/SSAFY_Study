import java.io.*;
import java.util.*;
public class p2583_HJ {
    static int m, n, k, ans, area;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        visited = new boolean[m][n];
        int a, b, c, d;
        for(int i = 0; i < k; ++i){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            for(int j = b; j < d; ++j){
                for(int l = a; l < c; ++l){
                    map[j][l] = 1;
                }
            }
        }
        ArrayList list = new ArrayList<Integer>();
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(visited[i][j] || map[i][j] == 1)
                    continue;
                area = 0;
                ++ans;
                dfs(i, j);
                list.add(area);
            }
        }
        sb.append(ans + "\n");
        Collections.sort(list);
        for(int i = 0; i < list.size(); ++i){
            sb.append(list.get(i) + " ");
        }
        System.out.println(sb.toString());

    }
    private static void dfs(int x, int y){
        visited[x][y] = true;
        ++area;
        int nx, ny;
        for(int i = 0; i < 4; ++i){
            nx = x + dx[i];
            ny = y + dy[i];
            if(nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;
            if(map[nx][ny] == 1 || visited[nx][ny])
                continue;
            dfs(nx, ny);
        }
    }
}
