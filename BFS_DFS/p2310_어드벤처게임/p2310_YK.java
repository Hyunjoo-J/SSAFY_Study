package BFS_DFS.p2310_어드벤처게임;

import java.io.*;
import java.util.*;

public class p2310_YK {

    static class Room {
        char inside;
        int cost;
        ArrayList<Integer> next;

        public Room(char inside, int cost) {
            this.inside = inside;
            this.cost = cost;
            next = new ArrayList<>();
        }
    }

    static int N;
    static boolean[][] visited;
    static Room[] rooms = new Room[1001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        while (N != 0) {
            for (int i = 0; i < N; ++i){
                st = new StringTokenizer(br.readLine());
                rooms[i] = new Room(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
                int k = Integer.parseInt(st.nextToken());
                while (k != 0) {
                    rooms[i].next.add(k - 1);
                    k = Integer.parseInt(st.nextToken());
                }
            }

            if (bfs()) sb.append("Yes\n");
            else sb.append("No\n");

            N = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
        br.close();
    }

    private static boolean bfs() {
        visited = new boolean[N][501];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            Room room = rooms[now[0]];
            int coin = now[1];

            // 방에 따른 상태 변화
            if (room.inside == 'L') {
                coin = Math.max(coin, room.cost);
            }
            if (room.inside == 'T') {
                coin -= room.cost;
            }

            if (coin < 0) continue;

            if (now[0] == N - 1) {
                return true;
            }

            for (int next : room.next) {
                if (visited[next][coin]) continue;
                visited[next][coin] = true;
                q.offer(new int[] {next, coin});
            }
        }

        return false;
    }
}
