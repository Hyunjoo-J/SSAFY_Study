package Greedy.p11000_강의실배정;
import java.util.*;
import java.io.*;

public class p11000_HJ {
	private static class Pair implements Comparable<Pair>{
		int s, t;
		public Pair(int s, int t){
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(Pair o){
			if(this.s == o.s)
				return Integer.compare(this.t, o.t);
			return Integer.compare(this.s, o.s);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt((br.readLine()));
		Pair[] lecture = new Pair[N];
		for(int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lecture[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(lecture);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(lecture[0].t);
		for(int i = 1; i < N; ++i){
			if(pq.peek() <= lecture[i].s)
				pq.poll();
			pq.add(lecture[i].t);
		}
		System.out.println(pq.size());
	}
}
