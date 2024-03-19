package BFS_DFS.프로그래머스LV2_1829_카카오프렌즈컬러링북;

import java.util.*;

class Solution_MJ {
    static boolean[][] visited;
    static int[] dirX = {0, 1, 0, -1};
    static int[] dirY = {-1, 0, 1, 0};

    class Spot{
        int x, y, w;

        public Spot(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(!visited[i][j]&&picture[i][j]!=0){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(m, n, i, j, picture));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    private int bfs(int m, int n, int x, int y, int[][] picture){
        Queue<Spot> queue = new LinkedList<>();
        queue.add(new Spot(x, y, picture[x][y]));
        int size = 1;
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Spot cur = queue.poll();

            for(int i=0; i<4; ++i){
                int nextX = cur.x + dirX[i];
                int nextY = cur.y + dirY[i];

                if(nextX<0||nextY<0||nextX>=m||nextY>=n)
                    continue;
                if(picture[nextX][nextY]==cur.w){
                    if(visited[nextX][nextY])
                        continue;
                    visited[nextX][nextY] = true;
                    size++;
                    queue.add(new Spot(nextX, nextY, cur.w));
                }
            }
        }
        return size;
    }
}