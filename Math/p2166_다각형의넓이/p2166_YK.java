package Math.p2166_다각형의넓이;

import java.io.*;
import java.util.*;

public class p2166_YK {

    static class Point {
        long x, y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N + 1];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        points[N] = new Point(points[0].x, points[0].y);

        double result = 0;
        for (int i = 0; i < N; ++i) {
            result += points[i].x * points[i + 1].y;
        }
        for (int i = 1; i < N + 1; ++i) {
            result -= points[i].x * points[i - 1].y;
        }

        System.out.printf("%.1f", Math.abs(result) / 2);
        br.close();
    }
}
