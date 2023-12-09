package Greedy.p1946_신입사원;

import java.io.*;
import java.util.*;

public class p1946_YK {

    static class Grade implements Comparable<Grade> {
        int paper, interview;

        public Grade(int paper, int interview) {
            this.paper = paper;
            this.interview = interview;
        }

        @Override
        public int compareTo(Grade o) {
            return Integer.compare(this.paper, o.paper);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        PriorityQueue<Grade> pq = new PriorityQueue<>();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int result = 0;
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                int paper = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                pq.offer(new Grade(paper, interview));
            }
            int min = Integer.MAX_VALUE; // 현재까지 뽑은 애 중에 가장 앞선 인터뷰 등수
            while (!pq.isEmpty()) {
                Grade grade = pq.poll();
                if (min < grade.interview) continue; // 이미 앞에 친구들한테 서류에서도 졌는데 인터뷰도 졌다면
                min = grade.interview; // minimum 인터뷰 등수 업데이트
                ++result;
            }
            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
