package Implematation.p17780_새로운게임;

import java.util.*;
import java.io.*;
public class p17780_HJ {
    static class Info implements Comparable<Info>{
        int no, dir, r, c;

        public Info(int r, int c, int dir) {
            this.dir = dir;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.no, o.no);
        }
    }
    static int N, K;
    static int[][] map;
    static List<Integer>[][] chess;
    static Info[] horse;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chess = new ArrayList[N][N];
        horse = new Info[K];
        for(int i = 0; i < N; ++i){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
                chess[i][j] = new ArrayList<>();
            }
        }
        int r, c, dir;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for(int i = 0; i < K; ++i){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken()) - 1;
            horse[i] = new Info(r, c, dir);
            chess[r][c].add(i);
        }
        System.out.println(play());
    }
    private static int play(){
        int turn = 0;
        while(true){
            ++turn;
            if (turn > 1000)
                return -1;
            for(int i = 0; i < K; ++i){
                Info tmp = horse[i];
                int r = tmp.r;
                int c = tmp.c;
                int dir = tmp.dir;
                if(chess[r][c].get(0) != i)
                    continue;
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                if(!isRange(nr, nc) || map[nr][nc] == 2){
                    if(dir == 0 ||dir == 2)
                        ++dir;
                    else
                        --dir;
                    tmp.dir = dir;
                    //방향 바꾼 후 한 칸 이동
                    nr = r + dr[dir];
                    nc = c + dc[dir];
                }
                //범위 벗어남, 파란칸이 또 나오면 pass
                if(!isRange(nr, nc) || map[nr][nc] == 2)
                    continue;
                else if(map[nr][nc] == 1) {
                    for (int pos = chess[r][c].size() - 1; pos >= 0; --pos) {
                        int move = chess[r][c].get(pos);
                        chess[nr][nc].add(move);
                        horse[move].r = nr;
                        horse[move].c = nc;
                    }
                    chess[r][c].clear();
                }else{
                    int size = chess[r][c].size();
                    for (int pos = 0; pos < size; ++pos) {
                        int move = chess[r][c].get(pos);
                        chess[nr][nc].add(move);
                        horse[move].r = nr;
                        horse[move].c = nc;
                    }
                    chess[r][c].clear();
                }
                if(chess[nr][nc].size() >= 4)
                    return turn;
            }
        }
    }

    private static boolean isRange(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= N)
            return false;
        return true;
    }
}