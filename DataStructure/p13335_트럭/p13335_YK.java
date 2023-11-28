package DataStructure.p13335_트럭;

import java.io.*;
import java.util.*;

public class p13335_YK {

    static class Truck {
        int weight, dist;
        public Truck(int weight, int dist) {
            this.weight = weight;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken()); // 최대로 올라갈 수 있는 트럭 대수?
        int L = Integer.parseInt(st.nextToken());
        Truck[] trucks = new Truck[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            trucks[i] = new Truck(Integer.parseInt(st.nextToken()), W);
        }

        int result = 0;
        Queue<Truck> q = new ArrayDeque<>();
        int t = 0, weight = 0, cnt = 0;
        while(t < N || !q.isEmpty()) {
            for (Truck truck : q) {
                --truck.dist;
            }
            if (!q.isEmpty() && q.peek().dist == 0) {
                --cnt;
                weight -= q.poll().weight;
            }
            while (t < N && cnt < W && weight + trucks[t].weight <= L) {
                if (!q.isEmpty() && trucks[t - 1].dist == trucks[t].dist) break;
                ++cnt;
                weight += trucks[t].weight;
                q.offer(trucks[t++]);
            }
            ++result;
        }
        System.out.println(result);
        br.close();
    }

}
