package DataStructure.p1202_보석도둑;

import java.io.*;
import java.util.*;

public class p1202_YK {
    static class Jewel implements Comparable<Jewel> {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(this.m, o.m);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        Jewel j;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            j = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            jewels[i] = j;
        }

        int[] bags = new int[K];
        int weight;
        for (int i = 0; i < K; ++i) {
            weight = Integer.parseInt(br.readLine());
            bags[i] = weight;
        }

        Arrays.sort(bags);
        Arrays.sort(jewels);

        long result = 0;
        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int b : bags) {
            while (idx < N && jewels[idx].m <= b) {
                pq.offer(-jewels[idx].v);
                ++idx;
            }

            if (!pq.isEmpty()) {
                result += -pq.poll();
            }
        }

        System.out.println(result);
        br.close();
    }
}
