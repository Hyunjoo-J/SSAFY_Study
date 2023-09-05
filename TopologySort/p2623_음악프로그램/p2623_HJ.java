package TopologySort.p2623_음악프로그램;

import java.io.*;
import java.util.*;

public class p2623_HJ {
public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] degree = new int[N + 1];
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		for(int i = 1; i <= N; ++i)
			list[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int pre = Integer.parseInt(st.nextToken());
			for(int j = 1; j < num; ++j) {
				int next = Integer.parseInt(st.nextToken());
				list[pre].add(next);
				++degree[next];
				pre = next;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; ++i) {
			if(degree[i] == 0)
				q.offer(i);
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur+"\n");
			++cnt;
			
			for(int tmp : list[cur]) {
				if(--degree[tmp] == 0)
					q.offer(tmp);
			}
		}
		if(cnt == N) {
			bw.write(sb.toString());
		}else
			bw.write("0\n");
		bw.flush();
	}
}
