package Implematation.p23289_온풍기안녕;

import java.io.*;
import java.util.*;

public class p23289_HC {

    private static class Pair {
        int x, y, dir;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static final int[] dx = {0, 0, 0, -1, 1};
    private static final int[] dy = {0, 1, -1, 0, 0};
    private static int R, C, K;
    private static int[][] temperature;
    private static int[][] temperature2;
    private static List<Pair> heaters;
    private static List<Pair> check;
    private static boolean[][][] wall;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        heaters = new ArrayList<>(R * C);
        check = new ArrayList<>(R * C);
        wall = new boolean[R + 2][C + 2][2];
        temperature = new int[R + 2][C + 2];
        temperature2 = new int[R + 2][C + 2];

        for (int i = 1, input; i <= R; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; ++j) {
                input = Integer.parseInt(st.nextToken());
                if (1 <= input && input <= 4) {
                    heaters.add(new Pair(i, j, input));
                } else if (input == 5) {
                    check.add(new Pair(i, j));
                }
            }
        }

        int W = Integer.parseInt(br.readLine());
        for (int i = 0, x, y, z; i < W; ++i) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            wall[x][y][z] = true;
        }

        int chocolate = 0;
        boolean goal = false;
        while (!goal && chocolate < 101) {
            wind();         // 1
            control();      // 2
            decrease();     // 3
            ++chocolate;    // 4
            goal = test();  // 5
        }
        System.out.println(chocolate);
        br.close();
    }

    // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
    private static void wind() {
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R + 2][C + 2];
        int nx, ny, dir;
        for (Pair heater: heaters) {
            queue.clear();
            for (int i = 1; i <= R; ++i)
                Arrays.fill(visited[i], false);

            dir = heater.dir;
            nx = heater.x + dx[dir];
            ny = heater.y + dy[dir];
            queue.add(new Pair(nx, ny));
            visited[nx][ny] = true;

            int t = 5;
            while (!queue.isEmpty() && t > 0) {
                int queueSize = queue.size();
                for (int q = 0; q < queueSize; ++q) {
                    Pair p = queue.poll();
                    temperature[p.x][p.y] += t;

                    for (int i = -1; i < 2; ++i) {
                        nx = p.x + dx[dir] + i * dy[dir];
                        ny = p.y + dy[dir] + i * dx[dir];

                        if (nx <= 0 || ny <= 0 || nx > R || ny > C)
                            continue;
                        if (isWall(p.x, p.y, i, dir))
                            continue;
                        if (visited[nx][ny])
                            continue;
                        visited[nx][ny] = true;
                        queue.add(new Pair(nx, ny));
                    }
                }
                --t;
            }
        }
    }

    // 2. 온도가 조절됨
    private static void control() {
        deepCopy(temperature, temperature2);

        int nx, ny;
        for (int x = 1; x <= R; ++x) {
            for (int y = 1; y <= C; ++y) {
                for (int i = 1; i <= 4; i += 3) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                    if (nx <= 0 || ny <= 0 || nx > R || ny > C)
                        continue;
                    if ((i == 1 && wall[x][y][1]) || (i == 2 && wall[x][y - 1][1])
                            || (i == 3 && wall[x][y][0]) || (i == 4 && wall[x + 1][y][0]))
                        continue;
                    int control = Math.abs(temperature[nx][ny] - temperature[x][y]) / 4;
                    if (temperature[nx][ny] > temperature[x][y]) {
                        temperature2[nx][ny] -= control;
                        temperature2[x][y] += control;
                    } else {
                        temperature2[nx][ny] += control;
                        temperature2[x][y] -= control;
                    }
                }
            }
        }
        int[][] temp = temperature;
        temperature = temperature2;
        temperature2 = temp;
    }

    // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
    private static void decrease() {
        for (int i = 1; i <= R; ++i) {
            temperature[i][1] = Math.max(temperature[i][1] - 1, 0);
            temperature[i][C] = Math.max(temperature[i][C] - 1, 0);
        }
        for (int j = 2; j < C; ++j) {
            temperature[1][j] = Math.max(temperature[1][j] - 1, 0);
            temperature[R][j] = Math.max(temperature[R][j] - 1, 0);
        }
    }

    // 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사.
    // 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.
    private static boolean test() {
        for (Pair p: check) {
            if (temperature[p.x][p.y] < K)
                return false;
        }
        return true;
    }

    /**
     * wind() 메서드에서 사용
     */
    private static boolean isWall(int x, int y, int i, int dir) {
        if (dir == 1) {
            if (i == 0) {
                if (wall[x][y][1])
                    return true;
            } else if (i == 1) {
                if (wall[x + 1][y][0] || wall[x + 1][y][1])
                    return true;
            } else if (i == -1) {
                if (wall[x][y][0] || wall[x - 1][y][1])
                    return true;
            }
        } else if (dir == 2) {
            if (i == 0) {
                if (wall[x][y - 1][1])
                    return true;
            } else if (i == 1) {
                if (wall[x][y][0] || wall[x - 1][y - 1][1])
                    return true;
            } else if (i == -1) {
                if (wall[x + 1][y][0] || wall[x + 1][y - 1][1])
                    return true;
            }
        } else if (dir == 3) {
            if (i == 0) {
                if (wall[x][y][0])
                    return true;
            } else if (i == 1) {
                if (wall[x][y - 1][1] || wall[x][y - 1][0])
                    return true;
            } else if (i == -1) {
                if (wall[x][y][1] || wall[x][y + 1][0])
                    return true;
            }
        } else if (dir == 4) {
            if (i == 0) {
                if (wall[x + 1][y][0])
                    return true;
            } else if (i == -1) {
                if (wall[x][y - 1][1] || wall[x + 1][y - 1][0])
                    return true;
            } else if (i == 1) {
                if (wall[x][y][1] || wall[x + 1][y + 1][0])
                    return true;
            }
        }
        return false;
    }

    private static void deepCopy(int[][] from, int[][] to) {
        for (int i = 1; i <= R; ++i) {
            for (int j = 1; j <= C; ++j)
                to[i][j] = from[i][j];
        }
    }
}
