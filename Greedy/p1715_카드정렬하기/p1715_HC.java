package Greedy.p1715_카드정렬하기;

import java.io.*;
import java.util.*;

public class p1715_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> heap = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			heap.add(Integer.parseInt(br.readLine()));
		}

		long answer = 0;
		while (heap.size() > 1) {
			int first = heap.poll();
			int second = heap.poll();
			answer += first;
			answer += second;
			heap.add(first + second);
		}
		System.out.println(answer);
	    br.close();
	}
}
