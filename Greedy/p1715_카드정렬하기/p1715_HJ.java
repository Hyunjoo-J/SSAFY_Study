package Greedy.p1715_카드정렬하기;

import java.util.*;
import java.io.*;
public class p1715_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; ++i)
			pq.add(Integer.parseInt(br.readLine()));
		long ans = 0;
		while(pq.size() > 1){
			int size = pq.size();
			for(int i = 0; i < (size >> 1); ++i){
				int sum = pq.poll() + pq.poll();
				ans += sum;
				pq.add(sum);
			}
		}
		System.out.println(ans);
	}
}

