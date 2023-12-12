package Greedy.p11000_강의실배정;

import java.io.*;
import java.util.*;

public class p11000_YK {

    static class Class implements Comparable<Class> {
        int start, end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            return Integer.compare(this.start, o.start);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Class> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Class(start, end));
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(pq.poll().end);

        while (!pq.isEmpty()) {
            Class now = pq.poll();
            if (now.start >= rooms.peek()) {
                rooms.poll();
            }
            rooms.offer(now.end);
        }

        System.out.println(rooms.size());
        br.close();
    }
}
