package Greedy.p15903_카드합체놀이;

import java.io.*;
import java.util.*;
public class p15903_YK {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        long sum;
        while (M-- > 0) {
            sum = pq.poll() + pq.poll();
            pq.offer(sum);
            pq.offer(sum);
        }

        sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        System.out.println(sum);
        br.close();
    }
}
