package DynamicProgramming.p11048_이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11048_MJ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][];
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// → ↓ ↘ 모두 가능할 때
				if(i-1>-1&&j-1>-1) {
					map[i][j] = Math.max(Math.max(map[i-1][j], map[i-1][j-1]), map[i][j-1]) + map[i][j];
					continue;
				}
				// ↓ 만 가능할 때
				if(i-1>-1&&j-1<0) {
					map[i][j] = map[i-1][j] + map[i][j];
					continue;
				}
				// → 만 가능할 떄
				if(i-1<0&&j-1>-1) {
					map[i][j] = map[i][j-1] + map[i][j];
				}
			}
		}
		
		System.out.println(map[N-1][M-1]);
		
		br.close();
	}
}
