package BFS_DFS.p16236_아기상어;

import java.io.*;
import java.util.*;

class Node {
    int x;
    int y;
    int dis;

    Node(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }

}

public class p16236_HJ {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] map = new int[n][n];
        int time = 0;
        int size = 2;
        int eat = 0;
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    q.add(new Node(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }
        while (true) {
            ArrayList<Node> fish = new ArrayList<>();
            int[][] dist = new int[n][n];

            //현재 칸에서 사방 돌면서 가까운 순서대로 먹을 수 있는 물고기 저장
            //아직 먹지는 않는다!
            while (!q.isEmpty()) {
                Node tmp = q.poll();
                int x = tmp.x;
                int y = tmp.y;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny] == 0 && map[nx][ny] <= size) {
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new Node(nx, ny, dist[nx][ny]));
                        //잡아 먹는 조건 충족
                        if (1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < size) {
                            fish.add(new Node(nx, ny, dist[nx][ny]));
                        }
                    }
                }
            }
            if (fish.size() == 0) {
                System.out.println(time);
                return;
            }
            Node now = fish.get(0);
            //먹을 순서 정하기
            for (int i = 1; i < fish.size(); i++) {
                if (now.dis > fish.get(i).dis) {
                    now = fish.get(i);
                } else if (now.dis == fish.get(i).dis) {
                    if (now.x > fish.get(i).x) {
                        //위쪽 먼저 먹기
                        now = fish.get(i);
                    } else if (now.x == fish.get(i).x && now.y > fish.get(i).y) {
                        //왼쪽 먼저 먹기
                        now = fish.get(i);
                    }
                }
            }
            map[now.x][now.y] = 0;
            time += now.dis;
            eat++;
            if (size == eat) {
                size++;
                eat = 0;
            }
            //새로운 기준점, 한 마리 먹고 난 후 상어의 위치
            q.add(new Node(now.x, now.y, 0));

        }

    }
}
