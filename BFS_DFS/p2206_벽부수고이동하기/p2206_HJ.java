import java.io.*;
import java.util.*;

public class p2206_HJ {
    static int n, m;
    static int[][] map;
    static int[][][] visited; //x, y, iscrash
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited =new int[n][m][2];
        visited[0][0][0] = 1;
        for(int i = 0; i < n; ++i){
            String line = br.readLine();
            for(int j = 0; j < m; ++j){
                map[i][j] = line.charAt(j);
            }
        }
        if(n == 1 && m == 1) {
            System.out.println(1);
            return;
        }
        System.out.println(bfs());
    }
    private static int bfs(){
        Queue<int[]> q = new LinkedList<>(); //x,y,iscrash를 저장한 배열
        q.offer(new int[] {0, 0, 0});
        int x, y, w, nx, ny;
        while (true){
            int[] tmp = q.poll();
            x = tmp[0];
            y = tmp[1];
            w = tmp[2];
            for(int i = 0; i < 4; ++i){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if(map[nx][ny] == '0'){ //다음 칸 벽이 아닐 때
                    if(visited[nx][ny][w] == 0){
                        q.offer(new int[] {nx, ny, w});
                        visited[nx][ny][w] = visited[x][y][w] + 1;
                        if(nx == n - 1 && ny == m - 1)
                            return visited[nx][ny][w];
                    }
                }
                else { //다음 칸 벽일 때
                    if (w == 0) {
                        if(visited[nx][ny][1] == 0){
                            q.offer(new int[] {nx, ny, 1});
                            visited[nx][ny][1] = visited[x][y][0] + 1;
                            if(nx == n - 1 && ny == m - 1)
                                return visited[nx][ny][1];
                        }
                    }
                }
            }
            if(q.isEmpty()){
                return -1;
            }
        }
    }
}
