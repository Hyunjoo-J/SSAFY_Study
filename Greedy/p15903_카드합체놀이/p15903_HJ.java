package Greedy.p15903_카드합체놀이;
import java.util.*;
import java.io.*;
public class p15903_HJ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Long N = Long.parseLong(st.nextToken());
        Long M = Long.parseLong(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i)
            pq.add(Long.parseLong(st.nextToken()));
        for(int i = 0; i < M; ++i){
            long sum = pq.poll() + pq.poll();
            pq.add(sum);
            pq.add(sum);
        }
        long ans = 0;
        while (!pq.isEmpty())
            ans += pq.poll();
        System.out.println(ans);
    }
}