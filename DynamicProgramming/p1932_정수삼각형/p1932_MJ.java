package DynamicProgramming.p1932_정수삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1932_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		for(int i=1; i<N; i++) {
			for(int j=0; j<i+1; j++) {
				if(j-1<0) {
					map[i][j] = map[i-1][j] + map[i][j];
					continue;
				}
				if(j-1<0) {
					map[i][j] = map[i-1][j-1] + map[i][j];
					continue;
				}
				map[i][j] = Math.max(map[i-1][j], map[i-1][j-1]) + map[i][j];
			}
		}
		
		for(int i=0; i<N; i++) {
			if(max<map[N-1][i])
				max = map[N-1][i];
		}
		
		System.out.println(max);
		
		br.close();
	}
}
