package Implematation.p23290_마법사상어와복제;

import java.io.*;
import java.util.*;

public class p23290_HC {

    private static final int[] fishDx = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] fishDy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] sharkDx = {-1, 0, 1, 0};
    private static final int[] sharkDy = {0, -1, 0, 1};
    private static final int[][] fishSmell = new int[4][4];
    private static final Queue<Integer>[][] graph = new ArrayDeque[4][4];
    private static final Queue<Integer>[][] tempGraph = new ArrayDeque[4][4];
    private static int sx, sy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                graph[i][j] = new ArrayDeque<>();
                tempGraph[i][j] = new ArrayDeque<>();
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            graph[x][y].add(d);
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        while (S-- > 0) {
            Queue<Integer>[][] cloned = castClonedMagic();
            fishMove();
            sharkMove();
            removeFishSmell();
            completeCloneMagic(cloned);
        }
        System.out.println(getFishCount());
        br.close();
    }

    private static Queue<Integer>[][] castClonedMagic() {
        Queue<Integer>[][] copied = new ArrayDeque[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                copied[i][j] = new ArrayDeque<>();
                for (int d: graph[i][j])
                    copied[i][j].add(d);
            }
        }
        return copied;
    }

    private static void fishMove() {
        for (int x = 0; x < 4; ++x) {
            for (int y = 0; y < 4; ++y) {
                while (!graph[x][y].isEmpty()) {
                    int d = graph[x][y].poll();
                    int nx = -1, ny = -1;
                    boolean move = false;
                    for (int i = 0; i < 8; ++i, d = (d + 7) % 8) {
                        nx = x + fishDx[d];
                        ny = y + fishDy[d];
                        if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                            continue;
                        if (nx == sx && ny == sy)
                            continue;
                        if (fishSmell[nx][ny] > 0)
                            continue;
                        move = true;
                        break;
                    }

                    if (move)
                        tempGraph[nx][ny].add(d);
                    else
                        tempGraph[x][y].add(d);
                }
            }
        }

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                while (!tempGraph[i][j].isEmpty())
                    graph[i][j].add(tempGraph[i][j].poll());
            }
        }
    }

    private static void sharkMove() {
        int[] sharkMovePath = getSharkMovePath(sx, sy);
        for (int d: sharkMovePath) {
            sx = sx + sharkDx[d];
            sy = sy + sharkDy[d];
            if (!graph[sx][sy].isEmpty()) {
                fishSmell[sx][sy] = 3;
                graph[sx][sy].clear();
            }
        }
    }

    private static int[] getSharkMovePath(int x, int y) {
        int[] sharkMovePath = new int[3];
        int maxFishCount = -1;
        for (int i = 0; i < 4; ++i) {
            int firstX = x + sharkDx[i];
            int firstY = y + sharkDy[i];
            if (isOutRange(firstX, firstY))
                continue;
            for (int j = 0; j < 4; ++j) {
                int secondX = firstX + sharkDx[j];
                int secondY = firstY + sharkDy[j];
                if (isOutRange(secondX, secondY))
                    continue;
                for (int k = 0; k < 4; ++k) {
                    int thirdX = secondX + sharkDx[k];
                    int thirdY = secondY + sharkDy[k];
                    if (isOutRange(thirdX, thirdY))
                        continue;
                    int fishCount = graph[firstX][firstY].size();
                    fishCount += secondX != firstX || secondY != firstY ? graph[secondX][secondY].size() : 0;
                    fishCount += (thirdX != firstX || thirdY != firstY) && (thirdX != secondX || thirdY != secondY)
                                    ? graph[thirdX][thirdY].size()
                                    : 0;
                    if (maxFishCount < fishCount) {
                        maxFishCount = fishCount;
                        sharkMovePath[0] = i;
                        sharkMovePath[1] = j;
                        sharkMovePath[2] = k;
                    }
                }
            }
        }
        return sharkMovePath;
    }

    private static void removeFishSmell() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                --fishSmell[i][j];
            }
        }
    }

    private static void completeCloneMagic(Queue<Integer>[][] cloned) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                graph[i][j].addAll(cloned[i][j]);
            }
        }
    }

    private static int getFishCount() {
        int count = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                count += graph[i][j].size();
            }
        }
        return count;
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= 4 || y >= 4;
    }
}
