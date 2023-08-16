import java.io.*;
import java.util.*;
public class p1189_HJ {
    static int r, c, k, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        for(int i = 0; i < r; ++i){
            String line = br.readLine();
            for(int j = 0; j < c; ++j){
                map[i][j] = line.charAt(j);
            }
        }
        visited[r - 1][0] = true;
        move(r - 1,0, 1);
        System.out.println(ans);
    }
    private static void move(int x, int y, int cnt){
        if(x == 0 && y == c - 1){
            if(cnt == k)
                ++ans;
            return;
        }
        for(int i = 0; i < 4; ++i){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c)
                continue;
            if(visited[nx][ny] || map[nx][ny] == 'T')
                continue;
            visited[nx][ny] = true;
            move(nx, ny, cnt + 1);
            visited[nx][ny] = false;
        }
    }
}
