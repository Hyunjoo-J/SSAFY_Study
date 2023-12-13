package FloydWarshall.p1389_케빈베이컨의6단계법칙;

import java.util.*;
import java.io.*;

public class p1389_HJ {
	private static final int INF = Integer.MAX_VALUE >> 2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //유저의 수
		int M = Integer.parseInt(st.nextToken()); //친구 관계의 수
		int[][] relation = new int[N + 1][N + 1];
		for(int i = 1; i <= N; ++i){
			Arrays.fill(relation[i], INF);
			relation[i][i] = 0;
		}

		for(int i = 0; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relation[a][b] = relation[b][a] = 1;
		}

		for(int k = 1; k <= N; ++k){
			for(int i = 1; i <= N; ++i){
				for(int j = 1; j <= N; ++j){
					if(relation[i][j] > relation[i][k] + relation[k][j]){
						relation[i][j] = relation[i][k] + relation[k][j];
					}
				}
			}
		}

		int ans = INF;
		int idx = -1;

		//케빈 베이컨 수가 가장 작은 인덱스 찾기
		for(int i = 1; i <= N; ++i){
			int sum = 0;
			for(int j = 1; j <= N; ++j){
				sum += relation[i][j];
			}
			if(ans > sum){
				ans = sum;
				idx = i;
			}
		}
		System.out.println(idx);
	}
}
