package Bruteforce.p18429_근손실;

import java.util.*;
import java.io.*;

public class p18429_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, kit[], number[], ans;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kit = new int[N];
		number = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}

		perm(0);
		
		System.out.println(ans);
	
		br.close();
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			int now = 500;
			for(int i=0; i<N; i++) {
				now = now + number[i] - K;
				if(now < 500)
					return;
			}
			ans++;		
			return;
		}

		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			number[cnt] = kit[i];
			visited[i] = true;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}
