package DataStructure.p11286_절댓값힙;

import java.util.*;
import java.io.*;

public class p11286_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int A = Math.abs(o1);
				int B = Math.abs(o2);

				if (A > B) {
					return A - B;
				} else if (A == B) {
					if (o1 > o2) {
						return 1;
					} else
						return -1;
				} else {
					return -1;
				}
			}
		});
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num==0) {
				if(pq.isEmpty()){
					System.out.println(0);
				}
				else {
					System.out.println(pq.poll());
				}
			}
			else {
				pq.offer(num);
			}
		}

		br.close();
	}
}
