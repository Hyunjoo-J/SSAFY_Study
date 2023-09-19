package Implematation.p17780_새로운게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p17780_MJ {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Integer>[][] map;
    static int N, K, horse[][], max = 1, cnt = 0;
    static int dirX[] = {0, 0, 0, -1, 1};
    static int dirY[] = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 말의 X, Y, 방향을 담는 horse 2차원 배열 생성
        horse = new int[K + 1][3];

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<Integer>();
            }
        }

        // map[i][j]의 0번째 인덱스에는 현재 판의 색깔이 들어감(흰, 빨, 파)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            map[x - 1][y - 1].add(i);
            horse[i][0] = x - 1;
            horse[i][1] = y - 1;
            horse[i][2] = dir;
        }

        while (max < 4) {
            cnt++;
            for (int horseN = 1; horseN < K + 1; horseN++) {
                int x = horse[horseN][0];
                int y = horse[horseN][1];
                int dir = horse[horseN][2];

                // 가장 아래에 있는 말이 아닐 때는 움직일 필요가 없음
                if (map[x][y].get(1) != horseN) {
                    continue;
                }

                int nowX = x + dirX[dir];
                int nowY = y + dirY[dir];

                // 이동하려는 칸이 체스판을 벗어나거나, 파란색일 경우
                if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N || map[nowX][nowY].get(0) == 2) {
                    // 방향을 바꿔줌
                    if (dir == 1)
                        dir = 2;
                    else if (dir == 2)
                        dir = 1;
                    else if (dir == 3)
                        dir = 4;
                    else
                        dir = 3;

                    horse[horseN][2] = dir;

                    nowX = x + dirX[dir];
                    nowY = y + dirY[dir];

                    // 이동하려는 칸이 체스판을 벗어나지 않고 파란색 칸이 아닐 경우
                    if (nowX >= 0 && nowY >= 0 && nowX < N && nowY < N) {
                        // 흰색 칸인 경우
                        if (map[nowX][nowY].get(0) == 0) {
                            while (map[x][y].size() != 1) {
                                int h = map[x][y].get(1);
                                map[nowX][nowY].add(h);
                                horse[h][0] = nowX;
                                horse[h][1] = nowY;
                                map[x][y].remove(1);
                            }
                        // 빨강색 칸인 경우    
                        } else if (map[nowX][nowY].get(0) == 1) {
                            for (int i = map[x][y].size() - 1; i > 0; i--) {
                                int h = map[x][y].get(i);
                                map[nowX][nowY].add(h);
                                horse[h][0] = nowX;
                                horse[h][1] = nowY;
                                map[x][y].remove(i);
                            }
                        }
                    }
                    continue;
                }

                // 이동하려는 칸이 흰색 칸인 경우
                if (map[nowX][nowY].get(0) == 0) {
                    while (map[x][y].size() != 1) {
                        int h = map[x][y].get(1);
                        map[nowX][nowY].add(h);
                        horse[h][0] = nowX;
                        horse[h][1] = nowY;
                        map[x][y].remove(1);
                    }
                    continue;
                }

                // 이동하려는 칸이 빨강색 칸인 경우
                if (map[nowX][nowY].get(0) == 1) {
                    for (int i = map[x][y].size() - 1; i > 0; i--) {
                        int h = map[x][y].get(i);
                        map[nowX][nowY].add(h);
                        horse[h][0] = nowX;
                        horse[h][1] = nowY;
                        map[x][y].remove(i);
                    }
                }
            }

            // 말의 쌓여있는 순서 중 가장 많이 쌓여있는 개수를 구함(max)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (max < map[i][j].size() - 1)
                        max = map[i][j].size() - 1;
                }
            }

            if (cnt > 1000) {
                cnt = -1;
                break;
            }
        }

        System.out.println(cnt);

        br.close();
    }
}
