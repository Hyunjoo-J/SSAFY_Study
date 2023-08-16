import java.util.*;
import java.io.*;

public class p2517_HJ {
	public static class Runner{
		int order;
		int skill;
		public Runner(int order, int skill) {
			this.order = order;
			this.skill = skill;
		}
		
	}
	static int n;
	static int[] stree;
	static Runner[] info;
	static int s;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		info = new Runner[n + 1];
		info[0] = new Runner(0, 1000000001);
		int[] res = new int[n+1];
		for(int i = 1; i <= n; ++i) {
			int skill = Integer.parseInt(br.readLine());
			info[i] = new Runner(i,skill);
		}
		Arrays.sort(info, (o1,o2) -> o2.skill - o1.skill);
		s = 1;
		while(s < n)
			s <<= 1;
		stree = new int[2*s];
		for(int i = 1; i <= n; ++i) {
			int order = info[i].order;
			int cnt = query(1, 1, s, 1, order);
			res[order] = cnt + 1;
			update(order);
		}
		for(int i = 1; i <= n; ++i) {
			sb.append(res[i]+"\n");
		}
		System.out.print(sb.toString());
	}
	private static void update(int node) {
		int idx = s + node - 1;
		while(idx >= 1) {
			stree[idx] += 1;
			idx /= 2;
		}
		
	}
	
	private static int query(int node, int start, int end, int left, int right) {
		if(right < start || left > end)
			return 0;
		if(left <= start && end <= right)
			return stree[node];
		int mid = (start + end) >> 1;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
		
	}

}
