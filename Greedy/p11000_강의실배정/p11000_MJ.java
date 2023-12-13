package Greedy.p11000_강의실배정;

import java.util.*;
import java.io.*;

public class p11000_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		int Class[][] = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			Class[i][0] = Integer.parseInt(st.nextToken()); 
			Class[i][1] = Integer.parseInt(st.nextToken()); 
		}

		// 시작하는 수업시간 기준으로 정렬
		Arrays.sort(Class, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
				// return Integer.compare(a[0], b[0]);
			}
		});
				
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.offer(Class[0][1]);
		for(int i=1; i<N; i++) {
			// 다른 수업을 시작할 수 있다면 (시작하는 시간보다 끝나는 시간이 빠르다)
			if(pq.peek()<=Class[i][0]) {
				// 끝나는 시간 날려준다
				pq.poll();
			}
			pq.offer(Class[i][1]);
		}
		
		System.out.println(pq.size());
		
		br.close();
	}
}
