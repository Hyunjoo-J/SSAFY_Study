package Greedy.p1715_카드정렬하기;

import java.io.*;
import java.util.*;

public class p1715_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine());
            pq.offer(num);
        }

        int result = 0;
        while (pq.size() > 1) {
            int size = pq.size();
            for (int i = 0; i < (size >> 1); ++i) {
                int sum = pq.poll() + pq.poll();
                result += sum;
                pq.offer(sum);
            }
        }

        System.out.println(result);
        br.close();
    }

}
