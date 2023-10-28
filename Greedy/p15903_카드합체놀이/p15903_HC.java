package Greedy.p15903_카드합체놀이;

import java.io.*;
import java.util.*;

public class p15903_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Queue<Long> heap = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            heap.add(Long.parseLong(st.nextToken()));
        }

        while (m-- > 0) {
            long first = heap.remove();
            long second = heap.remove();
            heap.add(first + second);
            heap.add(first + second);
        }

        long answer = 0;
        while (!heap.isEmpty()) {
            answer += heap.poll();
        }
        System.out.println(answer);
        br.close();
    }
}