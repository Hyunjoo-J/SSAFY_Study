package DynamicProgramming.p14501_퇴사;

import java.io.*;
import java.util.*;

public class p14501_YK {
    static int result, N;
    static Work[] works;

    static class Work {
        int time, profit;

        public Work(int time, int profit) {
            this.time = time;
            this.profit = profit;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        works = new Work[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            works[i] = new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        work(0, 0);

        System.out.println(result);
        br.close();
    }

    private static void work(int day, int profit) {
        if (day > N) return;
        if (day == N) {
            result = Math.max(result, profit);
            return;
        }
        work(day + 1, profit);
        work(day + works[day].time, profit + works[day].profit);
    }
}
