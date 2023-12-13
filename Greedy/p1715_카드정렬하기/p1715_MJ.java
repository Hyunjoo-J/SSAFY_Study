package Greedy.p1715_카드정렬하기;

import java.util.*;
import java.io.*;

public class p1715_MJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int min = 0;

		if (pq.size() != 1) {
			while (pq.size() != 1) {
				int a = pq.poll();
				int b = pq.poll();

				min += (a + b);
				pq.offer(a + b);
			}
		}

		System.out.println(min);

		br.close();
	}
}
