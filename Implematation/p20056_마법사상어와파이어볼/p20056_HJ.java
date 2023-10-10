package Implematation.p20056_마법사상어와파이어볼;
import java.util.*;
import java.io.*;

public class p20056_HJ {
    static class meteor{
        int r, c, m, s, d;
        public meteor(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<meteor>[][] map;
    static ArrayList<meteor> meteors = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            meteors.add(new meteor(r, c, m, s, d));
        }
        for (int i = 0; i < K; i++) {
            meteorMove(N);
            meteorFire(N);
        }
        bw.write(meteorCal() + "");
        bw.flush();
        bw.close();
        br.close();
    }
    static void meteorMove(int N) {
        for (meteor cur : meteors) {
            int tempR = (cur.r + N + dr[cur.d] * (cur.s%N)) % N;
            int tempC = (cur.c + N + dc[cur.d] * (cur.s%N)) % N;
            cur.r = tempR;
            cur.c = tempC;
            map[cur.r][cur.c].add(cur);
        }
    }
    static void meteorFire(int N){
        for(int r = 0; r<N;r++){
            for(int c = 0; c<N;c++) {
                if(map[r][c].size() < 2){
                    map[r][c].clear();
                    continue;
                }
                int mSum = 0, sSum = 0, oddCount = 0, evenCount = 0;
                int size = map[r][c].size();
                for(meteor cur : map[r][c]){
                    mSum += cur.m;
                    sSum += cur.s;
                    if(cur.d % 2 == 1)
                        oddCount++;
                    else
                        evenCount++;
                    meteors.remove(cur);
                }
                map[r][c].clear();
                mSum /= 5;
                if(mSum == 0)
                    continue;
                sSum /= size;
                if(oddCount == size || evenCount == size){
                    for(int i=0;i<8;i+=2)
                        meteors.add(new meteor(r,c,mSum, sSum, i));
                }else{
                    for(int i=1;i<8;i+=2)
                        meteors.add(new meteor(r,c,mSum, sSum, i));
                }
            }
        }
    }
    static int meteorCal(){
        int result = 0;
        for(meteor cur : meteors)
            result += cur.m;
        return result;
    }
}
