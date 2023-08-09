import java.io.*;
import java.util.*;

public class p3085_HJ {
    static int n, max;
    static char[][] arr;

    private static void swap(int x1, int y1, int x2, int y2) {
        char tmp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = tmp;
    }

    private static int findX(int x) {
        int tmp = 1, res = 1;
        char ch = arr[x][0];
        for (int i = 1; i < n; ++i) {
            if (arr[x][i] != ch) {
                ch = arr[x][i];
                res = Math.max(res, tmp);
                tmp = 1;
            } else tmp++;
        }
        return Math.max(res, tmp);
    }

    private static int findY(int y) {
        int tmp = 1, res = 1;
        char ch = arr[0][y];
        for (int i = 1; i < n; ++i) {
            if (arr[i][y] != ch) {
                ch = arr[i][y];
                res = Math.max(res, tmp);
                tmp = 1;
            } else tmp++;
        }
        return Math.max(res, tmp);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j);
            }
        }
        max = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                max = Math.max(max, findX(i));
                max = Math.max(max, findY(j));
                if(i + 1 < n) {
                    swap(i, j, i + 1, j);
                    max = Math.max(max, findX(i));
                    max = Math.max(max, findX(i + 1));
                    max = Math.max(max, findY(j));
                    if (max == n) {
                        System.out.println(n);
                        return;
                    }
                    swap(i, j, i + 1, j);
                }
                if(j + 1 < n) {
                    swap(i, j, i, j + 1);
                    max = Math.max(max, findX(i));
                    max = Math.max(max, findY(j));
                    max = Math.max(max, findY(j + 1));
                    if (max == n) {
                        System.out.println(n);
                        return;
                    }
                    swap(i, j, i, j + 1);
                }
            }
        }
        System.out.println(max);
    }
}
