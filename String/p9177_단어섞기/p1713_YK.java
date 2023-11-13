package String.p9177_단어섞기;

import sun.text.resources.JavaTimeSupplementary;

import java.io.*;
import java.util.*;

public class p1713_YK {

    static class Student implements Comparable<Student> {
        int num, like, start;

        public Student(int num) {
            this.num = num;
            this.like = 0;
            this.start = -1;
        }

        @Override
        public int compareTo(Student o) {
            int c = Integer.compare(this.like, o.like);
            return c == 0 ? Integer.compare(this.start, o.start) : c;
        }

    }

    static Queue<Student> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        Student[] students = new Student[101];
        for (int i = 1; i < 101; ++i) {
            students[i] = new Student(i);
        }

        PriorityQueue<Student> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        int available = N;

        for (int i = 0; i < K; ++i) {
            Student cur = students[Integer.parseInt(st.nextToken())];
            if (cur.start != -1) {
                cur.like++;
                pq = readjust(pq);
                continue;
            }
            if (available <= 0) {
                Student del = pq.poll();
                del.like = 0;
                del.start = -1;
                available++;
            }
            cur.like++;
            cur.start = i;
            --available;
            pq.offer(cur);
        }

        PriorityQueue<Integer> result = new PriorityQueue<>();
        while (!pq.isEmpty()) {
            result.offer(pq.poll().num);
        }
        while (!result.isEmpty())  {
            sb.append(result.poll()).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    private static PriorityQueue<Student> readjust(PriorityQueue<Student> pq) {
        while (!pq.isEmpty()) {
            q.offer(pq.poll());
        }
        while (!q.isEmpty()) {
            pq.offer(q.poll());
        }
        return pq;
    }

}
